package domain.conta;

import domain.pessoa.Pessoa;
import domain.pessoa.PessoaFisica;
import domain.pessoa.PessoaJuridica;
import domain.transacao.TipoTransacao;
import domain.transacao.Transacao;
import exception.SaldoInsuficienteException;
import exception.SaldoInvalidoContaPoupancaException;
import exception.VinculoEntrePessoasInvalidoException;
import exception.ValorNegativoException;
import service.vinculo.ValidaVinculo;

import java.time.LocalDate;

public class ContaConjuntaPoupanca extends ContaPoupanca {

    private final Pessoa segundoTitular;
    private static final Double TAXA_SAQUE = 1.05;

    public ContaConjuntaPoupanca(Long numero, Double saldo, PessoaFisica primeiroTitular, PessoaFisica segundoTitular) throws VinculoEntrePessoasInvalidoException, SaldoInvalidoContaPoupancaException, ValorNegativoException {
        super(numero, saldo, primeiroTitular);
        this.segundoTitular = segundoTitular;
        ValidaVinculo.validaVinculoEntreTitulares(primeiroTitular, segundoTitular);
    }

    public ContaConjuntaPoupanca(Long numero, Double saldo, PessoaJuridica primeiroTitular, PessoaJuridica segundoTitular) throws VinculoEntrePessoasInvalidoException, SaldoInvalidoContaPoupancaException, ValorNegativoException {
        super(numero, saldo, primeiroTitular);
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

    public Pessoa getSegundoTitular() {
        return segundoTitular;
    }
}
