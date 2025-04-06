package com.app.services.interfaces;

import java.time.LocalTime;
import java.util.List;

import com.app.model.DTO.FuncionDTO;
import com.app.model.entidades.Funcion;

public interface ICineService {
    public void llenarCartelera();

    public List<Funcion> obtenerPeliculas();

    public boolean eliminarFuncion(Integer id);

    public Funcion agregarFuncion(FuncionDTO funcionDto, LocalTime hora);

    public Funcion buscarFuncionPorId(int id);

    public Funcion buscarFuncionPorNombre(String nombre);
}
