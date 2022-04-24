
package com.emergentes.utiles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class ConexionBD {
//atributos de clase
 static String driver="com.mysql.jdbc.Driver";
 static String url="jdbc:mysql://localhost:3306/bd_biblio";
 static String usuario="root";
 static String password="";
  Connection conn=null;
  
  //constructor
  public ConexionBD(){
    try {
        //Especificacion del driver
        Class.forName(driver);
        //EStablece la conexion con la base de datos
        conn=DriverManager.getConnection(url,usuario,password);
        //Verificar si la conexion fue exitosa
        if(conn!=null){
          //  JOptionPane.showMessageDialog(null,"Conexion:"+conn);
        }
    }catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error de conexion:"+ex.getMessage());
        
    }catch(ClassNotFoundException ex ){
       Logger.getLogger (ConexionBD.class.getName()).log(Level.SEVERE,null,ex);
        
    }
        
      
  }// ConexinBD
    
  //metodos adiconales
  public Connection conectar (){
     return conn; 
  }  
    
   public void desconectar(){
       try{
           conn.close();
           
       }catch(SQLException ex){
            Logger.getLogger (ConexionBD.class.getName()).log(Level.SEVERE,null,ex); 
       }
   } 
    
}
