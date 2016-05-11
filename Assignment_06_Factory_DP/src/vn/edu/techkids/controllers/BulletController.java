package vn.edu.techkids.controllers;

import vn.edu.techkids.controllers.enemyplanes.EnemyPlaneController;
import vn.edu.techkids.models.*;
import vn.edu.techkids.views.ImageDrawer;

/**
 * Created by qhuydtvt on 4/29/2016.
 */
public class BulletController extends SingleController implements Colliable {

    public static int SPEED = 15;
    protected int speed = SPEED;

    public BulletController(Bullet gameObject, ImageDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        gameVector.dy = -speed;
        CollisionPool.getInst().add(this);
    }

    public BulletController(Bullet gameObject, ImageDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer);
        this.gameVector = gameVector;
        CollisionPool.getInst().add(this);
    }
    public static void stronger(GiftType giftType){
        switch (giftType){
            case STRONGER_BULLET_GIFT:
                SPEED += 20;
                Bullet.increaseDamage(200);
                break;
        }

    }

    @Override
    public void run() {
        super.run();
        if (!GameConfig.getInst().isInScreen(this.gameObject)) {
            this.gameObject.setAlive(false);
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof EnemyPlaneController) {
            EnemyPlane enemyPlane = (EnemyPlane) c.getGameObject();
            enemyPlane.decreaseHP();
            if (enemyPlane.getHp() <= 0) {
                enemyPlane.setAlive(false);
            }
            gameObject.setAlive(false);
        }
        else if(c instanceof EnemyBulletController){
            c.getGameObject().setAlive(false);
        }
    }
}







