package pm;

import tsm.State;
import tsm.TransitionSystem;

import java.util.Set;

public class Process extends TransitionSystem {
    public Process(Set<State> q, Set<String> sigma, Set<State> i) {
        super(q, sigma, i);
    }

    public Process(Set<State> q, Set<String> sigma, Set<State> i, Set<State> f) {
        super(q, sigma, i, f);
    }

}

