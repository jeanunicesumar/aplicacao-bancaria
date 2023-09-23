package domain.pessoa;

import domain.documento.Cpf;
import domain.endereco.Endereco;

import java.util.ArrayList;

public class PessoaFisica extends Pessoa {

    private PessoaFisica conjuge;
    private ArrayList<PessoaFisica> filhos = new ArrayList<>();

    public PessoaFisica(String nome, Cpf cpf, Endereco endereco) {
        super(nome, cpf, endereco);
    }

    public void casar(PessoaFisica conjuge) {
        this.conjuge = conjuge;
    }

    public void separar() {
        this.conjuge = null;
    }

    public void adicionaFilhos(PessoaFisica filho) {
        filhos.add(filho);
    }

    @Override
    public Boolean validaVinculo(Pessoa pessoa) {
        if (conjuge != null && conjuge.equals(pessoa)) {
            return true;
        }

        return filhos.contains(pessoa);
    }

    public PessoaFisica getConjuge() {
        return conjuge;
    }

    public ArrayList<PessoaFisica> getFilhos() {
        return filhos;
    }
}
