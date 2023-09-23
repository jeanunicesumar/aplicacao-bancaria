package domain.documento;

import exception.CnpjInvalidoException;
import exception.DocumentoInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CnpjTest {

    @Test
    void deveRetornarUmCnpjValido() throws DocumentoInvalidoException {
        Cnpj cnpj = new Cnpj("97.328.763/0001-06");

        String cnpjValido = cnpj.getNumero();

        assertEquals("97.328.763/0001-06", cnpjValido);
    }

    @Test
    void deveRetornarUmaCnpjInvalidoException() {
        assertThrows(CnpjInvalidoException.class, () -> new Cnpj("97.328.763/0001-0"));
    }
}