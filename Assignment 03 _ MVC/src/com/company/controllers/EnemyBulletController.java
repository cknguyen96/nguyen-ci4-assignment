package com.company.controllers;

import com.company.models.Bullet;
import com.company.models.GameObject;
import com.company.views.GameDrawer;
import com.company.views.ImageDrawer;

import java.io.IOException;

/**
 * Created by Admin on 5/3/2016.
 */
public class EnemyBulletController extends SingleController {
    private final int SPEED = 3;

    public EnemyBulletController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVecto.dy = SPEED;
    }

}
