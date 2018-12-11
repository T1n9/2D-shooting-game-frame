package com.game.main;

import com.game.gameobject.GameObject;

import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public final class Handler {
    public LinkedList<GameObject> objects  = new LinkedList<GameObject>();

    public void tick(){
        for(int i = 0; i< objects.size(); ++i){
            GameObject tmp = objects.get(i);
            tmp.tick();
        }
//        for(GameObject object : objects)
//            object.tick();
    }
    public void render(Graphics g){
        for(int i=0; i<objects.size(); ++i){
            GameObject tmp = objects.get(i);
            tmp.render(g);
        }

//        for(GameObject object : objects)
//            object.render(g);
    }
    public void add_object(GameObject obj){
        this.objects.add(obj);
    }
    public void remove_object(GameObject obj){
        this.objects.remove(obj);
    }
}
