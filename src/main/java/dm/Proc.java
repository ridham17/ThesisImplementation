package dm;

import scala.Int;
import tsm.Stat;
import tsm.Transition;
import tsm.TransitionSystem;

import java.util.HashSet;
import java.util.Set;

public class Proc extends TransitionSystem {

    public Proc(int tsID, Set<String> sigma) {
        super(tsID, sigma);
    }

    public boolean isSameAs(Proc process)
    {
        if(tsID==process.getTsID() || (process.getAlphabets().containsAll(sigma) && sigma.containsAll(process.getAlphabets())))
            return true;
        else 
            return false;
    }

    public boolean containsAlphabet(String alph)
    {
        for (String aphabet:sigma)
            if(alph.equals(aphabet))
                return true;

        return false;
    }

    public void setStates(int n)
    {
        transitionSet = new HashSet<Transition>();
        linkedStates = new HashSet<Stat>();

        for (int i=0;i<n;i++) {
            Q.add(Integer.toString(i));
            linkedStates.add(new Stat(Integer.toString(i)));
        }

    }

    @Override
    public String toString() {
        String finalString = "\n\nProcess ID: " +tsID+ "\nAlphabets are ";
        for (String s:sigma)
            finalString+=" "+s;

        return finalString;
    }

    public String printTS()
    {
        return super.toString();
    }
}

