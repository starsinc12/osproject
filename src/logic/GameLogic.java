package logic;

import GameStates.PlayState;
import controlls.Listeners;
import elements.Hero;
import managers.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameLogic extends JPanel implements Runnable{


    public static int WIDTH;
    public static int HEIGHT;

    public static int mouseX;
    public static int mouseY;
    public static boolean leftMouse;

    private Thread thread;
    private boolean isRunning;
    private final int FPS = 30;
    private final int TARGET_TIME = 1000 / FPS;

    private BufferedImage img;
    private static Graphics2D graph;

    public static Graphics2D getGraph() {
        return graph;
    }

    public static GameStateManager gsm;


    public static Hero hero;

    public GameLogic(int w, int h) {
        super();
        WIDTH = w;
        HEIGHT = h;
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(new Listeners());
        addMouseMotionListener(new Listeners());
        addMouseListener(new Listeners());
    }

    private void init() {
        isRunning = true;
        img = new BufferedImage(WIDTH, HEIGHT, 1);
        graph = (Graphics2D) img.getGraphics();
        hero = new Hero();
        gsm = new GameStateManager();

    }

    @Override
    public void run() {
        init();
        long start;
        long elapsed;
        long wait;
        while(isRunning) {
            start = System.nanoTime();
            update();
            draw();
            drawToScreen();
            setCursor(gsm.getCursor());
            elapsed = System.nanoTime() - start;
            wait = TARGET_TIME - elapsed / 1000000;
            if(wait < 0) wait = TARGET_TIME;
            try {
                thread.sleep(wait);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void draw() {
        gsm.draw(graph);
    }

    private void update() {
        gsm.update();
        Listeners.update();
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g2.dispose();
    }

    public void start() {
        thread =  new Thread(this);
        thread.start();
    }
}
