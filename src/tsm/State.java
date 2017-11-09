package tsm;

public class State {
    String label;

    public State(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isSameAs(State state)
    {
        if(label.equals(state.getLabel()))
            return  true;
        else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        return isSameAs((State)o);
    }

    @Override
    public String toString() {
        return label;
    }
}
