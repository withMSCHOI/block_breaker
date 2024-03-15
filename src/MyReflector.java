import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyReflector {
	float x, y, w, h;
	float speed = 15;
	int b = -1;
	MyReflector() {
		x = 350;
		y = 680;
		w = 120;
		h = 15;
	}
	
	void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		g2.fillRoundRect((int)x, (int)y, (int)w, (int)h, 10, 10);
	}
	
	void update(Dimension d) {
		if(b == 0) {
			x -= speed;
		}
		else if(b == 1) {
			x += speed;
		}
		else {
			x += 0;
		}
		
		if(x > d.width - w) 
			x = d.width - w;
		if(x < 0) 
			x = 0;
	}
}