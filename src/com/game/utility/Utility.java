package com.game.utility;

import java.util.Random;

public class Utility {

    public static Random r = new Random();

    static public String get_png(String file_name){
        return "src/resource/" + file_name;
    }


    static public int clamp(int current_value, int max_limit){
        if(current_value <= 0)   return 0;
        else if(current_value >= max_limit)  return max_limit;
        else return current_value;
    }
    static public double get_angle_from_vector(int vx, int vy){
        double angle = Math.toDegrees(Math.atan((double) vy/vx));
        if(vx <= 0)
            angle += 180;
        else if(vx > 0 && vy < 0)
            angle += 360;
        return angle;
    }
}
