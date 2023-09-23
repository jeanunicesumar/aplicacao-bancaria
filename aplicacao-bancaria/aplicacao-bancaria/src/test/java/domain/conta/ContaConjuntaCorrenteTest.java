package domain.conta;

import domain.documento.Cpf;
import domain.endereco.Endereco;
import domain.endereco.TipoImovel;
import domain.pessoa.PessoaFisica;
import exception.DocumentoInvalidoException;
import exception.VinculoEntrePessoasInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaConjuntaCorrenteTest {

    private PessoaFisica pessoa;

    private PessoaFisica pessoaDois;
    private ContaConjuntaCorrente conta;


    @BeforeEach
    void setup() throws DocumentoInvalidoException {
        pessoaDois = new PessoaFisica(
                "Jean",
                new Cpf("123.456.789-13"),
                new Endereco("Rua tal", TipoImovel.CASA, "12A", "123456-789", "Bairro tal", "Cidade tal", "PR"));
        pessoa = new PessoaFisica(
                "Jean Soares",
                new Cpf("123.456.789-10"),
                new Endereco("Rua tal", TipoImovel.CASA, "12A", "123456-789", "Bairro tal", "Cidade tal", "PR"));
    }

    @Test
    void deveCriarUmaContaConjuntaCorretamenteComConjuge() throws VinculoEntrePessoasInvalidoException {
        pessoa.casar(pessoaDois);
        ContaConjuntaCorrente conta = new ContaConjuntaCorrente(123L, pessoa, pessoaDois);


        assertEquals(pessoa, conta.getTitular());
        assertEquals(pessoaDois, conta.getSegundoTitular());
    }

    @Test
    void deveLancarUmVinculoEntrePessoasInvalidoException() {
        assertThrows(VinculoEntrePessoasInvalidoException.class, () -> new ContaConjuntaCorrente(123L, pessoaDois, pessoa));
    }

    @Test
    void deveCriarUmaContaConjuntaCorretamenteComFilho() throws VinculoEntrePessoasInvalidoException, DocumentoInvalidoException {
        PessoaFisica pessoaFilho = new PessoaFisica(
                "Jean",
                new Cpf("123.456.789-13"),
                new Endereco("Rua tal", TipoImovel.CASA, "12A", "123456-789", "Bairro tal", "Cidade tal", "PR"));

        pessoaDois.adicionaFilhos(pessoaFilho);

        ContaConjuntaCorrente conta = new ContaConjuntaCorrente(123L, pessoaDois, pessoaFilho);

        assertEquals(pessoaDois, conta.getTitular());
        assertEquals(pessoaFilho, conta.getSegundoTitular());
    }

    @Test
    void deveLancarUmaVinculoInvalidoExceptionAoCriarConjuntaComFilho() throws DocumentoInvalidoException {
        PessoaFisica pessoaFilho = new PessoaFisica(
                "Jean",
                new Cpf("123.456.789-13"),
                new Endereco("Rua tal", TipoImovel.CASA, "12A", "123456-789", "Bairro tal", "Cidade tal", "PR"));


        assertThrows(VinculoEntrePessoasInvalidoException.class, () -> new ContaConjuntaCorrente(123L, pessoaDois, pessoaFilho));
    }

}