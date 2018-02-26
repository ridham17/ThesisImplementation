import dm.DomainContainer;
import eqm.EquivalenceClassContainer;

public class DistCheckResult {
    boolean dist;
    EquivalenceClassContainer equivalenceClasses;
    DomainContainer domains;

    public DistCheckResult(boolean dist, EquivalenceClassContainer equivalenceClasses,DomainContainer domainContainer) {
        this.dist = dist;
        this.equivalenceClasses = equivalenceClasses;
        domains = domainContainer;
    }

    public DistCheckResult() {
        dist = false;
    }

    public boolean isDist() {
        return dist;
    }

    public void setDist(boolean dist) {
        this.dist = dist;
    }

    public EquivalenceClassContainer getEquivalenceClasses() {
        return equivalenceClasses;
    }

    public void setEquivalenceClasses(EquivalenceClassContainer equivalenceClasses) {
        this.equivalenceClasses = equivalenceClasses;
    }

    public DomainContainer getDomains() {
        return domains;
    }

    public void setDomains(DomainContainer domains) {
        this.domains = domains;
    }
}
