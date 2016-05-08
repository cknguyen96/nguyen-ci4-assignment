package vn.edu.techkids.controllers;

import vn.edu.techkids.models.EnemyBullet;
import vn.edu.techkids.models.EnemyPlane;
import vn.edu.techkids.models.GameConfig;
import vn.edu.techkids.models.Plane;
import vn.edu.techkids.views.GameDrawer;
import vn.edu.techkids.views.ImageDrawer;

import java.awt.*;

/**
 * Created by Admin on 5/8/2016.
 */
public class EBurst_Damage_PlaneController extends SingleControllerWithHP implements Colliable {
    private EBurst_Damage_BulletControllerManager eBurst_damage_bulletControllerManager;
    private int count = 0;
    public EBurst_Damage_PlaneController(EnemyPlane gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = 2;
        this.gameVector.dx = 2;
        this.eBurst_damage_bulletControllerManager = new EBurst_Damage_BulletControllerManager();
        CollisionPool.getInst().add(this);
    }

    @Override
    public void run() {
        super.run();
        this.eBurst_damage_bulletControllerManager.run();
        count++;
        if(GameConfig.getInst().durationInSeconds(count) >= 2){
            count = 0;
            EnemyBullet enemyBullet = new EnemyBullet(
                    gameObject.getX() + gameObject.getWidth() / 2 - EnemyBullet.WIDTH / 2,
                    gameObject.getY() + gameObject.getHeight(),
                    EnemyBullet.WIDTH,
                    EnemyBullet.HEIGHT
            );
            ImageDrawer imageDrawer = new ImageDrawer("resources/enemy_bullet.png");
            EBurst_Damage_BulletController tmp = new EBurst_Damage_BulletController(
                    enemyBullet,
                    imageDrawer
            );
            this.eBurst_damage_bulletControllerManager.add(tmp);
        }
        if (!GameConfig.getInst().isInScreen(this.gameObject)) {
            this.gameObject.setAlive(false);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.eBurst_damage_bulletControllerManager.paint(g);
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof Plane){
            Plane plane = (Plane) c.getGameObject();
            plane.decreaseHP(2);
        }
    }
}
