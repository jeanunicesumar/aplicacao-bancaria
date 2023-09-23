package service.financiamento;

import exception.ScoreMinimoException;

public class FinanciamentoImovel implements Financiamento {

    private Double valor;

    public FinanciamentoImovel(Double valor) {
        this.valor = valor;
    }

    @Override
    public Double calculaValorParcela(Integer quantidadeParcelas) {
        Double valorComJuros = valor * 1.6;
        return valorComJuros / quantidadeParcelas;
    }

    @Override
    public void validaScore(Integer score) throws ScoreMinimoException {
        if (score < 70) {
            throw new ScoreMinimoException("Score minimo para financiamento inválido!");
        }
    }
}
