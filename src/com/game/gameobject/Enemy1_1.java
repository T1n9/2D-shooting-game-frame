package com.game.gameobject;

import javax.print.attribute.HashDocAttributeSet;

import com.game.main.Handler;
import com.game.utility.Utility;
import java.awt.*;

public class Enemy1_1 extends BasicEnemy {
    public Enemy1_1(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
        character_img = toolkit.getImage(Utility.get_png("anger.png"));
        character_got_hurt_image = toolkit.getImage(Utility.get_png("anger_shot.png"));

        width = 39;
        height = 39;
        margin_x = 65;
        margin_y = 88;

        velX = 2;
        velY = 2;

        HP  = 200;
        bullet_resistance = 10;
    }
}
