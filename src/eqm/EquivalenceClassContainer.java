package eqm;

import dm.Distribution;
import dm.Proc;

import java.util.HashSet;
import java.util.Set;

public class EquivalenceClassContainer {
    public int noOfEqClass;
    public Set<EquivalenceClassWrapper> equivalenceClassWrappers;

    public EquivalenceClassContainer(Distribution distribution) {
        this.noOfEqClass = distribution.getProcessSet().size();
        equivalenceClassWrappers = new HashSet<>();
        for (Proc process:distribution.getProcessSet()) {
            equivalenceClassWrappers.add(new EquivalenceClassWrapper(process.getTsID()));
        }
    }

    public EquivalenceClassWrapper getEquivalenceClass(int pID) {
        for (EquivalenceClassWrapper e: equivalenceClassWrappers)
            if (e.eqClassFor == pID)
                return e;

        return null;
    }
}
