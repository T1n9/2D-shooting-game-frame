package com.game.gameobject;

import com.game.main.Handler;

public class Bullet_Boss_1 extends MonsterBullet {
//    public Bullet_Boss_1(int x, int y, int velX, int velY, ID id, Handler handler) {
//        super(x, y, velX, velY, id, handler);
//        speed = 12;
//        bullet_radius = 20;
//    }
    public Bullet_Boss_1(int x, int y, double angle, ID id, Handler handler){
        super(x, y, angle, id, handler);
        speed = 12;
        bullet_radius = 20;
    }
}
