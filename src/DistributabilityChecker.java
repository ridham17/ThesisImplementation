import dm.Distribution;
import dm.Proc;
import tsm.Stat;
import tsm.TransitionSystem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class DistributabilityChecker {
    public TransitionSystem transitionSystem;
    public Distribution distribution;

    public DistributabilityChecker(TransitionSystem transitionSystem) {
        this.transitionSystem = transitionSystem;
    }

    public boolean isDistributableWRT(Distribution distribution)
    {
        Queue<Stat> statQueue = new LinkedList<>();
        String[] initList = transitionSystem.getInitStates().toArray(new String[0]);
        for (Stat stat:transitionSystem.getLinkedStates())
                for (String init:initList)
                    if(stat.getLabel().equals(init))
                        statQueue.add(stat);

        Set<Proc> processSet = distribution.getProcessSet();
        System.out.println(processSet.size());

        return true;
    }
}
