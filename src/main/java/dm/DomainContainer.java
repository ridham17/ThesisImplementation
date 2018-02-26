package dm;

import java.util.HashSet;
import java.util.Set;

public class DomainContainer {
    Set<String> sigma;
    Set<DomainWrapper> domains;
    Set<Proc> processes;

    public DomainContainer(Distribution distribution) {
        sigma = distribution.getSigma();
        processes = distribution.getProcessSet();
        domains = new HashSet<>();
        for (String alph:sigma) {
            DomainWrapper domain = new DomainWrapper(alph);
            domains.add(domain);

            for (Proc process :processes)
                if (process.containsAlphabet(alph))
                    domain.getProcesses().add(process);
        }
    }

    public Set<Proc> getProcesses() {
        return processes;
    }

    public DomainWrapper getDomain(String alphabet)
    {
        for(DomainWrapper domain:domains)
            if (domain.alphabet.equals(alphabet))
                return domain;
        return null;
    }


    public Set<Proc> getProcNotInDomain(String alphabet)
    {
        DomainWrapper domain = getDomain(alphabet);
        Set<Proc> resultSet = new HashSet<>();
        for (Proc process:processes)
            if(!domain.isProcInDomain(process.getTsID()))
                resultSet.add(process);

        return resultSet;
    }

    public Set<Proc> getProcInDomain(String alphabet)
    {
        DomainWrapper domain = getDomain(alphabet);
        Set<dm.Proc> resultSet = new HashSet<>();
        for (dm.Proc process:processes)
            if(domain.isProcInDomain(process.getTsID()))
                resultSet.add(process);

        return resultSet;
    }


}
