import javax.swing.JFrame;

public class SnakeMain extends JFrame {

	public SnakeMain() {
		this.add(new SnakeRenderer());
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new SnakeMain();
	}

}
