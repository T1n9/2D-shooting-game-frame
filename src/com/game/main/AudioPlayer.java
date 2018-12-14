package com.game.main;


import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.HashMap;
import java.util.Map;


public class AudioPlayer {

    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
    public static Map<String, Music> musicMap = new HashMap<String, Music>();

    public static void load(){
        try{
            soundMap.put("shoot", new Sound("src/resource/shoot.ogg"));
            musicMap.put("music", new Music("src/resource/bg.ogg"));
        }catch (SlickException e){
            e.printStackTrace();
        }
    }
    public static Music get_music(String key){
        return musicMap.get(key);
    }
    public static Sound get_sound(String key){
        return soundMap.get(key);
    }
}
