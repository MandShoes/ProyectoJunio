package com.svalero.proyectojunio.domain;

public class Marca {

    private int idMarca;
    private String nombre;
    private String descripcion;
    private String logo;
    private String direccionSede;

    public Marca() {
    }

    public Marca(int idMarca, String nombre, String descripcion, String logo, String direccionSede) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.logo = logo;
        this.direccionSede = direccionSede;
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

    public String getDireccionSede() {
        return direccionSede;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
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

    public void setDireccionSede(String direccionSede) {
        this.direccionSede = direccionSede;
    }
}
