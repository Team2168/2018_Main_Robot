package org.team2168.PID.trajectory;

public class Linspace {
    private double current;
    private final float end;
    private final double step;
    public Linspace(double start, float end, double totalCount) {
        this.current=start;
        this.end=end;
        this.step=(end - start) / totalCount;
    }
    public boolean hasNext() {
        return current < (end + step/2); //MAY stop floating point error
    }
    public double getNextFloat() {
        current+=step;
        return current;
    }
}
