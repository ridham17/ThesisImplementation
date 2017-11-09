package tsm;

import java.util.HashSet;
import java.util.Set;

public class TransitionSystem {

    public Set<State> Q;
    public Set<String> sigma;
    public Set<Transition> transitionSet;
    public Set<State> I;
    public Set<State> F;

    public TransitionSystem(Set<State> q, Set<String> sigma, Set<State> i) {
        Q = q;
        this.sigma = sigma;
        I = i;
        transitionSet= new HashSet<Transition>();
        F = new HashSet<State>();
    }

    public TransitionSystem(Set<State> q, Set<String> sigma, Set<State> i, Set<State> f) {
        Q = q;
        this.sigma = sigma;
        I = i;
        F = f;
        transitionSet= new HashSet<Transition>();
    }

    public boolean addTransition(Transition transition)
    {
        if(Q.contains(transition.getFrom())&&Q.contains(transition.getTo())&&sigma.contains(transition.getLebel()))
        {
            for(Transition t:transitionSet)
            {
                if(t.equals(transition))
                    return false;
            }

            transitionSet.add(transition);
            return true;

        }
        else
            return false;
    }

    public boolean removeTransition(Transition transition)
    {
        for (Transition t: transitionSet) {
            if(t.isSameAs(transition)) {
                transitionSet.remove(t);
                return true;
            }
        }
        return  false;
    }

    @Override
    public String toString() {
        String finalString = "\n\nStates are";
        for (State s:Q)
            finalString+=" "+s.toString();

        finalString+="\n\nAlphabets are";
        for (String s:sigma)
            finalString+=" "+s;

        finalString+="\n\nInitial States are";
        for (State s:I)
            finalString+=" "+s.toString();

        finalString+="\n\nFinal States are";
        for (State s:F)
            finalString+=" "+s.toString();

        finalString+="\n\nTransitions are";
        for (Transition t :transitionSet)
            finalString+="\n"+t.toString();

        return finalString;
    }
}
