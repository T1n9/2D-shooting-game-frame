package com.game.main;

import com.game.gameobject.*;

import javax.print.attribute.HashDocAttributeSet;
import java.nio.file.FileAlreadyExistsException;
import java.util.Iterator;

public final class Spawn {

    private Handler handler;
    private HUD hud = HUD.get_instance();
    public static boolean spawn_bullet = false;

    private int score_keep = 0;

    private void spawn_bullet(){
        if(spawn_bullet){
            handler.add_object(new BasicBullet(Player.bullet_start_pos_x, Player.bullet_start_pos_y, Player.mouse_angle, ID.BasicBullet, handler));
            spawn_bullet = false;
        }
    }
    private void remove_bullet(){
        Iterator<GameObject> it = handler.objects.iterator();
        while (it.hasNext()){
            GameObject tmp = it.next();
            if(tmp.getId() == ID.BasicBullet){
                if(tmp.getX()<0 || tmp.getY()<0 ||
                        tmp.getX()>Game.WIDTH || tmp.getY()>Game.HEIGHT)
                    it.remove();
            }
        }
    }
    private void remove_monster(){
        Iterator<GameObject> it = handler.objects.iterator();
        while (it.hasNext()){
            GameObject tmp = it.next();
            if(tmp instanceof BasicEnemy){
                if(((BasicEnemy) tmp).get_hp() <= 0)
                    it.remove();
            }
        }
    }


    public Spawn(Handler handler){
        this.handler = handler;
    }
    public void tick(){
        spawn_bullet();
        remove_bullet();
        remove_monster();

//        int sum = 0;
//        for(GameObject obj: handler.objects){
//            if(obj.id == ID.BasicBullet)
//                ++sum;
//        }
//        System.out.println(sum);

        ++score_keep;
        //就是一个很简单的得分越多，产生的敌人越多
        if(score_keep>100){
//            score_keep = 0;
//            hud.set_level(hud.get_level() + 1);
//            handler.add_object(new Enemy1_1(Utility.r.nextInt(Game.WIDTH), Utility.r.nextInt(Game.HEIGHT), ID.Enemy_1_1));
        }
    }
}
