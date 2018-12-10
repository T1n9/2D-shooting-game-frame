package com.game.gameobject;

import com.game.main.Handler;

import java.awt.*;
import java.security.PrivateKey;

public class BasicBullet extends GameObject{

    private Handler handler;
    private int speed = 10;
    private int bullet_radius = 10;
    private double speed_y;
    private double speed_x;

    public BasicBullet(int x, int y, double angle, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = (int)(speed * (Math.cos(Math.toRadians(angle))));
        velY = (int)(speed * (Math.sin(Math.toRadians(angle))));
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }
    private void collision(){
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        int start_x = x-(bullet_radius/2);
        int start_y = y-(bullet_radius/2);
        g.fillOval(start_x,start_y,bullet_radius, bullet_radius);
    }

    @Override
    public Rectangle get_bounds() {
        return new Rectangle(x,y, bullet_radius, bullet_radius);
    }
}
