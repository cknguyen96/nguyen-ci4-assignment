package vn.edu.techkids.models;

/**
 * Created by qhuydtvt on 4/29/2016.
 */
public class Plane extends GameObjectWithHP {
    private static final int HP_DEFAULT = 5;

    public Plane(int x, int y, int width, int height, int hp) {
        super(x, y, width, height, hp);
    }
    /*TODO Tại sao có thêm 1 public Plane() với 4 tham số và this(5 tham số) => Whut does it mean? */
    public Plane(int x, int y, int width, int height) {
        this(x, y, width, height, HP_DEFAULT);
    }
}
