import dm.Distribution;
import tsm.Transition;
import tsm.TransitionSystem;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        //
        //System.out.print("Started Implementation");
        //System.out.print("Today Nov 9");

        HashSet<String> states = new HashSet<>();

        states.add("A");
        states.add("B");
        states.add("B");

        HashSet<String> alpb = new HashSet<>();
        alpb.add("0");
        alpb.add("1");

        HashSet<String> initStates = new HashSet<String>();
        initStates.add("A");

        HashSet<String> finalStates = new HashSet<String>();
        finalStates.add("B");


        TransitionSystem transitionSystem = new TransitionSystem(123,states,alpb,initStates,finalStates);

        assert transitionSystem.addTransition(new Transition("0","A","B"));
        assert transitionSystem.addTransition(new Transition("1","B","A"));
       // assert transitionSystem.addTransition(new Transition("1","B","A"));

        assert transitionSystem.checkValidity();
        System.out.print(transitionSystem);


        Distribution distribution = new Distribution();




    }
}
