package com.game.scene;

import com.game.gameobject.GameObject;
import com.game.gameobject.HUD;
import com.game.gameobject.ID;
import com.game.main.*;
import com.game.main.GameMenu;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Iterator;


public abstract class AbstractScene{

    public static Scene_ID current_scene_id = null;

    protected Handler handler;
    protected Game game;
    protected HUD hud = HUD.get_instance();
    private boolean running;
    protected Scene_ID own_scene_id;
    private MapCleaner spawner;

    public enum MODE{
        Menu,
        Game
    }
    private static MODE mode = MODE.Menu;
    public static void to_game_mode(){
        mode = MODE.Game;
    }
    public static void to_menu_mode(){
        mode = MODE.Menu;
    }
    private GameMenu menu = GameMenu.get_instance();

    public AbstractScene(Game game, Handler handler, boolean running){
        this.game = game;
        this.game.addKeyListener(menu);
        this.handler = handler;
        this.running = running;
        this.spawner = new MapCleaner(handler);
    }

    public abstract void add_characters(Handler handler);

    private void open_new_scene(AbstractScene new_scane){
        leave_scene();
        new_scane.open_scene();
    }

    private void leave_scene(){
        handler.objects.removeIf(x -> x.getId() != ID.HUD);
    }

    protected abstract void draw_gate(Graphics g);
    protected abstract void draw_background(Graphics g);
    protected abstract void draw_characters(Graphics g);

    public void open_scene(){
        add_characters(handler);
        handler.add_object(HUD.get_instance());

        long lastTime = System.nanoTime();
        double amount_of_ticks = 60.0;
        double ns = 1000000000 / amount_of_ticks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frame = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                --delta;
            }
            if(running)
                render();
            ++frame;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
//                System.out.println("FPS: " + frame);
                frame = 0;
            }
        }
    }



    private void go_to_scene(Scene_ID scene_id){
        switch (scene_id){
            case Scene_1_1:
                open_new_scene(new Scene1_1(game, handler, running));
                break;
            case Start_Scene:
                open_new_scene(new StartScene(game, handler, running));
                break;
        }
    }

    private void scene_tick(){
        if(current_scene_id != own_scene_id){
            go_to_scene(current_scene_id);
        }
    }

    protected void tick(){
        if(mode == MODE.Menu)
            menu.tick();
        else if(mode == MODE.Game){
            handler.tick();
            spawner.tick();
            scene_tick();
        }
    }

    protected void render(){
        BufferStrategy bs = game.getBufferStrategy();
        if(bs == null){
            game.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        if(mode == MODE.Menu){
            draw_background(g);
            menu.render(g);
        }
        else if(mode == MODE.Game){
            draw_background(g);
            draw_gate(g);
            draw_characters(g);
        }
        g.dispose();
        bs.show();
    }
}
