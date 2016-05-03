package com.company.views;

import com.company.models.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 5/3/2016.
 */
public class ImageDrawer implements GameDrawer {
    private Image image;
    public ImageDrawer(String url) throws IOException {
        this.image = ImageIO.read(new File(url));
    }
    @Override
    public void paint(GameObject gameObject, Graphics g) {
        g.drawImage(this.image, gameObject.getX(), gameObject.getY(),
                    gameObject.getWidth(), gameObject.getHeight(), null);
    }
}
