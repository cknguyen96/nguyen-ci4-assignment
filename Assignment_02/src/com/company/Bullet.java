package com.company;

import java.awt.*;

/**
 * Created by Admin on 4/26/2016.
 */
public class Bullet {
    public int x;
    public int y;
    private Image image;
    public static final int WIDTH = 12;
    public static final int HEIGHT = 30;

    public Bullet(int x, int y, Image image) {
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
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }
    public void run(){
        y -= 5;
    }
}
