package service.aplicacao;

public enum TipoAplicacao {

    TESOURO_DIRETO {
        @Override
        public Double aplicaTaxa(Double valor) {
            return valor * 1.05;
        }
    },
    FUNDO_IMOBILIARIO {
        @Override
        public Double aplicaTaxa(Double valor) {
            return valor * 1.07;
        }
    },
    MOEDAS {
        @Override
        public Double aplicaTaxa(Double valor) {
            return valor * 1.08;
        }
    },
    ACOES {
        @Override
        public Double aplicaTaxa(Double valor) {
            return valor * 1.1;
        }
    };

    public abstract Double aplicaTaxa(Double valor);

}
