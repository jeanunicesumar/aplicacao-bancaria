package domain.documento;

import exception.CnpjInvalidoException;
import exception.DocumentoInvalidoException;

public class Cnpj implements Documento {

    private final String numero;

    public Cnpj(String numero) throws DocumentoInvalidoException {
        this.numero = validaDocumento(numero);
    }

    @Override
    public String validaDocumento(String numero) throws DocumentoInvalidoException {
        String somenteNumeroCnpj = numero.replaceAll("\\D", "");

        if (somenteNumeroCnpj.length() != 14) {
            throw new CnpjInvalidoException();
        }

        return somenteNumeroCnpj.substring(0, 2) + "." +
                somenteNumeroCnpj.substring(2, 5) + "." +
                somenteNumeroCnpj.substring(5, 8) + "/" +
                somenteNumeroCnpj.substring(8, 12) + "-" +
                somenteNumeroCnpj.substring(12);
    }

    public String getNumero() {
        return numero;
    }
}
