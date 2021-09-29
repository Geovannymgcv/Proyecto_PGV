/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
        private void cargarRegistrar (int origen){
            if (origen == 1) {
                vista.getJlgIngreso().setVisible(false);
                vista.getJlgRegistro().setSize(650,620);
                vista.getJlgRegistro().setLocationRelativeTo(vista);
                vista.getJlgRegistro().setVisible(true);
            }
        }
        private void cargarCuenta (int origen){
            if (origen == 1) {
                vista.getJlgCuenta().setSize(505, 515);
                vista.getJlgCuenta().setLocationRelativeTo(vista);
                vista.getJlgCuenta().setVisible(true);
            }
        }
}
