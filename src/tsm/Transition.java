package tsm;

public class Transition {
    public String lebel,from,to;

    public Transition(String lebel, String from, String to) {
        this.lebel = lebel;
        this.from = from;
        this.to = to;
    }

    public String getLebel() {
        return lebel;
    }

    public void setLebel(String lebel) {
        this.lebel = lebel;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Transition from State "+from+" to State "+to+" on input "+lebel;
    }

    public boolean isSameAs(Transition transition)
    {
        if(this.lebel.equals(transition.getLebel()) &&
                this.from.equals(transition.getFrom()) &&
                this.to.equals(transition.getTo()))
            return true;
        else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        return  isSameAs((Transition)o);
    }
}
