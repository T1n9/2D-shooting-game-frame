package com.game.main;

import com.game.gameobject.GameObject;
import com.game.gameobject.ID;
import com.game.gameobject.Player;
import com.game.mousekeyboard.KeyInput;
import com.game.scene.AbstractScene;

import javax.swing.tree.FixedHeightLayoutCache;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameMenu extends KeyAdapter {


    private boolean show_about = false;

    private static GameMenu menu = new GameMenu();
    public static GameMenu get_instance(){
        return menu;
    }

    private enum Menu_option {
        Play,
        About,
        Exit
    }

    private Menu_option menu_selection = Menu_option.Play;


    public void tick(){

    }

    private void draw_button(Graphics g, String  name, int rect_y, int string_y){
        g.drawRect(Game.WIDTH/3, rect_y,Game.HEIGHT/3,64);
        g.drawString(name, Game.WIDTH/3 + 64, string_y);
        g.setColor(Color.black);
    }


    public void render(Graphics g) {
        if (!show_about) {
            g.setColor(Color.black);
            Font fnt = new Font("arial", 1, 50);
            g.setFont(fnt);
            g.drawString("Menu", Game.WIDTH / 3 + 50, 70);

            g.setFont(new Font("arial", 1, 45));

            if (menu_selection == Menu_option.Play)
                g.setColor(Color.red);
            draw_button(g, "Play", Game.HEIGHT / 3, Game.HEIGHT / 3 + 50);
            if (menu_selection == Menu_option.About)
                g.setColor(Color.red);
            draw_button(g, "About", Game.HEIGHT / 3 + 64 * 2, Game.HEIGHT / 3 + 45 + 64 * 2);
            if (menu_selection == Menu_option.Exit)
                g.setColor(Color.red);
            draw_button(g, "Quit", Game.HEIGHT / 3 + 64 * 4, Game.HEIGHT / 3 + 40 + 64 * 4);
        }else show_about(g);
    }
    private void show_about(Graphics g){
        g.setColor(Color.green);
        g.setFont(new Font("arial", 1, 45));
        g.drawString("Developer: Ting Fu", 100,100);
        g.drawString("Email: futinn@gmail.com", 100, 150);
    }

    public void keyPressed(KeyEvent e) {
        if(show_about){
            show_about = false;
        }else {
            int k = e.getKeyCode();
            if (k == KeyEvent.VK_ENTER) {
                switch (menu_selection) {
                    case Play:
                        AbstractScene.to_game_mode();
                        break;
                    case About:
                        show_about = true;
                        break;
                    case Exit:
                        System.exit(0);
                }
            }
            if (k == KeyEvent.VK_DOWN) {
                if (menu_selection == Menu_option.Play)
                    menu_selection = Menu_option.About;
                else if (menu_selection == Menu_option.About)
                    menu_selection = Menu_option.Exit;
                else menu_selection = Menu_option.Play;
            }
            if (k == KeyEvent.VK_UP)
                if (menu_selection == Menu_option.Play)
                    menu_selection = Menu_option.Exit;
                else if (menu_selection == Menu_option.About)
                    menu_selection = Menu_option.Play;
                else menu_selection = Menu_option.About;
        }
    }
    public void keyReleased(KeyEvent e){
    }

}
