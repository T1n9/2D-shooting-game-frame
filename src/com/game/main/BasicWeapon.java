package com.game.main;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class BasicWeapon extends GameObject {
    protected Image weapon_image;
    protected int height;
    protected int width;
    protected Handler handler;
    private double mouse_angle = 0;

    private int player_pos_x;
    private int player_pos_y;
    private int player_height;
    private int player_width;

    public void shots_fired(){

    }

    public int get_weapon_length(){
        return width;
    }

    public BasicWeapon(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        weapon_image = toolkit.getImage(Utility.get_png("sniper.png"));
        height = 99;
        width = 102;
        this.handler = handler;
    }

    public void tick(int x, int y, double mouse_angle, int player_width, int player_height) {
        this.mouse_angle = mouse_angle;
        this.player_pos_x = x;
        this.player_pos_y = y;
        this.player_width = player_width;
        this.player_height = player_height;
    }

    public void tick() {
    }

    public void render(Graphics g) {
        double rads = Math.toRadians(mouse_angle);

        AffineTransform at = new AffineTransform();
        double rotate_x = player_pos_x + (double)player_width/2;
        double rotate_y = player_pos_y + (double)player_height/2;
        at.rotate(rads, rotate_x, rotate_y);
        at.translate(player_pos_x + 20, player_pos_y - 35);

        Graphics2D g2d = (Graphics2D)g;
        AffineTransform oldXForm = g2d.getTransform();

        g2d.setTransform(at);
        g2d.drawImage(weapon_image, 0, 0, width, height, null);

        g2d.setTransform(oldXForm);
    }

    public Rectangle get_bounds() {
        return null;
    }
}
