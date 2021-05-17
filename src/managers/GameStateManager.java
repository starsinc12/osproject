package managers;

import GameStates.*;
import elements.Hero;
import logic.GameLogic;

import java.awt.*;

public class GameStateManager {

    private boolean paused;
    private boolean isSettings;
    private boolean gameOver;
    private boolean iswin;
    private boolean isUpgrading;
    public boolean isPaused() {
        return paused;
    }
    public void setPaused(boolean b) {
        paused = b;
    }
    public void setGameOver(boolean g) { gameOver = g;}
    public void setWin(boolean w) {
        iswin = w;
    }
    public void setSettings(boolean s){isSettings = s;}
    public void setUpgrading(boolean u) {
        isUpgrading = u;
    }

    private PauseState pauseState;
    private GameOverState gameOverState;
    private WinState winState;
    private SettingState settingState;
    private UpgradeState upgradeState;

    private GameState[] gameStates;
    public GameState[] getGameStates() {
        return gameStates;
    }

    private int currentState;
    public int getCurrentState() {
        return currentState;
    }

    private int previousState;

    public static int mouseX;
    public static int mouseY;
    public static boolean leftMouse;

    public static final int NUM_STATES = 4;
    public static final int INVENTORY = 0;
    public static final int TALENTS = 1;
    public static final int WORLD = 2;
    public static final int PLAY = 3;

    public Cursor getCursor() {
        return gameStates[currentState].getCursor();
    }

    public GameStateManager() {
        paused = false;
        gameOver = false;
        isSettings= false;
        iswin=false;

        pauseState = new PauseState(this);
        gameOverState = new GameOverState(this);
        winState = new WinState(this);
        settingState= new SettingState(this);
        upgradeState = new UpgradeState(this);
        gameStates = new GameState[NUM_STATES];

        setState(WORLD);
    }

    public void setState(int i) {
        previousState = currentState;
        //unloadState(previousState);
        currentState = i;

        if(i == INVENTORY) {
            gameStates[i] = new InventoryState(this);
            gameStates[i].init();
        }
        else if(i == TALENTS) {
            gameStates[i] = new TalentsState(this);
            gameStates[i].init();
        }
        else if(i == WORLD) {
            gameStates[i] = new WorldState(this);
            gameStates[i].init();
        }
        else if(i == PLAY) {
            gameStates[i] = new PlayState(this);
            gameStates[i].init();
        }
    }

    public void unloadState(int i) {
        gameStates[i] = null;
    }

    private void setStateResumeGame() {
        previousState = currentState;
        currentState = GameStateManager.PLAY;
    }

    public void update() {
        if(paused) {
            pauseState.update();
            if(GameLogic.leftMouse){

                if(PauseState.isResume) {
                    GameLogic.gsm.setStateResumeGame();
                    paused = false;
                }
                if (PauseState.isQuit) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Hero.setX(GameLogic.WIDTH / 2);
                    Hero.setY(GameLogic.HEIGHT - 20);
                    GameLogic.gsm.setState(GameStateManager.WORLD);
                    paused = false;
                }
            }
        } else if(isUpgrading){
            upgradeState.update();
            if(GameLogic.leftMouse){
                if(upgradeState.isQuit) {
                    GameLogic.gsm.setStateResumeGame();
                    isUpgrading = false;
                }
            }
        }  else if (isSettings) {
            settingState.update();
            if(GameLogic.leftMouse){
                if(SettingState.isQuit){
                    Hero.setX(GameLogic.WIDTH / 2);
                    Hero.setY(GameLogic.HEIGHT - 20);
                    GameLogic.gsm.setState(GameStateManager.WORLD);
                    isSettings = false;
                }
                if(SettingState.valueup){
                    if(GameLogic.volume<0.99){
                        GameLogic.volume+=0.01;
                    }
                }
                if(SettingState.valuedown){
                    if(GameLogic.volume>0.01) {
                        GameLogic.volume -= 0.01;
                    }
                }
            }

        } else if (gameOver) {
            gameOverState.update();
            if(GameLogic.leftMouse){
                if (GameOverState.isQuit) {
                    Hero.setX(GameLogic.WIDTH / 2);
                    Hero.setY(GameLogic.HEIGHT - 20);
                    GameLogic.gsm.setState(GameStateManager.WORLD);
                    gameOver = false;
                }
            }
        } else if (iswin) {
            winState.update();
            if(GameLogic.leftMouse){
                if (WinState.isQuit) {
                    Hero.setX(GameLogic.WIDTH / 2);
                    Hero.setY(GameLogic.HEIGHT - 20);
                    GameLogic.gsm.setState(GameStateManager.WORLD);
                    iswin = false;
                }
            }
        } else if(gameStates[currentState] != null) {
            gameStates[currentState].update();
            mouseX = GameLogic.mouseX;
            mouseY = GameLogic.mouseY;
        }
    }

    public void draw(Graphics2D g) {
        if(paused) {
            pauseState.draw(g);
        } else if (isUpgrading) {
            upgradeState.draw(g);
        } else if (isSettings) {
            settingState.draw(g);
        } else if (gameOver) {
            gameOverState.draw(g);
        } else if (iswin) {
            winState.draw(g);
        } else if (gameStates[currentState] != null) {
            gameStates[currentState].draw(g);
        }
    }
}