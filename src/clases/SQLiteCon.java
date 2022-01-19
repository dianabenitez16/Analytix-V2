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
 
       
  //   insertar();
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
                                "(id_tal INTEGER PRIMARY KEY AUTOINCREMENT, "
                               + "tipo_com INTEGER, " +
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
           //     hasdata = true;
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
                String sql ="INSERT INTO TALONARIOS (tipo_com, sucursal, punto_exp,nro_desde,nro_hasta, fecha_des, fecha_has, timbrado ) VALUES (1,1,1,'262150','280000','2020-01-01','2023-1-01',123456)";
  //              String sqln ="INSERT INTO COMPROBANTES VALUES(2, 'Nota de Crédito') ";
                 //       + "INSERT INTO COMPROBANTES VALUES(2, 'Nota de Crédito')";
                                
             
 //               stmt.executeUpdate(sqln);
                stmt.executeUpdate(sql);
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

/*
      
      if(hasdata){
          
      
      try {
         String sql = "INSERT INTO COMPROBANTES VALUES (1, 'Fac')"; 
         stmt.executeUpdate(sql);
c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
   
       try{
          ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPROBANTES;" );
     
      while ( rs.next() ) {
         int id = rs.getInt("id");
         String  tipo_com = rs.getString("tipo_com");
         
         System.out.println( "ID = " + id );
         System.out.println( "TIPO DE COMPROBANTE = " + tipo_com );
 
      }
      rs.close();
      stmt.close();
      c.close();
   } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
   }
   System.out.println("Operation done successfully");
  }
}
      /*
}
        CREATE TABLES 
  Connection c = null;
      Statement stmt = null;
      boolean hasdata = false;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:Analytix.db");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "CREATE TABLE IF NOT EXISTS TALONARIOS (tipo_com varchar(20), sucursal int, punto_exp varchar(30), nro_desde date, nro_hasta date, timbrado vachar(30));";
                    
         stmt.executeUpdate(sql);
         hasdata = true;

      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");

      

  /*        // TODO code application logic here
public void creartabla(){
    conectar();
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:Analytix.db");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "CREATE TABLE COMPANY " +
                        "(ID INT," +
                        " NAME CHAR(30)";
                    
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");
   }
*/
 