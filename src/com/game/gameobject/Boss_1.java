package com.game.gameobject;

import com.game.main.Game;
import com.game.main.Handler;
import com.game.utility.Utility;

public class Boss_1 extends BasicEnemy {

    static int boss_shooting_cycle_count = 0;

    public Boss_1(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
        character_img = toolkit.getImage(Utility.get_png("sunglasses.png"));
        character_got_hurt_image = toolkit.getImage(Utility.get_png("sad.png"));

        width = 100;
        height = 100;
        margin_x = width;
        margin_y = height;

        velX = 3;
        velY = 3;

        HP  = 2000;
        bullet_resistance = 10;
    }
    public void tick(){

        x += velX;
        y += velY;
        velX = bounce(x, velX, Game.WIDTH - width);
        velY = bounce(y, velY, Game.HEIGHT -height);

        int playerx = player.getX();
        int playery = player.getY();
        int diffy = playery - y;
        int diffx = playerx - x;
//        float distance = (float)Math.sqrt(diffx * diffx
//                + diffy * diffy);
        double shooting_angle = Utility.get_angle_from_vector(diffx, diffy);

        int bullet_start_x = x + Math.round((float) width/2);
        int bullet_start_y = y + Math.round((float) height/2);

//        int bullet_velX = Math.round(3*diffx/distance);
//        int bullet_velY = Math.round(3*diffy/distance);


        ++boss_shooting_cycle_count;
        if(boss_shooting_cycle_count == 55) {
            for(int i = -3; i< 3; ++i){
                Bullet_Boss_1 bullet =
                        new Bullet_Boss_1(bullet_start_x, bullet_start_y, shooting_angle + 3*i, ID.Bullet_Boss_1, handler);
//                        new Bullet_Boss_1(bullet_start_x, bullet_start_y, bullet_velX, bullet_velY, ID.Bullet_Boss_1, handler);
                handler.add_object(bullet);
            }
            boss_shooting_cycle_count = 0;
        }

        collision();

    }
}
