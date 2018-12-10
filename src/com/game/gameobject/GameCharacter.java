package com.game.gameobject;

import com.game.main.Handler;

import java.awt.*;

public abstract class GameCharacter extends GameObject {

    protected int hurt_state_count = 0;
    protected boolean got_hurt = false;
    protected Image character_img;
    protected Image character_got_hurt_image;
    protected int height;
    protected int width;
    protected int margin_x;
    protected int margin_y;
    protected Handler handler;
    protected int HP;

    public GameCharacter(int x, int y, ID id) {
        super(x, y, id);
        velX = velY = 0;
    }
    abstract public void fire_shots();
    public int get_hp(){
        return HP;
    }
    protected abstract void collision();
    public void render(Graphics g) {
        Image character;
        if(!got_hurt)
            character = character_img;
        else    character = character_got_hurt_image;
        g.drawImage(character, x, y, width, height, null);
    }

}
