package com.svalero.proyectojunio.domain;

public class Zapato {

    private int idZapato;
    private String modelo;
    private double numero;
    private String color;
    private String sexoZapato;
    private String descripcion;
    private double precio;

    public Zapato() {
    }

    public Zapato(int idZapato, String modelo, double numero, String color, String sexoZapato, String descripcion, double precio) {
        this.idZapato = idZapato;
        this.modelo = modelo;
        this.numero = numero;
        this.color = color;
        this.sexoZapato = sexoZapato;
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

    public String getSexoZapato() {
        return sexoZapato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getIdZapato() {
        return idZapato;
    }

    public void setIdZapato(int idZapato) {
        this.idZapato = idZapato;
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

    public void setSexoZapato(String sexoZapato) {
        this.sexoZapato = sexoZapato;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
