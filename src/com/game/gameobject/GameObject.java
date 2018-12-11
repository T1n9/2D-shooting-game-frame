package com.game.gameobject;


import java.awt.*;

public abstract class GameObject {
    protected int x, y;
    protected ID id;
    protected int velX, velY;
    protected Toolkit toolkit = Toolkit.getDefaultToolkit();

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle get_bounds();

    public final void setX(int x){
        this.x = x;
    }
    public final void setY(int y){
        this.y = y;
    }
    public final int getX(){
        return x;
    }
    public final int getY(){
        return y;
    }
    public final void setId(ID id){
        this.id = id;
    }
    public final ID getId(){
        return id;
    }
    public final void setVelX(int velx){
        this.velX = velx;
    }
    public final void setVelY(int velY){
        this.velY = velY;
    }
    public final int getVelX(){
        return velX;
    }
    public final int getVelY(){
        return velY;
    }
}
