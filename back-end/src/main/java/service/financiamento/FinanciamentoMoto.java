package service.financiamento;

import exception.ScoreMinimoException;

public class FinanciamentoMoto implements Financiamento {

    private Double valor;

    public FinanciamentoMoto(Double valor) {
        this.valor = valor;
    }

    @Override
    public Double calculaValorParcela(Integer quantidadeParcelas) {
        Double valorComJuros = valor * 1.2;
        return valorComJuros / quantidadeParcelas;
    }

    @Override
    public void validaScore(Integer score) throws ScoreMinimoException {
        if (score < 20) {
            throw new ScoreMinimoException();
        }
    }
}
