package lesson1;

public class Human implements Runnable, Jumpable, Creature{
    private final double maxHeight = 2.45;
    private final double maxLenght = 3000;

    public boolean jump(Wall wall) {
        if (maxHeight > wall.getHeight()) {
            System.out.println("Human makes one leap");
        } else {
            System.out.println("Human couldn't take this height");
        }
        return maxHeight > wall.getHeight();
    }

    public boolean run(RunningTrack runningTrack) {
        if (maxLenght > runningTrack.getLength()) {
            //System.out.printf("Run %d meters%n", runningTrack.getLength());
            System.out.println("Human run " + runningTrack.getLength() + " meters");
        } else {
            System.out.println("The man didn't reach the finish line");
        }
        return maxLenght > runningTrack.getLength();
    }
}
