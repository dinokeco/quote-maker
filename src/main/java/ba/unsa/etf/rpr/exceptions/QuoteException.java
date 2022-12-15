package ba.unsa.etf.rpr.exceptions;

public class QuoteException extends Exception{

    public QuoteException(String msg, Exception reason){
        super(msg, reason);
    }

    public QuoteException(String msg){
        super(msg);
    }
}
