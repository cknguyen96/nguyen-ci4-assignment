package vn.edu.techkids.models;

public class Bullet extends GameObject {
    public static final int DEFAULT_WIDTH = 13;
    public static final int DEFAULT_HEIGHT = 33;
    public static int DEFAULT_DAMAGE = 1;
    protected int damage = DEFAULT_DAMAGE;


    public Bullet(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public Bullet(int x, int y, int width, int height, int damage){
        this(x, y, width, height);
        this.damage = damage;
    }
    public static void increaseDamage(int x){
        DEFAULT_DAMAGE += 2;
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
