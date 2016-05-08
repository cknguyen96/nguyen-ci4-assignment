package vn.edu.techkids.controllers;

import vn.edu.techkids.models.EnemyPlane;
import vn.edu.techkids.models.GameConfig;
import vn.edu.techkids.views.ImageDrawer;

import java.awt.*;

/**
 * Created by Admin on 5/8/2016.
 */
public class EBurst_Damage_PlaneControllerManager extends ControllerManager {
    private int count = 0;
    private EBurst_Damage_PlaneControllerManager() {
        super();
    }

    @Override
    public void run() {
        super.run();
        count++;
        if(GameConfig.getInst().durationInSeconds(count) > 3) {
            count = 0;
            //for (int x = 40; x < GameConfig.getInst().getScreenWidth() - 40; x += 100) {
                EnemyPlane enemyPlane= new EnemyPlane(20, 0, 32, 32);
                ImageDrawer imageDrawer =
                        new ImageDrawer("resources/enemy_plane_white_1.png");
                EBurst_Damage_PlaneController eBurstDamagePlaneController = new EBurst_Damage_PlaneController(
                        enemyPlane,
                        imageDrawer
                );
                this.singleControllerVector.add(eBurstDamagePlaneController);
            //}
        }
    }
    private static EBurst_Damage_PlaneControllerManager inst;
    public static EBurst_Damage_PlaneControllerManager getInst(){
        if(inst == null){
            inst = new EBurst_Damage_PlaneControllerManager();
        }
        return inst;
    }
}
