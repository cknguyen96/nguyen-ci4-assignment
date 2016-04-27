import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 4/24/2016.
 */
public class GameWindow extends Frame implements Runnable {


    Plane plane1 ;
    Plane plane2 ;
    EnemyPlane enemyPlane1;
    EnemyPlane enemyPlane2;
    EnemyPlane enemyPlane3;
    Image backgroundImage;
    Thread thread;
    Image backbufferImage;
    public GameWindow() {
       // thread = new Thread();
        this.setVisible(true); //Hien thi window
        this.setSize(400,600);
        this.addWindowListener(new WindowListener() { //  close
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
            Image plane1Image = ImageIO.read(new File("resources/plane4.png"));
            Image plane2Image = ImageIO.read(new File("resources/plane3.png"));
            Image planeEnemyImage = ImageIO.read(new File("resources/plane1.png"));
            plane1 = new Plane(100 , 500, plane1Image);
            plane2 = new Plane(300 , 500, plane2Image);
            enemyPlane1 = new EnemyPlane(100 - EnemyPlane.WIDTH/2, 0, planeEnemyImage);
            enemyPlane2 = new EnemyPlane(200 - EnemyPlane.WIDTH/2, 0, planeEnemyImage);
            enemyPlane3 = new EnemyPlane(300 - EnemyPlane.WIDTH/2, 0, planeEnemyImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
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
                    case KeyEvent.VK_SPACE:
                        plane1.shot();
                        break;
                    case KeyEvent.VK_ENTER:
                        plane2.shot();
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                switch(e.getKeyCode()) {
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
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {


            }
        });
        thread = new Thread(this);
        thread.start();
        //repaint();
    }

    @Override
    public void update(Graphics g) {
        if(backbufferImage == null) {
            backbufferImage = new BufferedImage(400, 600, 1);
        }
        Graphics backbufferGraphics = backbufferImage.getGraphics();
        //super.update(g);
        backbufferGraphics.drawImage(backgroundImage, 0, 0, null);
        plane1.paint(backbufferGraphics);
        plane2.paint(backbufferGraphics);
        enemyPlane1.paint(backbufferGraphics);
        enemyPlane2.paint(backbufferGraphics);
        enemyPlane3.paint(backbufferGraphics);
        g.drawImage(backbufferImage, 0, 0, null);
    }

    @Override
    public void run() {
        while(true) {
                try {
                    plane2.run();
                    plane1.run();
                    enemyPlane1.run();
                    enemyPlane2.run();
                    enemyPlane3.run();
                    repaint();
                    Thread.sleep(17);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}
