import { Funcion } from "../types"





export function Cine({ peliculaFiltrada }: { peliculaFiltrada: Funcion | undefined }) {

    return (
        <div className="cine">
            <div className="contenedor-pantalla">
                <div className="pantalla"> <h2>Pantalla 😎</h2> </div>
            </div>
            <div className="asientos">
                {peliculaFiltrada && (
                    peliculaFiltrada.asientos.map((asiento, index) => {
                        return (
                            <div className={`asiento ${asiento.ocupado ? "red" : "green"}`} key={index}>{asiento.numero}</div>
                        )
                    })
                )
                }
            </div>
        </div>
    )
}