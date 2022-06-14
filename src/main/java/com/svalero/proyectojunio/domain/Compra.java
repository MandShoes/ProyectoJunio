package com.svalero.proyectojunio.domain;

import jdk.vm.ci.meta.Local;

import java.time.LocalDate;

public class Compra {

    private LocalDate fechaCompra;
    private boolean pagado;

    public Compra() {
    }

    public Compra(LocalDate fechaCompra, boolean pagado) {
        this.fechaCompra = fechaCompra;
        this.pagado = pagado;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public boolean isPagado() {
        return pagado;
    }
}
