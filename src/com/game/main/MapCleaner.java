package com.game.main;

import com.game.gameobject.*;

import javax.print.attribute.HashDocAttributeSet;
import java.nio.file.FileAlreadyExistsException;
import java.util.Iterator;

public final class MapCleaner {

    private Handler handler;
    private HUD hud = HUD.get_instance();

    private int score_keep = 0;

    private void remove_bullet(){
        Iterator<GameObject> it = handler.objects.iterator();
        while (it.hasNext()){
            GameObject tmp = it.next();
            if(tmp instanceof BasicBullet){
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


    public MapCleaner(Handler handler){
        this.handler = handler;
    }
    public void tick(){
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
