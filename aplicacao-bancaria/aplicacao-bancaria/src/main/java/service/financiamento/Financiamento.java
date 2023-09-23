package service.financiamento;

import exception.ScoreMinimoException;

public interface Financiamento {

    public Double calculaValorParcela(Integer parcela);

    public void validaScore(Integer score) throws ScoreMinimoException;

}
