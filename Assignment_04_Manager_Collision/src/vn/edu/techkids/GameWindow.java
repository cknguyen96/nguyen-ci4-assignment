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
    Image backgroundImage;
    Thread thread;
    Image backbufferImage;
    PlaneController planeController1;
    Plane2Controller plane2Controller;
    GameConfig gameConfig;

    public GameWindow () {
        this.gameConfig = GameConfig.getInst();

        this.setVisible(true);
        this.setSize(gameConfig.getScreenWidth(),
                gameConfig.getScreenHeight());

        this.planeController1 = PlaneController.getPlaneController1();
        this.plane2Controller = Plane2Controller.getInst();


        try {
            backgroundImage = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {
//                System.out.println("keyPressed");
//                System.out.println(e.getKeyCode());

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
                /*TODO static explanation*/
            }

            @Override
            public void keyReleased(KeyEvent e) {
//                System.out.println("keyReleased");

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
        this.addMouseMotionListener(new MouseMotionListener(){

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
//                dx2 = e.getX();
//                dy2 = e.getY();
//x2 => e.getX(); y2 => e.getY()

//                if(e.getX() - 5 > x2) {
//                    dx2 = 5;
//                } else if(e.getX() +5 < x2) {
//                    dx2 = -5;
//                } else {
//                    dx2 = 0;
//                }
               // System.out.println("mouseMoved");
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

    }

    @Override
    public void update(Graphics g) {

        if(backbufferImage == null){
            backbufferImage =  new BufferedImage(gameConfig.getScreenWidth(),
                    gameConfig.getScreenHeight(), 1);
        }
        Graphics backbufferGraphics = backbufferImage.getGraphics();
        backbufferGraphics.drawImage(backgroundImage, 0, 0,
                gameConfig.getScreenWidth(), gameConfig.getScreenHeight(), null);

        planeController1.paint(backbufferGraphics);
        plane2Controller.paint(backbufferGraphics);
        EnemyPlaneControllerManager.getInst().paint(backbufferGraphics);


        g.drawImage(backbufferImage, 0, 0, null);
    }

    @Override
    public void run() {
        long count = 0;

        while(true){
            try {

                Point mousePoint = MouseInfo.getPointerInfo().getLocation();

                mousePoint.x -= getLocationOnScreen().x;
                mousePoint.y -= getLocationOnScreen().y;

                /* TODO player2 moving */
                PlaneDirection planeDirection = PlaneDirection.NONE;
                if(mousePoint.x - 5 > plane2Controller.getGameObject().getX()) {
                    planeDirection = PlaneDirection.RIGHT;
                } else if(mousePoint.x + 5 < plane2Controller.getGameObject().getX()) {
                    planeDirection = PlaneDirection.LEFT;
                } else {
                    planeDirection = PlaneDirection.STOP_X;
                }
                plane2Controller.move(planeDirection);

                if(mousePoint.y - 5 > plane2Controller.getGameObject().getY()) {
                    planeDirection = PlaneDirection.DOWN;
                } else if(mousePoint.y + 5 < plane2Controller.getGameObject().getY()) {
                    planeDirection = PlaneDirection.UP;
                } else {
                    planeDirection = PlaneDirection.STOP_Y;
                }
                plane2Controller.move(planeDirection);

                CollisionPool.getInst().run();

                planeController1.run();
                plane2Controller.run();
                EnemyPlaneControllerManager.getInst().run();


                repaint();


                Thread.sleep(gameConfig.getThreadDelay());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
