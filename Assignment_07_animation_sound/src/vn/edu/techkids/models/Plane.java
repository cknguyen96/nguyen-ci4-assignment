package vn.edu.techkids.models;

/**
 * Created by qhuydtvt on 4/29/2016.
 */
public class Plane extends GameObjectWithHP {
    private static final int HP_DEFAULT = 500;
    public static final int SPEED_DEFAULT = 20;
    protected int speed = SPEED_DEFAULT;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Plane(int x, int y, int width, int height, int hp) {
        super(x, y, width, height, hp);
    }

    public Plane(int x, int y, int width, int height) {
        this(x, y, width, height, HP_DEFAULT);
    }
}
