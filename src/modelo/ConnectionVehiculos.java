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
public class ConnectionVehiculos extends Vehiculo{
    private ConnectionBD con = new ConnectionBD();

    public ConnectionVehiculos() {
    }

    public ConnectionVehiculos(int cilindraje, String id_modelo, String marca, String nombre, double precio_sin_iva, String imagen, String tipo_cambios, String tipo, String todoterreno) {
        super(cilindraje, id_modelo, marca, nombre, precio_sin_iva, imagen, tipo_cambios, tipo, todoterreno);
    }
        public List<Vehiculo> listarVehiculos() {

        String sql = "select * from vehiculo"; //Campos de la base de datos.
        ResultSet rs = con.consulta(sql);
        List<Vehiculo> lista = new ArrayList<Vehiculo>();
        try {
            while (rs.next()) {
                Vehiculo ve = new Vehiculo();
                ve.setId_modelo(rs.getString("id_modelo"));//campos de la BD
                ve.setCilindraje(Integer.parseInt(rs.getString("cilindraje")));//campos de la BD
                ve.setMarca(rs.getString("marca"));//campos de la BD
                ve.setNombre(rs.getString("nombre"));
                ve.setPrecio_sin_iva(Integer.parseInt(rs.getString("precio_sin_iva")));
                ve.setImagen(rs.getString("imagen"));
                ve.setTipo_cambios(rs.getString("tipo_cambios"));
                ve.setTipo(rs.getString("tipo"));
                ve.setTodoterreno(rs.getString("todoterreno"));
                lista.add(ve);
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
        
        
//    private VistaGeneral vista;
//    public boolean grabar() {
//        String sql;
//        sql="INSERT INTO encuesta(id_encuesta, ci_cliente, 1, 2, 3, 4, 5, 6, 7) ";
//        sql+=" VALUES ('" + b+ "','" + c+ "','" + vista.getCb1().toString()+ "','" + vista.getCb2().toString()+ "','" + Integer.parseInt(vista.getCb3().toString())+ "','" + vista.getCb4().toString()+ "','" + vista.getCb5().toString()+ "','" + Integer.parseInt(vista.getCb6().toString())+ "')";
//        return con.accion(sql);
//    }
}
