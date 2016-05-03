package com.company.controllers;

import com.company.models.GameObject;
import com.company.models.GameVecto;
import com.company.views.GameDrawer;

import java.awt.*;

/**
 * Created by Admin on 5/3/2016.
 */
public class SingleController implements Controller {
    protected GameObject gameObject;
    protected GameDrawer gameDrawer;
    protected GameVecto gameVecto;

    public SingleController(GameObject gameObject, GameDrawer gameDrawer){
        this.gameObject = gameObject;
        this.gameDrawer = gameDrawer;
        this.gameVecto = new GameVecto();
    }
    public GameObject getGameObject(){
        return this.gameObject;
    }
    @Override
    public void run() {
        this.gameObject.move(gameVecto);
    }

    @Override
    public void paint(Graphics g) {
        this.gameDrawer.paint(gameObject, g);
    }
}
