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
   
        
    //  insertar();
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

                    System.out.println("Opened database successfully");
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
                                "(tipo_com int , " +
                                "sucursal VARCHAR(20), "+
                                "punto_exp VARCHAR(20), " +
                                "nro_desde INTEGER, " +
                                "nro_hasta INTEGER, " +
                                "fecha_des DATE, " +
                                "fecha_has DATE, " +
                                "timbrado INTEGER, "
                        + "FOREIGN KEY (tipo_com) REFERENCES comprobantes(id))";
                                
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
        /*
      public void insertar(){
          
      Connection c = null;
      Statement stmt = null;
  //    boolean hasdata = false;
         try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:Analytix.db");
                System.out.println("Opened database successfully");

                stmt = c.createStatement();
                String sql = "INSERT INTO talonarios VALUES (2, 2,2, 123456,132465, 2020-03-21, 2020-03-22, 12345)";
                                
             
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
  
*/
     
          public static void main(String[] args) {
 

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
 