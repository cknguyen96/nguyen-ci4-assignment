package vn.edu.techkids.controllers;

import vn.edu.techkids.models.Bullet;
import vn.edu.techkids.models.EnemyBullet;
import vn.edu.techkids.models.EnemyPlane;
import vn.edu.techkids.models.GameConfig;
import vn.edu.techkids.views.ImageDrawer;

/**
 * Created by qhuydtvt on 4/29/2016.
 */
public class BulletController extends SingleController implements Colliable {

    public static final int SPEED = 15;

    public BulletController(Bullet gameObject, ImageDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        gameVector.dy = -SPEED;
        CollisionPool.getInst().add(this);
    }

    @Override
    public void run() {
        super.run();
        //Nếu đạn bay ra ngoài màn hình thì hãy remove nó đi
        if (!GameConfig.getInst().isInScreen(this.gameObject)) {
            this.gameObject.setAlive(false);
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof EnemyPlaneController || c instanceof EBurst_Damage_PlaneController) {
            EnemyPlane enemyPlane = (EnemyPlane) c.getGameObject();
            enemyPlane.decreaseHP();
            if (enemyPlane.getHp() <= 0) {
                enemyPlane.setAlive(false);
            }
            gameObject.setAlive(false);
        } else if (c instanceof EnemyBulletController){
            EnemyBullet enemyBullet = (EnemyBullet) c.getGameObject();
            enemyBullet.setAlive(false);
            this.gameObject.setAlive(false);
        }
    }
}







