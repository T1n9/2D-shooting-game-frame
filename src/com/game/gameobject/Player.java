package com.game.gameobject;

import com.game.main.AudioPlayer;
import com.game.main.Game;
import com.game.main.GameMenu;
import com.game.main.Handler;
import com.game.scene.AbstractScene;
import com.game.scene.Scene1_1;
import com.game.scene.Scene_ID;
import com.game.scene.StartScene;
import com.game.utility.Utility;
import com.game.mousekeyboard.MouseInput;

import java.awt.*;

public class Player extends GameCharacter {

    public static int speed = 5;
    protected BasicWeapon weapon;
    public static double mouse_angle = 0;

    public static int bullet_start_pos_x = 0;
    public static int bullet_start_pos_y = 0;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        character_img = toolkit.getImage(Utility.get_png("huaji.png"));
        character_got_hurt_image = toolkit.getImage(Utility.get_png("cry.png"));

        velX = velY = 0;
        margin_x = 52;
        margin_y = 75;
        height = 39;
        width = 39;
        weapon = new BasicWeapon(x, y,ID.BasicWeapon, handler);

        this.handler = handler;
    }

    protected void collision(){
        if(got_hurt){
            ++hurt_state_count;
            if(hurt_state_count > 40) {
                got_hurt = false;
                hurt_state_count = 0;
            }
        }
        //@todo 加上碰撞效果, 然后有后退效果
        for(GameObject object: handler.objects){
            if(object instanceof BasicEnemy || object instanceof MonsterBullet){
                if(get_bounds().intersects(object.get_bounds())){
                    --HUD.HEALTH;
                    got_hurt = true;
                }
            }
        }
    }

    //第一个参数是传送门，第二个参数是新的场景的ID
    private void move_from_gate(Rectangle gate, Scene_ID id){
        if(get_bounds().intersects(gate))
            AbstractScene.current_scene_id = id;
    }

    private void enter_new_scene(){
        switch(AbstractScene.current_scene_id){
            case Start_Scene:
                move_from_gate(StartScene.gate_north_east(), Scene_ID.Scene_1_1);
                break;
            case Scene_1_1:
                move_from_gate(Scene1_1.gate_south_east(), Scene_ID.Start_Scene);
                break;
        }
    }
    private double calculate_mouse_angle(){
        int vx = MouseInput.mouse_pos_x - x;
        int vy = MouseInput.mouse_pos_y - y;
        return Utility.get_angle_from_vector(vx, vy);

    }
    private void game_over(){
        if(HUD.HEALTH <= 0) {
            GameMenu.menu_to_gameover();
            AbstractScene.to_menu_mode();
        }
    }

    public void tick() {
        mouse_angle = calculate_mouse_angle();
        x += velX;
        y += velY;
        x = Utility.clamp(x, Game.WIDTH - margin_x);
        y = Utility.clamp(y, Game.HEIGHT - margin_y);

        weapon.tick(x,y, mouse_angle, width, height);
        collision();
        enter_new_scene();
        game_over();
    }


    public void render(Graphics g) {
        super.render(g);
        weapon.render(g);
    }
    // or change it to a circle with Ellipse2D
    public Rectangle get_bounds() {
        return new Rectangle(x,y, width-3, height-3);
    }

    public void fire_shots() {
        AudioPlayer.get_sound("shoot").play();
        int player_center_pos_x = x + width/2;
        int player_center_pos_y = y + height/2;
        mouse_angle = calculate_mouse_angle();

        int radius = weapon.get_weapon_length();
        bullet_start_pos_x = player_center_pos_x + (int)(radius * Math.cos(Math.toRadians(mouse_angle)));
        bullet_start_pos_y = player_center_pos_y + (int)(radius * Math.sin(Math.toRadians(mouse_angle)));

        handler.add_object(new PlayerBullet(Player.bullet_start_pos_x, Player.bullet_start_pos_y, Player.mouse_angle, ID.BasicBullet, handler));
    }
}
