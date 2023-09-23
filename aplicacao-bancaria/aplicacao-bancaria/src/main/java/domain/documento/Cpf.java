package domain.documento;

import exception.CpfInvalidoException;
import exception.DocumentoInvalidoException;

public class Cpf implements Documento {

    private final String numero;

    public Cpf(String numero) throws DocumentoInvalidoException {
        this.numero = validaDocumento(numero);
    }

    @Override
    public String validaDocumento(String numero) throws DocumentoInvalidoException {
        String somenteNumeroCpf = numero.replaceAll("\\D", "");

        if (somenteNumeroCpf.length() != 11) {
            throw new CpfInvalidoException("Número de cpf inválido!");
        }

        return somenteNumeroCpf.substring(0, 3) + "." +
                somenteNumeroCpf.substring(3, 6) + "." +
                somenteNumeroCpf.substring(6, 9) + "-" +
                somenteNumeroCpf.substring(9);
    }

    public String getNumero() {
        return numero;
    }
}
