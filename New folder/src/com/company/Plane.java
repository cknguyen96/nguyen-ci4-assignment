package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 4/26/2016.
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

    public Plane(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public void paint(Graphics g){
        if(bullet != null){
            bullet.paint(g);
        }
        g.drawImage(this.image, this.x, this.y, WIDTH, HEIGHT, null);
    }
    public void run(){
        x += dx;
        y += dy;
        if(x < -WIDTH/2 + 10 || x + WIDTH/2 + 10 >400) x -= dx;
        if(y < 30 || y + HEIGHT > 600) y -= dy;
        if(bullet != null){
            bullet.run();
        }
    }
    public void shot(){
        try {
            this.bullet = new Bullet(this.x + WIDTH/2 - Bullet.WIDTH/2, this.y, ImageIO.read(new File("resources/bullet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
