import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 4/25/2016.
 */
public class Plane {
    public int x;
    public int y;
    public int dx;
    public int dy;
    private Image image;
    public static final int WIDTH = 70;
    public static final int HEIGHT = 60;
    Bullet bullet;
    public void paint(Graphics g) {
        g.drawImage(image, x - WIDTH/2, y, WIDTH, HEIGHT, null);
        if (bullet != null)
            bullet.paint(g);
    }
    public void run() {
        x += dx;
        if (x > 390 || x < 10) x -= dx;
        y += dy;
        if (y > 550 || y < 30) y -= dy;
        if (bullet != null)
        bullet.run();
    }

    public void shot() {
        try {
            this.bullet = new Bullet(this.x, this.y, ImageIO.read(new File("resources/bullet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void move(Movement m) {
//
//        if (m.dx > 0) dx = 5;
//        else if (m.dx < 0) dx = -5;
//        else dx = 0;
//        if (m.dy > 0) dy = 5;
//        else if (m.dy < 0) dy = -5;
//        else dy = 0;
//
//    }


    public Plane(int x, int y, Image image) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public void setImage(Image image) {
        if (image != null && this.image == null) {
            this.image = image;
        }
    }

}
