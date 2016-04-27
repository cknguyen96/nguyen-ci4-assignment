package com.company;

/**
 * Created by Admin on 4/26/2016.
 */
public class Check {
    public static void checkBien(int x, int y, int width, int height, int dx, int dy){
        //vs man hinh 400 600
        if(x < - width/2 || x + width/2 + 10 > 350){
            x -= dx;
        }
        if(y < 0 || y + height > 600){
            y -= dy;
        }
    }
}
