import { create } from "zustand";
import { fetchAsiento, fetchPelis, insertarPeli } from "../logic/fetch";
import { CineState, FuncionData } from "../types";


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
        reservarAsiento: async (idPelicula: number, numeroAsiento: number) => { // Esta funcion es modificable para que reserve y quite reservas 
            const operacion = "reservar"
            const response = await fetchAsiento(idPelicula, numeroAsiento, operacion);
            const statePeliculas = get().peliculas;
            if (response && statePeliculas) {
                const nuevasPeliculas = structuredClone(statePeliculas); // Cambiable por maps si causa mal rendimiento => se necesita copia profunda para no alterar estado primario
                const indexPeliculaBuscada = statePeliculas?.findIndex(pelicula => pelicula.id == idPelicula);
                nuevasPeliculas[indexPeliculaBuscada].asientos[numeroAsiento - 1].ocupado = true;
                set({ peliculas: nuevasPeliculas });
            }
        },
        liberarAsiento: async (idPelicula: number, numeroAsiento: number) => {
            const operacion = "liberar"
            const response = await fetchAsiento(idPelicula, numeroAsiento, operacion);
            const statePeliculas = get().peliculas;
            if (response && statePeliculas) {
                const nuevasPeliculas = structuredClone(statePeliculas); // Cambiable por maps si causa mal rendimiento => se necesita copia profunda para no alterar estado primario
                const indexPeliculaBuscada = statePeliculas?.findIndex(pelicula => pelicula.id == idPelicula);
                nuevasPeliculas[indexPeliculaBuscada].asientos[numeroAsiento - 1].ocupado = false;
                set({ peliculas: nuevasPeliculas });
            }
        },
        insertarPelicula: async (funcion: FuncionData) => {
            const response = await insertarPeli(funcion);
            const prevPelis = get().peliculas;
            if (response && prevPelis) {
                const nuevasPelis = [...prevPelis, response.object];
                set((state) => ({ ...state, peliculas: nuevasPelis }));
            }
        }
    }
})