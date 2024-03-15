import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements Runnable {
	
	StartScreen screen1 = new StartScreen();
	LinkedList<Ball> ballList = new LinkedList<>();
	
	Ball ball1 = new Ball(200, 400, 2);
	Ball ball2 = new Ball(300, 500, 5);
	Ball ball3 = new Ball(600, 200, 3);
	Ball ball4 = new Ball(500, 400, 5);
	
	GameScreen screen2 = new GameScreen();
	
	EndScreen screen3 = new EndScreen();
	
	int mode = 0;
	int a = 30;
	int flag = 0;
	
	Clip clip1, clip2, clip3;
	
	MyPanel() {
		mode = 0;
		ballList.add(ball1); ballList.add(ball2); ballList.add(ball3); ballList.add(ball4);
		
		requestFocus();
		setFocusable(true);
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					if(mode == 0) {
						mode = 1;
						clip1.stop();
					}
					
					if(mode == 2) {
						mode = 0;
						screen1 = new StartScreen();
						screen2 = new GameScreen();
						screen3 = new EndScreen();
						screen3.finish = false;
						
						ball1 = new Ball(200, 400, 2);
						ball2 = new Ball(300, 500, 5);
						ball3 = new Ball(600, 200, 3);
						ball4 = new Ball(500, 400, 5);
						ballList.clear();
						ballList.add(ball1); ballList.add(ball2); ballList.add(ball3); ballList.add(ball4);
						
						clip1.setFramePosition(0);
						clip1.start();
						
						clip2.stop();
						clip3.stop();
					}
				}
				
				if(mode == 1) {
					if(e.getKeyCode() == KeyEvent.VK_LEFT) {
						screen2.reflect.b = 0;
					}
					else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						screen2.reflect.b = 1;
					}
				}
			}	
			public void keyReleased(KeyEvent e) {
				screen2.reflect.b = -1;
			}
		});
		
		Thread t = new Thread(this);
		t.start();
		
		loadAudio("audio/StartMusic.wav");
		clip1.setFramePosition(0);
		clip1.start();
		loadAudio2("audio/EndMusic.wav");
		loadAudio3("audio/Finish.wav");
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
	
	private void loadAudio3(String pathName) {
		try {
			clip3 = AudioSystem.getClip();
			File audioFile = new File(pathName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			clip3.open(audioStream);
	
		}
		catch (LineUnavailableException e) { e.printStackTrace(); }
		catch(UnsupportedAudioFileException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 800, 800);
		
		if(mode == 0) {
			screen1.draw(g);
			
			for(Ball b : ballList) {
				b.draw(g);
			}
		}
		
		else if(mode == 1) {
			Dimension d = getSize();
			screen2.draw(g, d);
		}
		
		else if(mode == 2) {
			screen3.draw(g);
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(a);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(mode == 0) {
				a = 30;
				flag++;
				if(flag % 4 == 0) screen1.str2 = "Press the space bar to start the game";
				else screen1.str2 = "";
				
				Dimension d = getSize();
				for(Ball b : ballList) {
					b.update(d);
				}
			}
			
			if(mode == 1) {
				a = 30;
				
				Iterator<MyBall> it = screen2.ballList.iterator();
				Dimension d = getSize();
				while(it.hasNext()) {
					MyBall ball = it.next();
					if(ball.y > d.height) {
						it.remove();
					}
					if(screen2.ballList.isEmpty()) {
						mode = 2;
						clip2.setFramePosition(0);
						clip2.start();
					}
			
				}
				if(screen2.stage == 4 && screen2.rectList.isEmpty()) {
					mode = 2; screen3.finish = true;
					clip3.setFramePosition(0);
					clip3.start();
				}
			}
			
			repaint();
		}
	}

}
