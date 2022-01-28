/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class SQLiteCon {

  
    public SQLiteCon() {
        conectar();
        crearTabla();
 
       
//     insertar();
    }
    
      public Connection conectar(){
          
        Connection c = null;
      
                    try {
                       Class.forName("org.sqlite.JDBC");
                       c = DriverManager.getConnection("jdbc:sqlite:Analytix.db");
                    } catch ( Exception e ) {
                       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                       System.exit(0);
                    }

       //             System.out.println("Opened database successfully");
                      return c;
        
      }
      
      public void crearTabla(){
          
      Connection c = null;
      Statement stmt = null;
  //    boolean hasdata = false;
         try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:Analytix.db");
                System.out.println("Opened database successfully");

                stmt = c.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS TALONARIOS " +
                                "(id_tal INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "tipo_com INTEGER, " +
                                "sucursal  INTEGER NOT NULL,  "+
                                "punto_exp INTEGER NOT NULL, " +
                                "nro_desde INTEGER NOT NULL, " +
                                "nro_hasta INTEGER NOT NULL, " +
                                "fecha_des DATE NOT NULL, " +
                                "fecha_has DATE NOT NULL, " +
                                "timbrado INTEGER NOT NULL, " 
                        + "FOREIGN KEY (tipo_com) REFERENCES COMPROBANTES(id)) ";
                   
                                
                String sqln = "CREATE TABLE IF NOT EXISTS COMPROBANTES (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, descripcion varchar(20));";          
                stmt.executeUpdate(sqln);
                stmt.executeUpdate(sql);
                stmt.close();
                  c.close();
             } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
             }
      System.out.println("Table created successfully"); 
      
      }

 
      public void insertar(){
          
      Connection c = null;
      Statement stmt = null;
  //    boolean hasdata = false;
         try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:Analytix.db");
                System.out.println("Opened database successfully");

                stmt = c.createStatement();
                      String sqln ="INSERT INTO COMPROBANTES (descripcion) VALUES('Factura'); "
                        + "INSERT INTO COMPROBANTES (descripcion) VALUES('Nota de Débito'); "
                        + "INSERT INTO COMPROBANTES (descripcion) VALUES('Nota de Crédito'); "
                        + "INSERT INTO COMPROBANTES (descripcion) VALUES('Despacho');"
                        + "INSERT INTO COMPROBANTES (descripcion) VALUES('Auto factura');"
                        + "INSERT INTO COMPROBANTES (descripcion) VALUES('Boleta de Venta'); "
                        + "INSERT INTO COMPROBANTES (descripcion) VALUES('Pasaje Aéreo'); "
                        + "INSERT INTO COMPROBANTES (descripcion) VALUES('Factura del Exterior'); "
                        + "INSERT INTO COMPROBANTES (descripcion) VALUES('Planilla de Sueldos'); "
                        + "INSERT INTO COMPROBANTES (descripcion) VALUES('Comprobante de Ingresos'); "
                        + "INSERT INTO COMPROBANTES (descripcion) VALUES('Retencion Absorvida'); "
                        + "INSERT INTO COMPROBANTES (descripcion) VALUES('Pasaje Aéreo Electrónico'); ";
                                
             
                stmt.executeUpdate(sqln);
          
          //     hasdata = true;
                 stmt.close();
                  c.close();
             } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
             }
      System.out.println("RECORDS created successfully"); 
      
        }
}

