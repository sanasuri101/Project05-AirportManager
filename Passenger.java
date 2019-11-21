import java.io.File;
import java.io.Serializable;

public class Passenger implements Serializable {

    private String firstname;
    private String lastname;
    private int age;
    private Airline airline;

    public Passenger(String firstname, String lastname, int age, Airline airline){
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.airline = airline;
        //this.passengers = new ArrayList<>();

    }

    public String getFirstname() {
        return firstname.toUpperCase();
    }

    public String getLastname() {
        return lastname.toUpperCase();
    }

    public int getAge() {
        return age;
    }

    public Airline getAirline() {
        return airline;
    }

    @Override
    public String toString() {
        return getFirstname().charAt(0) + ". " + getLastname()+ ", " + getAge();
    }
}
