package App.RestAPI.Infra.Exceptions;

public class InsufficientQuantityException extends RuntimeException{

    public InsufficientQuantityException() {
        super("Quantidade insuficiente para produção");
    }

    public InsufficientQuantityException(String message) {
        super(message);
    }
}
