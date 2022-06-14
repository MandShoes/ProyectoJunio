package com.svalero.proyectojunio.domain;

public class Proveedor {

    private String nombre;
    private  String direccion;
    private String email;
    private String cif;
    private String pais;

    public Proveedor() {
    }

    public Proveedor(String nombre, String direccion, String email, String cif, String pais) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.cif = cif;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getCif() {
        return cif;
    }

    public String getPais() {
        return pais;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
