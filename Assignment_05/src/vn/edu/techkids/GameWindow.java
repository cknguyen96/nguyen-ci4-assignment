package vn.edu.techkids;
/* TODO packaage exanplation */

import vn.edu.techkids.controllers.*;
import vn.edu.techkids.models.GameConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by qhuydtvt on 4/24/2016.
 */
public class GameWindow extends Frame implements Runnable {
    //Background
    Image backgroundImage;
    //Luồng xử lý
    Thread thread;
    //Vẽ lên lên backbuffered >> vẽ lên Background
    Image backbufferImage;
    // player
    PlaneController planeController1;
    //Tất cả những gì liên quan đến màn hình >> models >> GameConfig
    GameConfig gameConfig;

    public GameWindow () {
        //Singleton
        this.gameConfig = GameConfig.getInst();
        //Hieu thi window
        this.setVisible(true);
        //Set size cho window
        this.setSize(gameConfig.getScreenWidth(), gameConfig.getScreenHeight());
        //Singleton player1
        this.planeController1 = PlaneController.getPlaneController1();

        //LOad background
        try {
            backgroundImage = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Bat su kien phim exit
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
                System.exit(0);

            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowClosed");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("windowIconified");
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
        //Bat su kien di chuyen Plane cua Player 1
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {

                PlaneDirection planeDirection = PlaneDirection.NONE;

                switch (e.getKeyCode()) {
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
                switch (e.getKeyCode()) {
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
        //cho chạy luồng
        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void update(Graphics g) {
        //Cấu trúc
        if(backbufferImage == null){
            //1 : type_Image (RPG)
            backbufferImage =  new BufferedImage(gameConfig.getScreenWidth(), gameConfig.getScreenHeight(), 1);
        }
        //Lấy Graphics của backbuffered
        Graphics backbufferGraphics = backbufferImage.getGraphics();
        //Vẽ lên backbuffered
        backbufferGraphics.drawImage(backgroundImage, 0, 0,
                gameConfig.getScreenWidth(), gameConfig.getScreenHeight(), null);
        //Vẽ Plane = paint(g)
        planeController1.paint(backbufferGraphics);
        EnemyPlaneControllerManager.getInst().paint(backbufferGraphics);
        EBurst_Damage_PlaneControllerManager.getInst().paint(backbufferGraphics);

        //Draw lai backbuffered len background
        g.drawImage(backbufferImage, 0, 0, null);
    }

    @Override
    public void run() {
        while(true){
            try {

                Point mousePoint = MouseInfo.getPointerInfo().getLocation();

                mousePoint.x -= getLocationOnScreen().x;
                mousePoint.y -= getLocationOnScreen().y;

                /* TODO player2 moving */
//                if(mousePoint.x - 5 > plane2.x) {
//                    plane2.dx = 5;
//                } else if(mousePoint.x + 5 < plane2.x) {
//                    plane2.dx = -5;
//                } else {
//                    plane2.dx = 0;
//                }
//
//                if(mousePoint.y - 5 > plane2.y) {
//                    plane2.dy = 5;
//                } else if(mousePoint.y + 5 < plane2.y) {
//                    plane2.dy = -5;
//                } else {
//                    plane2.dy = 0;
//                }
 //               plane2.run();

                CollisionPool.getInst().run();

                planeController1.run();
                EnemyPlaneControllerManager.getInst().run();
                EBurst_Damage_PlaneControllerManager.getInst().run();


                repaint();


                Thread.sleep(gameConfig.getThreadDelay());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
