package vn.edu.techkids.controllers;

import vn.edu.techkids.controllers.enemyplanes.EnemyPlaneControllerManager;
import vn.edu.techkids.models.GameObject;
import vn.edu.techkids.models.GameVector;
import vn.edu.techkids.views.GameDrawer;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Admin on 5/11/2016.
 */
public class StrongBulletGiftController extends SingleController implements Colliable{
    public StrongBulletGiftController(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        CollisionPool.getInst().add(this);
    }

    public StrongBulletGiftController(GameObject gameObject, GameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
        CollisionPool.getInst().add(this);
    }

    @Override
    public void paint(Graphics g) {
        if(this.getGameObject().isAlive())
        super.paint(g);
    }

    @Override
    public void run() {
        if(this.getGameObject().isAlive()) super.run();
    }
    @Override
    public void onCollide(Colliable c) {
        if(c instanceof PlaneController){
            this.getGameObject().setAlive(false);
            BulletController.stronger(GiftType.STRONGER_BULLET_GIFT);
        }
    }
}

