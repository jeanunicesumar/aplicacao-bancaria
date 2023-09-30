package exception;

public class SaldoInsuficienteException extends Exception {

    private final static String MESSAGE_EXCEPTION_SALDO_INSUFICIENTE = "Saldo insuficiente para transação!";

    public SaldoInsuficienteException() { super(MESSAGE_EXCEPTION_SALDO_INSUFICIENTE); }

}
