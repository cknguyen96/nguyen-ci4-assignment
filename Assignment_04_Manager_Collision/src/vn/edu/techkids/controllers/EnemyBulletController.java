package vn.edu.techkids.controllers;


import vn.edu.techkids.models.EnemyBullet;
import vn.edu.techkids.views.GameDrawer;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public class EnemyBulletController extends SingleController implements Colliable{

    public EnemyBulletController(EnemyBullet gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = 5;
        CollisionPool.getInst().add(this);
    }

    @Override
    public void onCollide(Colliable c) {
        if(c instanceof PlaneController){
            c.getGameObject().setHP(c.getGameObject().getHP()-1);
            this.gameObject.setAlive(false);
        }
    }
}
