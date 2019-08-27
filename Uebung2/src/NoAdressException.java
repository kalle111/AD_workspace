public class NoAdressException extends Exception {
    public NoAdressException(){

    }

    public NoAdressException(String message){
        super(message);
    }

    public NoAdressException (Throwable cause) {
        super (cause);
    }

    public NoAdressException (String message, Throwable cause) {
        super (message, cause);
    }
}
