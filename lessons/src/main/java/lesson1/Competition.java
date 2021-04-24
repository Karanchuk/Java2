package lesson1;

public class Competition {
    public static void main(String[] args) {

        Creature[] participants = {new Cat(), new Human(), new Robot()};
        Obstacle[] obstacles = {new RunningTrack(), new Wall()};

        for (Creature participant: participants) {
            boolean finished = true;

            for (Obstacle obstacle: obstacles) {
                if (!finished) {
                    break;
                }
                if(obstacle instanceof RunningTrack) {
                    finished = ((RunningTrack) obstacle).run((Runnable) participant);
                } else {
                    finished = ((Wall) obstacle).jump((Jumpable) participant);
                }

            }
        }
    }
}
