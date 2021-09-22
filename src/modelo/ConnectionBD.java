/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chusp
 */
public class ConnectionBD {
    String cadenaConexion="jdbc:postgresql://localhost:5432/Concesionario";
    String usuarioBD="postgres";
    String contrasBD="Mcgeodeath9";
    
    Connection con;
    public ConnectionBD() {
    
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
           con= DriverManager.getConnection(cadenaConexion, usuarioBD, contrasBD);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public ResultSet consulta(String sqlc){
    
        try {
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(sqlc);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean accion(String sqla){
        try {
            Statement st= con.createStatement();
            boolean rb=st.execute(sqla);
            st.close();
            return rb;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
}
