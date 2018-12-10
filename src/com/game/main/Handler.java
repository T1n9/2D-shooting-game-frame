package com.game.main;

import com.game.gameobject.GameObject;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    public LinkedList<GameObject> objects  = new LinkedList<GameObject>();

    public void tick(){
        for(GameObject object : objects)
            object.tick();
    }
    public void render(Graphics g){
        for(GameObject object : objects)
            object.render(g);
    }
    public void add_object(GameObject obj){
        this.objects.add(obj);
    }
    public void remove_object(GameObject obj){
        this.objects.remove(obj);
    }
}
