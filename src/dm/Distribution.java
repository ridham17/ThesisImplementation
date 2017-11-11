package dm;

import java.util.HashSet;
import java.util.Set;

public class Distribution {
    public String distId;
    public Set<Proc> processSet;
    public Set<String> sigma;

    public Distribution(String distId, Set<String> sigma) {
        this.distId = distId;
        this.sigma = sigma;
        processSet = new HashSet<>();
    }

    public Distribution(String distId, Set<Proc> processSet, Set<String> sigma) {
        this.distId = distId;
        this.processSet = processSet;
        this.sigma = sigma;
    }

    public Set<Proc> getProcessSet() {
        return processSet;
    }

    public Set<String> getSigma() {
        return sigma;
    }

    public boolean addProcess(Proc process)
    {
        for(Proc p:processSet)
        {
           if(p.isSameAs(process))
               return false;
        }
        return processSet.add(process);
    }

    public boolean checkValidity()
    {
        Set<String> testSigma = new HashSet<>();

        for (Proc process:processSet) {
            testSigma.addAll(process.getAlphabets());
        }

        if(testSigma.containsAll(sigma) && sigma.containsAll(testSigma))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        String finalString = "\nDistribution Name: "+distId;

        finalString += "\nProcess are:";
        for (Proc proc :processSet)
            finalString+=proc.toString();

        return finalString;
    }
}
