package com.game.mousekeyboard;

import com.game.gameobject.GameObject;
import com.game.gameobject.ID;
import com.game.gameobject.Player;
import com.game.main.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.FileAlreadyExistsException;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] key_down = {false, false, false, false};

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        for(GameObject object : handler.objects){
            if(object.getId() == ID.Player){
                if(k == KeyEvent.VK_W)  {   object.setVelY(-1 * Player.speed); key_down[0] = true;   }
                else if(k == KeyEvent.VK_A)  {  object.setVelX(-1 * Player.speed); key_down[1] = true;   }
                else if(k == KeyEvent.VK_S)  {  object.setVelY(Player.speed);   key_down[2] = true; }
                else if(k == KeyEvent.VK_D)  {  object.setVelX(Player.speed);   key_down[3] = true; }
            }
        }
        if(k == KeyEvent.VK_ESCAPE) System.exit(1);
    }
    public void keyReleased(KeyEvent e){
        int k = e.getKeyCode();
        for(GameObject object : handler.objects){
            if(object.getId() == ID.Player){
                if(k == KeyEvent.VK_W)  key_down[0] = false;
                else if(k == KeyEvent.VK_A)  key_down[1] = false;
                else if(k == KeyEvent.VK_S)  key_down[2] = false;
                else if(k == KeyEvent.VK_D)  key_down[3] = false;

                if(!key_down[0] && !key_down[2])    object.setVelY(0);
                if(!key_down[1] && !key_down[3])    object.setVelX(0);

            }
        }
    }
}
