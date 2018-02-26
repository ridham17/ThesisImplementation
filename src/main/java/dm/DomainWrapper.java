package dm;

import java.util.HashSet;
import java.util.Set;

public class DomainWrapper {
    public String alphabet;
    public Set<Proc> processes;

    public DomainWrapper(String alphabet) {
        this.alphabet = alphabet;
        processes = new HashSet<>();
    }

    public String getAlphabet() {
        return alphabet;
    }

    public Set<Proc> getProcesses() {
        return processes;
    }

    public boolean isProcInDomain(int p)
    {
        for (Proc process:processes)
            if(process.getTsID() == p)
                return  true;

        return false;
    }
}
