package lesson1;

public class Wall implements Obstacle{
    private double height = 3d;

    public double getHeight() {
        return height;
    }

    public boolean jump(Jumpable creature){
        return creature.jump(this);
    }
}