public class BoardingPass {

    private Passenger passenger;

    public BoardingPass(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "BOARDING PASS FOR FLIGHT 18000 WITH " + passenger.getAirline()
                + " Airlines\nPASSENGER FIRST NAME: " + passenger.getFirstname()
                + "\nPASSENGER LAST NAME: " + passenger.getLastname()
                + "\nPASSENGER AGE: " + passenger.getAge()
                + "\nYou can now begin boarding at gate " + passenger.getAirline().getGatetoString() ;
    }
}
