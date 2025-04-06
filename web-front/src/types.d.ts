export interface Asiento {
    ocupado: boolean,
    numero: number
}

export interface Funcion extends FuncionData {
    id: number,
    asientos: Asiento[]
}

export interface FuncionData {
    nombre: string,
    horaStr: string,
    dia: number,
    precio: number
}

export interface FetchAllApiResponse {
    message: string,
    object: Funcion[]
}
export interface FetchOneApiResponse {
    message: string,
    object: Funcion
}


export interface CineState {
    peliculas: Funcion[] | null,
    filters: Filters,
    fetchPeliculas: () => Promise<void>;
    setFilterId: (filmId: number) => void;
    reservarAsiento: (idPelicula: number, numeroAsiento: number) => void;
    liberarAsiento: (idPelicula: number, numeroAsiento: number) => void;
    insertarPelicula: (funcion: FuncionData) => void;
}


export interface Filters {
    id: number | null,
}

interface CineProps {
    peliculaFiltrada: Funcion | undefined,
    reservarAsiento: (idPelicula: number, numeroAsiento: number) => void;
    liberarAsiento: (idPelicula: number, numeroAsiento: number) => void;
}