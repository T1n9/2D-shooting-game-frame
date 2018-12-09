package com.game.main;

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
        int diffx = player.getX() - x;
        int diffy = player.getY() - y;
        int playerx = player.getX();
        int playery = player.getY();
        float distance = (float)Math.sqrt((x-playerx)*(x-playerx)
                + (y-playery)*(y-playery));
        velX = (int)(4*diffx/distance);
        velY = (int)(4*diffy/distance);
    }
}
