package vn.edu.techkids.controllers;

import vn.edu.techkids.models.GameObject;
import vn.edu.techkids.views.GameDrawer;

/**
 * Created by qhuydtvt on 5/7/2016.
 */
public class SingleControllerWithHP extends SingleController {
    //Dont have to GameVecto in Constructor right?
    public SingleControllerWithHP(GameObject gameObject, GameDrawer gameDrawer) {
        super(gameObject, gameDrawer);
    }
    //SO if you have st to say, say quickly
    public void getHit(GameObject gameObject){

    }
}
