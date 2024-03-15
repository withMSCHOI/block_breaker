import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameScreen {
	
	int stage = 1;
	LinkedList<MyRect> rectList = new LinkedList<>();
	int boundary = 5;
	Random random = new Random();
	int check = 1;
	
	MyReflector reflect = new MyReflector();
	
	MyBall ball1;
	LinkedList<MyBall> ballList = new LinkedList<>();
	
	Clip clip1, clip2;
	
	GameScreen() {
		stage = 1;
		loadAudio("audio/BlockBreak.wav");
		loadAudio2("audio/SpecialBlock.wav");
	}
	
	private void loadAudio(String pathName) {
		try {
			clip1 = AudioSystem.getClip();
			File audioFile = new File(pathName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			clip1.open(audioStream);
	
		}
		catch (LineUnavailableException e) { e.printStackTrace(); }
		catch(UnsupportedAudioFileException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
	}
	
	private void loadAudio2(String pathName) {
		try {
			clip2 = AudioSystem.getClip();
			File audioFile = new File(pathName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			clip2.open(audioStream);
	
		}
		catch (LineUnavailableException e) { e.printStackTrace(); }
		catch(UnsupportedAudioFileException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
	}
	
	void draw(Graphics g, Dimension d) {
		
		drawRect(g, d);
		
		for(MyRect rect : rectList) { 
			if(rect.special)
				g.setColor(Color.yellow);
			else
				g.setColor(new Color(126, 48, 225));
			Graphics2D g2 = (Graphics2D) g;
			g2.fillRoundRect((int)rect.x, (int)rect.y, (int)rect.w, (int)rect.h, 20, 20);
		}
		
		reflect.update(d);
		reflect.draw(g);
		
		for(MyBall ball : ballList) {
			ball.update(reflect, d);
			ball.draw(g);
			update(ball);
		}
	}
	
	void drawRect(Graphics g, Dimension d) {
		if(check == 1 && stage == 1) {
			rectList.clear();
			int width = d.width - 20;
			int height = d.height - 20;
			int x, y, w, h;
			
			x = boundary; 
			y = boundary;
			w = width / 3;
			h = height / 2 / 3;
			
			for(int j = 0; j < 3; j++) {
				for(int i = 0; i < 3; i++) {
					MyRect rect = new MyRect(x, y, w, h);
					if(random.nextBoolean()) {
						rect.special = true;
					}
					rectList.add(rect);
					x += w + boundary;
				}
				x = boundary;
				y += h + boundary;
			}
			check++;
			ballList.clear();
			ball1 = new MyBall();
			ballList.add(ball1);
		}
		
		else if(check == 2 && stage == 2) {
			rectList.clear();
			int width = d.width - 20;
			int height = d.height - 20;
			int x, y, w, h;
			
			x = boundary; 
			y = boundary;
			w = width / 6;
			h = height / 2 / 6;
			
			for(int j = 0; j < 6; j++) {
				for(int i = 0; i < 6; i++) {
					MyRect rect = new MyRect(x, y, w, h);
					if(random.nextBoolean()) {
						rect.special = true;
					}
					rectList.add(rect);
					x += w + boundary;
				}
				x = boundary;
				y += h + boundary;
			}
			check++;
			ballList.clear();
			ball1 = new MyBall();
			ballList.add(ball1);
		}
		
		else if(check == 3 && stage == 3) {
			rectList.clear();
			int width = d.width - 20;
			int height = d.height - 20;
			int x, y, w, h;
			
			x = boundary; 
			y = boundary;
			w = width / 9;
			h = height / 2 / 9;
			
			for(int j = 0; j < 9; j++) {
				for(int i = 0; i < 9; i++) {
					MyRect rect = new MyRect(x, y, w, h);
					if(random.nextBoolean()) {
						rect.special = true;
					}
					rectList.add(rect);
					x += w + boundary;
				}
				x = boundary;
				y += h + boundary;
			}
			check++;
			ballList.clear();
			ball1 = new MyBall();
			ballList.add(ball1);
		}
	}
	
	int isCollision(MyRect rect) {
		for(MyBall ball : ballList) {
			if(ball.y - ball.r >= rect.y + rect.h && ball.y - ball.r <= rect.y + rect.h + 10 && 
					ball.x >= rect.x && ball.x <= rect.x + rect.w) { //사각형 아래 부분
				return 0;
			}
			if(ball.y >= rect.y && ball.y <= rect.y + rect.h &&
					ball.x + ball.r >= rect.x && ball.x + ball.r <= rect.x - 10) { //사각형 왼쪽 부분
				return 1;
			}
			if(ball.y >= rect.y && ball.y  <= rect.y + rect.h &&
					ball.x - ball.r >= rect.x + rect.w && ball.x - ball.r <= rect.x + rect.w + 10) { //사각형 오른쪽 부분
				return 2;
			}
			if(ball.y + ball.r <= rect.y && ball.y + ball.r >= rect.y - 10 && 
					ball.x >= rect.x && ball.x <= rect.x + rect.w) { //사각형 윗 부분
				return 3;
			}
		}
		return -1;
	}
	
	void update(MyBall ball) {
		
		Iterator<MyRect> it = rectList.iterator();
		
		while(it.hasNext()) {
			MyRect rect = it.next();
			
			if(isCollision(rect) == 0) {
				it.remove();
				ball.vy = -ball.vy;   
			}
			if(isCollision(rect) == 1) {
				it.remove();
				ball.vx = -ball.vx;
			}
			if(isCollision(rect) == 2) {
				it.remove();
				ball.vx = -ball.vx;
			}
			
			if(isCollision(rect) == 3) {
				it.remove();
				ball.vy = -ball.vy;
			}
			
			if(isCollision(rect) == 0 || isCollision(rect) == 1|| isCollision(rect) == 2 || isCollision(rect) == 3) {
				if(rect.special) {
					clip2.setFramePosition(0);
					clip2.start();
					MyBall ball2 = new MyBall();
					ball2.y = rect.y + rect.h; ball2.vy = 5; ball2.vx = -4; ball2.x = rect.x + rect.w / 2;
					
					MyBall ball3 = new MyBall();
					ball3.y = rect.y + rect.h; ball3.vy = 10; ball3.vx = 0; ball3.x = rect.x + rect.w / 2;
					
					MyBall ball4 = new MyBall();
					ball4.y = rect.y + rect.h; ball4.vy = 5; ball4.vx = 4; ball4.x = rect.x + rect.w / 2;
					
					ballList.add(ball2); ballList.add(ball3); ballList.add(ball4);
				}
				else {
					clip1.setFramePosition(0);
					clip1.start();
				}
			}
			
			if(rectList.isEmpty()) {
				stage++;
			}
		}
	}
}
