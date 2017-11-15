import dm.Distribution;
import dm.Proc;
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
        states.add("C");
        states.add("D");
        states.add("E");
        states.add("F");
        states.add("G");
        states.add("H");

        HashSet<String> pAlp = new HashSet<>();
        pAlp.add("prod");
        pAlp.add("send");
        pAlp.add("recv");
        pAlp.add("cons");

        HashSet<String> initStates = new HashSet<String>();
        initStates.add("A");
        //initStates.add("C");


        TransitionSystem transitionSystem = new TransitionSystem(123,states,pAlp,initStates);

        assert transitionSystem.addTransition(new Transition("prod","A","B"));
        assert transitionSystem.addTransition(new Transition("send","B","C"));
        assert transitionSystem.addTransition(new Transition("recv","C","D"));
        assert transitionSystem.addTransition(new Transition("prod","C","E"));
        assert transitionSystem.addTransition(new Transition("cons","D","A"));
        assert transitionSystem.addTransition(new Transition("prod","D","F"));
        assert transitionSystem.addTransition(new Transition("recv","E","F"));
        assert transitionSystem.addTransition(new Transition("send","F","G"));
        assert transitionSystem.addTransition(new Transition("cons","F","B"));
        assert transitionSystem.addTransition(new Transition("cons","G","C"));
        assert transitionSystem.addTransition(new Transition("prod","G","H"));
        assert transitionSystem.addTransition(new Transition("cons","H","E"));

        assert transitionSystem.checkValidity();
        System.out.print(transitionSystem);


        Distribution distribution = new Distribution("ProdCons",pAlp);

        pAlp = new HashSet<>();
        pAlp.add("prod");
        pAlp.add("send");
        Proc process = new Proc(0,pAlp);
        distribution.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("send");
        pAlp.add("recv");
        process = new Proc(1,pAlp);
        distribution.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("recv");
        pAlp.add("cons");
        process = new Proc(2,pAlp);
        distribution.addProcess(process);

        assert distribution.checkValidity();
        System.out.println(distribution);

        DistributabilityChecker distributabilityChecker = new DistributabilityChecker(transitionSystem);
        distributabilityChecker.isDistributableWRT(distribution);










        states = new HashSet<>();
        states.add("A");
        states.add("B");
        states.add("C");
        states.add("D");
        states.add("E");
        states.add("F");


        pAlp = new HashSet<>();
        pAlp.add("x");
        pAlp.add("a");
        pAlp.add("y");


        initStates = new HashSet<String>();
        initStates.add("A");
        //initStates.add("C");


        TransitionSystem transitionSystem2 = new TransitionSystem(1231,states,pAlp,initStates);

        assert transitionSystem2.addTransition(new Transition("x","A","B"));
        assert transitionSystem2.addTransition(new Transition("y","A","D"));
        assert transitionSystem2.addTransition(new Transition("a","A","C"));
        assert transitionSystem2.addTransition(new Transition("a","B","E"));
        assert transitionSystem2.addTransition(new Transition("a","D","F"));

        assert transitionSystem2.checkValidity();
        System.out.print(transitionSystem2);


        Distribution distribution2 = new Distribution("Test",pAlp);

        pAlp = new HashSet<>();
        pAlp.add("y");
        pAlp.add("x");
        process = new Proc(0,pAlp);
        distribution2.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("a");
        process = new Proc(1,pAlp);
        distribution2.addProcess(process);

        assert distribution2.checkValidity();
        System.out.println(distribution2);

        DistributabilityChecker distributabilityChecker2 = new DistributabilityChecker(transitionSystem2);
        distributabilityChecker2.isDistributableWRT(distribution2);



    }
}
