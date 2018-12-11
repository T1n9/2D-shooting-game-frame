package com.game.gameobject;

import com.game.main.Handler;

import javax.swing.*;
import java.awt.*;


public class BasicBullet extends GameObject{

    private Handler handler;
    public int speed = 10;
    public int bullet_radius = 10;
    private double speed_y;
    private double speed_x;


    public BasicBullet(int x, int y, double angle, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = (int)(speed * (Math.cos(Math.toRadians(angle))));
        velY = (int)(speed * (Math.sin(Math.toRadians(angle))));
    }
    public BasicBullet(int x, int y, int velX, int velY, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
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
        int pos_x = x-(bullet_radius/2);
        int pos_y = y-(bullet_radius/2);
        g.fillOval(pos_x,pos_y,bullet_radius, bullet_radius);
    }

    @Override
    public Rectangle get_bounds() {
        return new Rectangle(x,y, bullet_radius, bullet_radius);
    }
}
