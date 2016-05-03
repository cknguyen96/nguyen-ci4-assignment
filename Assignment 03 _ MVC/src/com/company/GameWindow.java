package com.company;

import com.company.controllers.*;
import com.company.models.Plane;
import com.company.views.ImageDrawer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Admin on 5/3/2016.
 */
public class GameWindow extends Frame implements Runnable{
    Image backgroundImage;
    Image backbufferedImage;
    Thread thread;
    PlaneController planeController1;
    Plane2Controller plane2Controller;
    Vector<EnemyPlaneController> enemyPlaneControllers = new Vector<EnemyPlaneController>();
        public GameWindow(){
        this.setVisible(true);
        this.setSize(400, 600);
        this.planeController1 = PlaneController.getPlaneController();
        this.plane2Controller = Plane2Controller.getPlane2Controller();

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        //Load background va enemyPlane
        try {
            backgroundImage = ImageIO.read(new File("resources/background.png"));
            for(int i = 0; i <= 4; i++){
                Plane enemyPlane = new Plane(10+85*i, 0, EnemyPlaneController.DEFAULT_WIDTH, EnemyPlaneController.DAFAULT_HEIGHT);
                ImageDrawer tmpDrawer = new ImageDrawer("resources/plane1.png");
                enemyPlaneControllers.add(i, new EnemyPlaneController(enemyPlane, tmpDrawer));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Bat su kien plane1 dc = phim
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                PlaneDirection planeDirection = PlaneDirection.NONE;
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        planeDirection = PlaneDirection.UP;
                        break;
                    case KeyEvent.VK_DOWN:
                        planeDirection = PlaneDirection.DOWN;
                        break;
                    case KeyEvent.VK_LEFT:
                        planeDirection = PlaneDirection.LEFT;
                        break;
                    case KeyEvent.VK_RIGHT:
                        planeDirection = PlaneDirection.RIGHT;
                        break;
                    case KeyEvent.VK_SPACE:
                        planeController1.shot();
                        break;
                }
                planeController1.move(planeDirection);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                PlaneDirection planeDirection = PlaneDirection.NONE;
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        planeDirection = PlaneDirection.STOP_Y;
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                        planeDirection = PlaneDirection.STOP_X;
                        break;
                }
                planeController1.move(planeDirection);
            }
        });
            //Bat su kien shot cua plane2 = click
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                plane2Controller.shot();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        thread = new Thread(this);
        thread.start();
            //EnemyPlane shot per 2 sec
        while(true){
            try {
                for(EnemyPlaneController enemyPlaneController : this.enemyPlaneControllers) enemyPlaneController.shot();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }

    @Override
    public void update(Graphics g) {
        //super.update(g);
        if(backbufferedImage == null){
            backbufferedImage = new BufferedImage(400, 600, 1);
        }
        Graphics backbufferedGraphics = backbufferedImage.getGraphics();
        backbufferedGraphics.drawImage(backgroundImage, 0, 0, null);
        //paint Plane
        planeController1.paint(backbufferedGraphics);
        plane2Controller.paint(backbufferedGraphics);
        //paint EP
        for(EnemyPlaneController enemyPlaneController : this.enemyPlaneControllers) enemyPlaneController.paint(backbufferedGraphics);
        g.drawImage(backbufferedImage, 0, 0, null);
    }

    @Override
    public void run() {
        while (true){
            try {
                //Di chuyen = key
                Point mousePoint = MouseInfo.getPointerInfo().getLocation();
                PlaneDirection planeDirection = PlaneDirection.NONE;

                mousePoint.x -= getLocationOnScreen().x;
                mousePoint.y -= getLocationOnScreen().y;

                if(mousePoint.y - 5 > plane2Controller.getY()) {
                    planeDirection = PlaneDirection.DOWN;
                } else if(mousePoint.y + 5 < plane2Controller.getY()) {
                    planeDirection = PlaneDirection.UP;
                } else {
                    planeDirection = PlaneDirection.STOP_Y;
                }
                plane2Controller.move(planeDirection);

                if(mousePoint.x - 5 > plane2Controller.getX()) {
                    planeDirection = PlaneDirection.RIGHT;
                } else if(mousePoint.x + 5 < plane2Controller.getX()) {
                    planeDirection = PlaneDirection.LEFT;
                } else {
                    planeDirection = PlaneDirection.STOP_X;
                }
                plane2Controller.move(planeDirection);
                //cho 2 plane chay
                plane2Controller.run();
                planeController1.run();
                //phat hien va xu ly va cham
                try {
                    Iterator<EnemyPlaneController> it = enemyPlaneControllers.iterator();
                    while(it.hasNext()){
                        EnemyPlaneController tmp = it.next();
                        tmp.run();
                        for(BulletController bulletController : plane2Controller.getBulletControllerVector()) {
                            //dieu kien va cham
                            if (tmp.getGameObject().getX() < bulletController.getGameObject().getX() + bulletController.getGameObject().getWidth()
                                    && tmp.getGameObject().getX() + tmp.getGameObject().getWidth() > bulletController.getGameObject().getX()
                                    && tmp.getGameObject().getY() < bulletController.getGameObject().getY() + bulletController.getGameObject().getHeight()
                                    && tmp.getGameObject().getY() + tmp.getGameObject().getHeight() > bulletController.getGameObject().getY()){
                                it.remove();
                            }

                        }
                        for(BulletController bulletController : planeController1.getBulletControllerVector()) {
                            if (tmp.getGameObject().getX() < bulletController.getGameObject().getX() + bulletController.getGameObject().getWidth()
                                    && tmp.getGameObject().getX() + tmp.getGameObject().getWidth() > bulletController.getGameObject().getX()
                                    && tmp.getGameObject().getY() < bulletController.getGameObject().getY() + bulletController.getGameObject().getHeight()
                                    && tmp.getGameObject().getY() + tmp.getGameObject().getHeight() > bulletController.getGameObject().getY()){
                                it.remove();
                            }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /*TODO hoi xem tai sao dung nhu duoi lai loi~ */
                //for(EnemyPlaneController enemyPlaneController : this.enemyPlaneControllers){
                    //enemyPlaneController.run();
//                    for(BulletController bulletController : plane2Controller.getBulletControllerVector()){
//                        if (enemyPlaneController.getGameObject().getX() < bulletController.getGameObject().getX() + bulletController.getGameObject().getWidth()
//                                    && enemyPlaneController.getGameObject().getX() + enemyPlaneController.getGameObject().getWidth() > bulletController.getGameObject().getX()
//                                    && enemyPlaneController.getGameObject().getY() < bulletController.getGameObject().getY() + bulletController.getGameObject().getHeight()
//                                    && enemyPlaneController.getGameObject().getY() + enemyPlaneController.getGameObject().getHeight() > bulletController.getGameObject().getY())
//                            System.out.println("Enemy Plane " + enemyPlaneControllers.indexOf(enemyPlaneController) + "cracked");
//                            enemyPlaneControllers.remove(enemyPlaneControllers);
//                    }
                //}
                repaint();
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
