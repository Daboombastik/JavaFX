package JavaFX.Calendar;

public class WrongDateException extends Exception{

    String msg;

    public WrongDateException (String msg) {
        super(msg);
        this.msg = msg;
    }
}
