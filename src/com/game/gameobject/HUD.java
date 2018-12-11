package com.game.gameobject;

import javax.crypto.interfaces.PBEKey;

import com.game.utility.Utility;
import java.awt.*;

public final class HUD extends GameObject{
    public static int HEALTH = 100;
    private int green_value = 255;

    private int score = 0;
    private int level = 1;

    private static HUD hud = new HUD();
    public static HUD get_instance(){
        return hud;
    }

    public HUD(int x, int y, ID id) {
        super(x, y, id);
    }
    public HUD(){
        super(25, 25, ID.HUD);
    }

    public int get_score(){ return score;   }
    public int get_level(){ return level;   }
    public void set_score(int score){   this.score = score; }
    public void set_level(int level){   this.level = level; }

    public void tick(){

        score++;

        HEALTH = Utility.clamp(HEALTH, 100);
        green_value = Utility.clamp(green_value, 255);
        green_value = HEALTH * 2;
    }
    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(120, green_value,0));
        g.fillRect(15, 15, HEALTH*2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);

        g.drawString("Score: " + score, 15, 65);
        g.drawString("Level: " + level, 15, 80);

    }

    public Rectangle get_bounds() {
        return null;
    }
}
