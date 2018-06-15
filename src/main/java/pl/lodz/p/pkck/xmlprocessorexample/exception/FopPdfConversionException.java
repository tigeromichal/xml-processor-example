package pl.lodz.p.pkck.xmlprocessorexample.exception;

public class FopPdfConversionException extends Exception {

    public FopPdfConversionException() {
        super();
    }

    public FopPdfConversionException(String message) {
        super(message);
    }

    public FopPdfConversionException(String message, Throwable cause) {
        super(message == null ? cause.getCause().getMessage() : message, cause);
    }

}
