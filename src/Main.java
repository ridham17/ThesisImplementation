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
        if(distributabilityChecker.isDistributableWRT(distribution))
            System.out.println("\n\nIS DISTRIBUTABLE");




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
        if(distributabilityChecker2.isDistributableWRT(distribution2))
            System.out.println("\n\nIS DISTRIBUTABLE");




        states = new HashSet<>();
        states.add("0");
        states.add("1");
        states.add("2");
        states.add("3");
        states.add("4");
        states.add("5");
        states.add("6");
        states.add("7");

        pAlp = new HashSet<>();
        pAlp.add("b");
        pAlp.add("a");
        pAlp.add("c");

        initStates = new HashSet<String>();
        initStates.add("0");

        TransitionSystem transitionSystem3 = new TransitionSystem(3,states,pAlp,initStates);

        assert transitionSystem3.addTransition(new Transition("a","0","1"));
        assert transitionSystem3.addTransition(new Transition("c","0","2"));
        assert transitionSystem3.addTransition(new Transition("b","0","3"));
        assert transitionSystem3.addTransition(new Transition("c","1","4"));
        assert transitionSystem3.addTransition(new Transition("b","1","5"));
        assert transitionSystem3.addTransition(new Transition("a","3","5"));
        assert transitionSystem3.addTransition(new Transition("c","3","6"));
        assert transitionSystem3.addTransition(new Transition("c","5","7"));

        assert transitionSystem3.checkValidity();
        System.out.print(transitionSystem3);


        Distribution distribution3 = new Distribution("T3",pAlp);

        pAlp = new HashSet<>();
        pAlp.add("a");
        pAlp.add("c");
        process = new Proc(1,pAlp);
        distribution3.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("b");
        pAlp.add("c");
        process = new Proc(2,pAlp);
        distribution3.addProcess(process);

        assert distribution3.checkValidity();
        System.out.println(distribution3);

        DistributabilityChecker distributabilityChecker3 = new DistributabilityChecker(transitionSystem3);
        if(distributabilityChecker3.isDistributableWRT(distribution3))
            System.out.println("\n\nIS DISTRIBUTABLE");






        states = new HashSet<>();
        states.add("0");
        states.add("1");
        states.add("2");
        states.add("3");
        states.add("5");
        states.add("7");

        pAlp = new HashSet<>();
        pAlp.add("b");
        pAlp.add("a");
        pAlp.add("c");

        initStates = new HashSet<String>();
        initStates.add("0");

        TransitionSystem transitionSystem4 = new TransitionSystem(4,states,pAlp,initStates);

        assert transitionSystem4.addTransition(new Transition("a","0","1"));
        assert transitionSystem4.addTransition(new Transition("c","0","2"));
        assert transitionSystem4.addTransition(new Transition("b","0","3"));
        assert transitionSystem4.addTransition(new Transition("b","1","5"));
        assert transitionSystem4.addTransition(new Transition("a","3","5"));
        assert transitionSystem4.addTransition(new Transition("c","5","7"));

        assert transitionSystem4.checkValidity();
        System.out.print(transitionSystem4);


        Distribution distribution4 = new Distribution("T4",pAlp);

        pAlp = new HashSet<>();
        pAlp.add("a");
        pAlp.add("c");
        process = new Proc(1,pAlp);
        distribution4.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("b");
        pAlp.add("c");
        process = new Proc(2,pAlp);
        distribution4.addProcess(process);

        assert distribution4.checkValidity();
        System.out.println(distribution4);

        DistributabilityChecker distributabilityChecker4 = new DistributabilityChecker(transitionSystem4);
        if(distributabilityChecker4.isDistributableWRT(distribution4))
            System.out.println("\n\nIS DISTRIBUTABLE");





        states = new HashSet<>();
        states.add("0");
        states.add("1");
        states.add("2");
        states.add("3");
        states.add("4");

        pAlp = new HashSet<>();
        pAlp.add("b");
        pAlp.add("a");
        pAlp.add("c");

        initStates = new HashSet<String>();
        initStates.add("0");

        TransitionSystem transitionSystem5 = new TransitionSystem(4,states,pAlp,initStates);

        assert transitionSystem5.addTransition(new Transition("a","0","1"));
        assert transitionSystem5.addTransition(new Transition("c","0","3"));
        assert transitionSystem5.addTransition(new Transition("c","1","2"));
        assert transitionSystem5.addTransition(new Transition("b","3","4"));

        assert transitionSystem5.checkValidity();
        System.out.print(transitionSystem5);


        Distribution distribution5 = new Distribution("T5",pAlp);

        pAlp = new HashSet<>();
        pAlp.add("a");
        pAlp.add("c");
        process = new Proc(1,pAlp);
        distribution5.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("b");
        pAlp.add("c");
        process = new Proc(2,pAlp);
        distribution5.addProcess(process);

        assert distribution5.checkValidity();
        System.out.println(distribution5);

        DistributabilityChecker distributabilityChecker5 = new DistributabilityChecker(transitionSystem5);
        if(distributabilityChecker5.isDistributableWRT(distribution5))
            System.out.println("\n\nIS DISTRIBUTABLE");




        states = new HashSet<>();
        states.add("0");
        states.add("1");
        states.add("2");
        states.add("3");

        pAlp = new HashSet<>();
        pAlp.add("floor");
        pAlp.add("wall");
        pAlp.add("roof");

        initStates = new HashSet<String>();
        initStates.add("0");

        TransitionSystem transitionSystem6 = new TransitionSystem(4,states,pAlp,initStates);

        assert transitionSystem6.addTransition(new Transition("floor","0","1"));
        assert transitionSystem6.addTransition(new Transition("wall","0","2"));
        assert transitionSystem6.addTransition(new Transition("wall","1","3"));
        assert transitionSystem6.addTransition(new Transition("floor","2","3"));
        assert transitionSystem6.addTransition(new Transition("roof","2","3"));

        assert transitionSystem6.checkValidity();
        System.out.print(transitionSystem6);


        Distribution distribution6 = new Distribution("T6",pAlp);

        pAlp = new HashSet<>();
        pAlp.add("wall");
        pAlp.add("roof");
        process = new Proc(1,pAlp);
        distribution6.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("floor");
        pAlp.add("roof");
        process = new Proc(2,pAlp);
        distribution6.addProcess(process);

        assert distribution6.checkValidity();
        System.out.println(distribution6);

        DistributabilityChecker distributabilityChecker6 = new DistributabilityChecker(transitionSystem6);
        if(distributabilityChecker6.isDistributableWRT(distribution6))
            System.out.println("\n\nIS DISTRIBUTABLE");




    }
}
