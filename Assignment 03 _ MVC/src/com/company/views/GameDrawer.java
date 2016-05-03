package com.company.views;

import com.company.models.GameObject;

import java.awt.*;

/**
 * Created by Admin on 5/3/2016.
 */
public interface GameDrawer {
    public void paint(GameObject gameObject, Graphics g);
}
