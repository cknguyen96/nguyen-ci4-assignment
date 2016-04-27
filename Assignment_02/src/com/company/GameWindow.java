package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 4/26/2016.
 */
public class GameWindow extends Frame implements Runnable{

    Image backgroundImage;
    Image backbufferedImage;
    Plane plane1;
    Plane plane2;
    EnemyPlane[] enemyPlane = new EnemyPlane[7];
    //EnemyPlane enemyPlane;
    Image tmp;
    Thread thread;

    public GameWindow(){
        this.setVisible(true);
        this.setSize(400, 600);
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
            tmp = ImageIO.read(new File("resources/plane2.png"));
            plane1 = new Plane(100, 500, tmp);
            tmp = ImageIO.read(new File("resources/plane4.png"));
            plane2 = new Plane(200, 500, tmp);
            //tmp = ImageIO.read(new File("resources/plane1.png"));
            //enemyPlane = new EnemyPlane(100, 100, tmp);
            for(int i = 0; i <= 4; i++){
                tmp = ImageIO.read(new File("resources/plane1.png"));
                enemyPlane[i] = new EnemyPlane((10 + 85*i), 0, tmp);
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
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        plane1.dy = -5;
                        break;
                    case KeyEvent.VK_DOWN:
                        plane1.dy = 5;
                        break;
                    case KeyEvent.VK_LEFT:
                        plane1.dx = -5;
                        break;
                    case KeyEvent.VK_RIGHT:
                        plane1.dx = 5;
                        break;
                    case KeyEvent.VK_SPACE:
                        plane1.shot();
                        break;
                    //plane 2 di chuyen bagn WASD
                    case KeyEvent.VK_W:
                        plane2.dy = -5;
                        break;
                    case KeyEvent.VK_S:
                        plane2.dy = 5;
                        break;
                    case KeyEvent.VK_A:
                        plane2.dx = -5;
                        break;
                    case KeyEvent.VK_D:
                        plane2.dx = 5;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        plane1.dy = 0;
                        break;
                    case KeyEvent.VK_DOWN:
                        plane1.dy = 0;
                        break;
                    case KeyEvent.VK_LEFT:
                        plane1.dx = 0;
                        break;
                    case KeyEvent.VK_RIGHT:
                        plane1.dx = 0;
                        break;
                    //plane 2 dung di chuyen
                    case KeyEvent.VK_W:
                        plane2.dy = 0;
                        break;
                    case KeyEvent.VK_S:
                        plane2.dy = 0;
                        break;
                    case KeyEvent.VK_A:
                        plane2.dx = 0;
                        break;
                    case KeyEvent.VK_D:
                        plane2.dx = 0;
                        break;
                }
            }
        });
        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void update(Graphics g) {
        //super.update(g);
        if (backbufferedImage == null){
            backbufferedImage = new BufferedImage(400, 600, 1);
        }
        Graphics backbufferedGraphics =  backbufferedImage.getGraphics();
        backbufferedGraphics.drawImage(backgroundImage, 0, 0, null);
        plane1.paint(backbufferedGraphics);
        plane2.paint(backbufferedGraphics);
        //enemyPlane.paint(backbufferedGraphics);
        for (int i = 0; i <= 4; i++) enemyPlane[i].paint(backbufferedGraphics);
        g.drawImage(backbufferedImage, 0, 0, null);

    }

    @Override
    public void run() {
        while(true){
            try {
                plane1.run();
                plane2.run();
                for (int i = 0; i <= 4; i++){
                    enemyPlane[i].run();
                }
                repaint();
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
