/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.VistaGeneral;

/**
 *
 * @author chusp
 */
public class ConnectionEncuesta extends Encuesta{
    private ConnectionBD con = new ConnectionBD();
    public ConnectionEncuesta() {
    }

    public ConnectionEncuesta(String id_encuesta, String ci_cliente, String p1, String p2, int p3, String p4, String p5, String p6, int p7) {
        super(id_encuesta, ci_cliente, p1, p2, p3, p4, p5, p6, p7);
    }
    public List<Cliente> listarClientes() {

        String sql = "select * from encuesta"; //Campos de la base de datos.
        ResultSet rs = con.consulta(sql);
        List<Cliente> lista = new ArrayList<Cliente>();
        try {
            while (rs.next()) {
                Cliente p = new Cliente();
                p.setCi(rs.getString("1"));//campos de la BD
                p.setNombre(rs.getString("2"));//campos de la BD
                p.setApellido(rs.getString("3"));//campos de la BD
                p.setEdad(rs.getInt("4"));
                p.setTelefono(rs.getString("5"));
                p.setCorreo(rs.getString("6"));
                p.setDiscapacidad(rs.getString("7"));
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

//    public List<Cliente> listaClientes(String aguja) {
//
//        try {
//            String sql = "select * from clientes WHERE ";
//            sql += " UPPER(nombre) like UPPER('%" + aguja + "%') OR";
//            sql += " UPPER(apellido) like UPPER('%" + aguja + "%') OR";
//            sql += " UPPER(ci) like UPPER('%" + aguja + "%') ";
//            ResultSet rs = con.consulta(sql);
//            List<Cliente> lp = new ArrayList<Cliente>();
//            while (rs.next()) {
//                Cliente per = new Cliente();
//                per.setCi(rs.getString("ci"));//campos de la BD
//                per.setNombre(rs.getString("nombre"));//campos de la BD
//                per.setApellido(rs.getString("apellido"));//campos de la BD
//                per.setEdad(rs.getInt("edad"));
//                per.setTelefono(rs.getString("telefono"));
//                per.setCorreo(rs.getString("correo"));
//                per.setDiscapacidad(rs.getString("discapacidad"));
//                lp.add(per);
//            }
//            rs.close();
//            return lp;
//        } catch (SQLException ex) {
//            Logger.getLogger(ConnectionCliente.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
    private VistaGeneral vista;
    public boolean grabar() {
        String sql;
        int a = 0 + (int) (Math.random() * 100);
        String b = Integer.toString(a);
        String c = vista.getJtCi().toString();
        sql="INSERT INTO encuesta(id_encuesta, ci_cliente, 1, 2, 3, 4, 5, 6, 7) ";
        sql+=" VALUES ('" + b+ "','" + c+ "','" + vista.getCb1().toString()+ "','" + vista.getCb2().toString()+ "','" + Integer.parseInt(vista.getCb3().toString())+ "','" + vista.getCb4().toString()+ "','" + vista.getCb5().toString()+ "','" + vista.getCb6().toString()+ "','" + Integer.parseInt(vista.getCb7().toString())+ "')";
        return con.accion(sql);
    }
}
