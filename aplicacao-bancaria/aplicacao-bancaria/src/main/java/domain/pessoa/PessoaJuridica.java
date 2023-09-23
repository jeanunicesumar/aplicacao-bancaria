package domain.pessoa;

import domain.documento.Cpf;
import domain.endereco.Endereco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PessoaJuridica extends Pessoa {

    private ArrayList<PessoaJuridica> socios = new ArrayList<>();

    public PessoaJuridica(String nome, Cpf cpf, Endereco endereco) {
        super(nome, cpf, endereco);
    }

    public void adicionaSocio(PessoaJuridica socio) {
        socios.add(socio);
    }

    public void removeSocio(PessoaJuridica socio) {
        socios.remove(socio);
    }

    public List<PessoaJuridica> getSocios() {
        return Collections.unmodifiableList(socios);
    }

    @Override
    public Boolean validaVinculo(Pessoa pessoa) {
        return socios.contains(pessoa);
    }
}
