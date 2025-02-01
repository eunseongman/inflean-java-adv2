package network.tcp.autocloserable;

public class CloseException extends Exception{

    public CloseException(String message) {
        super(message);
    }
}
