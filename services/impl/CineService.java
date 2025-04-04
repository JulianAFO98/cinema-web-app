package services.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import Exception.FuncionNoEncontradaException;
import entidades.Cine;
import entidades.Funcion;
import services.interfaces.ICineService;

public class CineService implements ICineService {

    private Cine cine; // En caso de api rest conectar con el DAO

    public CineService(Cine cine) {
        this.cine = cine;
    }

    @Override
    public void llenarCartelera(String nombreArch) { // CREATE ALL

        File archivo = new File(nombreArch + ".txt");

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
    public void mostrarPeliculas() {
        List<Funcion> cartelera = cine.getCartelera();
        if (!cartelera.isEmpty()) {
            for (Funcion funcion : cartelera) {
                System.out.println(funcion.toString());
            }
        } else {
            System.out.println("La cartelera esta vacia");
        }

    }

    @Override
    public void eliminarFuncion(int id) { // DELETE
        int originalSize = cine.getCartelera().size();
        cine.getCartelera().removeIf(funcion -> funcion.getId() == id);

        if (cine.getCartelera().size() != originalSize)
            System.out.println("Elemento eliminado correctamente");
        else {
            throw new FuncionNoEncontradaException(id);
        }

    }

    @Override
    public void agregarFuncion(String nombre, int dia, LocalTime hora, float precio) { // CREATE
        int id = cine.getMaxId() + 1;
        cine.addFuncion(new Funcion(id, nombre, dia, hora, cine.getCantAsientos(), precio));
    }

    @Override
    public Funcion buscarFuncionPorId(int id) {
        return cine.getCartelera().stream().filter(funcion -> funcion.getId() == id).findFirst().orElse(null);
    }

}
