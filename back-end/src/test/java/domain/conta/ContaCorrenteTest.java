package domain.conta;

import domain.documento.Cpf;
import domain.endereco.Endereco;
import domain.endereco.TipoImovel;
import domain.pessoa.Pessoa;
import domain.pessoa.PessoaFisica;
import domain.transacao.TipoTransacao;
import domain.transacao.Transacao;
import exception.DocumentoInvalidoException;
import exception.SaldoInsuficienteException;
import exception.ValorNegativoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContaCorrenteTest {

    private Pessoa pessoa;
    private ContaCorrente conta;


    @BeforeEach
    void setup() throws DocumentoInvalidoException {
        pessoa = new PessoaFisica(
                "Jean Soares",
                new Cpf("123.456.789-10"),
                new Endereco("Rua tal", TipoImovel.CASA, "12A", "123456-789", "Bairro tal", "Cidade tal", "PR"));
        conta = new ContaCorrente(1234L, pessoa);
    }

    @Test
    void deveRealizarUmDepositoCorretamente() throws ValorNegativoException {
        Double valorDeposito = 300d;

        conta.deposito(valorDeposito);

        assertEquals(valorDeposito, conta.getSaldo());
    }

    @Test
    void deveLancarUmaValorNegativoException() throws ValorNegativoException {
        assertThrows(ValorNegativoException.class, () -> conta.deposito(-10.0));
    }

    @Test
    void deveRealizarUmSaqueCorretamente() throws ValorNegativoException, SaldoInsuficienteException {
        Double valorDeposito = 300d;
        Double valorSaque = 150d;

        conta.deposito(valorDeposito);
        Double valorSacado = conta.saque(valorSaque);

        assertEquals(150d, valorSacado);
        assertEquals(145.5, conta.getSaldo());
    }

    @Test
    void deveLancarUmSaldoInsuficienteException() throws ValorNegativoException {
        Double valorDeposito = 300d;

        conta.deposito(valorDeposito);

        assertThrows(SaldoInsuficienteException.class, () -> conta.saque(310d));
    }

    @Test
    void deveRealizarUmPagamentoDeUmaConta() throws SaldoInsuficienteException, ValorNegativoException {
        Double valorDeposito = 300d;

        conta.deposito(valorDeposito);
        conta.pagamento(200d);

        assertEquals(100d, conta.getSaldo());
    }

    @Test
    void deveRealizarUmaTransferenciaDeUmaContaParaOutra() throws SaldoInsuficienteException, ValorNegativoException {
        ContaCorrente contaDois = new ContaCorrente(1233L, pessoa);
        Double valorDeposito = 300d;

        conta.deposito(valorDeposito);
        conta.transferencia(200d, contaDois);

        assertEquals(94d, conta.getSaldo());
        assertEquals(200d, contaDois.getSaldo());
    }

    @Test
    void deveRetornarUmExtratoPorPeriodo() throws SaldoInsuficienteException, ValorNegativoException {
        conta.deposito(300d);

        List<Transacao> transacoes = conta.extratoPorPeriodo(LocalDate.now().minusDays(3L), LocalDate.now().plusDays(3L));

        assertFalse(transacoes.isEmpty());
        assertEquals(TipoTransacao.DEPOSITO, transacoes.get(0).getTipoTransacao());

    }

}