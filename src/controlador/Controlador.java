/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ConnectionCliente;
import modelo.ConnectionEncuesta;
import modelo.Encuesta;
import vista.VistaGeneral;

/**
 *
 * @author chusp
 */
public class Controlador {

    private ConnectionCliente conectcliente;
    private ConnectionEncuesta conectencuesta;
    private VistaGeneral vista;
    private Cliente cliente;
    private Encuesta encuesta;

    public Controlador(ConnectionCliente modelo, VistaGeneral vista) {
        this.conectcliente = modelo;
        this.vista = vista;
        //SOLAMENTE INICIALIZAR ELEMENTOS.
        vista.setTitle("CONCESIONARIO XXXX");

        vista.setVisible(true);
    }

    public void iniciaControl() {
        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

            }
        };
        vista.getJmiRegistro().addActionListener(l -> cargarDialogo(1));
        vista.getBtnRegistrar().addActionListener(l -> cargarRegistrar(1));
        vista.getJmiCuenta().addActionListener(l -> cargarCuenta(1));
        vista.getBtnRegistrarCliente().addActionListener(l -> registrarCliente(1));
        vista.getBtnIngresar().addActionListener(l -> ingresarSistema(1));
//        vista.getBtnCrear().addActionListener(l -> cargarDialogo(1));
//        vista.getBtnEditar().addActionListener(l -> cargarDialogo(2));
//        vista.getBtnExaminar().addActionListener(l -> examinaFoto());
//        vista.getBtnAceptar().addActionListener(l -> grabaPersona());
//        vista.getBtnAceptarE().addActionListener(l -> editarPersona());
//        vista.getBtnEliminar().addActionListener(l -> borrarPersona());
//        vista.getBtnCancelar().addActionListener(l -> cerrar());
//        //Controlador Buscar
//        vista.getTxtBuscar().addKeyListener(kl);
    }

    private void cargarDialogo(int origen) {
        if (origen == 1) {
            vista.getJlgIngreso().setSize(430, 370);
            vista.getJlgIngreso().setLocationRelativeTo(vista);
            vista.getJlgIngreso().setVisible(true);
        }
    }

    private void cargarRegistrar(int origen) {
        if (origen == 1) {
            vista.getJlgIngreso().setVisible(false);
            vista.getJlgRegistro().setSize(650, 620);
            vista.getJlgRegistro().setLocationRelativeTo(vista);
            vista.getJlgRegistro().setVisible(true);
        }
    }

    private void cargarCuenta(int origen) {
        if (origen == 1) {
            vista.getJlgCuenta().setSize(575, 515);
            vista.getJlgCuenta().setLocationRelativeTo(vista);
            vista.getJlgCuenta().setVisible(true);
        }
    }

    private void registrarCliente(int origen) {
        if (origen == 1) {
            String ci = vista.getJtCi().getText();
            String nombres = vista.getJtNombres().getText();
            String apellidos = vista.getJtApellidos().getText();
            String correo = vista.getJtCorreo().getText();
            String telefono = vista.getJtTelefono().getText();
            String direccion = vista.getJtDireccion().getText();
            String discapacidad = vista.getJtDiscapacidad().getText();
            String contrasena = vista.getJtContrasena().getText();

            Instant instant = vista.getJdNacimientoCliente().getDate().toInstant();
            ZoneId zid = ZoneId.of("America/Guayaquil");
            ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
            Date fecha = Date.valueOf(zdt.toLocalDate());

            ConnectionCliente cli = new ConnectionCliente();
            cli.setCi(ci);
            cli.setNombres(nombres);
            cli.setApellidos(apellidos);
            cli.setCorreo(correo);
            cli.setTelefono(telefono);
            cli.setDireccion(direccion);
            cli.setDiscapacidad(discapacidad);
            cli.setEdad(fecha);
            cli.setContrasena(contrasena);

            if (cli.grabar()) {
                JOptionPane.showMessageDialog(vista, "Cliente Registrado Satisfactoriamente");
                vista.getJtCi().setText("");
                vista.getJtNombres().setText("");
                vista.getJtApellidos().setText("");
                vista.getJtCorreo().setText("");
                vista.getJtTelefono().setText("");
                vista.getJtDireccion().setText("");
                vista.getJtDiscapacidad().setText("");
                vista.getJtContrasena().setText("");
                vista.getJlgRegistro().setVisible(false);
            } else {
                JOptionPane.showMessageDialog(vista, "ERROR al Crear el Usuario, revise los Datos");
            }
        }
    }

    private void ingresarSistema(int origen) {
        if (origen == 1) {
            ConnectionCliente ci = new ConnectionCliente();
            List<Cliente> lista = ci.listarClientes();
            System.out.println(lista.size());
            for (int i = 0; i < lista.size(); i++) {
                String us = vista.getTxtUsuario().getText();
                String cont = vista.getTxtContrasena().getText();
                if (us.equals(lista.get(i).getCi()) && cont.equals(lista.get(i).getContrasena())) {
                    vista.getJlgIngreso().setVisible(false);
                    vista.getTxtUsuario().setText("");
                    vista.getTxtContrasena().setText("");
                    //Datos Cuenta
                    vista.getJlCi().setText(lista.get(i).getCi());
                    vista.getJlNombres().setText(lista.get(i).getNombres());
                    vista.getJlApellidos().setText(lista.get(i).getApellidos());
                    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
                    String datei = dt.format(lista.get(i).getEdad());
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fechaNac = LocalDate.parse(datei, fmt);
                    LocalDate ahora = LocalDate.now();
                    Period periodo = Period.between(fechaNac, ahora);
                    String datte = Integer.toString(periodo.getYears());
                    vista.getJlEdad().setText(datte);
                    vista.getJlTelefono().setText(lista.get(i).getTelefono());
                    vista.getJlCorreo().setText(lista.get(i).getCorreo());
                    vista.getJlDireccion().setText(lista.get(i).getDireccion());
                    vista.getJlDiscapacidad().setText(lista.get(i).getDiscapacidad());
                }else{
                    System.out.println(":(");
                }
            }
        }
    }
}
