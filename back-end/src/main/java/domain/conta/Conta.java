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
        if (dataInicial == null) {
            return transacoes
                    .stream()
                    .filter(transacao -> !transacao.getData().isAfter(dataFinal))
                    .collect(Collectors.toList());
        }

        if (dataFinal == null) {
            return transacoes
                    .stream()
                    .filter(transacao -> !transacao.getData().isBefore(dataInicial))
                    .collect(Collectors.toList());
        }

        return transacoes
                .stream()
                .filter(transacao -> !transacao.getData().isBefore(dataInicial) && !transacao.getData().isAfter(dataFinal))
                .collect(Collectors.toList());
    }

    protected void verificaValorNegativo(Double valor) throws ValorNegativoException {
        if (valor < 0) {
            throw new ValorNegativoException("Não é possível realizar transação com valor negativo!");
        }
    }

    protected void verificaSaldo(Double valor) throws SaldoInsuficienteException {
        if (valor > getSaldo()) {
            throw new SaldoInsuficienteException("Saldo insuficiente para transação!");
        }
    }

    protected void setSaldo(Double novoSaldo) {
        saldo = novoSaldo;
    }

    protected void aumentaScore(Integer valor) {
        score += valor;
    }

    protected abstract Double saque(Double valor) throws SaldoInsuficienteException, ValorNegativoException;

    protected abstract void transferencia(Double valor, Conta contaDestino) throws SaldoInsuficienteException, ValorNegativoException;

    public Double getSaldo() {
        return saldo;
    }

    public Pessoa getTitular() {
        return titular;
    }
}
