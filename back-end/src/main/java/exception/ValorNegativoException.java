package exception;

public class ValorNegativoException extends Exception {

    private final static String MESSAGE_EXCEPTION_VALOR_NEGATIVO = "Não é possível realizar transação com valor negativo!";

    public ValorNegativoException() { super(MESSAGE_EXCEPTION_VALOR_NEGATIVO); }

}
