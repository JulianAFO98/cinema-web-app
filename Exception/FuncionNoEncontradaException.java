package Exception;

public class FuncionNoEncontradaException extends RuntimeException {
    public FuncionNoEncontradaException(int id) {
        super("No se encontro la funcion con ID " + id);
    }
}
