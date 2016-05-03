package com.company.controllers;

import com.company.models.Bullet;
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
public class PlaneController extends SingleController {
    public final int SPEED = 10;
    public final int MAX_BULLET_COUNT = 10;
    protected Vector<BulletController> bulletControllerVector;

    public PlaneController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        bulletControllerVector = new Vector<BulletController>();
    }

    public void move(PlaneDirection planeDirection){
        switch (planeDirection){
            case UP:
                gameVecto.dy =  -SPEED;
                break;
            case DOWN:
                gameVecto.dy = SPEED;
                break;
            case LEFT:
                gameVecto.dx = -SPEED;
                break;
            case RIGHT:
                gameVecto.dx = SPEED;
                break;
            case STOP_X:
                gameVecto.dx = 0;
                break;
            case STOP_Y:
                gameVecto.dy = 0;
                break;
            case NONE:
                break;
        }
    }

    public void shot(){
        if(bulletControllerVector.size() < MAX_BULLET_COUNT){
            Bullet bullet = new Bullet(
                    this.gameObject.getX() + this.gameObject.getWidth()/2 - Bullet.width/2,
                    this.gameObject.getY(),
                    Bullet.width, Bullet.height
            );
            ImageDrawer bulletDrawer = null;
            try {
                bulletDrawer = new ImageDrawer("resources/bullet.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            BulletController bulletController = new BulletController(bullet, bulletDrawer);
            this.bulletControllerVector.add(bulletController);
        }
    }

    private static PlaneController planeController1;
    public static PlaneController getPlaneController(){
        if(planeController1 == null){
            Plane plane = new Plane(100, 500, 70, 60);
            ImageDrawer planeDrawer = null;
            try {
                planeDrawer = new ImageDrawer("resources/plane4.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            planeController1 = new PlaneController(plane, planeDrawer);
        }
        return planeController1;
    }

    @Override
    public void run() {
        //cho gameObject (plane) run theo dx, dy cua gameVecto
        super.run();
        if((this.gameObject.getX() < -this.gameObject.getWidth()/2 +10 ) || this.gameObject.getX() > 400 - this.gameObject.getWidth()/2 - 10){
            this.gameObject.setX(this.gameObject.getX() - this.gameVecto.dx);
        }
        if(this.gameObject.getY() < 0 || this.gameObject.getY() > 600 - this.gameObject.getHeight()){
            this.gameObject.setY(this.gameObject.getY() - this.gameVecto.dy);
        }

        //Duyet het Vecto =>> bullet run
        for(BulletController bulletController : this.bulletControllerVector) bulletController.run();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(BulletController bulletController : this.bulletControllerVector) bulletController.paint(g);
    }
}
