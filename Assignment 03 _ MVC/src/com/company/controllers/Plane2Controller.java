package com.company.controllers;

import com.company.models.GameObject;
import com.company.models.Plane;
import com.company.views.GameDrawer;
import com.company.views.ImageDrawer;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by Admin on 5/3/2016.
 */
//Controller cua plan2 extends tu Plane1
public class Plane2Controller extends PlaneController {
    //Constructor
    public Plane2Controller(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
        bulletControllerVector = new Vector<BulletController>();
    }
    //Single
    private static Plane2Controller plane2Controller;
    public static Plane2Controller getPlane2Controller(){
        if(plane2Controller == null){
            Plane plane = new Plane(200, 500, 70, 60);
            ImageDrawer planeDrawer = null;
            try {
                planeDrawer = new ImageDrawer("resources/plane2.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            plane2Controller = new Plane2Controller(plane, planeDrawer);
        }
        return plane2Controller;
    }
    public int getX(){
        return this.gameObject.getX();
    }
    public int getY(){
        return this.gameObject.getY();
    }
}
