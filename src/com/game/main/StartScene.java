package com.game.main;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class StartScene extends AbstractScene {


    public StartScene(Game game, Handler handler, boolean running){
        super(game, handler, running);
        own_scene_id = Scene_ID.Start_Scene;
    }

    public void add_characters(Handler handler) {
        handler.add_object(new Player(Game.WIDTH/2-32, Game.HEIGHT/2, ID.Player, handler));
        handler.add_object(new BasicEnemy(Game.WIDTH/2+32, Game.HEIGHT/2, ID.BasicEnemy, handler));
    }
    public void open_scene(){
        AbstractScene.current_scene_id = Scene_ID.Start_Scene;
        super.open_scene();
    }
    public static Rectangle gate_north_east(){
        return new Rectangle(Game.WIDTH-90, 0, 90, 50);
    }


    protected void draw_gate(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(Game.WIDTH-90,0, 90,50);
//        Font f1 = new Font("Helvetica",Font.PLAIN,18);
        Font f2 = new Font("Helvetica", Font.BOLD,15);
//        Font f3 = new Font("Helvetica",Font.ITALIC,12);
//        Font f4 = new Font("Courier",Font.PLAIN,12);
//        Font f5 = new Font("TimesRoman", Font.BOLD+Font.ITALIC,14);
//        Font f6 = new Font("Dialog",Font.ITALIC,14);
//        g.setFont(f1);drawString("18pt plain Helvetica",5,20);
//        g.setFont(f2);drawString("10pt bold Helvetica",5,43);
//        g.setFont(f3);drawString("12pt italic Helvetica",5,58);
//        g.setFont(f4);drawString("12pt plain courier",5,75);
//        g.setFont(f5);drawString("14pt bold & italic times Roman",5,92);
        g.setColor(Color.white);
        g.setFont(f2);
        g.drawString("传送门",Game.WIDTH-80,25);
    }

    protected void draw_background(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
    }

    protected void draw_characters(Graphics g) {
        handler.render(g);
    }

}
