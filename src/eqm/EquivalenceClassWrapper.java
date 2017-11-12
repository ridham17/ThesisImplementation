package eqm;

import java.util.HashSet;
import java.util.Set;

public class EquivalenceClassWrapper {
    public int eqClassFor;
    public Set<Set<String>> eqClass;

    public EquivalenceClassWrapper(int eqClassFor) {
        this.eqClassFor = eqClassFor;
        eqClass = new HashSet<>();
    }

    public int getEqClassFor() {
        return eqClassFor;
    }

    public Set<Set<String>> getEqClass() {
        return eqClass;
    }
}
