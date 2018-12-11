package com.game.gameobject;

import com.game.main.Handler;

public class PlayerBullet extends BasicBullet {
    public PlayerBullet(int x, int y, double angle, ID id, Handler handler) {
        super(x, y, angle, id, handler);
        speed = 10;
        bullet_radius = 10;
    }
}
