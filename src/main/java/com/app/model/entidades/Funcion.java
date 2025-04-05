package com.app.model.entidades;

import java.time.LocalTime;
import java.util.ArrayList;

public class Funcion {

    private int id;
    private String nombreFuncion;
    private int dia;
    private LocalTime hora;
    private ArrayList<Asiento> asientos = new ArrayList<>();
    private float precioEntrada;

    public Funcion(int id, String nombreFuncion, int dia, LocalTime hora, int numAsientos, float precioEntrada) {
        this.id = id;
        this.nombreFuncion = nombreFuncion;
        this.dia = dia;
        this.hora = hora;
        this.precioEntrada = precioEntrada;
        generarAsientos(numAsientos);
    }

    void generarAsientos(int numAsientos) {

        for (int i = 0; i < numAsientos; i++) {
            asientos.add(new Asiento(i + 1));
        }

    }

    public String getNombreFuncion() {
        return nombreFuncion;
    }

    public void setNombreFuncion(String nombreFuncion) {
        this.nombreFuncion = nombreFuncion;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public ArrayList<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(ArrayList<Asiento> asientos) {
        this.asientos = asientos;
    }

    public float getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(float precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean reservarAsiento(int numero) {
        Asiento asiento = asientos.stream()
                .filter(a -> a.getNumero() == numero)
                .findFirst()
                .orElse(null);

        if (asiento == null || asiento.getOcupado()) {
            return false;
        }

        asiento.setOcupado(true);
        return true;
    }

    public boolean eliminarReserva(int numero) {
        Asiento asiento = asientos.stream()
                .filter(a -> a.getNumero() == numero)
                .findFirst()
                .orElse(null);

        if (asiento == null || !asiento.getOcupado()) {
            return false;
        }

        asiento.setOcupado(false);
        return true;
    }

    @Override
    public String toString() {
        return "Id " + id + "=> La pelicula " + nombreFuncion + " se dara el dia " + dia + " a las " + hora
                + " horas y su costo de entrada sera "
                + precioEntrada;
    }

}
