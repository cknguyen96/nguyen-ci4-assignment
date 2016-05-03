package com.company.controllers;

import com.company.models.Bullet;
import com.company.models.EnemyBullet;
import com.company.models.GameObject;
import com.company.models.Plane;
import com.company.views.GameDrawer;
import com.company.views.ImageDrawer;

import java.awt.*;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Admin on 5/3/2016.
 */
public class EnemyPlaneController extends SingleController {
    private final int SPEED = 1;
    public static final int DEFAULT_WIDTH = 50;
    public static final int DAFAULT_HEIGHT = 40;
    private Vector<EnemyBulletController> enemyBulletControllerVector;
    public EnemyPlaneController(GameObject gameObject, ImageDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        enemyBulletControllerVector = new Vector<EnemyBulletController>();
    }
    public void shot(){
        EnemyBullet enemyBullet = new EnemyBullet(
                this.gameObject.getX() + this.gameObject.getWidth()/2 - EnemyBullet.width/2,
                this.gameObject.getY() + this.gameObject.getHeight(),
                EnemyBullet.width, EnemyBullet.height
        );
        ImageDrawer bulletDrawer = null;
        try {
            bulletDrawer = new ImageDrawer("resources/bullet-green.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        EnemyBulletController enemyBulletController = new EnemyBulletController(enemyBullet, bulletDrawer);
        this.enemyBulletControllerVector.add(enemyBulletController);
    }

    @Override
    public void run() {
        this.gameVecto.dy = SPEED;
        super.run();
        for(EnemyBulletController enemyBulletController : this.enemyBulletControllerVector) enemyBulletController.run();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(EnemyBulletController enemyBulletController : this.enemyBulletControllerVector) enemyBulletController.paint(g);
    }

}
