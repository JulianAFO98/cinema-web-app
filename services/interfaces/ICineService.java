package services.interfaces;

import java.time.LocalTime;

import entidades.Funcion;

public interface ICineService {
    public void llenarCartelera(String nombreArch);

    public void mostrarPeliculas();

    public void eliminarFuncion(int id);

    public void agregarFuncion(String nombre, int dia, LocalTime hora, float precio);

    public Funcion buscarFuncionPorId(int id);
}
