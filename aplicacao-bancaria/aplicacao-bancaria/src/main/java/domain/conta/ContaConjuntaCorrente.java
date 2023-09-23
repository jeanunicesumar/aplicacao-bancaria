package domain.conta;

import domain.pessoa.Pessoa;
import domain.pessoa.PessoaFisica;
import domain.pessoa.PessoaJuridica;
import domain.transacao.TipoTransacao;
import domain.transacao.Transacao;
import exception.SaldoInsuficienteException;
import exception.VinculoEntrePessoasInvalidoException;
import exception.ValorNegativoException;
import service.vinculo.ValidaVinculo;

import java.time.LocalDate;

public class ContaConjuntaCorrente extends ContaCorrente {

    private final Pessoa segundoTitular;
    private static final Double TAXA_SAQUE = 1.05;
    private static final Double TAXA_TRANSFERENCIA = 1.03;

    public ContaConjuntaCorrente(Long numero, PessoaFisica primeiroTitular, PessoaFisica segundoTitular) throws VinculoEntrePessoasInvalidoException {
        super(numero, primeiroTitular);
        this.segundoTitular = segundoTitular;
        ValidaVinculo.validaVinculoEntreTitulares(primeiroTitular, segundoTitular);
    }

    public ContaConjuntaCorrente(Long numero, Double saldo, PessoaJuridica primeiroTitular, PessoaJuridica segundoTitular) throws VinculoEntrePessoasInvalidoException {
        super(numero, primeiroTitular);
        this.segundoTitular = segundoTitular;
        ValidaVinculo.validaVinculoEntreTitulares(primeiroTitular, segundoTitular);
    }

    @Override
    public Double saque(Double valor) throws SaldoInsuficienteException, ValorNegativoException {
        verificaValorNegativo(valor);
        Double valorSaqueComJuros = valor * TAXA_SAQUE;

        verificaSaldo(valorSaqueComJuros);

        setSaldo(getSaldo() - valorSaqueComJuros);
        aumentaScore(3);
        adicionaTransacao(new Transacao(LocalDate.now(), TipoTransacao.SAQUE, valorSaqueComJuros, 0d));
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

    public Pessoa getSegundoTitular() {
        return segundoTitular;
    }
}
