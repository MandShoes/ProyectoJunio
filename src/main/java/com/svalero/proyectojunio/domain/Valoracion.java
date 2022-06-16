package com.svalero.proyectojunio.domain;

import java.time.LocalDate;

public class Valoracion {

    private int cantidadEstrellas;
    private LocalDate fechaValoracion;
    private String descripcion;

    private Usuario usuario;

    private Zapato zapato;

    public Valoracion() {
    }

    public Valoracion(int cantidadEstrellas, LocalDate fechaValoracion, String descripcion, Usuario usuario, Zapato zapato) {
        this.cantidadEstrellas = cantidadEstrellas;
        this.fechaValoracion = fechaValoracion;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.zapato = zapato;
    }

    public Valoracion(Usuario usuario, Zapato zapato){
        this.usuario = usuario;
        this.zapato = zapato;
    }

    public int getCantidadEstrellas() {
        return cantidadEstrellas;
    }

    public LocalDate getFechaValoracion() {
        return fechaValoracion;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public Zapato getZapato() {
        return zapato;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setZapato(Zapato zapato) {
        this.zapato = zapato;
    }

    public void setCantidadEstrellas(int cantidadEstrellas) {
        this.cantidadEstrellas = cantidadEstrellas;
    }

    public void setFechaValoracion(LocalDate fechaValoracion) {
        this.fechaValoracion = fechaValoracion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
