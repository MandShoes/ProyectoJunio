package com.svalero.proyectojunio.domain;

public class Marca {

    private String nombre;
    private String descripcion;
    private String logo;
    private String direccion_sede;

    public Marca() {
    }

    public Marca(String nombre, String descripcion, String logo, String direccion_sede) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.logo = logo;
        this.direccion_sede = direccion_sede;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLogo() {
        return logo;
    }

    public String getDireccion_sede() {
        return direccion_sede;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setDireccion_sede(String direccion_sede) {
        this.direccion_sede = direccion_sede;
    }
}
