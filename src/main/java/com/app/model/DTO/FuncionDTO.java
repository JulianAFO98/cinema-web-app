package com.app.model.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FuncionDTO {

    @NotBlank(message = "El nombre de la pelicula no puede estar vacio")
    private String nombre;

    @Min(value = 1, message = "Dia fuera de rango")
    @Max(value = 31, message = "Dia fuera de rango")
    @NotNull(message = "El dia no puede ser nulo")
    private Integer dia;
    @NotBlank(message = "La hora ingresada no puede estar vacia")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Formato de hora inválido. Debe ser HH:mm")
    private String horaStr;
    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser mayor a 0")
    private float precio;
}
