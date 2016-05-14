package vn.edu.techkids.controllers.plane;


import vn.edu.techkids.controllers.*;
import vn.edu.techkids.controllers.bullet.BulletController;
import vn.edu.techkids.controllers.bullet.BulletControllerManager;
import vn.edu.techkids.controllers.enemybullets.EnemyBulletController;
import vn.edu.techkids.controllers.enemyplanes.EnemyPlaneController;
import vn.edu.techkids.models.*;
import vn.edu.techkids.views.GameDrawer;
import vn.edu.techkids.views.ImageDrawer;

import java.awt.*;

/**
 * Created by qhuydtvt on 4/29/2016.
 */
public class PlaneController extends SingleControllerWithHP implements Colliable {
    //Tao mot speed trong GameObjectWithHP => speed = getSpeed cua GOWHP
    public final int MAX_BULLET_COUNT = 3;
    private int durationEffective;
    //private int speed = ((plane)this.gameObject).getSpeed();

    private BulletControllerManager bulletControllerManager;

    private PlaneController(Plane gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        //((plane)this.gameObject).setSpeed(SPEED);
        bulletControllerManager = new BulletControllerManager();
        this.durationEffective = 0;
        CollisionPool.getInst().add(this);
    }

    public void move(PlaneDirection planeDirection) {
        switch (planeDirection) {
            case NONE:
                break;
            case UP:
                this.gameVector.dy = -((Plane)this.gameObject).getSpeed();
                break;
            case DOWN:
                this.gameVector.dy = ((Plane)this.gameObject).getSpeed();
                break;
            case LEFT:
                this.gameVector.dx = -((Plane)this.gameObject).getSpeed();
                break;
            case RIGHT:
                this.gameVector.dx = ((Plane)this.gameObject).getSpeed();
                break;
            case STOP_X:
                this.gameVector.dx = 0;
                break;
            case STOP_Y:
                this.gameVector.dy = 0;
                break;
        }

    }

    public void shot() {
        if (bulletControllerManager.size() < MAX_BULLET_COUNT) {
            Bullet bullet = new Bullet(
                    gameObject.getX() + gameObject.getWidth() / 2 - Bullet.DEFAULT_WIDTH / 2,
                    gameObject.getY(),
                    Bullet.DEFAULT_WIDTH,
                    Bullet.DEFAULT_HEIGHT
            );
            ImageDrawer imageDrawer = new ImageDrawer("resources/bullet.png");
            BulletController bulletController = new BulletController(
                    bullet,
                    imageDrawer
            );

            this.bulletControllerManager.add(bulletController);
        }
    }

    private static PlaneController planeController1;

    public static PlaneController getPlaneController1() {
        if (planeController1 == null) {
            Plane plane = new Plane(100, 500, 70, 60);
            ImageDrawer planeDrawer = new ImageDrawer("resources/plane4.png");
            planeController1 = new PlaneController(plane, planeDrawer);
        }
        return planeController1;
    }

    public void isInEffective(EffectiveType effectiveType){
        switch (effectiveType){
            case NONE:
                break;
            case SLOW_DURATION_3S:
                durationEffective = 3000;
                ((Plane)this.gameObject).setSpeed(((Plane)this.gameObject).getSpeed() / 10);
                break;
        }
    }

    public void defaultStatus(){
        ((Plane)this.gameObject).setSpeed(Plane.SPEED_DEFAULT);
    }

    @Override
    public void run() {
        if (this.gameObject.isAlive()) {
            Rectangle rectangle=this.gameObject.getNextRect(this.gameVector);
            if(GameConfig.getInst().isInScreen(rectangle)) {
                if(durationEffective > 0) durationEffective -= GameConfig.getInst().DEFAULT_THREAD_DELAY;
                else{
                    durationEffective = 0;
                    defaultStatus();
                }
                super.run();
            }
            bulletControllerManager.run();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (this.gameObject.isAlive()) {
            super.paint(g);
            bulletControllerManager.paint(g);
        }
    }

    @Override
    public void onCollide(Colliable c) {
        if (c instanceof EnemyPlaneController) {
            EnemyPlane enemyPlane = (EnemyPlane) c.getGameObject();
            enemyPlane.setAlive(false);
        } else if (c instanceof EnemyBulletController) {
            EnemyBullet enemyBullet = (EnemyBullet) c.getGameObject();
            enemyBullet.setAlive(false);
        }
    }


    /* TODO: Work on the second plane */
}
