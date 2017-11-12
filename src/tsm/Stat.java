package tsm;

import java.util.HashSet;
import java.util.Set;

public class Stat {
    String label;
    Set<Transition> linkes;
    boolean processStatus;

    public Stat(String label) {
        this.label = label;
        linkes = new HashSet<>();
        processStatus = false;
    }

    public String getLabel() {
        return label;
    }

    public Set<Transition> getLinkes() {
        return linkes;
    }

    public boolean getProcessStatus() {
        return processStatus;
    }

    public void resetProcessedStatus()
    {
        processStatus = false;
    }

    public void setProcessedStatus()
    {
        processStatus = true;
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
