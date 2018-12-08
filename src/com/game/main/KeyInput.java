package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        for(GameObject object : handler.objects){
            if(object.getId() == ID.Player){
                if(k == KeyEvent.VK_W)  object.setVelY(-1 * Player.speed);
                else if(k == KeyEvent.VK_A)  object.setVelX(-1 * Player.speed);
                else if(k == KeyEvent.VK_S)  object.setVelY(Player.speed);
                else if(k == KeyEvent.VK_D)  object.setVelX(Player.speed);
            }
        }
        if(k == KeyEvent.VK_ESCAPE) System.exit(1);
    }
    public void keyReleased(KeyEvent e){
        int k = e.getKeyCode();
        for(GameObject object : handler.objects){
            if(object.getId() == ID.Player){
                if(k == KeyEvent.VK_W)  object.setVelY(0);
                else if(k == KeyEvent.VK_A)  object.setVelX(0);
                else if(k == KeyEvent.VK_S)  object.setVelY(0);
                else if(k == KeyEvent.VK_D)  object.setVelX(0);
            }
        }
    }
}
