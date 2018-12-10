package com.game.gameobject;

import com.game.main.Handler;
import com.game.utility.Utility;
public class Enemy1_2 extends BasicEnemy {


    public Enemy1_2(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
        character_img = toolkit.getImage(Utility.get_png("question.png"));
        character_got_hurt_image = toolkit.getImage(Utility.get_png("puke.png"));

        width = 39;
        height = 39;
        margin_x = 65;
        margin_y = 88;

        velX = 2;
        velY = 2;

        HP  = 200;
        bullet_resistance = 10;
    }
    public void tick(){
        super.tick();
        int playerx = player.getX();
        int playery = player.getY();
        int diffy = playery - y;
        int diffx = playerx - x;
        float distance = (float)Math.sqrt(diffx * diffx
                + diffy * diffy);
        velX = Math.round(3*diffx/distance);
        velY = Math.round(3*diffy/distance);
    }
}
