package com.game.gameobject;

import com.game.main.Handler;

abstract public class MonsterBullet extends BasicBullet {
    public MonsterBullet(int x, int y, int velX, int velY, ID id, Handler handler) {
        super(x, y, velX, velY, id, handler);
    }
    public MonsterBullet(int x, int y, double angle, ID id, Handler handler){
        super(x, y, angle, id, handler);
    }
}
