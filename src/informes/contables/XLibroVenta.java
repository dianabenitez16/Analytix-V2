/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informes.contables;

import clases.Factura;
import clases.SQLiteCon;
import clases.Talonario;
import clases.Timbrado;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import swing.JColor;
import system.Consola;


/**
 *
 * @author juan.bogado
 */
public class XLibroVenta {
    public JLabel estado;
    public String ultimoMensaje;
    
    public Properties configuracion;
    public String[] talonariosncr;
    public String[] importesconsolidados;
    
    
    public File folder = new File("informes\\contables");
    public String fileName;
    public Object[][] resultados;
    public List<String> encabezados;
    
    private List<Timbrado> timbrados;
    
    public Detallado detallado;
    
    public int rowNum, colNum, reintentar;
    Row row;
    String comprobanteTipo;
    String comprobantePrefijo;
    String comprobanteNumero;
    String formaDePago;
    
    
    
    
    Boolean exento;

    SimpleDateFormat fFecha = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat fFechaDB = new SimpleDateFormat("yyyy-MM-dd");
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    
    public XLibroVenta(JLabel estado, Properties config) {
        this.configuracion = config;
        this.estado = estado;
        this.ultimoMensaje = "";
        
       
        procesarTalonarios();
        cargarTimbrados();
    }
    
