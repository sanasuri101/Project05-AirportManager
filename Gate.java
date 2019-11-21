import java.io.Serializable;
import java.util.ArrayList;

public class Gate implements Serializable {

    private String gateLetter;
    private String gateNumber;

    public Gate(){
        ArrayList<String> gateletter = new ArrayList<>();
        ArrayList<String> gatenumber = new ArrayList<>();
        int randgateletter = (int) Math.random()*3;

        for (int i = 1; i < 19; i++){
            gatenumber.add(Integer.toString(i));
        }

        int gatenum = (int)Math.random()*(18-1)+1;
        this.gateNumber= gatenumber.get(gatenum);
        gatenumber.remove(gatenum);
        gateletter.add("A");
        gateletter.add("B");
        gateletter.add("C");
        this.gateLetter = gateletter.get(randgateletter);
        gateletter.remove(randgateletter);
    }

    public String toString(){
        return gateLetter+gateNumber;
    }
}
