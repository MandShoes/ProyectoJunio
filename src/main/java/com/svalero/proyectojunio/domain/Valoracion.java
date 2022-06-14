package com.svalero.proyectojunio.domain;

import java.time.LocalDate;

public class Valoracion {

    private int cantidadEstrellas;
    private LocalDate fechaValoracion;
    private String descripcion;

    private Marca marca;

    private Zapato zapato;

    public Valoracion() {
    }

    public Valoracion(int cantidadEstrellas, LocalDate fechaValoracion, String descripcion, Marca marca, Zapato zapato) {
        this.cantidadEstrellas = cantidadEstrellas;
        this.fechaValoracion = fechaValoracion;
        this.descripcion = descripcion;
        this.marca = marca;
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

    public Marca getMarca() {
        return marca;
    }

    public Zapato getZapato() {
        return zapato;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
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
