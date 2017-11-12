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

    public DistributabilityChecker(TransitionSystem transitionSystem) {
        this.transitionSystem = transitionSystem;
    }

    public boolean isDistributableWRT(Distribution distribution)
    {
        this.distribution = distribution;
        domains = new DomainContainer(distribution);

        Queue<Stat> statQueue = new LinkedList<>();
        String[] initList = transitionSystem.getInitStates().toArray(new String[0]);
        for (Stat stat:transitionSystem.getLinkedStates())
                for (String init:initList)
                    if(stat.getLabel().equals(init))
                        statQueue.add(stat);

        Set<Proc> processSet = distribution.getProcessSet();
        System.out.println(processSet.size());

        EquivalenceClassContainer eqClsCntnr = new EquivalenceClassContainer(distribution);
        Queue<Stat> statesQueue = new LinkedList<Stat>();

        while (!statesQueue.isEmpty()) {
            Stat statInProcess = statQueue.poll();

            for (Transition transition:statInProcess.getLinkes())
            {
                Stat nextStat = transitionSystem.getLinkedState(transition.getTo());
                assert nextStat!=null;

                if(!nextStat.getProcessStatus())
                    statesQueue.add(nextStat);

                for (Proc process:domains.getProcInDomain(transition.getLebel()))
                {
                    Set<Set<String>> eqClass =  eqClsCntnr.getEquivalenceClass(process.getTsID()).getEqClass();


                }

            }
        }
        return true;
    }
}
