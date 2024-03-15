import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MyBall {
	float r = 5;
	float x, y;
	float vx = 0, vy = 0;
	float speedY = 10;
	float speedX = 4;
	GameScreen game;
	
	Clip clip;
	
	MyBall() {
		x = 400; y = 400;
		vy = speedY;
		vx = 0;
		
		loadAudio("audio/Reflect.wav");
	}
	
	private void loadAudio(String pathName) {
		try {
			clip = AudioSystem.getClip();
			File audioFile = new File(pathName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			clip.open(audioStream);
	
		}
		catch (LineUnavailableException e) { e.printStackTrace(); }
		catch(UnsupportedAudioFileException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
	}
	
	void update(MyReflector reflect, Dimension d) { 
		x += vx;
		y += vy;
		
		if(isContain(reflect) == 1) {
			vy = -speedY;
			vx = -speedX * 1.5f;
			clip.setFramePosition(0);
			clip.start();
		}
		else if(isContain(reflect) == 2) {
			vy = -speedY;
			vx = vx * 0.5f;
			clip.setFramePosition(0);
			clip.start();
		}
		else if(isContain(reflect) == 3) {
			vy = -speedY;
			vx = speedX * 1.5f;
			clip.setFramePosition(0);
			clip.start();
		}
		
		if(x > d.width - r) {
			x = d.width - r; vx = -speedX; 
		}
		if(x < 0) {
			x = 0; vx = speedX;
		}
		if(y < 0) {
			y = 0; vy = speedY;
		}
	}
	
	void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval((int)(x - r), (int)(y - r), (int)(r * 2), (int)(r * 2));
	}
	
	int isContain(MyReflector reflect) {
		
		if(y + r <= reflect.y && y + r >= reflect.y - 10 && x >= reflect.x && x <= reflect.x + (reflect.w * (1.0 / 3))) 
			return 1;
		else if(y + r <= reflect.y && y + r >= reflect.y - 10 && x >= reflect.x + (reflect.w * (1.0 / 3))&& x <= reflect.x + (reflect.w * (2.0 / 3)))
			return 2;
		else if(y + r <= reflect.y && y + r >= reflect.y - 10 && x >= reflect.x + (reflect.w * (2.0 / 3)) && x <= reflect.x + reflect.w) 
			return 3;
		else
			return -1;	
		
	}
}
