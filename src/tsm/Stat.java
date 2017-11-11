package tsm;

import java.util.HashSet;
import java.util.Set;

public class Stat {
    String label;
    Set<Transition> linkes;

    public Stat(String label) {
        this.label = label;
        linkes = new HashSet<>();
    }

    public String getLabel() {
        return label;
    }

    public Set<Transition> getLinkes() {
        return linkes;
    }

    public boolean addTransition(Transition transition)
    {
        if(label != transition.getFrom())
            return  false;

        return linkes.add(transition);
    }

    @Override
    public String toString() {
        String finalString = "\nState label: "+label+"\nLinks are:";
        for (Transition transition:linkes)
            finalString+="\n"+transition.toString();

        return finalString;
    }
}
