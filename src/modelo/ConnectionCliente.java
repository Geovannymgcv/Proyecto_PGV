/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.text.html.HTMLDocument;
import org.postgresql.util.Base64;

/**
 *
 * @author chusp
 */
public class ConnectionCliente extends Cliente{
    private ConnectionBD con = new ConnectionBD();

    public ConnectionCliente() {
    }

    public ConnectionCliente(String ci, String nombre, String apellido, int edad, String correo, String telefono, String discapacidad, Image foto) {
        super(ci, nombre, apellido, edad, correo, telefono, discapacidad, foto);
    }
    public List<Cliente> listarClientes() {

        String sql = "select * from clientes"; //Campos de la base de datos.
        ResultSet rs = con.consulta(sql);
        List<Cliente> lista = new ArrayList<Cliente>();
        try {
            while (rs.next()) {
                Cliente p = new Cliente();
                p.setCi(rs.getString("ci"));//campos de la BD
                p.setNombre(rs.getString("nombre"));//campos de la BD
                p.setApellido(rs.getString("apellido"));//campos de la BD
                p.setEdad(rs.getInt("edad"));
                p.setTelefono(rs.getString("telefono"));
                p.setCorreo(rs.getString("correo"));
                p.setDiscapacidad(rs.getString("discapacidad"));
                lista.add(p);
            }
            //IMPORTANTISIMO CERRAR CONEXION.
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionCliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<Cliente> listaClientes(String aguja) {

        try {
            String sql = "select * from clientes WHERE ";
            sql += " UPPER(nombre) like UPPER('%" + aguja + "%') OR";
            sql += " UPPER(apellido) like UPPER('%" + aguja + "%') OR";
            sql += " UPPER(ci) like UPPER('%" + aguja + "%') ";
            ResultSet rs = con.consulta(sql);
            List<Cliente> lp = new ArrayList<Cliente>();
            while (rs.next()) {
                Cliente per = new Cliente();
                per.setCi(rs.getString("ci"));//campos de la BD
                per.setNombre(rs.getString("nombre"));//campos de la BD
                per.setApellido(rs.getString("apellido"));//campos de la BD
                per.setEdad(rs.getInt("edad"));
                per.setTelefono(rs.getString("telefono"));
                per.setCorreo(rs.getString("correo"));
                per.setDiscapacidad(rs.getString("discapacidad"));
                lp.add(per);
            }
            rs.close();
            return lp;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionCliente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    public boolean grabar() {
        String sql;
        sql="INSERT INTO clientes(ci, nombre, apellido, edad, correo, telefono, discapacidad) ";
        sql+=" VALUES ('" + getCi()+ "','" + getNombre()+ "','" + getApellido()+ "','" + getEdad()+ "','" + getCorreo()+ "','" + getTelefono()+ "','" + getDiscapacidad()+ "')";
        return con.accion(sql);
    }
//    public boolean eliminar(String ci){
//        String nsql = "delete from persona where \"ci\"='" + ci + "'";
//        return con.accion(nsql);
//    }
    public boolean editar(String ci){
        String nsql = "UPDATE clientes set \"nombre\"='" + getNombre()+ "',\"apellido\"='" + getApellido()+ "',\"edad\"='" + getEdad()+ "',\"correo\"='" + getCorreo()+ "',\"telefono\"='" + getTelefono()+ "',\"discapacidad\"='" + getDiscapacidad()+ "'"
                + "WHERE \"ci\"='" + ci + "'";
        return con.accion(nsql);
    }
}
