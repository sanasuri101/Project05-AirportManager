import java.io.Serializable;
import java.util.ArrayList;

/**
 * A handler for requests made to a countdown server.
 *
 * @author Sriram Anasuri sanasuri
 * @version 11/11/19 1.0
 */
public final class ReservationModel implements Serializable {

    private static final long serialVersionUID = -8193718403486079405L;
    private ArrayList<Reservation> reservationList;

    public ReservationModel() {
        this.reservationList = new ArrayList<>();
    }

    public boolean contains(Reservation aReservation) throws IllegalArgumentException {
        if (aReservation == null) {
            throw new IllegalArgumentException("aReservation argument is null");
        } else {
            for (Reservation reservation : this.reservationList) {
                if (reservationList.contains(aReservation)) {
                    return true;
                } //end if
            } //end for

            return false;
        } //end if
    } //contains

    public boolean add(Reservation aReservation) throws IllegalArgumentException {
        if (aReservation == null) {
            throw new IllegalArgumentException("aReservation argument is null");
        } else if (this.contains(aReservation)) {
            return false;
        } else {
            this.reservationList.add(aReservation);

            return true;
        } //end if
    } //add

    public boolean remove(Reservation aReservation) throws IllegalArgumentException {
        return this.reservationList.remove(aReservation);
    } //remove

    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    /*
    public Optional<Reservation> searchByFirstName(String firstName) {
        return this.ReservationList.stream()
                               .filter(Reservation -> (Reservation. () == null ? sku == null :
                                product.getSku().equalsIgnoreCase(sku)))
                               .findAny();
    } 
	*/
    @Override
    public String toString() {
        return (this.reservationList == null ? "ReservationModel[]" : String.format("ReservationModel%s",
                this.reservationList));
    } //toString
}