export interface Asiento {
    ocupado: boolean,
    numero: number
}

export interface Funcion {
    id: string,
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