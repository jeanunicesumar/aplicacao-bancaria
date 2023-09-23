package service.vinculo;

import domain.pessoa.Pessoa;
import exception.VinculoEntrePessoasInvalidoException;

public class ValidaVinculo {

    public static void validaVinculoEntreTitulares(Pessoa pessoaUm, Pessoa pessoaDois) throws VinculoEntrePessoasInvalidoException {
        if (!pessoaUm.validaVinculo(pessoaDois)) {
            throw new VinculoEntrePessoasInvalidoException("Vinculo entre pessoas inválido!");
        }
    }

}
