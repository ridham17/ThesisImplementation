import dm.Distribution;
import dm.DomainContainer;
import dm.Proc;
import eqm.EquivalenceClassContainer;
import eqm.EquivalenceClassWrapper;
import tsm.TransitionSystem;

import java.util.HashSet;
import java.util.Set;

public class TSGenerator {
    TransitionSystem globalDef;
    EquivalenceClassContainer equivalenceClasses;
    Distribution distribution;

    public TSGenerator(TransitionSystem globalDef,Distribution distribution,DistCheckResult distCheckResult) {
        this.globalDef = globalDef;
        equivalenceClasses = distCheckResult.getEquivalenceClasses();
        this.distribution = distribution;
    }

    public Set<Proc> generateDistSYS()
    {
        Set<Proc> distSystems = new HashSet<>();

        for (EquivalenceClassWrapper equivalenceClass:equivalenceClasses.getEquivalenceClassWrappers())
        {
            Proc process = distribution.getProcess(equivalenceClass.getEqClassFor());
            System.out.println(process);


/* To be implemented */


        }

        return distSystems;
    }
}
