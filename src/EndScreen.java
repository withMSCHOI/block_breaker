import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class EndScreen {
	String str = new String("Game Over");
	String str2 = new String("Try Again");
	String str3 = new String("Good Game");
	String str4 = new String("Press the space bar to start the game");
	
	Font f = new Font("Arial", Font.BOLD, 100);
	Font f2 = new Font("Arial", Font.BOLD, 50);
	Font f3 = new Font("Arial", Font.BOLD, 100);
	Font f4 = new Font("Arial", Font.BOLD, 30);
	
	boolean finish = false;
	
	void draw(Graphics g) {
		if(!finish) {
			g.setFont(f);
			g.setColor(Color.white);
			g.drawString(str, 130, 300);
			
			g.setFont(f2);
			g.drawString(str2, 280, 510);
			
			g.setFont(f4);
			g.drawString(str4, 125, 610);
		}
		else {
			g.setFont(f3);;
			g.setColor(Color.white);
			g.drawString(str3, 130, 300);
			
			g.setFont(f4);
			g.drawString(str4, 125, 510);
		}
	}
}