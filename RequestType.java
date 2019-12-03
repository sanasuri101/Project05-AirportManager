import java.io.Serializable;

/**
 * A handler for requests made to a countdown server.
 *
 * @author Sriram Anasuri sanasuri
 * @version 11/11/19 1.0
 */
public enum RequestType implements Serializable {
    EXIT,
    ADDPASSENGER,
    REFRESHFLIGHT,
    CUSTOMERLIST
}