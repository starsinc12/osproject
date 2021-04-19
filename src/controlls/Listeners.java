package controlls;

import elements.Hero;
import logic.GameLogic;
import managers.GameStateManager;

import java.awt.event.*;

public class Listeners implements KeyListener, MouseListener, MouseMotionListener {

    public static void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            Hero.up = true;
        }
        if (key == KeyEvent.VK_A) {
            Hero.left = true;
        }
        if (key == KeyEvent.VK_S) {
            Hero.down = true;
        }
        if (key == KeyEvent.VK_D) {
            Hero.right = true;
        }
        if(GameLogic.gsm.getCurrentState() == GameStateManager.PLAY) {
            if(key == KeyEvent.VK_ESCAPE){
                GameLogic.gsm.setPaused(true);
            }
            if(key == KeyEvent.VK_ENTER && GameLogic.gsm.isPaused()) {
                GameLogic.gsm.setPaused(false);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            Hero.up = false;
        }
        if (key == KeyEvent.VK_A) {
            Hero.left = false;
        }
        if (key == KeyEvent.VK_S) {
            Hero.down = false;
        }
        if (key == KeyEvent.VK_D) {
            Hero.right = false;
        }
        if (key == KeyEvent.VK_SPACE) {
            Hero.isAttack = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            GameLogic.leftMouse = true;
            GameStateManager.leftMouse = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            GameLogic.leftMouse = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        GameLogic.mouseX = e.getX();
        GameLogic.mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        GameLogic.mouseX = e.getX();
        GameLogic.mouseY = e.getY();
    }
}
