package domain.conta;

import domain.pessoa.Pessoa;
import domain.transacao.TipoTransacao;
import domain.transacao.Transacao;
import exception.SaldoInsuficienteException;
import exception.ValorNegativoException;

import java.time.LocalDate;

public class ContaCorrente extends Conta {

    private static final Double TAXA_SAQUE = 1.03;
    private static final Double TAXA_TRANSFERENCIA = 1.03;

    public ContaCorrente(Long numero, Pessoa titular) {
        super(numero, titular);
    }

    @Override
    public Double saque(Double valor) throws SaldoInsuficienteException, ValorNegativoException {
        verificaValorNegativo(valor);
        Double valorSaqueComJuros = valor * TAXA_SAQUE;

        verificaSaldo(valorSaqueComJuros);

        setSaldo(getSaldo() - valorSaqueComJuros);
        adicionaTransacao(new Transacao(LocalDate.now(), TipoTransacao.SAQUE, valorSaqueComJuros, 0d));
        aumentaScore(3);
        return valor;
    }

    @Override
    public void transferencia(Double valor, Conta contaDestino) throws SaldoInsuficienteException, ValorNegativoException {
        verificaValorNegativo(valor);
        Double valorTransferenciaComJuros = valor * TAXA_TRANSFERENCIA;

        verificaSaldo(valorTransferenciaComJuros);

        setSaldo(getSaldo() - valorTransferenciaComJuros);
        adicionaTransacao(new Transacao(LocalDate.now(), TipoTransacao.TRANSFERENCIA, valorTransferenciaComJuros, 0d));
        contaDestino.adicionaTransacao(new Transacao(LocalDate.now(), TipoTransacao.TRANSFERENCIA, 0d, valor));
        contaDestino.deposito(valor);
        aumentaScore(10);
    }
}
