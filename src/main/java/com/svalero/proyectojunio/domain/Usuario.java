package com.svalero.proyectojunio.domain;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String contrasena;
    private String email;
    private String direccion;

    public Usuario() {
    }

    public Usuario(String nombre, String contrasena, String email, String direccion) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
