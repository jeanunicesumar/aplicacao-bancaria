package exception;

public class SaldoInvalidoContaPoupancaException extends Exception {

    private final static String MESSAGE_POUPANCA_SALDO_INVALIDO = "Conta poupança deve ter um saldo mínimo de R$50,00";

    public SaldoInvalidoContaPoupancaException() {
        super(MESSAGE_POUPANCA_SALDO_INVALIDO);
    }

}
