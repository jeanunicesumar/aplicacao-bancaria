package service.financiamento;

import exception.ScoreMinimoException;

public class FinanciamentoAutomovel implements Financiamento {

    private Double valor;

    public FinanciamentoAutomovel(Double valor) {
        this.valor = valor;
    }

    @Override
    public Double calculaValorParcela(Integer quantidadeParcelas) {
        Double valorComJuros = valor * 1.3;
        return valorComJuros / quantidadeParcelas;
    }

    @Override
    public void validaScore(Integer score) throws ScoreMinimoException {
        if (score < 40) {
            throw new ScoreMinimoException();
        }
    }
}
