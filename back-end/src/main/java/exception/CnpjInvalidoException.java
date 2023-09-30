package exception;

public class CnpjInvalidoException extends DocumentoInvalidoException {

    private final static String MESSAGE_EXCEPTION_CNPJ = "Número de cnpj inválido!";

    public CnpjInvalidoException() {
        super(MESSAGE_EXCEPTION_CNPJ);
    }

}
