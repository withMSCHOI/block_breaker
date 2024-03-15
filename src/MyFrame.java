import javax.swing.JFrame;

public class MyFrame extends JFrame {

	MyFrame() {
		setTitle("MyFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new MyPanel());
		setSize(800, 800);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MyFrame();
	}

}
