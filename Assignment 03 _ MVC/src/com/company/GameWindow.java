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

/**
 * Created by Admin on 5/3/2016.
 */
public class GameWindow extends Frame implements Runnable{
    Image backgroundImage;
    Image backbufferedImage;
    Thread thread;
    PlaneController planeController1;
    Plane2Controller plane2Controller;
    EnemyPlaneController[] enemyPlaneController = new EnemyPlaneController[7];
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

        try {
            backgroundImage = ImageIO.read(new File("resources/background.png"));
            for(int i = 0; i <= 4; i++){
                Plane enemyPlane = new Plane(10+85*i, 0, EnemyPlaneController.DEFAULT_WIDTH, EnemyPlaneController.DAFAULT_HEIGHT);
                ImageDrawer tmpDrawer = new ImageDrawer("resources/plane1.png");
                enemyPlaneController[i] = new EnemyPlaneController(enemyPlane, tmpDrawer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        while(true){
            try {
                System.out.println("aaa");
                for(int i = 0; i <= 4; i++){
                    enemyPlaneController[i].shot();
                }
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
        planeController1.paint(backbufferedGraphics);
        plane2Controller.paint(backbufferedGraphics);
        for(int i=0; i<=4; i++){
            enemyPlaneController[i].paint(backbufferedGraphics);
        }
        g.drawImage(backbufferedImage, 0, 0, null);
    }

    @Override
    public void run() {
        while (true){
            try {
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

                plane2Controller.run();
                planeController1.run();
                for(int i=0; i<=4; i++){
                    enemyPlaneController[i].run();
                }
                repaint();
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
