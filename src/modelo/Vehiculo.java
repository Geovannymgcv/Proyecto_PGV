/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author chusp
 */
public class Vehiculo {
    private int cilindraje;
    private String id_modelo;
    private String marca;
    private String nombre;
    private double precio_sin_iva;
    private String imagen;
    private String tipo_cambios;
    private String tipo;
    private String todoterreno;

    public Vehiculo() {
    }

    public Vehiculo(int cilindraje, String id_modelo, String marca, String nombre, double precio_sin_iva, String imagen, String tipo_cambios, String tipo, String todoterreno) {
        this.cilindraje = cilindraje;
        this.id_modelo = id_modelo;
        this.marca = marca;
        this.nombre = nombre;
        this.precio_sin_iva = precio_sin_iva;
        this.imagen = imagen;
        this.tipo_cambios = tipo_cambios;
        this.tipo = tipo;
        this.todoterreno = todoterreno;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(String id_modelo) {
        this.id_modelo = id_modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio_sin_iva() {
        return precio_sin_iva;
    }

    public void setPrecio_sin_iva(double precio_sin_iva) {
        this.precio_sin_iva = precio_sin_iva;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTipo_cambios() {
        return tipo_cambios;
    }

    public void setTipo_cambios(String tipo_cambios) {
        this.tipo_cambios = tipo_cambios;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTodoterreno() {
        return todoterreno;
    }

    public void setTodoterreno(String todoterreno) {
        this.todoterreno = todoterreno;
    }
    
}
