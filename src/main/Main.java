/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controlador.Controlador;
import modelo.ConnectionCliente;
import vista.VistaGeneral;

/**
 *
 * @author chusp
 */
public class Main {
    public static void main(String[] args) {
        ConnectionCliente m = new ConnectionCliente();
        VistaGeneral v = new VistaGeneral();
        Controlador c = new Controlador(m, v);
        c.iniciaControl();
    }
}
