package com.svalero.proyectojunio.domain;

public class Zapato {

    private int idZapato;
    private String modelo;
    private double numero;
    private String color;
    private String sexoZapato;
    private String descripcion;
    private double precio;
    private Marca marca;
    private Proveedor proveedor;

    public Zapato() {
    }

    public Zapato(String modelo, double numero, String color, String sexoZapato, String descripcion, double precio, Marca marca, Proveedor proveedor) {
        this.modelo = modelo;
        this.numero = numero;
        this.color = color;
        this.sexoZapato = sexoZapato;
        this.descripcion = descripcion;
        this.precio = precio;
        this.marca = marca;
        this.proveedor = proveedor;
    }

    public int getIdZapato() {
        return idZapato;
    }

    public void setIdZapato(int idZapato) {
        this.idZapato = idZapato;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSexoZapato() {
        return sexoZapato;
    }

    public void setSexoZapato(String sexoZapato) {
        this.sexoZapato = sexoZapato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}

