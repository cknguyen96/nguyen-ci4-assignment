package vn.edu.techkids.controllers;

import vn.edu.techkids.controllers.enemyplanes.EnemyPlaneController;
import vn.edu.techkids.controllers.enemyplanes.EnemyPlaneControllerManager;
import vn.edu.techkids.models.GameObject;
import vn.edu.techkids.models.GameVector;
import vn.edu.techkids.models.Plane;
import vn.edu.techkids.views.GameDrawer;
import vn.edu.techkids.views.ImageDrawer;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Admin on 5/11/2016.
 */
public class BombController extends SingleController implements Colliable{
    public BombController(GameObject gameObject, ImageDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        CollisionPool.getInst().add(this);
    }

    public BombController(GameObject gameObject, GameDrawer gameDrawer, GameVector gameVector) {
        super(gameObject, gameDrawer, gameVector);
        CollisionPool.getInst().add(this);
    }

    @Override
    public void paint(Graphics g) {
        if (this.getGameObject().isAlive())
        super.paint(g);
    }

    @Override
    public void run() {
        if(this.getGameObject().isAlive())
        super.run();
    }

    @Override
    public void onCollide(Colliable c) {
        if(c instanceof PlaneController){
            this.getGameObject().setAlive(false);
            Vector<SingleController> enemyPlaneControllerVector =
                    EnemyPlaneControllerManager.getInst().getSingleControllerVector();
            Iterator<SingleController> iterator = enemyPlaneControllerVector.iterator();
            while(iterator.hasNext()){
                SingleController singleController = iterator.next();
                iterator.remove();
            }
        }
    }
}
