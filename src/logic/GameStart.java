package logic;

import javax.swing.*;

public class GameStart {

    public static void main(String[]args){
        GameLogic gl = new GameLogic(1000,1000);
        JFrame startFrame = new JFrame("Basic RPG");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setContentPane(gl);
        startFrame.pack();
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);
        startFrame.setResizable(false);
        gl.start();
    }

}

