package tsm;

import java.util.HashSet;
import java.util.Set;

public class TransitionSystem {

    public int tsID;
    public Set<State> Q;
    public Set<String> sigma;
    public Set<Transition> transitionSet;
    public Set<State> I;
    public Set<State> F;

    protected TransitionSystem(int tsID,Set<String> sigma)
    {
        this.tsID = tsID;
        this.sigma = sigma;
        Q = new HashSet<State>();
        I = new HashSet<State>();
        F = new HashSet<State>();
    }

    public TransitionSystem(int tsID,Set<State> q, Set<String> sigma, Set<State> i) {
        this.tsID = tsID;
        Q = q;
        this.sigma = sigma;
        I = i;
        transitionSet= new HashSet<Transition>();
        F = new HashSet<State>();
    }

    public TransitionSystem(int tsID,Set<State> q, Set<String> sigma, Set<State> i, Set<State> f) {
        this.tsID = tsID;
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
        String finalString = "\n\nTransaction Syatem Id:"+tsID+"\nStates are";
        for (State s:Q)
            finalString+=" "+s.toString();

        finalString+="\nAlphabets are";
        for (String s:sigma)
            finalString+=" "+s;

        finalString+="\nInitial States are";
        for (State s:I)
            finalString+=" "+s.toString();

        finalString+="\nFinal States are";
        for (State s:F)
            finalString+=" "+s.toString();

        finalString+="\n\nTransitions are";
        for (Transition t :transitionSet)
            finalString+="\n"+t.toString();

        return finalString;
    }
}
