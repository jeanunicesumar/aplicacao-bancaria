package domain.transacao;

import java.time.LocalDate;

public class Transacao {

    private LocalDate data;

    private TipoTransacao tipoTransacao;

    private Double valorRetirado;

    private Double valorRecebido;

    public Transacao(LocalDate data, TipoTransacao tipoTransacao, Double valorRetirado, Double valorRecebido) {
        this.data = data;
        this.tipoTransacao = tipoTransacao;
        this.valorRetirado = valorRetirado;
        this.valorRecebido = valorRecebido;
    }

    public LocalDate getData() {
        return data;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public Double getValorRetirado() {
        return valorRetirado;
    }

    public Double getValorRecebido() {
        return valorRecebido;
    }
}
