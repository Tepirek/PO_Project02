import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public final class Game extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public Game(int width, int height) {
		this.setBounds(0, 0, width, height);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		
		JLabel test = new JLabel("TESTASKDASODFASFAS");
		test.setBounds(0, 0, 200, 200);
		test.setOpaque(true);
		test.setBackground(Color.RED);
		test.setForeground(Color.white);
		this.add(test);
	}
	public void addNewElement(JLabel newElement) {
		this.add(newElement);
	}
}
