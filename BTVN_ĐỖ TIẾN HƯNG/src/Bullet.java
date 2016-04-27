import java.awt.*;

/**
 * Created by Administrator on 4/25/2016.
 */
public class Bullet {
    private int x;
    private int y;
    private static final int WIDTH = 12;
    private static final int HEIGHT = 33;
    private Image image;

    public Bullet(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void run() {
         y -= 10;
    }

    public void paint (Graphics g) {
        g.drawImage(this.image, x - WIDTH/2, y, WIDTH, HEIGHT, null);
    }
}
