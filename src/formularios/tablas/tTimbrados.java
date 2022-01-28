/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package formularios.tablas;

import clases.SQLiteCon;
import clases.Talonario;
import clases.Timbrado;
import informes.contables.XLibroVenta;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.threeten.bp.LocalDate;

/**
 *
 * @author junju
 */
public class tTimbrados extends javax.swing.JInternalFrame { 
 private SQLiteCon sqlite= new SQLiteCon();
 private Connection cn= sqlite.conectar();
 private String sSQL="";
 private List<Timbrado> timbrados;
 
  SimpleDateFormat fFechaDB = new SimpleDateFormat("yyyy-MM-dd");
 boolean bandera = false;
    public tTimbrados() {
    
        initComponents();
        initDatePickers();
        mostrar();
 
      
    }
     public void limpiar(Boolean full){
        if(full){
            cboTC.setSelectedIndex(0);
            fechaDesde.setText("");
            fechaHasta.setText("");
            txtSucursal.setText("");
            txtDesde.setText("");
            txtHasta.setText("");
            txtTimbrado.setText("");
            txtPEX.setText("");
            //fechaSelector.setSelectedIndex(0);
        }
      
        
        fechaDesde.requestFocus();
    }
public void comparar(){
  for (int i = 0; i < tTal.getRowCount(); i++) //RECORRIO POR TODA LAS FILAS DE LAS TABLA
{
if (tTal.getValueAt(i, 4).equals(txtDesde.getText()) && 
        tTal.getValueAt(i, 5).equals(txtHasta.getText()) &&
        tTal.getValueAt(i, 6).equals(fechaDesde.getText()) && 
        tTal.getValueAt(i, 7).equals(fechaHasta.getText()) && 
        tTal.getValueAt(i, 8).equals(txtTimbrado.getText())) {
 
bandera = true;

}
}
}
 public DefaultTableModel mostrar(){
        DefaultTableModel modelo;
        
        String titulos[] = {"ID","COMPROBANTE","SUCURSAL","P.EXPEDICION","NRO. DESDE","NRO.HASTA","FECHA DESDE","FECHA HASTA", "TIMBRADO"};
        String registro[]= new String[10];
        modelo= new DefaultTableModel(null,titulos);
        modelo.setRowCount(0);
        sSQL="Select id_tal,comprobantes.descripcion, sucursal, "
                + "punto_exp, nro_desde, nro_hasta, fecha_des,"
                + "fecha_has, timbrado "
                + " from talonarios inner join comprobantes on talonarios.tipo_com = comprobantes.id ";
        try{
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sSQL);
            
            while(rs.next()){
                
                registro[0]= rs.getString("id_tal");
                registro[1]= rs.getString("descripcion");
                registro[2]= rs.getString("sucursal");
                registro[3]= rs.getString("punto_exp");
                registro[4]= rs.getString("nro_desde");       
                registro[5]= rs.getString("nro_hasta");
                registro[6]= rs.getString("fecha_des");
                registro[7]= rs.getString("fecha_has");
                registro[8]= rs.getString("timbrado");
                modelo.addRow(registro);
               
               
            } 
         //   System.out.println(" TRU " + Arrays.toString(registro));
            tTal.setModel(modelo);
            return modelo;
        }
        catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
            
        }
        
    }

    private void initDatePickers(){
        // Limites de fechas
        Date now = new Date();
        LocalDate max = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR)+10, 01, 01);
        LocalDate min = LocalDate.of(2010, 01, 01);

        // Fecha desde
        fechaDesde.getComponentDateTextField().setPreferredSize(new Dimension(80, 25));
        fechaDesde.getComponentToggleCalendarButton().setText("");
        fechaDesde.getComponentToggleCalendarButton().setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/ico/calendar.png"))); // NOI18N
        fechaDesde.getComponentToggleCalendarButton().setPreferredSize(new Dimension(25, 25));
        fechaDesde.getComponentDateTextField().setMargin(new Insets(0, 0, 0, 0));
        fechaDesde.getSettings().setFormatForDatesCommonEra("yyyy-MM-dd");
        fechaDesde.getSettings().setDateRangeLimits(min, max);
        
        // Fecha hasta
        fechaHasta.getComponentDateTextField().setPreferredSize(new Dimension(80, 25));
        fechaHasta.getComponentToggleCalendarButton().setText("");
        fechaHasta.getComponentToggleCalendarButton().setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/ico/calendar.png"))); // NOI18N
        fechaHasta.getComponentToggleCalendarButton().setPreferredSize(new Dimension(25, 25));
        fechaHasta.getComponentDateTextField().setMargin(new Insets(0, 0, 0, 0));
        fechaHasta.getSettings().setFormatForDatesCommonEra("yyyy-MM-dd");
        fechaHasta.getSettings().setDateRangeLimits(min, max);
        
    }
 
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ABM = new javax.swing.JTabbedPane();
        pTalonarios = new javax.swing.JPanel();
        btnBorrar = new javax.swing.JButton();
        SPTalonarios = new javax.swing.JScrollPane();
        tTal = new javax.swing.JTable();
        pABM = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboTC = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSucursal = new javax.swing.JTextField();
        txtPEX = new javax.swing.JTextField();
        txtDesde = new javax.swing.JTextField();
        txtHasta = new javax.swing.JTextField();
        txtTimbrado = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        fechaDesde = new com.github.lgooddatepicker.components.DatePicker();
        fechaHasta = new com.github.lgooddatepicker.components.DatePicker();
        btnActualizar = new javax.swing.JButton();
        txtid_tal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setTitle("Talonarios");

        btnBorrar.setText("Borrar");
        btnBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBorrarMousePressed(evt);
            }
        });
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        tTal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo Comprobante", "Sucursal", "Punto de Expedicion", "Numero Desde", "Numero Hasta", "Fecha Desde", "Fecha Hasta", "Timbrado"
            }
        ));
        tTal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tTalMousePressed(evt);
            }
        });
        SPTalonarios.setViewportView(tTal);

        javax.swing.GroupLayout pTalonariosLayout = new javax.swing.GroupLayout(pTalonarios);
        pTalonarios.setLayout(pTalonariosLayout);
        pTalonariosLayout.setHorizontalGroup(
            pTalonariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTalonariosLayout.createSequentialGroup()
                .addComponent(SPTalonarios, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTalonariosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        pTalonariosLayout.setVerticalGroup(
            pTalonariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTalonariosLayout.createSequentialGroup()
                .addComponent(SPTalonarios, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBorrar)
                .addContainerGap())
        );

        ABM.addTab("Talonarios", pTalonarios);

        jLabel1.setText("Tipo de Comprobante");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 25));

        cboTC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "1 = Factura", "2 = Nota de Débito", "3 = Nota de Crédito", "4 = Despacho", "5 = Auto factura", "6 = Boleta de Venta", "7 = Pasaje Aéreo", "8 = Factura del Exterior", "9 = Planilla de Sueldos", "10 = Comprobante de Ingresos", "12 = Factura de Exportación", "11 = Retencion Absorvida", "13 = Pasaje Aéreo Electrónico" }));
        cboTC.setPreferredSize(new java.awt.Dimension(125, 25));

        jLabel2.setText("Sucursal");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel3.setText("Punto de Expedición");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel4.setText("Desde Número");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel5.setText("Hasta Número");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel6.setText("Fecha Inicio");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel7.setText("Fecha Final");
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel8.setText("Timbrado");
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 25));

        txtSucursal.setPreferredSize(new java.awt.Dimension(125, 25));
        txtSucursal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSucursalKeyTyped(evt);
            }
        });

        txtPEX.setPreferredSize(new java.awt.Dimension(125, 25));
        txtPEX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPEXKeyTyped(evt);
            }
        });

        txtDesde.setPreferredSize(new java.awt.Dimension(125, 25));
        txtDesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDesdeKeyTyped(evt);
            }
        });

        txtHasta.setPreferredSize(new java.awt.Dimension(125, 25));
        txtHasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHastaKeyTyped(evt);
            }
        });

        txtTimbrado.setPreferredSize(new java.awt.Dimension(125, 25));
        txtTimbrado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimbradoKeyTyped(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.setMaximumSize(new java.awt.Dimension(80, 28));
        btnAgregar.setMinimumSize(new java.awt.Dimension(80, 28));
        btnAgregar.setPreferredSize(new java.awt.Dimension(80, 28));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        fechaDesde.setMinimumSize(null);
        fechaDesde.setPreferredSize(new java.awt.Dimension(125, 25));

        fechaHasta.setMinimumSize(null);
        fechaHasta.setPreferredSize(new java.awt.Dimension(125, 25));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        txtid_tal.setEnabled(false);

        jButton1.setText("Nuevo Registro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pABMLayout = new javax.swing.GroupLayout(pABM);
        pABM.setLayout(pABMLayout);
        pABMLayout.setHorizontalGroup(
            pABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pABMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pABMLayout.createSequentialGroup()
                        .addGroup(pABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pABMLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pABMLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pABMLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pABMLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimbrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pABMLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pABMLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPEX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pABMLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pABMLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
                        .addComponent(txtid_tal, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(pABMLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pABMLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(68, 68, 68))
        );
        pABMLayout.setVerticalGroup(
            pABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pABMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtid_tal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(pABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPEX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimbrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pABMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar))
                .addGap(44, 44, 44)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        ABM.addTab("ABM", pABM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ABM)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(ABM, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
   comparar();
   Connection c = null;
   ArrayList lista = new ArrayList();   
    
    if(!bandera){
     
      try {
                    Class.forName("org.sqlite.JDBC");
                    c = DriverManager.getConnection("jdbc:sqlite:Analytix.db");
                    System.out.println("Opened database successfully");

                    String sql = "INSERT INTO TALONARIOS (tipo_com, sucursal, punto_exp, nro_desde, nro_hasta, fecha_des, fecha_has, timbrado) VALUES(?,?,?,?,?,?,?,?);";
          
             try (PreparedStatement pstmt = c.prepareStatement(sql)) {
                             
              pstmt.setInt(1, cboTC.getSelectedIndex());
              pstmt.setString (2, txtSucursal.getText());
              pstmt.setString (3, txtPEX.getText());
              pstmt.setString (4, txtDesde.getText());
              pstmt.setString (5, txtHasta.getText());
              pstmt.setString (6, fechaDesde.getText());
              pstmt.setString (7, fechaHasta.getText());
              pstmt.setString (8, txtTimbrado.getText());
         
               if(txtSucursal.getText().isEmpty() || txtPEX.getText().isEmpty()||
                      txtDesde.getText().isEmpty() || txtHasta.getText().isEmpty() ||
                      fechaDesde.getText().isEmpty() || fechaHasta.getText().isEmpty() || txtTimbrado.getText().isEmpty()){
             JOptionPane.showMessageDialog(this, "¡No se admiten valores nulos!");
               }else{
                   pstmt.executeUpdate();
                   JOptionPane.showMessageDialog(this, "¡Registro Exitoso!");
               }  
             c.close();
             } } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
                
             }
                mostrar();
    }

    else{
        JOptionPane.showMessageDialog(this, "¡No se admiten registros duplicados!");
    }
       
         // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
    Connection c = null;
    int row = tTal.getSelectedRow();
    DefaultTableModel model= (DefaultTableModel)tTal.getModel();
    String selected = model.getValueAt(row, 0).toString();
           
                if (row >= 0) {

                    model.removeRow(row);
                    try {
                         Class.forName("org.sqlite.JDBC");
             c = DriverManager.getConnection("jdbc:sqlite:Analytix.db");
                        PreparedStatement ps = c.prepareStatement("delete from talonarios where id_tal= ? ");
                        ps.setString(1, selected);
                        ps.executeUpdate();
                    }
                    catch (Exception w) {
                        JOptionPane.showMessageDialog(this, "Connection Error!");
                    }           
            }
        mostrar();                // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnBorrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBorrarMousePressed

        
    }//GEN-LAST:event_btnBorrarMousePressed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
  if(bandera){
     
      sSQL="update talonarios set sucursal=?,punto_exp=?,nro_desde=?,nro_hasta=?,fecha_des=?,fecha_has=?, timbrado=? where id_tal="+tTal.getValueAt(tTal.getSelectedRow(), 0)+"";
        try {
            PreparedStatement pst=cn.prepareStatement(sSQL);
            
            pst.setInt(1,Integer.parseInt(txtSucursal.getText()));
            pst.setInt(2,Integer.parseInt(txtPEX.getText()));
            pst.setInt(3,Integer.parseInt(txtDesde.getText()));
            pst.setInt(4,Integer.parseInt(txtHasta.getText()));
            pst.setString(5,fechaDesde.getText());
            pst.setString(6,fechaHasta.getText());
            pst.setInt(7,Integer.parseInt(txtTimbrado.getText()));
           
            int n=pst.executeUpdate();
           JOptionPane.showMessageDialog(this, "¡El registro ha sido actualizado!");
        
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        
        }
    
        mostrar();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tTalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tTalMousePressed
        tTal =(JTable) evt.getSource();
        Point point = evt.getPoint();
         Integer row = tTal.rowAtPoint(point);
        if (evt.getClickCount() == 2) {
     SQLiteCon lite = new SQLiteCon();
     cboTC.setEnabled(false);
     txtid_tal.setText((String) tTal.getValueAt(tTal.getSelectedRow(), 0));
     cboTC.setSelectedItem((String) tTal.getValueAt(tTal.getSelectedRow(), 1).toString());
     txtSucursal.setText((String) tTal.getValueAt(tTal.getSelectedRow(), 2));
     txtPEX.setText((String) tTal.getValueAt(tTal.getSelectedRow(), 3));
     txtDesde.setText((String) (tTal.getValueAt(tTal.getSelectedRow(), 4)));
     txtHasta.setText((String)(tTal.getValueAt(tTal.getSelectedRow(), 5)));
     fechaDesde.setText((String)(tTal.getValueAt(tTal.getSelectedRow(), 6)));
     fechaHasta.setText((String)(tTal.getValueAt(tTal.getSelectedRow(), 7)));
     txtTimbrado.setText((String)(tTal.getValueAt(tTal.getSelectedRow(), 8)));

     bandera = true;
   
        }  // TODO add your handling code here:
    }//GEN-LAST:event_tTalMousePressed

    private void txtSucursalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSucursalKeyTyped
String Caracteres = txtSucursal.getText();
        if(Caracteres.length()>=3){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtSucursalKeyTyped

    private void txtPEXKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPEXKeyTyped
String Caracteres = txtPEX.getText();
        if(Caracteres.length()>=3){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtPEXKeyTyped

    private void txtDesdeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesdeKeyTyped
String Caracteres = txtDesde.getText();
        if(Caracteres.length()>=11){
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtDesdeKeyTyped

    private void txtHastaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHastaKeyTyped
   String Caracteres = txtHasta.getText();
        if(Caracteres.length()>=11){
            evt.consume();
        }    
    }//GEN-LAST:event_txtHastaKeyTyped

    private void txtTimbradoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimbradoKeyTyped
String Caracteres = txtTimbrado.getText();
        if(Caracteres.length()>=11){
            evt.consume();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_txtTimbradoKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
limpiar(true);
btnAgregar.setText("Agregar");// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane ABM;
    private javax.swing.JScrollPane SPTalonarios;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JComboBox<String> cboTC;
    private com.github.lgooddatepicker.components.DatePicker fechaDesde;
    private com.github.lgooddatepicker.components.DatePicker fechaHasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel pABM;
    private javax.swing.JPanel pTalonarios;
    private javax.swing.JTable tTal;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtHasta;
    private javax.swing.JTextField txtPEX;
    private javax.swing.JTextField txtSucursal;
    private javax.swing.JTextField txtTimbrado;
    private javax.swing.JTextField txtid_tal;
    // End of variables declaration//GEN-END:variables
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
   

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tTimbrados().setVisible(true);
            }
        });
    }

}
