package com.company.controllers;

import com.company.models.GameObject;
import com.company.views.GameDrawer;
import com.company.views.ImageDrawer;

/**
 * Created by Admin on 5/3/2016.
 */
public class BulletController extends SingleController {
    public static final int SPEED = 10;

    public BulletController(GameObject gameObject, ImageDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        gameVecto.dy = -SPEED;
    }
}
