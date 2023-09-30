package domain.conta;

import domain.pessoa.Pessoa;
import domain.transacao.TipoTransacao;
import domain.transacao.Transacao;
import exception.SaldoInsuficienteException;
import exception.ScoreMinimoException;
import exception.ValorNegativoException;
import service.aplicacao.TipoAplicacao;
import service.financiamento.Financiamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Conta {

    private static final Double TAXA_TRANSFERENCIA = 1.03;

    private Long numero;
    private Double saldo;
    private Integer score = 0;
    private Pessoa titular;
    private ArrayList<Transacao> transacoes = new ArrayList<>();

    public Conta(Long numero, Pessoa titular) {
        this.numero = numero;
        this.saldo = 0d;
        this.titular = titular;
    }

    public void deposito(Double valor) throws ValorNegativoException {
        verificaValorNegativo(valor);
        this.saldo += valor;
        aumentaScore(5);
        adicionaTransacao(new Transacao(LocalDate.now(), TipoTransacao.DEPOSITO, 0d, valor));
    }

    public void financiamento(Financiamento financiamento, Integer parcelas) throws ScoreMinimoException {
        financiamento.validaScore(score);
        Double valor = financiamento.calculaValorParcela(parcelas);
        adicionaTransacao(new Transacao(LocalDate.now(), TipoTransacao.FINANCIAMENTO, 0d, valor));
    }

    public void pagamento(Double valorDaConta) throws SaldoInsuficienteException {
        verificaSaldo(valorDaConta);
        saldo -= valorDaConta;
        adicionaTransacao(new Transacao(LocalDate.now(), TipoTransacao.PAGAMENTO, valorDaConta, 0d));
    }

    public void aplicarValor(TipoAplicacao aplicacao, Double valor) throws ValorNegativoException, SaldoInsuficienteException {
        verificaValorNegativo(valor);
        Double valorAplicacaoComTaxa = aplicacao.aplicaTaxa(valor);
        verificaSaldo(valorAplicacaoComTaxa);
        adicionaTransacao(new Transacao(LocalDate.now(), TipoTransacao.APLICACAO, valorAplicacaoComTaxa, 0d));
        saldo -= valorAplicacaoComTaxa;
    }

    protected void adicionaTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public List<Transacao> extratoPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        return transacoes
                .stream()
                .filter(transacao -> !transacao.getData().isBefore(dataInicial) && !transacao.getData().isAfter(dataFinal))
                .collect(Collectors.toList());
    }

    protected void verificaValorNegativo(Double valor) throws ValorNegativoException {
        if (valor < 0) {
            throw new ValorNegativoException();
        }
    }

    protected void verificaSaldo(Double valor) throws SaldoInsuficienteException {
        if (valor > getSaldo()) {
            throw new SaldoInsuficienteException();
        }
    }

    protected void setSaldo(Double novoSaldo) {
        saldo = novoSaldo;
    }

    protected void aumentaScore(Integer valor) {
        score += valor;
    }

    protected abstract Double saque(Double valor) throws SaldoInsuficienteException, ValorNegativoException;

    public Double getSaldo() {
        return saldo;
    }

    public Pessoa getTitular() {
        return titular;
    }

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
