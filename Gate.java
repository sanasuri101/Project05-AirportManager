import java.io.Serializable;

/**
 * A class that implements Serializable. Use it to create Gates for flights in the form of [terminal][gate]
 * where terminal is a letter A,B,C and gate is a random number between 1 - 18, inclusive.
 * Note: Gates are associated with flights after the first passenger buys a ticket, and can be deleted after
 * the flight is full.
 *
 * @author Sriram Anasuri sanasuri
 * @version 11/11/19 1.0
 */
public class Gate implements Serializable {
    private Terminal terminal;
    private int gateNumber;
    private boolean isGateOpen;
    Flight flight;
    int ticketCount;

    public Gate(Terminal terminal, int gateNumber, Flight flight) {
        this.terminal = terminal;
        this.gateNumber = gateNumber;
        this.isGateOpen = true;
        this.flight = flight;
        this.ticketCount = 0;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public void incrementTicketCount() {
        if (ticketCount < flight.getPassengerCount())
            ticketCount++;
    }

    public boolean isGateOpen() {
        if (ticketCount == flight.getPassengerCount()) {
            isGateOpen = false;
        }
        return isGateOpen;
    }

    public Flight getFlight() {
        return flight;
    }

    @Override
    public String toString() {
        return "Gate [terminal=" + terminal + ", gateNumber=" + gateNumber + ",ticketCount=" + ticketCount
                + ", isGateOpen=" + isGateOpen + ", airLineName=" + flight.getAirLineName()
                + ",flight=" + flight.getFlightNumber()
                + "]";
    }

}
