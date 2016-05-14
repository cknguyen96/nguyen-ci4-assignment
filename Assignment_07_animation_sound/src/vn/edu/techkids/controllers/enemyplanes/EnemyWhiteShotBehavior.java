package vn.edu.techkids.controllers.enemyplanes;

import vn.edu.techkids.controllers.enemybullets.EnemyBulletController;

/**
 * Created by Admin on 5/14/2016.
 */
public class EnemyWhiteShotBehavior implements EnemyShotBehavior {
    @Override
    public EnemyBulletController doShot(int x, int y) {
        return EnemyBulletController.create(EnemyPlaneType.WHITE, x, y);
    }
}
