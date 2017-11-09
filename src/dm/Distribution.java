package dm;

import java.util.HashSet;
import java.util.Set;

public class Distribution {
    public String distId;
    public Set<Process> processSet;
    public Set<String> sigma;

    public Distribution(String distId, Set<String> sigma) {
        this.distId = distId;
        this.sigma = sigma;
    }

    public Distribution(String distId, Set<Process> processSet, Set<String> sigma) {
        this.distId = distId;
        this.processSet = processSet;
        this.sigma = sigma;
    }

    public Set<Process> getProcessSet() {
        return processSet;
    }

    public Set<String> getSigma() {
        return sigma;
    }

    public boolean addProcess(){
        return true;
    }

    public boolean checkValidity()
    {
        Set<String> testSigma = new HashSet<>();

        for (Process process:processSet) {
            testSigma.addAll(process.getAlphabets());
        }

        if(testSigma.containsAll(sigma) && sigma.containsAll(testSigma))
            return true;
        else
            return false;
    }


}
