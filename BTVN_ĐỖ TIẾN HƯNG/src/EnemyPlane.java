import java.awt.*;

/**
 * Created by Administrator on 4/26/2016.
 */
public class EnemyPlane {

    public int x;
    public int y;
    public static final int WIDTH = 40;
    public static final int HEIGHT = 50;
    private Image image;

    public EnemyPlane(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void run () {
        y += 2;
        if (y > 600) y = 0;
    }

    public void paint (Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }
}
