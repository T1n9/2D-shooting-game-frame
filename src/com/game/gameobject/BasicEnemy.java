package com.game.gameobject;

import com.game.main.Game;
import com.game.main.Handler;
import com.game.utility.Utility;
import java.awt.*;

public class BasicEnemy extends GameCharacter {

    protected int bullet_resistance = 10;
    protected Player player;

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        character_img = toolkit.getImage(Utility.get_png("goutou.png"));
        character_got_hurt_image = toolkit.getImage(Utility.get_png("goutou_cry.png"));

        width = 39;
        height = 39;
        margin_x = 45;
        margin_y = 70;
        HP = 100;

        velX = 2;
        velY = 2;
        got_hurt = false;
        this.handler = handler;
        for(GameObject object: handler.objects){
            if(object.getId() == ID.Player)
                player = (Player) object;
        }
    }

    protected void collision(){
        if(got_hurt){
            ++hurt_state_count;
            if(hurt_state_count > 40) {
                got_hurt = false;
                hurt_state_count = 0;
            }
        }
        //@todo 给enemy加上碰撞效果

        for(GameObject object: handler.objects){
            if(object instanceof BasicBullet){
                if(get_bounds().intersects(object.get_bounds())){
                    HP -= bullet_resistance;
                    got_hurt = true;
                }
            }
        }
    }

    //@todo 一些高级的怪物有能力发射子弹
    public void fire_shots() {
    }

    private int bounce(int movemoment, int vel, int max_limit){
        return ((movemoment <= 0 || movemoment >= max_limit) ? (-1)*vel : vel);
    }

    public void tick() {
        x += velX;
        y += velY;
        velX = bounce(x, velX, Game.WIDTH - margin_x);
        velY = bounce(y, velY, Game.HEIGHT -margin_y);
        collision();
    }

    public Rectangle get_bounds() {
        return new Rectangle(x,y, width-3, height-3);
    }
}
