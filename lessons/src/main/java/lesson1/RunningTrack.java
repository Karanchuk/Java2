package lesson1;

public class RunningTrack implements Obstacle{
    private double length = 2500d;

    public double getLength() {
        return length;
    }

    public boolean run (Runnable creature) {
        return creature.run(this);
    }
}
