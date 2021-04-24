package lesson1;

public class Cat implements Runnable, Jumpable, Creature{
    private final double maxHeight = 3.1;
    private final double maxLenght = 500;

    public boolean jump(Wall wall) {
        if (maxHeight > wall.getHeight()) {
            System.out.println("Cat makes one leap");
        } else {
            System.out.println("Cat couldn't take this height");
        }
        return maxHeight > wall.getHeight();
    }

    public boolean run(RunningTrack runningTrack) {
        if (maxLenght > runningTrack.getLength()) {
            System.out.println("Cat run " + runningTrack.getLength() + " meters");
        } else {
            System.out.println("The cat left the race");
        }
        return maxLenght > runningTrack.getLength();
    }
}