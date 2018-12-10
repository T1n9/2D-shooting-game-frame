package com.game.gameobject;

import com.game.main.Game;
import com.game.main.Handler;
import com.game.main.Spawn;
import com.game.scene.AbstractScene;
import com.game.scene.Scene1_1;
import com.game.scene.Scene_ID;
import com.game.scene.StartScene;
import com.game.utility.Utility;
import com.game.mousekeyboard.MouseInput;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.nio.file.FileAlreadyExistsException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.PrimitiveIterator;
import java.util.Random;

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
            if(object instanceof BasicEnemy){
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
        double side_a = MouseInput.mouse_pos_y - y;
        double side_b = MouseInput.mouse_pos_x - x;
        double angle = Math.toDegrees(Math.atan(side_a/side_b));
        if(MouseInput.mouse_pos_x < x)
            angle += 180;
        else if(MouseInput.mouse_pos_x > x && MouseInput.mouse_pos_y < y)
            angle += 360;
        return angle;
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
        int player_center_pos_x = x + width/2;
        int player_center_pos_y = y + height/2;
        mouse_angle = calculate_mouse_angle();

        int radius = weapon.get_weapon_length();
        bullet_start_pos_x = player_center_pos_x + (int)(radius * Math.cos(Math.toRadians(mouse_angle)));
        bullet_start_pos_y = player_center_pos_y + (int)(radius * Math.sin(Math.toRadians(mouse_angle)));

        Spawn.spawn_bullet = true;
    }
}
