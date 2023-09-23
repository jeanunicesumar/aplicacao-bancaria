package domain.documento;

import exception.DocumentoInvalidoException;

public interface Documento {

    String validaDocumento(String numero) throws DocumentoInvalidoException;

}
