package com.game.scene;

import java.awt.*;
import java.awt.image.BufferStrategy;

import com.game.gameobject.Enemy1_1;
import com.game.gameobject.Enemy1_2;
import com.game.gameobject.ID;
import com.game.gameobject.Player;
import com.game.main.*;
import com.game.utility.Utility;

public class Scene1_1 extends AbstractScene {


    public Scene1_1(Game game, Handler handler, boolean running){
        super(game, handler, running);
        own_scene_id = Scene_ID.Scene_1_1;
    }
    public void add_characters(Handler handler) {
        handler.add_object(new Player(Game.WIDTH/2-32, Game.HEIGHT/2, ID.Player, handler));
        for(int i = 0; i<5; ++i){
            handler.add_object(new Enemy1_1(Utility.r.nextInt(Game.WIDTH), Utility.r.nextInt(Game.HEIGHT), ID.Enemy_1_1, handler));
        }
        handler.add_object(new Enemy1_2(Utility.r.nextInt(Game.WIDTH), Utility.r.nextInt(Game.HEIGHT), ID.Enemy_1_2, handler));
    }

    protected void draw_gate(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(Game.WIDTH-150,Game.HEIGHT-80, 150,80);
        Font f = new Font("Helvetica", Font.BOLD,15);
        g.setColor(Color.red);
        g.setFont(f);
        g.drawString("Gate to Main Hall",Game.WIDTH-150,Game.HEIGHT-60);
    }
    public static Rectangle gate_south_east(){
        return new Rectangle(Game.WIDTH-100, Game.HEIGHT-80, 100, 80);
    }

    protected void draw_background(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
    }
    protected void draw_characters(Graphics g){
        handler.render(g);
    }
}
