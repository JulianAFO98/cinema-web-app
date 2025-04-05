import { create } from "zustand";
import { fetchPelis } from "../logic/fetchPelis";
import { CineState } from "../types";


export const CineStore = create<CineState>((set) => {
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
        }
    }
})