import pm.Process;
import tsm.State;
import tsm.Transition;
import tsm.TransitionSystem;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        //System.out.print("Started Implementation");
        //System.out.print("Today Nov 9");
/*
        Set<tsm.Transition> set = new HashSet<tsm.Transition>();
        set.add(new tsm.Transition("A","asd","zxc"));
        set.add(new tsm.Transition("B","zxc","asd"));

        for(tsm.Transition s:set)
        {
            System.out.println(s);
        }

        tsm.Transition t = new tsm.Transition("B","zxc","asd");


        for(tsm.Transition s:set)
        {
            if(s.isSameAs(t))
                System.out.println(s);
        }
*/
        HashSet<State> states = new HashSet<State>();
        State stateA = new State("A");
        State stateB = new State("B");
        states.add(stateA);
        states.add(stateB);

        HashSet<String> alpb = new HashSet<String>();
        alpb.add("0");
        alpb.add("1");

        HashSet<State> ins = new HashSet<State>();
        ins.add(stateA);

        TransitionSystem transitionSystem = new Process(states,alpb,ins);

        transitionSystem.addTransition(new Transition("0",stateA,stateB));
        transitionSystem.addTransition(new Transition("1",stateB,stateA));
        transitionSystem.addTransition(new Transition("1",stateB,stateA));

        System.out.print(transitionSystem);

    }
}