    private void cargarTimbrados(){
        timbrados = new ArrayList<>();
        int contador = 0;
        SQLiteCon lite = new SQLiteCon();
        lite.conectar();
        String sql = "select comprobantes.id, sucursal, punto_exp, nro_desde, nro_hasta, fecha_des, fecha_has, timbrado from talonarios inner join comprobantes on talonarios.tipo_com = comprobantes.id";
       
      try {
             Statement st= lite.conectar().createStatement();
             ResultSet rs= st.executeQuery(sql);

             while(rs.next()) {
              
              Timbrado timbrado = new Timbrado();
              
              timbrado.setTipoComprobante(rs.getInt("id"));
              timbrado.setPrefijoSucursal(rs.getInt("sucursal"));
              timbrado.setPrefijoPuntoExpedicion(rs.getInt("punto_exp"));
              timbrado.setNumeroFacturaDesde(rs.getInt("nro_desde"));
              timbrado.setNumeroFacturaHasta(rs.getInt("nro_hasta"));
              timbrado.setFechaDesde(fFechaDB.parse(rs.getString("fecha_des")));
              timbrado.setFechaHasta(fFechaDB.parse(rs.getString("fecha_has")));
              timbrado.setNumeroTimbrado(rs.getInt("timbrado"));
              
                 
              timbrados.add(timbrado);
              
              contador++;
              
                 System.out.println("CONTADOR " + contador);
             }
      } catch (SQLException ex) {
          Logger.getLogger(XLibroVenta.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ParseException ex) {
            Logger.getLogger(XLibroVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void procesarTalonarios(){
        talonariosncr = configuracion.getProperty("talonariosncr").split(",");
        importesconsolidados = configuracion.getProperty("importesconsolidados").split(",");
        
        
    }
    
    public void detallado(Object[][] res){
        fileName = "informes/contables/LibroVentas_detallado.xlsx";
        
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("LibroVentasDetallado");
    
        detallado = new Detallado(res);
    }
    
        
    public void fin(){
        do{
            reintentar = 1;
            try {
                if (!folder.exists()) { folder.mkdirs();}
                FileOutputStream outputStream = new FileOutputStream(fileName);
                workbook.write(outputStream);
                if (Desktop.isDesktopSupported()){
                    Desktop.getDesktop().edit(new File(fileName));
                }

            } catch (FileNotFoundException e) {
                    reintentar = JOptionPane.showOptionDialog(null, "No se pudo crear el archivo, asegurese de que no este abierto un archivo con el mismo nombre y tenga permisos de escritura. ¿Desea reintentar?", "Error al crear archivo", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null) ;
            } catch (IOException e) {
                    Consola.out(JColor.red,"IO Error");
                    reintentar = -1;
            }
        }while (reintentar == 0);

        try {
            workbook.close();
        } catch (IOException ex) {
            Consola.out(JColor.red,"Error al cerrar, quizas, no habia nada que cerrar." );
        }
    }
    
    public void print(String mensaje){
        if(!ultimoMensaje.equals(mensaje)){
            ultimoMensaje = mensaje;
            estado.setText(mensaje);
            estado.setForeground(JColor.blue);
            if(analytix.Analytix.DEBUG){
                Consola.out(JColor.blue, mensaje);
            }
        }
    }
    
    public class Detallado extends SwingWorker<Void, String>{
        public Detallado(Object[][] res) {
            resultados = res;
            encabezados = new ArrayList<>(resultados.length);
            
            for (Object cabecera : resultados[0]) {
                encabezados.add((cabecera.toString()));
            }

            rowNum = 0;
            colNum = 0;
        }

        @Override
        protected Void doInBackground()  {
            for (Object[] registro : resultados) {
                publish("Elaborando informe "+ rowNum*100/resultados.length + "%");

                row = sheet.createRow(rowNum++);

                colNum = 0;
                comprobanteTipo = "";
                comprobantePrefijo = "";
                comprobanteNumero = "";
                formaDePago = "";
                exento = false;
                Factura factura = new Factura();

                for (Object campo : registro) {
                    Cell cell = row.createCell(colNum++);

                    if(rowNum == 1){
                        // SE OMITAN LAS COLUMNAS DE LOS CAMPOS TEMPORALES (TMP_RUC, TMP_NOM, LAST_COL, ETC)
                        if(row.getLastCellNum() <=53){ // Le numera desde 1, y no desde 0.
                            cell.setCellValue((String) campo);
                        }
                    }else{
                        switch (encabezados.get(colNum -1)){ // SE RESTA 1 POR BUSQUEDA EN ARRAY QUE ARRANCA EN 0
                            case "ven_numero": // NUMERO DE COMPROBANTE
                                try{
                                    comprobanteNumero = ((String) campo).substring(((String) campo).indexOf("-")+1);
                                   
                                    comprobantePrefijo = ((String) campo).substring(0, ((String) campo).indexOf("-"));
                                    while(comprobantePrefijo.length() < 4){ 
                                        comprobantePrefijo = "0"+comprobantePrefijo;
                                    }
                                    
                                    while(comprobanteNumero.length() < 7){ // SE MODIFICA DE 8 A 7, PORQUE DA ERROR EN EL EXPERT
                                        comprobanteNumero = "0"+comprobanteNumero;
                                    }
                                    
                                    
                                    factura.setTalonario(new Talonario());
                                    factura.setNumero(Integer.valueOf(comprobanteNumero));
                                    // Si el prefijo es tipo 1001,2001,3002, hace lo primero, y si es 0201, 0101, etc, hace lo segundo
                                    if(Integer.valueOf(comprobantePrefijo)>=1000){
                                        cell.setCellValue("00"+comprobantePrefijo.substring(0, 1)+"-"+comprobantePrefijo.substring(1, 4)+"-"+comprobanteNumero);
                                        factura.getTalonario().setSucursal(Integer.valueOf("00"+comprobantePrefijo.substring(0, 1)));
                                        factura.getTalonario().setPuntoExpedicion(Integer.valueOf(comprobantePrefijo.substring(1, 4)));
                                    }else{
                                        cell.setCellValue("0"+comprobantePrefijo.substring(0, 2)+"-0"+comprobantePrefijo.substring(2, 4)+"-"+comprobanteNumero);
                                        factura.getTalonario().setSucursal(Integer.valueOf("0"+comprobantePrefijo.substring(0, 2)));
                                        factura.getTalonario().setPuntoExpedicion(Integer.valueOf(comprobantePrefijo.substring(2, 4)));
                                    }            
                                    
                                   
                                    
                                    
                                   
                                    // RECORRER ARRAY DE TIMBRADOS Y COMPARAR EN CADA LINEA PARA IDENTIFICAR QUE NUMERO DE TIMBRADO LE CORRESPONDE, SEGUN NUMEORO, TIPO Y FECHA DEL COMPROBANTE
                                    
                                  
                                    
                                }catch (StringIndexOutOfBoundsException ex){
                                    System.out.println("Error: "+ex);
                                    System.out.println("Prefijo: "+comprobantePrefijo + "  Numero: "+comprobanteNumero);
                                }
                                break;
                            case "ven_sucurs":
                                cell.setCellValue("0"+comprobantePrefijo.substring(0, 1));
                                break;
                            case "form_pag": // MEDIO DE PAGO
                                switch((Integer) campo){
                                    case 67:
                                    case 70:
                                        formaDePago = "Crédito";
                                        break;
                                    case 77:
                                    case 86:
                                    case 82:
                                        formaDePago = "Contado";
                                        break;
                                     
                                    default:
                                        formaDePago = "";
                                        break;
                                }
                                cell.setCellValue((String) formaDePago);
                                break;
                            
                            case "ven_tipofa": //TIPO DE COMPROBANTE (CREDITO/CONTADO)
                                switch((Integer) campo){
                                    case 67:
                                    case 77:    
                                        comprobanteTipo = "Nota de Crédito"; //RECORDAR SIEMPRE CAMBIAR LOS NOMBRES DE COMPROBANTES DE MODO A QUE EL ANALYTIX ACEPTE EJ: ES "Nota de Crédito" no "Nota de crédito"
                                        factura.setTipoComprobante(3);
                                        break;
                                    case 70:
                                    case 86:    
                                        comprobanteTipo = "Factura";
                                        factura.setTipoComprobante(1);
                                        break;     
                                    case 82:
                                        comprobanteTipo = "Recibo";
                                        break;
                                    default:
                                        comprobanteTipo = String.valueOf(campo);
                                        break;
                                  
                                }
                                cell.setCellValue((String) comprobanteTipo);
                                break;
                           
                                case "ven_iva": // VERIFICA IVA PARA EXENTO
                                if((Double) campo == 0){
                                    
                                    cell.getRow().getCell(20).setCellValue(cell.getRow().getCell(21).getNumericCellValue());
                                    cell.getRow().getCell(21).setCellValue(0);
                                }else{
                                    
                                    cell.getRow().getCell(15).setCellValue(configuracion.getProperty("cuentaventas10")); 
                                    /*
                                    if(cell.getRow().getCell(20).getNumericCellValue() > 0){
                                        cell.getRow().getCell(46).setCellValue(configuracion.getProperty("cuentaventasexe")); 
                                    }
                                    */
                                    cell.getRow().getCell(15).setCellValue(configuracion.getProperty("cuentaventas10")); 
                                }
                                cell.setCellValue((Double) campo);
                                break;
                            case "ven_fecha": // VERIFICA VENCIMIENTO PARA CUOTAS
                  //              System.out.println("entro");
                                if(campo instanceof Date){
                      //              System.out.println("is a date");
                                    cell.setCellValue((String) fFecha.format(campo));
                                    factura.setFecha((Date) campo);
                                }else{
                   //                 System.out.println("is a string");
                                    cell.setCellValue((String) campo);
                                }
                                
                                break;

                            case "ven_fecven": // VERIFICA VENCIMIENTO PARA CUOTAS
                                if(campo instanceof Date){
                                    cell.getRow().getCell(27).setCellValue(1);
                                    cell.setCellValue((String) fFecha.format(campo));
                                }else{
                                    cell.setCellValue((String) campo);
                                }
                                break;
                            case "forma_devo": // VERIFICA ANULACION
                                if(comprobanteTipo.equals("Nota de Crédito")){
                                    cell.setCellValue(1);
                                }else{
                                    cell.setCellValue(0);
                                }
                                break;
                            case "anular": // VERIFICA ANULACION
                                if((Integer) campo == 0){
                                   cell.setCellValue((Integer) 0);
                                }else{
                                    cell.getRow().getCell(12).setCellValue("Anulado");
                                    cell.setCellValue((Integer) 1);
              //                      cell.getRow().getCell(46).setCellValue("");
                                }
                                break;
                            case "timbrado":
                                 
                                for (Timbrado timbrados : timbrados) {
                                  
                                  if(
                                            factura.getTipoComprobante() == timbrados.getTipoComprobante() &&
                                            factura.getTalonario().getSucursal() == timbrados.getPrefijoSucursal() &&
                                            factura.getTalonario().getPuntoExpedicion() == timbrados.getPrefijoPuntoExpedicion() &&
                                            factura.getNumero() >= timbrados.getNumeroFacturaDesde() &&
                                            factura.getNumero() <= timbrados.getNumeroFacturaHasta()
                                            ){
                                        if(fFechaDB.format(factura.getFecha()).compareTo(fFechaDB.format(timbrados.getFechaDesde())) >= 0 && fFechaDB.format(factura.getFecha()).compareTo(fFechaDB.format(timbrados.getFechaHasta())) <= 0){
                                            
                                        cell.setCellValue(timbrados.getNumeroTimbrado());
                                        
                                    }
                                          
                                }
                                /*
                                    System.out.println(" \nFACTURA COMPROBANTE " + factura.getTipoComprobante());
                                    System.out.println(" FACTURA PUNTO_EXP " + factura.getTalonario().getPuntoExpedicion());
                                    System.out.println(" FACTURA SUCURSAL " + factura.getTalonario().getSucursal());
                                    System.out.println(" FACTURA NUMERO " + factura.getNumero());
                                    System.out.println(" FACTURA FECHA " + factura.getFecha());
                                    
                                    System.out.println(" \nTALONARIO COMPROBANTE " + timbrados.getTipoComprobante());
                                    System.out.println(" TALONARIO PUNTO_EXP " + timbrados.getPrefijoSucursal());
                                    System.out.println(" TALONARIO SUCURSAL " + timbrados.getPrefijoSucursal());
                                    System.out.println(" TALONARIO DESDE " + timbrados.getNumeroFacturaDesde());
                                    System.out.println(" TALONARIO HASTA " + timbrados.getNumeroFacturaHasta());
                                    System.out.println(" TALONARIO FECHA " + timbrados.getFechaDesde());
                                    
                                    
                                    System.out.println(" TIMBRADO " + timbrados.getNumeroTimbrado());
                                    */
                                }
                                
                                break;
                                
                                
                             case "usu_ide":
                                // EN LA ULTIMA COLUMNA, VERIFICAMOS SI ES EXENTA, ENTONCES INSERTAMOS NUMERO DE CUENTA ENXENTA.
                                if(cell.getRow().getCell(20).getNumericCellValue() > 0){
                                    cell.getRow().getCell(46).setCellValue(configuracion.getProperty("cuentaventasexe")); // OJO AGREGA EN UNA COLUMNA DISTINTA A LA DE GRAVADA
                                    cell.getRow().getCell(14).setCellValue("77777701-0"); 
                                    cell.getRow().getCell(16).setCellValue("AGENTES DIPLOMATICOS"); 
                                }   
                              
                                if(Arrays.asList(importesconsolidados).contains(cell.getRow().getCell(14).getStringCellValue().trim())){
                                    cell.getRow().getCell(14).setCellValue("44444401-7"); 
                                    cell.getRow().getCell(16).setCellValue("IMPORTES CONSOLIDADOS"); 
                                }
                                break;
                                
                            // SE DA DE BAJA PORQUE YA NO ESTA OPERATIVO
                            case "Prefijo":
                            case "MinNumero":
                            case "MaxNumero":
                                System.out.println("CELDA: \t"+encabezados.get(colNum -1)+"\t"+cell.getColumnIndex());
                                row.removeCell(cell);
                                break;
                         
                            case "last_col":
                                // VERIFICAMOS EL RUC, SI SE TRATA DE UN CLIENTE REGISTRADO O UN CLIENTE TEMPORAL
                                if(cell.getRow().getCell(14).getStringCellValue().isEmpty() && !cell.getRow().getCell(53).getStringCellValue().isEmpty()){
                                    cell.getRow().getCell(14).setCellValue((String) cell.getRow().getCell(53).getStringCellValue()); 
                                }
                                // VERIFICAMOS LA RAZON SOCIAL, SI SE TRATA DE UN CLIENTE REGISTRADO O UN CLIENTE TEMPORAL
                                if(cell.getRow().getCell(16).getStringCellValue().isEmpty() && !cell.getRow().getCell(54).getStringCellValue().isEmpty()){
                                    cell.getRow().getCell(16).setCellValue((String) cell.getRow().getCell(54).getStringCellValue()); 
                                }
                                
                                // SE ELIMINAN LAS COLUMNAS DE USO TEMPORAL
                                row.removeCell(row.getCell(53)); // TMP_RUC
                                row.removeCell(row.getCell(54)); // TMP_NOM
                                row.removeCell(row.getCell(55)); // LAST_COL
                                break;
                            default:
                                 if (campo instanceof String) {
                                    cell.setCellValue((String) campo);
                                } else if (campo instanceof Integer) {
                                    cell.setCellValue((Integer) campo);
                                } else if (campo instanceof Date) {
                                    cell.setCellValue((String) fFecha.format(campo));
                                } else if (campo instanceof Double) {
                                    cell.setCellValue((Double) campo);
                                } else {
                                    cell.setCellValue((String) campo);
                                }
                        }
                    }

                }

                if(formaDePago.equals("") && rowNum >1){
                    sheet.removeRow(row);
                    rowNum--;
                }
            }
            return null;
        }
            
        @Override
        protected void done(){
            publish("Abriendo archivo ...");
            fin();
            publish("Listo.");
        }

        @Override
        protected void process(List<String> publish){
            print(publish.get(publish.size()-1));
        }
    }
}
