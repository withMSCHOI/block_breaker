import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Ball {
	float x, y;
	float r = 10;
	float vy = 0;
	float ay = 1.0f;
	float start_time;
	
	Ball(float x, float y, float start_time) {
		this.x = x;
		this.y = y;
		this.start_time = start_time;
	}
	
	void update(Dimension d) {
		start_time -= 1;
		if(start_time > 0) return;
		
		vy += ay;
		y += vy;
		
		if(y > d.height - r) {
			y = d.height - r;
			vy = -vy * 0.9f;
		}
	}
	
	void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval((int)(x - r), (int)(y - r), (int)(r * 2), (int)(r * 2));
	}
}