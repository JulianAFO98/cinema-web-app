package com.app.validators;

public class Validators {
    public static void validateInput(int opcion) {
        if (opcion < 0 || opcion > 6)
            System.err.println("Ingrese una opcion valida");
    }
}
