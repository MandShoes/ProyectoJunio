package com.svalero.proyectojunio.domain;


import java.time.LocalDate;

public class Compra {

    private LocalDate fechaCompra;
    private boolean pagado;

    private Zapato zapato;

    private Usuario usuario;

    public Compra() {
    }

    public Compra(LocalDate fechaCompra, boolean pagado, Zapato zapato, Usuario usuario) {
        this.fechaCompra = fechaCompra;
        this.pagado = pagado;
        this.zapato = zapato;
        this.usuario = usuario;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public Zapato getZapato() {
        return zapato;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setZapato(Zapato zapato) {
        this.zapato = zapato;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public boolean isPagado() {
        return pagado;
    }
}
