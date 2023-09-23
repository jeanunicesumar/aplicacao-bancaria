package domain.pessoa;

import domain.documento.Documento;
import domain.endereco.Endereco;

public abstract class Pessoa {

    private String nome;

    private Documento documento;

    private Endereco endereco;

    public Pessoa(String nome, Documento documento, Endereco endereco) {
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
    }

    public abstract Boolean validaVinculo(Pessoa pessoa);

    public String getNome() {
        return nome;
    }

    public Documento getDocumento() {
        return documento;
    }
}
