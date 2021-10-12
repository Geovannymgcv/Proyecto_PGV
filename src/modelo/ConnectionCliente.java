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

    public ConnectionCliente(String ci, String nombres, String apellidos, Date edad, String correo, String telefono, String direccion, String discapacidad, Image foto, String contrasena) {
        super(ci, nombres, apellidos, edad, correo, telefono, direccion, discapacidad, foto, contrasena);
    }
    public List<Cliente> listarClientes() {

        String sql = "select * from clientes"; //Campos de la base de datos.
        ResultSet rs = con.consulta(sql);
        List<Cliente> lista = new ArrayList<Cliente>();
        try {
            while (rs.next()) {
                Cliente p = new Cliente();
                p.setCi(rs.getString("ci"));//campos de la BD
                p.setNombres(rs.getString("nombre"));//campos de la BD
                p.setApellidos(rs.getString("apellido"));//campos de la BD
                p.setEdad(rs.getDate("edad"));
                p.setTelefono(rs.getString("telefono"));
                p.setCorreo(rs.getString("correo"));
                p.setDireccion(rs.getString("direccion"));
                p.setDiscapacidad(rs.getString("discapacidad"));
                p.setContrasena(rs.getString("contrasena"));
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
                per.setNombres(rs.getString("nombre"));//campos de la BD
                per.setApellidos(rs.getString("apellido"));//campos de la BD
                per.setEdad(rs.getDate("edad"));
                per.setTelefono(rs.getString("telefono"));
                per.setCorreo(rs.getString("correo"));
                per.setDireccion(rs.getString("direccion"));
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
        sql="INSERT INTO clientes(ci, nombre, apellido, edad, correo, telefono, direccion, discapacidad, contrasena) ";
        sql+=" VALUES ('" + getCi()+ "','" + getNombres()+ "','" + getApellidos()+ "','" + getEdad()+ "','" + getCorreo()+ "','" + getTelefono()+ "','" + getDireccion()+ "','" + getDiscapacidad()+ "','" + getContrasena()+ "')";
        return con.accion(sql);
    }
//    public boolean eliminar(String ci){
//        String nsql = "delete from persona where \"ci\"='" + ci + "'";
//        return con.accion(nsql);
//    }
    public boolean editar(String ci){
        String nsql = "UPDATE clientes set \"nombre\"='" + getNombres()+ "',\"apellido\"='" + getApellidos()+ "',\"correo\"='" + getCorreo()+ "',\"telefono\"='" + getTelefono()+ "'\"direccion\"='" + getDireccion()+ "'"
                + "WHERE \"ci\"='" + ci + "'";
        return con.accion(nsql);
    }
}
