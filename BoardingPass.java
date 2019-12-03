import java.io.Serializable;

/**
 * The Boarding Pass is created for a specific passenger, airline, and gate. When printed,
 * it should contain the airline�s
 * name, passenger�s first and last names, the passenger�s age, and the gate.
 *
 * @author Sriram Anasuri sanasuri
 * @version 11/11/19 1.0
 */

public class BoardingPass implements Serializable {
    Passenger passenger;
    String airline;
    Gate gate;

    public BoardingPass(Passenger passenger, String airline, Gate gate) {
        this.passenger = passenger;
        this.airline = airline;
        this.gate = gate;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public String getAirline() {
        return airline;
    }

    public Gate getGate() {
        return gate;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    @Override
    public String toString() {
        return "BoardingPass [" + passenger + ", airline=" + airline + ", gate=" + gate.getGateNumber() + "]";
    }
}