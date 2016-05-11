package vn.edu.techkids.controllers.enemyplanes;

import vn.edu.techkids.controllers.EnemyBulletController;
import vn.edu.techkids.controllers.EnemyBulletType;

/**
 * Created by Admin on 5/11/2016.
 */
public class EnemyNonDirectShotBehavior implements EnemyShotBehavior{
    @Override
    public EnemyBulletController doShot(int x, int y) {
        return EnemyBulletController.create(EnemyBulletType.DAMAGE_3, x, y);
    }
}
