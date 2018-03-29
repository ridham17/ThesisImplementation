import dm.Distribution;
import dm.DomainContainer;
import dm.Proc;
import eqm.EquivalenceClassContainer;
import eqm.EquivalenceClassWrapper;
import tsm.Transition;
import tsm.TransitionSystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

            process.setStates(equivalenceClass.getEqClass().size());

            Map<Integer,Set<String>> setMap = new HashMap<Integer,Set<String>>(equivalenceClass.getEqClass().size());
            int i=0;
            for(Set<String> s:equivalenceClass.getEqClass())
            {
                setMap.put(i,s);
                i++;
            }

            for(i = 0; i<setMap.size();i++)
            {
    nxt:        for(int j=0;j<setMap.size();j++)
                {
                    Set<String> sSet = new HashSet<String>();
                    for (String from:setMap.get(i) ) {
                        for (String to:setMap.get(j)){
                            String s =globalDef.getTrans(from,to);
                            if((s!=null) && process.containsAlphabet(s))
                            {
                                sSet.add(s);
/*
                                assert process.addTransition(new Transition(s,Integer.toString(i),Integer.toString(j)));
                                continue nxt;
*/
                            }
                        }

                    }

                    for (String s:sSet)
                        assert process.addTransition(new Transition(s,Integer.toString(i),Integer.toString(j)));

                }
            }

            assert process.checkValidity();

            //System.out.println(process.printTS());
            distSystems.add(process);
        }

        return distSystems;
    }
}
