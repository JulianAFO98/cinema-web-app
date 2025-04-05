export interface Asiento {
    ocupado: boolean,
    numero: number
}

export interface Funcion {
    id: number,
    nombreFuncion: string,
    hora: string,
    dia: number,
    asientos: Asiento[]
    precio: number
}

export interface ApiResponse {
    message: string,
    object: Funcion[]
}

export interface CineState {
    peliculas: Funcion[] | null,
    filters: Filters,
    fetchPeliculas: () => Promise<void>;
    setFilterId: (filmId: number) => void;
}


export interface Filters {
    id: number | null,
}