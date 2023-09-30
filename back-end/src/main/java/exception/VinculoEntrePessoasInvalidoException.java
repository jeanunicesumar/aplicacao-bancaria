package exception;

public class VinculoEntrePessoasInvalidoException extends Exception {

    private final static String MESSAGE_EXCEPTION_VINCULO_PESSOAS = "Vinculo entre pessoas inválido!";

    public VinculoEntrePessoasInvalidoException() { super(MESSAGE_EXCEPTION_VINCULO_PESSOAS); }

}
