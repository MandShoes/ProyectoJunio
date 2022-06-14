package com.svalero.proyectojunio.domain;

import java.time.LocalDate;

public class Valoracion {

    private int cantidadEstrellas;
    private LocalDate fechaValoracion;
    private String descripcion;

    public Valoracion() {
    }

    public Valoracion(int cantidadEstrellas, LocalDate fechaValoracion, String descripcion) {
        this.cantidadEstrellas = cantidadEstrellas;
        this.fechaValoracion = fechaValoracion;
        this.descripcion = descripcion;
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
