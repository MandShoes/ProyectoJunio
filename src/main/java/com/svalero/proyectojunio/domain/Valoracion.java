package com.svalero.proyectojunio.domain;

import java.time.LocalDate;
import java.util.Date;

public class Valoracion {

    private int cantidadEstrellas;
    private Date fechaValoracion;
    private String descripcion;

    private Usuario usuario;

    private Zapato zapato;

    public Valoracion() {
    }

    public Valoracion(int cantidadEstrellas, Date fechaValoracion, String descripcion, Usuario usuario, Zapato zapato) {
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

    public Date getFechaValoracion() {
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

    public void setFechaValoracion(Date fechaValoracion) {
        this.fechaValoracion = fechaValoracion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
