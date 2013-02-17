package real_time_scheduling_system.data_managment;

/**
 * Created with IntelliJ IDEA.
 * Creator: Valery Palamarchuk
 * Date: 17.02.13
 * Time: 21:03
 */
public class XmlException extends Exception {
    private static final long serialVersionUID = 1L;

    public XmlException(String message, Throwable cause)  {
        super(message, cause);
    }

    public XmlException(String message)  {
        super(message);
    }

    public XmlException(Throwable cause)  {
        super(cause);
    }
}