package dm;

import tsm.TransitionSystem;

import java.util.Set;

public class Proc extends TransitionSystem {

    public Proc(int tsID, Set<String> sigma) {
        super(tsID, sigma);
    }

    public boolean isSameAs(Proc process)
    {
        if(process.getAlphabets().containsAll(sigma) && sigma.containsAll(process.getAlphabets()))
            return true;
        else 
            return false;
    }    
}

