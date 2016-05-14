package vn.edu.techkids.controllers.enemybullets;


import vn.edu.techkids.controllers.*;
import vn.edu.techkids.controllers.plane.EffectiveType;
import vn.edu.techkids.controllers.plane.PlaneController;
import vn.edu.techkids.controllers.bullet.BulletController;
import vn.edu.techkids.controllers.enemyplanes.EnemyPlaneType;
import vn.edu.techkids.models.*;
import vn.edu.techkids.views.GameDrawer;
import vn.edu.techkids.views.ImageDrawer;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public class EnemyBulletController extends SingleController implements Colliable {

    public EnemyBulletController(EnemyBullet gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        this.gameVector.dy = 5;
        CollisionPool.getInst().add(this);
    }

    public EnemyBulletController(EnemyBullet gameObject,
                                 GameDrawer gameDrawer,
                                 GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
        System.out.println(gameObject.getClass().toString());
        CollisionPool.getInst().add(this);
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
        if (c instanceof PlaneController) {
            Plane plane = (Plane) c.getGameObject();
            EnemyBullet enemyBullet = (EnemyBullet)gameObject;
            plane.decreaseHP(enemyBullet.getDamage());
            switch (enemyBullet.getEnemyBulletType()){
                case NONE:
                    break;
                case SLOW:
                    ((PlaneController) c).isInEffective(EffectiveType.SLOW_DURATION_3S);
                    break;
            }
            if (plane.getHp() <= 0) {
                plane.setAlive(false);
            }

        }
        else if(c instanceof BulletController){
            //c.getGameObject().setAlive(false);
        }
    }
    //Factory Design
    public static EnemyBulletController create(EnemyPlaneType enemyPlaneType, int x, int y){
        EnemyBullet enemyBullet = null;
        GameVector gameVector = null;
        ImageDrawer imageDrawer = new ImageDrawer("resources/enemy_bullet.png");
        switch (enemyPlaneType){
            case BLACK:
                gameVector = new GameVector(0, 3);
                enemyBullet = new EnemyBullet(x, y, EnemyBullet.WIDTH, EnemyBullet.HEIGHT, 1);
                break;
            case WHITE:
                gameVector = new GameVector(-2, 3);
                enemyBullet = new EnemyBullet(x, y, EnemyBullet.WIDTH, EnemyBullet.HEIGHT, 3);
                break;
            case YELLOW:
                gameVector = new GameVector(-2, 3);
                enemyBullet = new EnemyBullet(x, y, EnemyBullet.WIDTH, EnemyBullet.HEIGHT, 2, EnemyBulletType.SLOW);
                break;
        }
        EnemyBulletController enemyBulletController = new EnemyBulletController(
                enemyBullet,
                imageDrawer,
                gameVector
        );
        return enemyBulletController;
    }
}
