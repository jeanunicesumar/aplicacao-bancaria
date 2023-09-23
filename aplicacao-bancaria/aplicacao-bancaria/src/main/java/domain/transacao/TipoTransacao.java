package domain.transacao;

public enum TipoTransacao {

    SAQUE("Saque"),
    DEPOSITO("Deposito"),
    TRANSFERENCIA("Transferencia"),
    FINANCIAMENTO("Financiamento"),
    PAGAMENTO("Pagamento"),
    APLICACAO("Aplicacao");

    private final String descricao;

    TipoTransacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
