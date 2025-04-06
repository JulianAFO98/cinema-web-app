import { useEffect } from "react";
import { CineStore } from "../store/CineStore";



export const useCineHook = () => {

    const peliculas = CineStore(state => state.peliculas);
    const { id } = CineStore(state => state.filters);
    const fetchPeliculas = CineStore(state => state.fetchPeliculas);
    const setFilterId = CineStore(state => state.setFilterId);
    const reservarAsiento = CineStore(state => state.reservarAsiento);

    useEffect(() => {
        fetchPeliculas();
    }, [])

    console.log("Se refresca CineHook");
    const peliculaFiltrada = peliculas?.filter(pelicula => pelicula.id == id)[0];

    return { peliculas, peliculaFiltrada, setFilterId, reservarAsiento };
}