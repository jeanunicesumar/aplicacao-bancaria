package domain.endereco;

public class Endereco {

    private String logradouro;
    private TipoImovel tipoImovel;
    private String numero;
    private String cep;
    private String bairro;
    private String cidade;
    private String uf;

    public Endereco(String logradouro, TipoImovel tipoImovel, String numero, String cep, String bairro, String cidade, String uf) {
        this.logradouro = logradouro;
        this.tipoImovel = tipoImovel;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }
}
