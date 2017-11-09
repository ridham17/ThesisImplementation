package tsm;

public class Transition {
    public String lebel;
    State from,to;

    public Transition(String lebel, State from, State to) {
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

    public State getFrom() {
        return from;
    }

    public void setFrom(State from) {
        this.from = from;
    }

    public State getTo() {
        return to;
    }

    public void setTo(State to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "tsm.Transition from tsm.State "+from+" to tsm.State "+to+" on input "+lebel;
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
