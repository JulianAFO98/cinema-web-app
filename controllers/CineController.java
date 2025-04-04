package controllers;

import java.time.LocalTime;
import java.util.Optional;

import Exception.FuncionNoEncontradaException;
import entidades.Asiento;
import entidades.Funcion;
import services.impl.CineService;

public class CineController {

    private final CineService cineService;

    public CineController(CineService cineService) {
        this.cineService = cineService;
    }

    public void agregarDesdeArchivo(String nombreArch) {
        cineService.llenarCartelera(nombreArch);
    }

    public void agregarFuncion(String nombre, int dia, String horaStr, float precio) {
        try {
            LocalTime hora = LocalTime.parse(horaStr);
            cineService.agregarFuncion(nombre, dia, hora, precio);
        } catch (Exception e) {
            System.out.println("Error: Formato de hora incorrecto. Use HH:MM");
        }
    }

    public void eliminarFuncion(int id) {
        try {
            cineService.eliminarFuncion(id);
        } catch (FuncionNoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarPeliculas() {
        cineService.mostrarPeliculas();
    }

    public void reservarAsiento(int id, int numAsiento) {
        Funcion funcion = cineService.buscarFuncionPorId(id);
        if (funcion == null) {
            System.out.println("La pelicula ingresada(Id) no se encontro");
            return;
        }

        if (!funcion.reservarAsiento(numAsiento)) {
            System.out.println("El asiento ya está ocupado o esta fuera de rango");
            return;
        }
        System.out.println("Asiento reservado con éxito");
    }

}
