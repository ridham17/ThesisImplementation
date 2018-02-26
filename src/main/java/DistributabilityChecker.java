import dm.Distribution;
import dm.DomainContainer;
import dm.Proc;
import eqm.EquivalenceClassContainer;
import eqm.EquivalenceClassWrapper;
import tsm.Stat;
import tsm.Transition;
import tsm.TransitionSystem;

import java.util.*;

public class DistributabilityChecker {
    public TransitionSystem transitionSystem;
    public Distribution distribution;
    public DomainContainer domains;
    EquivalenceClassContainer equalClasses;

    public DistributabilityChecker(TransitionSystem transitionSystem) {
        this.transitionSystem = transitionSystem;
    }

    public DistCheckResult checkDistributableWRT(Distribution distribution)
    {
        this.distribution = distribution;
        domains = new DomainContainer(distribution);

        applyDSP1();
        applyDSP2();

        if(checkDSP3() && checkDSP4())
            return new DistCheckResult(true,equalClasses,domains);
        else
            return new DistCheckResult();
    }

    private boolean applyDSP1()
    {
        EquivalenceClassContainer eqClsCntnr = new EquivalenceClassContainer(distribution);
        Queue<Stat> statesQueue = new LinkedList<Stat>();

        for (String state:transitionSystem.getInitStates()) {
            Stat stat = transitionSystem.getLinkedState(state);
            statesQueue.add(stat);
            stat.setProcessedStatus();
        }


        while (!statesQueue.isEmpty()) {

            Stat statInProcess = statesQueue.remove();

            for (Transition transition:statInProcess.getLinkes())
            {
                //System.out.println("\nIn Process: "+transition);

                Stat nextStat = transitionSystem.getLinkedState(transition.getTo());
                assert nextStat!=null;


                if(!nextStat.getProcessStatus()) {
                    nextStat.setProcessedStatus();
                    statesQueue.add(nextStat);
                }
                for (Proc process:domains.getProcInDomain(transition.getLebel()))
                {
                    EquivalenceClassWrapper eqClassForP =  eqClsCntnr.getEquivalenceClass(process.getTsID());

                    eqClassForP.addEqivalance(transition.getFrom());
                    eqClassForP.addEqivalance(transition.getTo());
                    //System.out.println("UnEqival Detected: "+transition.getFrom() +" AND "+transition.getTo()+" WRT "+eqClassForP.getEqClassFor());

                }

                for (Proc process:domains.getProcNotInDomain(transition.getLebel()))
                {
                    EquivalenceClassWrapper eqClassForP = eqClsCntnr.getEquivalenceClass(process.getTsID());

                    //System.out.println("Eqival Detected: "+transition.getFrom() +" AND "+transition.getTo()+" WRT "+eqClassForP.getEqClassFor());
                    eqClassForP.addOrMergeEqivalance(transition.getFrom(),transition.getTo());
                }
            }
        }

        for (Stat stat:transitionSystem.getLinkedStates())
            stat.resetProcessedStatus();

        equalClasses = new EquivalenceClassContainer(eqClsCntnr);
        //System.out.println(equalClasses);

        return true;
    }


    private boolean applyDSP2()
    {
        EquivalenceClassContainer eqClsCntnr = new EquivalenceClassContainer(equalClasses);
        Queue<Stat> statesQueue = new LinkedList<Stat>();

        for (String state : transitionSystem.getInitStates()) {
            Stat stat = transitionSystem.getLinkedState(state);
            statesQueue.add(stat);
            stat.setProcessedStatus();
        }


        while (!statesQueue.isEmpty())
        {
            Stat statInProcess = statesQueue.remove();

            for (Transition transition : statInProcess.getLinkes())
            {
                //System.out.println("In Process: " + transition);

                Stat nextStat = transitionSystem.getLinkedState(transition.getTo());
                assert nextStat != null;


                if (!nextStat.getProcessStatus()) {
                    nextStat.setProcessedStatus();
                    statesQueue.add(nextStat);
                }

                for (Transition simTrans: transitionSystem.getAllTransitionWithLabel(transition.getLebel()))
                {
                    for (Proc process:domains.getProcInDomain(transition.getLebel()))
                    {
                        EquivalenceClassWrapper equivalenceClass = eqClsCntnr.getEquivalenceClass(process.getTsID());

                        if(equivalenceClass.isEquivalant(transition.getFrom(),simTrans.getFrom()))
                            equivalenceClass.addOrMergeEqivalance(transition.getTo(),simTrans.getTo());

                    }
                }
            }
        }

        for (Stat stat:transitionSystem.getLinkedStates())
            stat.resetProcessedStatus();

        equalClasses = new EquivalenceClassContainer(eqClsCntnr);
        System.out.println(equalClasses);

        return true;
    }

    private boolean checkDSP3()
    {
        for (String state:transitionSystem.getStateSpace())
        {
    next:   for (String compState:transitionSystem.getStateSpace())
            {
                if (state.equals(compState))
                    continue ;

                for (EquivalenceClassWrapper eqClass:equalClasses.getEquivalenceClassWrappers())
                {
                    if (!eqClass.isEquivalant(state,compState))
                        continue next;
                }
                System.out.println("States are Equivalant for "+state+" "+compState);
                return false;
            }
        }

        return true;
    }

/*    private boolean checkDSP4()
    {
        System.out.println("In DSP4");

        Set<Stat> stateSpace = transitionSystem.getLinkedStates();
        for (Stat state:stateSpace)
        {
            System.out.println("Stat in Process: "+state.getLabel());

            for (Transition transition:state.getLinkes())
            {

                System.out.println("T in Process: "+transition.getLebel());
                Set<String> compStates = new HashSet<>();
                compStates.addAll(transitionSystem.getStateSpace());

                for (Proc process:domains.getProcInDomain(transition.getLebel())) {
                    compStates.retainAll(equvalClasses.getEquivalenceClass(process.getTsID()).getEqivalantStates(state.getLabel()));
                }

        next:   for (String compState:compStates)
                {
                    for (Transition t:transitionSystem.getLinkedState(compState).getLinkes())
                        if(transition.getLebel().equals(t.getLebel()))
                            continue next;

                    System.out.println("Not Equal at State "+state.getLabel()+" for transition " + transition.getLebel()+" with state "+compState);//+" for Process"+process.getTsID());
                    return false;
                }
            }
        }

        return true;
    }*/

    private boolean checkDSP4()
    {
        Set<String> aplhabets = transitionSystem.getAlphabets();

        for (String alph:aplhabets)
        {
            Set<Transition> simTrans = transitionSystem.getAllTransitionWithLabel(alph);
            Set<Stat> simStats = new HashSet<>();
            for (Transition t:simTrans)
                simStats.add(transitionSystem.getLinkedState(t.getFrom()));

            Set<String> eqStst = new HashSet<>();
            eqStst.addAll(transitionSystem.getStateSpace());

            for (Proc process:domains.getProcInDomain(alph))
            {
                Set<String> pEqStat = new HashSet<>();
                for (Stat simStat : simStats)
                    pEqStat.addAll(equalClasses.getEquivalenceClass(process.getTsID()).getEqivalantStates(simStat.getLabel()));

                eqStst.retainAll(pEqStat);
            }

    next:   for (String state:eqStst)
            {
                Stat stat = transitionSystem.getLinkedState(state);
                for (Transition t: stat.getLinkes())
                {
                    if (t.getLebel().equals(alph))
                        continue next;
                }

                System.out.println("Not Equal at State "+state+" for transition " + alph +" WRT EquvalStates "+Arrays.toString(eqStst.toArray()));
                return false;
            }
        }

        return true;
    }

}
