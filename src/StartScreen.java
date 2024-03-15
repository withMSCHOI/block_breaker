import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class StartScreen {
	String str1 = new String("Game Start");
	Font f1 = new Font("Arial", Font.BOLD, 100);
	
	String str2 = new String("Press the space bar to start the game");
	Font f2 = new Font("Arial", Font.BOLD, 30);
	
	StartScreen() {}
	
	void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(f1);
		g.drawString(str1, 130, 300);
		
		g.setFont(f2);
		g.drawString(str2, 125, 510);
	}
}