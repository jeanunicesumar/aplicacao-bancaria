package domain.documento;

import exception.CpfInvalidoException;
import exception.DocumentoInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CpfTest {

    @Test
    void deveRetornarUmCpfValido() throws DocumentoInvalidoException {
        Cpf cpf = new Cpf("123.456.678-23");

        String cnpjValido = cpf.getNumero();

        assertEquals("123.456.678-23", cnpjValido);
    }

    @Test
    void deveRetornarUmaCpfInvalidoException() {
        assertThrows(CpfInvalidoException.class, () -> new Cpf("123.456.678-2"));
    }
}