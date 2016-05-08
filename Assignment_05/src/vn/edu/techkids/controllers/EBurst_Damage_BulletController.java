package vn.edu.techkids.controllers;

import vn.edu.techkids.models.EnemyBullet;
import vn.edu.techkids.models.Plane;
import vn.edu.techkids.views.GameDrawer;

/**
 * Created by Admin on 5/8/2016.
 */
public class EBurst_Damage_BulletController extends EnemyBulletController {

    public EBurst_Damage_BulletController(EnemyBullet gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dx = 2;
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof PlaneController){
            Plane plane = (Plane) c.getGameObject();
            plane.decreaseHP(2);
        }
        super.onCollide(c);
    }
}
