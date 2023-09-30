package exception;

public class ScoreMinimoException extends Exception {

    private final static String MESSAGE_EXCEPTION_SCORE_MINIMO = "Score minimo para financiamento inválido!";

    public ScoreMinimoException() {
        super(MESSAGE_EXCEPTION_SCORE_MINIMO);
    }
}
