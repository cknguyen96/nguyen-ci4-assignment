package vn.edu.techkids.controllers;

import vn.edu.techkids.models.GameObject;

import java.util.Collection;

/**
 * Created by qhuydtvt on 5/6/2016.
 */
public interface Colliable {
    //onCollide dùng để override tại các class xét va chạm >> xử lý trong hàm onCollide này
    void onCollide(Colliable c);
    GameObject getGameObject();
}
