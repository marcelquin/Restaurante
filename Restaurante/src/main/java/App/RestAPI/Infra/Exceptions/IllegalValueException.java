package App.RestAPI.Infra.Exceptions;

public class IllegalValueException extends RuntimeException{

    public IllegalValueException() {
        super("Valor não correspondente, verifique os dados e tente novamente");
    }

    public IllegalValueException(String message) {
        super(message);
    }
}
