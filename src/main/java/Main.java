import dm.Distribution;
import dm.Proc;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import tsm.Transition;
import tsm.TransitionSystem;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {



    public static void main(String[] args) {

        HashSet<String> states;
        HashSet<String> pAlp;
        HashSet<String> initStates;
        TransitionSystem transitionSystem;
        Distribution distribution;
        Proc process;
        TSGenerator tsGenerator;
        Set<Proc> distributedSystems;

        int nStstes,nAlp,nI,nTrans,nProcess;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter No of States, No of Alphabets, No of Initial States and No of transition:");
        nStstes = scanner.nextInt();
        nAlp = scanner.nextInt();
        nI = scanner.nextInt();
        nTrans = scanner.nextInt();

        System.out.println(nStstes+" "+nAlp+" "+nI+" "+nTrans);
        System.out.println("Enter Name of "+nStstes+" States:");

        scanner.nextLine();

        states = new HashSet<>();
        for (int i=0;i<nStstes;i++)
        {
            String s =scanner.nextLine();
            states.add(s);
        }
        //System.out.println();

        System.out.println("Enter Name of Alphabets for "+nAlp+" Alphabets:");
        //scanner.nextLine();
        pAlp = new HashSet<>();
        for (int i=0;i<nAlp;i++)
        {
            String s =scanner.nextLine();
            pAlp.add(s);
        }

        System.out.println("Enter Name of "+nI+" Initial States:");
        //scanner.nextLine();
        initStates = new HashSet<>();
        for (int i=0;i<nI;i++)
        {
            String s =scanner.nextLine();
            initStates.add(s);
        }

        transitionSystem = new TransitionSystem(1, states, pAlp, initStates);

        System.out.println("Enter Name of "+nTrans+" Transitions(Label From To):");
        //scanner.nextLine();
        for (int i=0;i<nTrans;i++)
        {
            String s =scanner.nextLine();
            String st[] = s.split(" ");
         //   System.out.println(st[0]+" "+st[1]+" "+st[2]);
            transitionSystem.addTransition(new Transition(st[0],st[1].toString(),st[2].toString()));
        }


        assert transitionSystem.checkValidity();

        distribution = new Distribution("TESTCASE 1", pAlp);

        System.out.println("\n\nEnter No of Processes in Distribution");
        nProcess = scanner.nextInt();

        for (int i=0;i<nProcess;i++)
        {
            int nal;
            pAlp = new HashSet<>();
            System.out.println("For Process "+i+1+" : \nEnter No of Alphabets:");
            nal = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter Alphabets:");
            for (int j=0;j<nal;j++)
                pAlp.add(scanner.nextLine());

            process = new Proc(i, pAlp);
            distribution.addProcess(process);
        }



        assert distribution.checkValidity();

        System.out.println("Given Specification is: ");
        System.out.print(transitionSystem);
        transitionSystem.displayTranSys();
        System.out.println(distribution);

        DistributabilityChecker distributabilityChecker = new DistributabilityChecker(transitionSystem);
        DistCheckResult distCheckResult =distributabilityChecker.checkDistributableWRT(distribution);



        if(!distCheckResult.isDist())
            System.out.println("\n\nNOT DISTRIBUTABLE");
        else {
            System.out.println("\n\nDISTRIBUTABLE");
            tsGenerator = new TSGenerator(transitionSystem, distribution, distCheckResult);
            distributedSystems = tsGenerator.generateDistSYS();

            for (Proc distSys : distributedSystems) {
                scanner.nextLine();
                System.out.println(distSys.printTS());
                distSys.displayTranSys();

            }

        }



/*        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
        testCase6();
        testCase7();
        testCase8();
*/

    }

    private static void testCase1() {


        HashSet<String> states;
        HashSet<String> pAlp;
        HashSet<String> initStates;
        TransitionSystem transitionSystem;
        Distribution distribution;
        Proc process;
        TSGenerator tsGenerator;
        Set<Proc> distributedSystems;

        states = new HashSet<>();
        states.add("A");
        states.add("B");
        states.add("C");
        states.add("D");
        states.add("E");
        states.add("F");
        states.add("G");
        states.add("H");

        pAlp = new HashSet<>();
        pAlp.add("prod");
        pAlp.add("send");
        pAlp.add("recv");
        pAlp.add("cons");

        initStates = new HashSet<String>();
        initStates.add("A");
        //initStates.add("C");


        transitionSystem = new TransitionSystem(1, states, pAlp, initStates);

        assert transitionSystem.addTransition(new Transition("prod", "A", "B"));
        assert transitionSystem.addTransition(new Transition("send", "B", "C"));
        assert transitionSystem.addTransition(new Transition("recv", "C", "D"));
        assert transitionSystem.addTransition(new Transition("prod", "C", "E"));
        assert transitionSystem.addTransition(new Transition("cons", "D", "A"));
        assert transitionSystem.addTransition(new Transition("prod", "D", "F"));
        assert transitionSystem.addTransition(new Transition("recv", "E", "F"));
        assert transitionSystem.addTransition(new Transition("send", "F", "G"));
        assert transitionSystem.addTransition(new Transition("cons", "F", "B"));
        assert transitionSystem.addTransition(new Transition("cons", "G", "C"));
        assert transitionSystem.addTransition(new Transition("prod", "G", "H"));
        assert transitionSystem.addTransition(new Transition("cons", "H", "E"));

        assert transitionSystem.checkValidity();
        System.out.print(transitionSystem);

        transitionSystem.displayTranSys();

        distribution = new Distribution("TESTCASE 1", pAlp);

        pAlp = new HashSet<>();
        pAlp.add("prod");
        pAlp.add("send");
        process = new Proc(0, pAlp);
        distribution.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("send");
        pAlp.add("recv");
        process = new Proc(1, pAlp);
        distribution.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("recv");
        pAlp.add("cons");
        process = new Proc(2, pAlp);
        distribution.addProcess(process);

        assert distribution.checkValidity();
        System.out.println(distribution);

        DistributabilityChecker distributabilityChecker = new DistributabilityChecker(transitionSystem);
        DistCheckResult distCheckResult =distributabilityChecker.checkDistributableWRT(distribution);

        if(!distCheckResult.isDist())
            System.out.println("\n\nNOT DISTRIBUTABLE");
        else
        {
            System.out.println("\n\nDISTRIBUTABLE");
            tsGenerator = new TSGenerator(transitionSystem,distribution ,distCheckResult);
            distributedSystems = tsGenerator.generateDistSYS();

            System.out.println("\nDistributed Transition Systems are");
            for (TransitionSystem distSys:distributedSystems)
                distSys.displayTranSys();

        }


    }

    private static void testCase2() {


        HashSet<String> states;
        HashSet<String> pAlp;
        HashSet<String> initStates;
        TransitionSystem transitionSystem;
        Distribution distribution;
        Proc process;
        TSGenerator tsGenerator;
        Set<Proc> distributedSystems;

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

        TransitionSystem transitionSystem2 = new TransitionSystem(2, states, pAlp, initStates);

        assert transitionSystem2.addTransition(new Transition("x", "A", "B"));
        assert transitionSystem2.addTransition(new Transition("y", "A", "D"));
        assert transitionSystem2.addTransition(new Transition("a", "A", "C"));
        assert transitionSystem2.addTransition(new Transition("a", "B", "E"));
        assert transitionSystem2.addTransition(new Transition("a", "D", "F"));

        assert transitionSystem2.checkValidity();
        System.out.print(transitionSystem2);

        transitionSystem2.displayTranSys();

        Distribution distribution2 = new Distribution("TESTCASE 2", pAlp);

        pAlp = new HashSet<>();
        pAlp.add("y");
        pAlp.add("x");
        process = new Proc(0, pAlp);
        distribution2.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("a");
        process = new Proc(1, pAlp);
        distribution2.addProcess(process);

        assert distribution2.checkValidity();
        System.out.println(distribution2);

        DistributabilityChecker distributabilityChecker2 = new DistributabilityChecker(transitionSystem2);
        DistCheckResult distCheckResult =distributabilityChecker2.checkDistributableWRT(distribution2);
        if(!distCheckResult.isDist())
            System.out.println("\n\nNOT DISTRIBUTABLE");
        else
        {
            System.out.println("\n\nDISTRIBUTABLE");
            tsGenerator = new TSGenerator(transitionSystem2,distribution2 ,distCheckResult);
            distributedSystems = tsGenerator.generateDistSYS();

            for (TransitionSystem distSys:distributedSystems)
                distSys.displayTranSys();

        }


    }

    private static void  testCase3() {


        HashSet<String> states;
        HashSet<String> pAlp;
        HashSet<String> initStates;
        TransitionSystem transitionSystem;
        Distribution distribution;
        Proc process;
        TSGenerator tsGenerator;
        Set<Proc> distributedSystems;

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

        TransitionSystem transitionSystem3 = new TransitionSystem(3, states, pAlp, initStates);

        assert transitionSystem3.addTransition(new Transition("a", "0", "1"));
        assert transitionSystem3.addTransition(new Transition("c", "0", "2"));
        assert transitionSystem3.addTransition(new Transition("b", "0", "3"));
        assert transitionSystem3.addTransition(new Transition("c", "1", "4"));
        assert transitionSystem3.addTransition(new Transition("b", "1", "5"));
        assert transitionSystem3.addTransition(new Transition("a", "3", "5"));
        assert transitionSystem3.addTransition(new Transition("c", "3", "6"));
        assert transitionSystem3.addTransition(new Transition("c", "5", "7"));

        assert transitionSystem3.checkValidity();
        System.out.print(transitionSystem3);

        transitionSystem3.displayTranSys();

        Distribution distribution3 = new Distribution("TESTCASE 3", pAlp);

        pAlp = new HashSet<>();
        pAlp.add("a");
        pAlp.add("c");
        process = new Proc(1, pAlp);
        distribution3.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("b");
        pAlp.add("c");
        process = new Proc(2, pAlp);
        distribution3.addProcess(process);

        assert distribution3.checkValidity();
        System.out.println(distribution3);

        DistributabilityChecker distributabilityChecker3 = new DistributabilityChecker(transitionSystem3);
        DistCheckResult distCheckResult =distributabilityChecker3.checkDistributableWRT(distribution3);
        if(!distCheckResult.isDist())
            System.out.println("\n\nNOT DISTRIBUTABLE");
        else
        {
            System.out.println("\n\nDISTRIBUTABLE");
            tsGenerator = new TSGenerator(transitionSystem3,distribution3 ,distCheckResult);
            distributedSystems = tsGenerator.generateDistSYS();

            for (TransitionSystem distSys:distributedSystems)
                distSys.displayTranSys();

        }

    }

    private static void testCase4() {


        HashSet<String> states;
        HashSet<String> pAlp;
        HashSet<String> initStates;
        TransitionSystem transitionSystem;
        Distribution distribution;
        Proc process;
        TSGenerator tsGenerator;
        Set<Proc> distributedSystems;

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

        TransitionSystem transitionSystem4 = new TransitionSystem(4, states, pAlp, initStates);

        assert transitionSystem4.addTransition(new Transition("a", "0", "1"));
        assert transitionSystem4.addTransition(new Transition("c", "0", "2"));
        assert transitionSystem4.addTransition(new Transition("b", "0", "3"));
        assert transitionSystem4.addTransition(new Transition("b", "1", "5"));
        assert transitionSystem4.addTransition(new Transition("a", "3", "5"));
        assert transitionSystem4.addTransition(new Transition("c", "5", "7"));

        assert transitionSystem4.checkValidity();
        System.out.print(transitionSystem4);

        transitionSystem4.displayTranSys();

        Distribution distribution4 = new Distribution("TESTCASE 4", pAlp);

        pAlp = new HashSet<>();
        pAlp.add("a");
        pAlp.add("c");
        process = new Proc(1, pAlp);
        distribution4.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("b");
        pAlp.add("c");
        process = new Proc(2, pAlp);
        distribution4.addProcess(process);

        assert distribution4.checkValidity();
        System.out.println(distribution4);

        DistributabilityChecker distributabilityChecker4 = new DistributabilityChecker(transitionSystem4);
        DistCheckResult distCheckResult =distributabilityChecker4.checkDistributableWRT(distribution4);
        if(!distCheckResult.isDist())
            System.out.println("\n\nNOT DISTRIBUTABLE");
        else
        {
            System.out.println("\n\nDISTRIBUTABLE");
            tsGenerator = new TSGenerator(transitionSystem4,distribution4 ,distCheckResult);
            distributedSystems = tsGenerator.generateDistSYS();

            for (TransitionSystem distSys:distributedSystems)
                distSys.displayTranSys();

        }


    }

    private static void testCase5() {


        HashSet<String> states;
        HashSet<String> pAlp;
        HashSet<String> initStates;
        TransitionSystem transitionSystem;
        Distribution distribution;
        Proc process;
        TSGenerator tsGenerator;
        Set<Proc> distributedSystems;

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

        TransitionSystem transitionSystem5 = new TransitionSystem(5, states, pAlp, initStates);

        assert transitionSystem5.addTransition(new Transition("a", "0", "1"));
        assert transitionSystem5.addTransition(new Transition("c", "0", "3"));
        assert transitionSystem5.addTransition(new Transition("c", "1", "2"));
        assert transitionSystem5.addTransition(new Transition("b", "3", "4"));

        assert transitionSystem5.checkValidity();
        System.out.print(transitionSystem5);

        transitionSystem5.displayTranSys();

        Distribution distribution5 = new Distribution("TESTCASE 5", pAlp);

        pAlp = new HashSet<>();
        pAlp.add("a");
        pAlp.add("c");
        process = new Proc(1, pAlp);
        distribution5.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("b");
        pAlp.add("c");
        process = new Proc(2, pAlp);
        distribution5.addProcess(process);

        assert distribution5.checkValidity();
        System.out.println(distribution5);

        DistributabilityChecker distributabilityChecker5 = new DistributabilityChecker(transitionSystem5);
        DistCheckResult distCheckResult =distributabilityChecker5.checkDistributableWRT(distribution5);
        if(!distCheckResult.isDist())
            System.out.println("\n\nNOT DISTRIBUTABLE");
        else
        {
            System.out.println("\n\nDISTRIBUTABLE");
            tsGenerator = new TSGenerator(transitionSystem5,distribution5,distCheckResult);
            distributedSystems = tsGenerator.generateDistSYS();

            for (TransitionSystem distSys:distributedSystems)
                distSys.displayTranSys();

        }

    }

    private static void testCase6() {


        HashSet<String> states;
        HashSet<String> pAlp;
        HashSet<String> initStates;
        TransitionSystem transitionSystem;
        Distribution distribution;
        Proc process;
        TSGenerator tsGenerator;
        Set<Proc> distributedSystems;

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

        TransitionSystem transitionSystem6 = new TransitionSystem(6, states, pAlp, initStates);

        assert transitionSystem6.addTransition(new Transition("floor", "0", "1"));
        assert transitionSystem6.addTransition(new Transition("wall", "0", "2"));
        assert transitionSystem6.addTransition(new Transition("wall", "1", "3"));
        assert transitionSystem6.addTransition(new Transition("floor", "2", "3"));
        assert transitionSystem6.addTransition(new Transition("roof", "2", "3"));

        assert transitionSystem6.checkValidity();
        System.out.print(transitionSystem6);

        transitionSystem6.displayTranSys();

        Distribution distribution6 = new Distribution("TESTCASE 6", pAlp);

        pAlp = new HashSet<>();
        pAlp.add("wall");
        pAlp.add("roof");
        process = new Proc(1, pAlp);
        distribution6.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("floor");
        pAlp.add("roof");
        process = new Proc(2, pAlp);
        distribution6.addProcess(process);

        assert distribution6.checkValidity();
        System.out.println(distribution6);

        DistributabilityChecker distributabilityChecker6 = new DistributabilityChecker(transitionSystem6);
        DistCheckResult distCheckResult =distributabilityChecker6.checkDistributableWRT(distribution6);
        if(!distCheckResult.isDist())
            System.out.println("\n\nNOT DISTRIBUTABLE");
        else
        {
            System.out.println("\n\nDISTRIBUTABLE");
            tsGenerator = new TSGenerator(transitionSystem6,distribution6 ,distCheckResult);
            distributedSystems = tsGenerator.generateDistSYS();

            for (TransitionSystem distSys:distributedSystems)
                distSys.displayTranSys();

        }


    }

    private static void testCase7()
    {

        HashSet<String> states;
        HashSet<String> pAlp;
        HashSet<String> initStates;
        TransitionSystem transitionSystem;
        Distribution distribution;
        Proc process;
        TSGenerator tsGenerator;
        Set<Proc> distributedSystems;

        states = new HashSet<>();
        states.add("0");
        states.add("1");

        pAlp = new HashSet<>();
        pAlp.add("a");
        pAlp.add("b");

        initStates = new HashSet<String>();
        initStates.add("0");

        TransitionSystem transitionSystem7 = new TransitionSystem(7,states,pAlp,initStates);

        assert transitionSystem7.addTransition(new Transition("a","0","1"));
        assert transitionSystem7.addTransition(new Transition("a","1","0"));
        assert transitionSystem7.addTransition(new Transition("b","1","0"));
        assert transitionSystem7.addTransition(new Transition("b","0","1"));

        assert transitionSystem7.checkValidity();
        System.out.print(transitionSystem7);

        transitionSystem7.displayTranSys();

        Distribution distribution7 = new Distribution("TESTCASE 7",pAlp);

        pAlp = new HashSet<>();
        pAlp.add("a");
        process = new Proc(1,pAlp);
        distribution7.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("b");
        process = new Proc(2,pAlp);
        distribution7.addProcess(process);

        assert distribution7.checkValidity();
        System.out.println(distribution7);

        DistributabilityChecker distributabilityChecker7 = new DistributabilityChecker(transitionSystem7);
        DistCheckResult distCheckResult =distributabilityChecker7.checkDistributableWRT(distribution7);
        if(!distCheckResult.isDist())
            System.out.println("\n\nNOT DISTRIBUTABLE");
        else
        {
            System.out.println("\n\nDISTRIBUTABLE");
            tsGenerator = new TSGenerator(transitionSystem7,distribution7 ,distCheckResult);
            distributedSystems = tsGenerator.generateDistSYS();

            for (TransitionSystem distSys:distributedSystems)
                distSys.displayTranSys();

        }

    }

    private static void testCase8()
    {

        HashSet<String> states;
        HashSet<String> pAlp;
        HashSet<String> initStates;
        TransitionSystem transitionSystem;
        Distribution distribution;
        Proc process;
        TSGenerator tsGenerator;
        Set<Proc> distributedSystems;

        states = new HashSet<>();
        states.add("0");
        states.add("1");
        states.add("2");
        states.add("3");
        states.add("4");

        pAlp = new HashSet<>();
        pAlp.add("a");
        pAlp.add("b");
        pAlp.add("c");
        pAlp.add("d");

        initStates = new HashSet<String>();
        initStates.add("0");

        TransitionSystem transitionSystem8 = new TransitionSystem(8,states,pAlp,initStates);

        assert transitionSystem8.addTransition(new Transition("a","0","1"));
        assert transitionSystem8.addTransition(new Transition("b","1","2"));
        assert transitionSystem8.addTransition(new Transition("c","2","3"));
        assert transitionSystem8.addTransition(new Transition("d","3","4"));

        assert transitionSystem8.checkValidity();
        System.out.print(transitionSystem8);

        transitionSystem8.displayTranSys();

        Distribution distribution8 = new Distribution("TESTCASE 8",pAlp);

        pAlp = new HashSet<>();
        pAlp.add("a");
        pAlp.add("d");
        process = new Proc(1,pAlp);
        distribution8.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("b");
        pAlp.add("c");
        pAlp.add("d");
        process = new Proc(2,pAlp);
        distribution8.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("b");
        pAlp.add("c");
        pAlp.add("a");
        process = new Proc(3,pAlp);
        distribution8.addProcess(process);

        assert distribution8.checkValidity();
        System.out.println(distribution8);

        DistributabilityChecker distributabilityChecker7 = new DistributabilityChecker(transitionSystem8);
        DistCheckResult distCheckResult =distributabilityChecker7.checkDistributableWRT(distribution8);
        if(!distCheckResult.isDist())
            System.out.println("\n\nNOT DISTRIBUTABLE");
        else
        {
            System.out.println("\n\nDISTRIBUTABLE");
            tsGenerator = new TSGenerator(transitionSystem8,distribution8 ,distCheckResult);
            distributedSystems = tsGenerator.generateDistSYS();

            for (TransitionSystem distSys:distributedSystems)
                distSys.displayTranSys();

        }

    }

    private static void testCase9()
    {

        HashSet<String> states;
        HashSet<String> pAlp;
        HashSet<String> initStates;
        TransitionSystem transitionSystem;
        Distribution distribution;
        Proc process;
        TSGenerator tsGenerator;
        Set<Proc> distributedSystems;

        states = new HashSet<>();
        states.add("0");
        states.add("1");
        states.add("2");
        states.add("3");
        states.add("4");
        states.add("5");
        states.add("6");
        states.add("7");
        states.add("8");
        states.add("9");
        states.add("10");
        states.add("11");
        states.add("12");

        pAlp = new HashSet<>();
        pAlp.add("a");
        pAlp.add("b");
        pAlp.add("c");
        pAlp.add("d");
        pAlp.add("e");
        pAlp.add("f");
        pAlp.add("g");

        initStates = new HashSet<String>();
        initStates.add("0");

        TransitionSystem transitionSystem8 = new TransitionSystem(9,states,pAlp,initStates);

        /*assert transitionSystem8.addTransition(new Transition("a","0","3"));
        assert transitionSystem8.addTransition(new Transition("b","0","1"));
        assert transitionSystem8.addTransition(new Transition("c","0","2"));
        assert transitionSystem8.addTransition(new Transition("c","1","4"));
        assert transitionSystem8.addTransition(new Transition("a","1","5"));
        assert transitionSystem8.addTransition(new Transition("d","1","2"));
        assert transitionSystem8.addTransition(new Transition("b","4","5"));
        assert transitionSystem8.addTransition(new Transition("b","2","6"));
        assert transitionSystem8.addTransition(new Transition("a","3","7"));
        assert transitionSystem8.addTransition(new Transition("d","3","8"));
        assert transitionSystem8.addTransition(new Transition("e","5","9"));
        assert transitionSystem8.addTransition(new Transition("a","6","9"));
        assert transitionSystem8.addTransition(new Transition("b","6","10"));
        assert transitionSystem8.addTransition(new Transition("b","7","10"));
        assert transitionSystem8.addTransition(new Transition("b","8","11"));
        assert transitionSystem8.addTransition(new Transition("c","11","10"));
        assert transitionSystem8.addTransition(new Transition("a","9","12"));
        assert transitionSystem8.addTransition(new Transition("c","10","12"));
        assert transitionSystem8.addTransition(new Transition("a","11","12"));*/


        assert transitionSystem8.addTransition(new Transition("a","0","3"));
        assert transitionSystem8.addTransition(new Transition("g","0","1"));
        assert transitionSystem8.addTransition(new Transition("c","0","2"));
        assert transitionSystem8.addTransition(new Transition("d","1","4"));
        assert transitionSystem8.addTransition(new Transition("e","1","5"));
        assert transitionSystem8.addTransition(new Transition("f","1","2"));
        assert transitionSystem8.addTransition(new Transition("g","4","5"));
        assert transitionSystem8.addTransition(new Transition("a","2","6"));
        assert transitionSystem8.addTransition(new Transition("b","3","7"));
        assert transitionSystem8.addTransition(new Transition("c","3","8"));
        assert transitionSystem8.addTransition(new Transition("d","5","9"));
        assert transitionSystem8.addTransition(new Transition("g","6","9"));
        assert transitionSystem8.addTransition(new Transition("f","6","10"));
        assert transitionSystem8.addTransition(new Transition("g","7","10"));
        assert transitionSystem8.addTransition(new Transition("f","8","11"));
        assert transitionSystem8.addTransition(new Transition("b","11","10"));
        assert transitionSystem8.addTransition(new Transition("c","9","12"));
        assert transitionSystem8.addTransition(new Transition("d","10","12"));
        assert transitionSystem8.addTransition(new Transition("e","11","12"));

        assert transitionSystem8.checkValidity();
        System.out.print(transitionSystem8);

        transitionSystem8.displayTranSys();

        Distribution distribution8 = new Distribution("TESTCASE 9",pAlp);

        pAlp = new HashSet<>();
        pAlp.add("a");
        pAlp.add("d");
        pAlp.add("f");
        pAlp.add("e");
        process = new Proc(1,pAlp);
        distribution8.addProcess(process);

        pAlp = new HashSet<>();
        pAlp.add("b");
        pAlp.add("c");
        pAlp.add("d");
        pAlp.add("g");
        process = new Proc(2,pAlp);
        distribution8.addProcess(process);

        assert distribution8.checkValidity();
        System.out.println(distribution8);

        DistributabilityChecker distributabilityChecker7 = new DistributabilityChecker(transitionSystem8);
        DistCheckResult distCheckResult =distributabilityChecker7.checkDistributableWRT(distribution8);
        if(!distCheckResult.isDist())
            System.out.println("\n\nNOT DISTRIBUTABLE");
        else
        {
            System.out.println("\n\nDISTRIBUTABLE");
            tsGenerator = new TSGenerator(transitionSystem8,distribution8 ,distCheckResult);
            distributedSystems = tsGenerator.generateDistSYS();

            for (TransitionSystem distSys:distributedSystems)
                distSys.displayTranSys();

        }

    }
}
