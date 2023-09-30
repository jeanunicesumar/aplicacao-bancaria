package exception;

public class CpfInvalidoException extends DocumentoInvalidoException {

    private final static String MESSAGE_EXCEPTION_CNPJ = "Número de cpf inválido!";

    public CpfInvalidoException() {
        super(MESSAGE_EXCEPTION_CNPJ);
    }

}
