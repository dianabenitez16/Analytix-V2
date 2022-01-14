/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package formularios.tablas;

import clases.SQLiteCon;
import java.awt.Dimension;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.threeten.bp.LocalDate;

/**
 *
 * @author junju
 */
public class tTimbrados extends javax.swing.JInternalFrame { 
 private SQLiteCon sqlite= new SQLiteCon();
 private Connection cn= sqlite.conectar();
 private String sSQL="";

 

    public tTimbrados() {
        initComponents();
        initDatePickers();
        mostrar();
        limpiartabla();
     
    }

 public DefaultTableModel mostrar(){
        DefaultTableModel modelo;
        
        String titulos[] = {"COMPROBANTE","SUCURSAL","P.EXPEDICION","NRO. DESDE","NRO.HASTA","FECHA DESDE","FECHA HASTA", "TIMBRADO"};
        String registro[]= new String[9];
        modelo= new DefaultTableModel(null,titulos);
        modelo.setRowCount(0);
        sSQL="Select * from talonarios";
        try{
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sSQL);
            
            while(rs.next()){
                registro[0]= rs.getString("tipo_com");
                registro[1]= rs.getString("sucursal");
                registro[2]= rs.getString("punto_exp");
                registro[3]= rs.getString("nro_desde");       
                registro[4]= rs.getString("nro_hasta");
                registro[5]= rs.getString("fecha_desde");
                registro[6]= rs.getString("fecha_hasta");
                registro[7]= rs.getString("timbrado");
                modelo.addRow(registro);
               
                System.out.println("COMPROBANTE " + rs.getString("tipo_com"));
                System.out.println("SUCURSAL "+ rs.getString("sucursal"));
                System.out.println("PUNTOEX "+ rs.getString("punto_exp"));
                System.out.println("NRO_DESDE "+ rs.getString("nro_desde"));
                System.out.println("NRO_HASTA " + rs.getString("nro_hasta"));
                System.out.println("FCHA_DES "+ rs.getString("fecha_desde"));
                System.out.println("FCHA_HAS "+ rs.getString("fecha_hasta"));
                System.out.println("TIMBRADO "+ rs.getString("timbrado"));
            } 
            System.out.println(" TRU " + Arrays.toString(registro));
            jTable1.setModel(modelo);
            return modelo;
        }
        catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
            
        }
    
        
    }
 public void limpiartabla(){
  DefaultTableModel dtm=(DefaultTableModel) jTable1.getModel();
    int i=dtm.getRowCount();       
    for(int j=i-1;j>=0;j--)
         {

         if(!(jTable1.getValueAt(j, 0) != null 
         && jTable1.getValueAt(j, 0).toString().trim().length() != 0) )
         {
               dtm.removeRow(j);
         }

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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
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
        jButton1 = new javax.swing.JButton();
        fechaDesde = new com.github.lgooddatepicker.components.DatePicker();
        fechaHasta = new com.github.lgooddatepicker.components.DatePicker();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setTitle("Talonarios");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo Comprobante", "Sucursal", "Punto de Expedicion", "Numero Desde", "Numero Hasta", "Fecha Desde", "Fecha Hasta", "Timbrado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Talonarios", jPanel1);

        jLabel1.setText("Tipo de Comprobante");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 25));

        cboTC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 = Factura", "2 = Nota de Débito", "3 = Nota de Crédito", "4 = Despacho", "5 = Auto factura", "6 = Boleta de Venta", "7 = Pasaje Aéreo", "8 = Factura del Exterior", "9 = Planilla de Sueldos", "10 = Comprobante de Ingresos", "12 = Factura de Exportación", "11 = Retencion Absorvida", "13 = Pasaje Aéreo Electrónico" }));
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

        txtPEX.setPreferredSize(new java.awt.Dimension(125, 25));

        txtDesde.setPreferredSize(new java.awt.Dimension(125, 25));

        txtHasta.setPreferredSize(new java.awt.Dimension(125, 25));

        txtTimbrado.setPreferredSize(new java.awt.Dimension(125, 25));

        jButton1.setText("Agregar");
        jButton1.setPreferredSize(new java.awt.Dimension(125, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        fechaDesde.setMinimumSize(null);
        fechaDesde.setPreferredSize(new java.awt.Dimension(125, 25));

        fechaHasta.setMinimumSize(null);
        fechaHasta.setPreferredSize(new java.awt.Dimension(125, 25));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPEX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimbrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(339, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPEX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimbrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ABMc", jPanel2);

        jButton2.setText("Borrar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Actualizar");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 mostrar();
 Connection c = null;
      Statement stmt = null;
    
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:Analytix.db");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "INSERT INTO TALONARIOS VALUES(?,?,?,?,?,?,?,?);";
          PreparedStatement preparedStmt = c.prepareStatement(sql);
int seleccionado = cboTC.getSelectedIndex();
preparedStmt.setString(1, cboTC.getItemAt(seleccionado));
preparedStmt.setString (2, txtSucursal.getText());
preparedStmt.setString (3, txtPEX.getText());
preparedStmt.setString (4, txtHasta.getText());
preparedStmt.setString (5, txtDesde.getText());
preparedStmt.setString (6, fechaDesde.getText());
preparedStmt.setString (7, fechaHasta.getText());
preparedStmt.setString (8, txtTimbrado.getText());

preparedStmt.execute();          
         stmt.executeUpdate(sql);
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");  // TODO code application logic here
      // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
Connection c = null;
int row = jTable1.getSelectedRow();
DefaultTableModel model= (DefaultTableModel)jTable1.getModel();
String selected = model.getValueAt(row, 0).toString();
           
            if (row >= 0) {

                model.removeRow(row);
                model.removeRow(row);

                try {
                     Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:Analytix.db");
                    PreparedStatement ps = c.prepareStatement("delete from talonarios where tipo_com='"+selected+"' ");
                    ps.executeUpdate();
                }
                catch (Exception w) {
                    JOptionPane.showMessageDialog(this, "Connection Error!");
                }           
            }    // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed

        
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
 Connection c = null;
 int fila= jTable1.rowAtPoint(evt.getPoint());
 int row = jTable1.getSelectedRow();
DefaultTableModel model= (DefaultTableModel)jTable1.getModel();
String selected = model.getValueAt(row, 0).toString();
    
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:Analytix.db");
         System.out.println("Opened database successfully");
        
        String sql = "UPDATE talonarios SET tipo_com = ?, sucursal = ?, punto_exp = ?, nro_desde = ?, nro_hasta = ?, fecha_desde = ?, fecha_hasta = ?, timbrado = ? WHERE tipo_com = "+selected+"";
cboTC.setSelectedItem(jTable1.getValueAt(fila,1).toString());
        txtSucursal.setText(jTable1.getValueAt(fila,2).toString());
        txtPEX.setText(jTable1.getValueAt(fila,3).toString());
        txtDesde.setText(jTable1.getValueAt(fila,4).toString());
        txtHasta.setText(jTable1.getValueAt(fila,5).toString());
        fechaDesde.setText(jTable1.getValueAt(fila,6).toString());
        fechaHasta.setText(jTable1.getValueAt(fila,7).toString());           // TODO add your handling code here:
        txtTimbrado.setText(jTable1.getValueAt(fila,8).toString()); 
PreparedStatement stmt = c.prepareStatement(sql);
          c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully"); // TODO add your handling code here:

                 // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboTC;
    private com.github.lgooddatepicker.components.DatePicker fechaDesde;
    private com.github.lgooddatepicker.components.DatePicker fechaHasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtHasta;
    private javax.swing.JTextField txtPEX;
    private javax.swing.JTextField txtSucursal;
    private javax.swing.JTextField txtTimbrado;
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
