package pm;

import tsm.State;
import tsm.TransitionSystem;

import java.util.Set;

public class Process extends TransitionSystem {

    protected Process(int tsID, Set<String> sigma) {
        super(tsID, sigma);
    }

}

