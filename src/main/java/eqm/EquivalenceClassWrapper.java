package eqm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EquivalenceClassWrapper {
    public int eqClassFor;
    public Set<Set<String>> eqClass;

    public EquivalenceClassWrapper(int eqClassFor) {
        this.eqClassFor = eqClassFor;
        eqClass = new HashSet<>();
    }

    public EquivalenceClassWrapper(EquivalenceClassWrapper eqw) {
        this.eqClassFor = eqw.getEqClassFor();
        eqClass = new HashSet<>();

        for (Set<String> eq:eqw.getEqClass())
            if(eq.isEmpty())
                continue;
            else
                eqClass.add(eq);
    }

    public int getEqClassFor() {
        return eqClassFor;
    }

    public Set<Set<String>> getEqClass() {
        return eqClass;
    }

    private Set<String> checkAndGetEqClassContaining(String stateName) {
        for (Set<String> eqc : eqClass)
            if (eqc.contains(stateName))
                return eqc;

        return null;
    }

    public boolean containEqivalance(String stateName) {
        for (Set<String> eqc : eqClass)
            if (eqc.contains(stateName))
                return true;

        return false;
    }

    public void addEqivalance(String stateName) {
        if (!containEqivalance(stateName)) {
            Set <String> eqc = new HashSet<>();
            eqc.add(stateName);
            eqClass.add(eqc);
        }
    }

    public Set<String> getEqivalantStates(String state)
    {
        for (Set<String> set:eqClass)
            if(set.contains(state))
                return set;

        return null;
    }

    private boolean removeEq(String stateName){
        Iterator<Set<String>> iterator = eqClass.iterator();
        while (iterator.hasNext()) {
            Set<String> st = iterator.next();
            if (st.contains(stateName)) {
                for (String t:st)
                    st.remove(t);
                iterator.remove();
                return true;
            }
        }

        return false;
    }

    public boolean addOrMergeEqivalance(String stateOne,String stateTwo)
    {
        Set<String> fClass,sClass;
        fClass = checkAndGetEqClassContaining(stateOne);
        sClass = checkAndGetEqClassContaining(stateTwo);


        if(fClass == null && sClass == null)
        {
            Set<String> eqc = new HashSet<>();
            eqc.add(stateOne);
            eqc.add(stateTwo);
            return eqClass.add(eqc);
        }

        if(fClass != null && sClass == null)
            return fClass.add(stateTwo);

        if (sClass!= null && fClass == null)
            return sClass.add(stateOne);

        if(fClass != null && sClass!= null) {
            if (fClass.equals(sClass))
                return true;

            fClass.addAll(sClass);
            sClass.removeAll(fClass);
            eqClass.remove(sClass);
            removeEmpty();
            return true;
        }

        return false;
    }

    public void removeEmpty()
    {
        for (Set<String> s:eqClass)
        {
            if (s.isEmpty())
                eqClass.remove(s);
        }
    }

    public boolean isEquivalant(String stateOne,String stateTwo)
    {
        Set<String> fClass,sClass;
        fClass = checkAndGetEqClassContaining(stateOne);
        sClass = checkAndGetEqClassContaining(stateTwo);

        assert fClass!=null && sClass!= null;

        if(fClass.equals(sClass))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        String finalString = "\nProcess "+eqClassFor +" Eqivalant States ";

        for (Set<String> eqc:eqClass) {
/*
            if(eqc.isEmpty())
                continue;
*/
            finalString += "\n";
            for (String stateName:eqc)
                finalString+=" "+stateName;
        }

        return finalString;
    }
}
