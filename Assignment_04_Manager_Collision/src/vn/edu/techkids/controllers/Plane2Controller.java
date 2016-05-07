package vn.edu.techkids.controllers;

import vn.edu.techkids.models.Plane;
import vn.edu.techkids.views.GameDrawer;
import vn.edu.techkids.views.ImageDrawer;

import java.awt.*;

/**
 * Created by Admin on 5/7/2016.
 */
public class Plane2Controller  extends PlaneController{

    private Plane2Controller(Plane gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
    }

    private static Plane2Controller inst;
    public static Plane2Controller getInst(){
        if(inst == null){
            Plane plane = new Plane(300, 500, 70, 60);
            ImageDrawer planeDrawer = new ImageDrawer("resources/plane2.png");
            inst = new Plane2Controller(plane, planeDrawer);
        }
        return inst;
    }
}
