import { create } from "zustand";
import { fetchAsiento, fetchPelis } from "../logic/fetch";
import { CineState } from "../types";


export const CineStore = create<CineState>((set, get) => {
    return {
        peliculas: null,
        filters: {
            id: null,
        },
        fetchPeliculas: async () => {
            const pelis = await fetchPelis();
            set(state => ({ ...state, peliculas: pelis }))
        },
        setFilterId: (filmId: number) => {
            set(state => ({ ...state, filters: { ...state.filters, id: filmId } }));
        },
        reservarAsiento: async (idPelicula: number, numeroAsiento: number) => {
            const response = await fetchAsiento(idPelicula, numeroAsiento);
            const statePeliculas = get().peliculas;
            if (response && statePeliculas) {
                const nuevasPeliculas = structuredClone(statePeliculas); // Cambiable por maps si causa mal rendimiento
                const indexPeliculaBuscada = statePeliculas?.findIndex(pelicula => pelicula.id == idPelicula);
                nuevasPeliculas[indexPeliculaBuscada].asientos[numeroAsiento - 1].ocupado = true;
                set({ peliculas: nuevasPeliculas });
            }
        }
    }
})