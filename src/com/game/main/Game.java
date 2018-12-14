package com.game.main;

import com.game.mousekeyboard.MouseInput;
import com.game.mousekeyboard.KeyInput;
import com.game.scene.StartScene;

import java.awt.*;


public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = -473349850293143017L;

	public static final int WIDTH = 990, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;

	private Handler handler = new Handler();


	public Game() {
		this.requestFocus();
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler));
		this.addMouseMotionListener(new MouseInput(handler));
		AudioPlayer.load();
		AudioPlayer.get_music("music").loop();
		new Window(WIDTH, HEIGHT, "荒野大滑稽", this);

		//		handler.add_object(new Player(WIDTH/2+66, HEIGHT/2, ID.Player2));
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		StartScene start = new StartScene(this, handler, running);
		start.open_scene();
		stop();
	}

	public static void main(String args[]) {
		new Game();
	}
}
