import dm.Distribution;
import dm.DomainContainer;
import dm.Proc;
import eqm.EquivalenceClassContainer;
import eqm.EquivalenceClassWrapper;
import tsm.Stat;
import tsm.Transition;
import tsm.TransitionSystem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class DistributabilityChecker {
    public TransitionSystem transitionSystem;
    public Distribution distribution;
    public DomainContainer domains;
    EquivalenceClassContainer equvalClasses;

    public DistributabilityChecker(TransitionSystem transitionSystem) {
        this.transitionSystem = transitionSystem;
    }

    public boolean isDistributableWRT(Distribution distribution)
    {
        this.distribution = distribution;
        domains = new DomainContainer(distribution);

        checkDSP1();

        return true;
    }


    private void checkDSP1()
    {
        Set<Proc> processSet = distribution.getProcessSet();
        System.out.println(processSet.size());

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
                System.out.println("\n"+transition);

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
                    System.out.println("UnEqival Detected: "+transition.getFrom() +" AND "+transition.getTo()+" WRT "+eqClassForP.getEqClassFor());

                }

                for (Proc process:domains.getProcNotInDomain(transition.getLebel()))
                {
                    EquivalenceClassWrapper eqClassForP = eqClsCntnr.getEquivalenceClass(process.getTsID());

                    System.out.println("Eqival Detected: "+transition.getFrom() +" AND "+transition.getTo()+" WRT "+eqClassForP.getEqClassFor());
                    eqClassForP.addOrMergeEqivalance(transition.getFrom(),transition.getTo());
                }
            }
        }

        equvalClasses = new EquivalenceClassContainer(eqClsCntnr);
        System.out.println(equvalClasses);

    }
}
