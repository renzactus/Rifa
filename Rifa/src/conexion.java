
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anubis_1392
 */
public class conexion {

    static Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    Connection con=null;
    public Connection Conectar(){
    try{
        
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://www.edusalto.uy/pow_sorteo2019","pow_costanzo","costanzo2019");
        //JOptionPane.showMessageDialog(null, "CONEXION ESTABLECIDA");
    }catch (ClassNotFoundException | SQLException | HeadlessException e){
        JOptionPane.showMessageDialog(null, "CONEXION NO ESTABLECIDA"+e);
        }
        return con;
    
    
    }

}
