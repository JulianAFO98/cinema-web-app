package com.app.services.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.DTO.FuncionDTO;
import com.app.model.entidades.Cine;
import com.app.model.entidades.Funcion;
import com.app.services.interfaces.ICineService;

@Service
public class CineService implements ICineService {

    private Cine cine; // En caso de api rest conectar con el DAO

    @Autowired
    public CineService(Cine cine) {
        this.cine = cine;
        llenarCartelera(); // CAMBIAR
    }

    @Override
    public void llenarCartelera() { // CREATE ALL

        File archivo = new File("src\\main\\resources\\archivo.txt");

        if (!archivo.exists()) {
            System.out.println("El archivo " + archivo.getName() + " no existe.");
            return;
        }

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Funcion funcion = operarDatosDeLinea(linea);
                if (funcion != null) {
                    cine.addFuncion(funcion);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error de lectura del archivo: " + e.getMessage());
        }
    }

    private Funcion operarDatosDeLinea(String linea) {

        int id = 0;
        String nombreFunc = null;
        int dia = 0;
        LocalTime hora = null;
        float precio = 0;

        try {
            String[] datos = linea.split(",");
            id = Integer.parseInt(datos[0]);
            nombreFunc = datos[1];
            dia = Integer.parseInt(datos[2]);
            hora = LocalTime.parse(datos[3]);
            precio = Float.parseFloat(datos[4]);
            return new Funcion(id, nombreFunc, dia, hora, cine.getCantAsientos(), precio);
        } catch (NumberFormatException e) {
            System.out.println("Error en la conversion de datos enteros:" + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("La fecha tiene formato incorrecto: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en los datos de la línea: " + e.getMessage());
        }

        return null;

    }

    @Override
    public List<Funcion> obtenerPeliculas() {
        return cine.getCartelera();
    }

    @Override
    public boolean eliminarFuncion(Integer id) { // DELETE
        boolean eliminado = cine.getCartelera().removeIf(funcion -> id.equals(funcion.getId()));
        return eliminado;
    }

    @Override
    public Funcion agregarFuncion(FuncionDTO funcionDto, LocalTime hora) { // CREATE
        int id = cine.getMaxId() + 1;
        Funcion funcion = new Funcion(id, funcionDto.getNombre(), funcionDto.getDia(), hora,
                cine.getCantAsientos(), funcionDto.getPrecio());
        cine.addFuncion(funcion);
        return funcion;
    }

    @Override
    public Funcion buscarFuncionPorId(int id) {
        return cine.getCartelera().stream().filter(funcion -> funcion.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Funcion buscarFuncionPorNombre(String nombre) {
        return cine.getCartelera().stream().filter(funcion -> nombre.equals(funcion
                .getNombreFuncion())).findFirst()
                .orElse(null);
    }

}
