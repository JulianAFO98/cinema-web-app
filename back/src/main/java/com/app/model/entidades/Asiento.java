package com.app.model.entidades;

public class Asiento {

    private boolean ocupado;
    private int numero;

    public Asiento() {
        this.ocupado = false;
    }

    public Asiento(int numero) {
        this.ocupado = false;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public boolean getOcupado() {
        return ocupado;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    @Override
    public String toString() {
        return "Asiento numero " + numero + " esta " + (ocupado ? "ocupado" : "desocupado");
    }

}
