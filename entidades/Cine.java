package entidades;

import java.util.ArrayList;

public class Cine {

    private String nombre;
    private int cantAsientos;
    private ArrayList<Funcion> cartelera = new ArrayList<>();;

    public Cine() {
    }

    public Cine(String nombre, int cantAsientos) {
        this.nombre = nombre;
        this.cantAsientos = cantAsientos;
    }

    public int getMaxId() {
        return cartelera.stream().mapToInt(Funcion::getId).max().orElse(-1);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantAsientos() {
        return cantAsientos;
    }

    public void setCantAsientos(int cantAsientos) {
        this.cantAsientos = cantAsientos;
    }

    public ArrayList<Funcion> getCartelera() {
        return cartelera;
    }

    public void setCartelera(ArrayList<Funcion> cartelera) {
        this.cartelera = cartelera;
    }

    public void addFuncion(Funcion funcion) {
        cartelera.add(funcion);
    }

}
