import java.util.InputMismatchException;
import java.util.Scanner;

import controllers.CineController;
import entidades.Cine;
import services.impl.CineService;
import validators.Validators;

public class Main {
    public static void main(String[] args) {

        Cine cine = new Cine("Gran Rex", 100);
        CineService cineService = new CineService(cine);
        CineController cineController = new CineController(cineService);

        cineController.agregarDesdeArchivo("archivo");

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        int id;

        do {
            mostrarOpciones();
            opcion = ingresarInputNumerico(scanner);

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre de la pelicula");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el dia de la funcion");
                    int dia = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese la hora de la funcion HH:MM");
                    String hora = scanner.nextLine();
                    System.out.println("Ingrese el precio de la entrada");
                    float precio = scanner.nextFloat();
                    cineController.agregarFuncion(nombre, dia, hora, precio);
                    break;
                case 2:
                    System.out.println("Ingrese el id de la pelicula a eliminar");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    cineController.eliminarFuncion(id);
                    break;
                case 3:
                    cineController.mostrarPeliculas();
                    break;
                case 4:
                    System.out.println("Ingrese el id de la pelicula a la cual hacer la reserva");
                    id = ingresarInputNumerico(scanner);
                    System.out.println("Ingrese el asiento que desea reservar");
                    int numAsiento = ingresarInputNumerico(scanner);
                    cineController.reservarAsiento(id, numAsiento);
                default:
                    System.out.println("Saliendo de la aplicacion");
                    break;
            }

        } while (opcion != 0);

        scanner.close();
    }

    public static void mostrarOpciones() {
        System.out.println("1-Agregar Pelicula");
        System.out.println("2-Eliminar Pelicula");
        System.out.println("3-Ver Peliculas disponibles");
        System.out.println("4-Reservar asiento");
        System.out.println("0-Salir del programa");
        System.out.println("Ingrese una Opcion");
    }

    public static int ingresarInputNumerico(Scanner scanner) {

        boolean entradaValida = false;
        int num = -1;
        try {
            while (!entradaValida) {
                num = scanner.nextInt();
                scanner.nextLine();
                entradaValida = true;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número válido.");
            scanner.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return num;
    }
}
