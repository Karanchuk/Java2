package lesson1;

public class Robot implements Runnable, Jumpable, Creature{
    private final double maxHeight = 5;
    private final double maxLenght = 15000;
    public boolean jump(Wall wall) {
        if (maxHeight > wall.getHeight()) {
            System.out.println("Robot makes one leap");
        } else {
            System.out.println("Robot couldn't take this height");
        }
        return maxHeight > wall.getHeight();
    }

    public boolean run(RunningTrack runningTrack) {
        if (maxLenght > runningTrack.getLength()) {
            System.out.println("Robot run " + runningTrack.getLength() + " meters");
        } else {
            System.out.println("The robot couldn't stand the load");
        }
        return maxLenght > runningTrack.getLength();
    }
}
