package com.app.controllers;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.DTO.FuncionDTO;
import com.app.model.entidades.Asiento;
import com.app.model.entidades.Funcion;
import com.app.response.ApiResponse;
import com.app.services.impl.CineService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class CineController {

    private final CineService cineService;

    public CineController(CineService cineService) {
        this.cineService = cineService;
    }

    public void agregarDesdeArchivo(String nombreArch) {
        cineService.llenarCartelera();
    }

    /// FUNCION SECTION
    @PostMapping("/funcion")
    public ResponseEntity<?> agregarFuncion(@Valid @RequestBody FuncionDTO funcionDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors().stream().map(e -> e.getField() + ":" + e.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest()
                    .body(ApiResponse
                            .builder()
                            .message("Los campos no cumplen los requerimientos especificos")
                            .object(errores)
                            .build());
        }

        try {
            LocalTime hora = LocalTime.parse(funcionDto.getHoraStr());
            cineService.agregarFuncion(funcionDto, hora);
            return ResponseEntity.ok()
                    .body(ApiResponse.builder().message("Funcion agregada con exito").object(null).build());

        } catch (DateTimeParseException e) {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse
                            .builder()
                            .message("Formato de fecha invalido (esperado HH:mm)")
                            .object(null)
                            .build());
        }
    }

    @GetMapping("/funcion")
    public ResponseEntity<?> mostrarPeliculas() {
        List<Funcion> cartelera = cineService.obtenerPeliculas();
        if (cartelera.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok()
                .body(ApiResponse
                        .builder()
                        .message("Exito en la busqueda")
                        .object(cartelera)
                        .build());
    }

    @DeleteMapping("/funcion/{id}")
    public ResponseEntity<?> eliminarFuncion(@PathVariable Integer id) {

        if (cineService.eliminarFuncion(id)) {
            return ResponseEntity.ok()
                    .body(ApiResponse
                            .builder()
                            .message("Funcion eliminada")
                            .object(null)
                            .build());

        } else {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder().message("Error al eliminar funcion").object(id).build());
        }

    }

    //// ASIENTO SECTION ////
    @PostMapping("/funcion/{id}/asiento/{numAsiento}")
    public ResponseEntity<?> reservarAsiento(@PathVariable("id") Integer id,
            @PathVariable("numAsiento") Integer numAsiento) {
        Funcion funcion = cineService.buscarFuncionPorId(id);
        if (funcion == null) {
            return ResponseEntity.badRequest().body(ApiResponse
                    .builder()
                    .message("Id incorrecto, pelicula no encontrada")
                    .object(null)
                    .build());
        }

        if (!funcion.reservarAsiento(numAsiento)) {
            return ResponseEntity.badRequest().body(ApiResponse
                    .builder()
                    .message("El asiento ya está ocupado o esta fuera de rango")
                    .object(null)
                    .build());
        }
        return ResponseEntity.ok().body(ApiResponse
                .builder()
                .message("Asiento reservado")
                .object(null)
                .build());
    }

    public ResponseEntity<?> eliminarReservaAsiento(@PathVariable("id") Integer id,
            @PathVariable("numAsiento") Integer numAsiento) {
        Funcion funcion = cineService.buscarFuncionPorId(id);
        if (funcion == null) {
            return ResponseEntity.badRequest().body(ApiResponse
                    .builder()
                    .message("Id incorrecto, pelicula no encontrada")
                    .object(null)
                    .build());
        }

        if (!funcion.eliminarReserva(numAsiento)) {
            System.out.println("El asiento esta fuera de rango");
            return ResponseEntity.badRequest().body(ApiResponse
                    .builder()
                    .message("El asiento esta fuera de rango o ya esta desocupado")
                    .object(null)
                    .build());
        }
        return ResponseEntity.ok().body(ApiResponse
                .builder()
                .message("Reservar eliminada")
                .object(null)
                .build());
    }

    @GetMapping("/funcion/{id}/asientos")
    public ResponseEntity<?> verAsientosReservados(@PathVariable Integer id) {
        Funcion funcion = cineService.buscarFuncionPorId(id);
        if (funcion == null) {
            return ResponseEntity.badRequest().body(ApiResponse
                    .builder()
                    .message("Id incorrecto, pelicula no encontrada")
                    .object(null)
                    .build());
        }
        return ResponseEntity.ok().body(ApiResponse
                .builder()
                .message("Asientos de la funcion " + funcion.getNombreFuncion() + " Id:" + funcion.getId())
                .object(funcion.getAsientos())
                .build());
    }
}
