package tsm;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.HashSet;
import java.util.Set;

public class TransitionSystem {

    public int tsID;
    public Set<String> Q;
    public Set<Stat> linkedStates;
    public Set<String> sigma;
    public Set<Transition> transitionSet;
    public Set<String> I;
    public Set<String> F;

    protected TransitionSystem(int tsID, Set<String> sigma)
    {
        this.tsID = tsID;
        this.sigma = sigma;
        Q = new HashSet<String>();
        I = new HashSet<String>();
        F = new HashSet<String>();
        linkedStates = new HashSet<Stat>();
        for (String stateName:Q)
            linkedStates.add(new Stat(stateName));

    }

    public TransitionSystem(int tsID,Set<String> q, Set<String> sigma, Set<String> i) {
        this.tsID = tsID;
        Q = q;
        this.sigma = sigma;
        I = i;
        transitionSet= new HashSet<Transition>();
        F = new HashSet<String>();
        linkedStates = new HashSet<Stat>();
        for (String stateName:Q)
            linkedStates.add(new Stat(stateName));
    }

    public TransitionSystem(int tsID,Set<String> q, Set<String> sigma, Set<String> i, Set<String> f) {
        this.tsID = tsID;
        Q = q;
        this.sigma = sigma;
        I = i;
        F = f;
        transitionSet= new HashSet<Transition>();
        linkedStates = new HashSet<Stat>();
        for (String stateName:Q)
            linkedStates.add(new Stat(stateName));
    }


    public int getTsID() {
        return tsID;
    }

    public Set<String> getStateSpace() {
        return Q;
    }

    public Set<Stat> getLinkedStates() {
        return linkedStates;
    }

    public Stat getLinkedState(String stateName) {
        for (Stat state:linkedStates)
            if(state.getLabel().equals(stateName))
                return state;

        return  null;
    }

    public Set<String> getAlphabets() {
        return sigma;
    }

   /* public  boolean containsAplhabet(String aplh)
    {
        for (String a:sigma)
            if(a.equals(aplh))
            {
                System.out.println("H I "+a);
                return true;
            }
        System.out.println("H I X");
        return false;

    }
*/
    public Set<Transition> getTransitionSet() {
        return transitionSet;
    }

    public Set<String> getInitStates() {
        return I;
    }

    public Set<String> getFinalStates() {
        return F;
    }

    public boolean addTransition(Transition transition)
    {
        if(Q.contains(transition.getFrom())&&
                Q.contains(transition.getTo())&&
                sigma.contains(transition.getLebel()))
        {

            for(Transition t:transitionSet)
            {
                if(t.equals(transition))
                    return false;
            }



            for (Stat state:linkedStates)
                if(state.getLabel().equals(transition.getFrom()))
                {
                    transitionSet.add(transition);
//                    System.out.println(state);
  //                  System.out.println(transition);
                    assert  state.addTransition(transition);
                    return true;
                }
        }


        return false;
    }
/*
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
*/

    public boolean containsTransition(Transition transition)
    {
        for(Transition t:transitionSet)
        {
            if(t.equals(transition))
                return true;
        }

        return false;
    }

    public String getTrans(String from,String to)
    {
        for(Transition t:transitionSet)
            if(t.getFrom().equals(from)&&t.getTo().equals(to))
                return t.getLebel();

        return null;
    }

    public Set<Transition> getAllTransitionWithLabel(String label)
    {
        Set<Transition> simTrans = new HashSet<Transition>();

        for (Transition transition:transitionSet)
            if(transition.getLebel().equals(label))
                simTrans.add(transition);

        return simTrans;
    }

    public boolean checkValidity()
    {
        for (String state:I) {
            if (!Q.contains(state))
                return false;
        }

        for (String state:F){
            if (!Q.contains(state))
                return false;
        }

        return true;
    }


    public void displayTranSys()
    {

        Graph graph = new MultiGraph(Integer.toString(tsID));

        for (String state:Q )
            graph.addNode(state);

        int i=0;
        for(Transition transition:transitionSet)
        {
            graph.addEdge(transition.getLebel()+i,transition.getFrom(),transition.getTo(),true);
            i++;
        }

        graph.display();

        for (Node node : graph) {
            node.addAttribute("ui.label","      "+node.getId());
        }

        for (Edge edge: graph.getEdgeSet())
            edge.addAttribute("ui.label", "      "+edge.getId().replaceAll("[\\d.]", ""));

        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");



    }

    @Override
    public String toString() {
        String finalString = "\n\nTransaction Syatem Id:"+tsID+"\nStates are";
        for (String s:Q)
            finalString+=" "+s.toString();

        finalString+="\nAlphabets are";
        for (String s:sigma)
            finalString+=" "+s;

        finalString+="\nInitial states are";
        for (String s:I)
            finalString+=" "+s.toString();

        if(!F.isEmpty()) {
            finalString += "\nFinal states are";
            for (String s : F)
                finalString += " " + s.toString();
        }

        finalString+="\n\nTransitions are";
        for (Transition t :transitionSet)
            finalString+="\n"+t.toString();

/*
        finalString+="\n\nLinkedStates are";
        for (Stat t :linkedStates)
            finalString+="\n"+t.toString();
*/


        return finalString;
    }


}
