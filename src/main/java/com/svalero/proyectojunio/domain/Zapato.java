package com.svalero.proyectojunio.domain;

public class Zapato {

    private String modelo;
    private double numero;
    private String color;
    private String sexo_zapato;
    private String descripcion;
    private double precio;

    public Zapato() {
    }

    public Zapato(String modelo, double numero, String color, String sexo_zapato, String descripcion, double precio) {
        this.modelo = modelo;
        this.numero = numero;
        this.color = color;
        this.sexo_zapato = sexo_zapato;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getModelo() {
        return modelo;
    }

    public double getNumero() {
        return numero;
    }

    public String getColor() {
        return color;
    }

    public String getSexo_zapato() {
        return sexo_zapato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSexo_zapato(String sexo_zapato) {
        this.sexo_zapato = sexo_zapato;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
