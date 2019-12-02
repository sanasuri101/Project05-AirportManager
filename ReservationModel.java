import java.io.Serializable;
import java.util.ArrayList;

public final class ReservationModel implements Serializable{

	private static final long serialVersionUID = -8193718403486079405L;
	private ArrayList<Reservation> ReservationList;

    public ReservationModel() {
        this.ReservationList = new ArrayList<>();
    }

    public boolean contains(Reservation aReservation) throws IllegalArgumentException {
        if (aReservation == null) {
            throw new IllegalArgumentException("aReservation argument is null");
        } else {
            for (Reservation Reservation : this.ReservationList) {
            	if(ReservationList.contains(aReservation)) {
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
            this.ReservationList.add(aReservation);

            return true;
        } //end if
    } //add

    public boolean remove(Reservation aReservation) throws IllegalArgumentException {
        return this.ReservationList.remove(aReservation);
    } //remove

    public ArrayList<Reservation> getReservationList()
    {
    	return ReservationList;
    }
    /*
    public Optional<Reservation> searchByFirstName(String firstName) {
        return this.ReservationList.stream()
                               .filter(Reservation -> (Reservation. () == null ? sku == null : product.getSku().equalsIgnoreCase(sku)))
                               .findAny();
    } 
	*/
    @Override
    public String toString() {
        return (this.ReservationList == null ? "ReservationModel[]" : String.format("ReservationModel%s", this.ReservationList));
    } //toString
}