import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public final class GameMenu extends JFrame implements ActionListener {

	JButton newGame;
	JButton loadGame;
	public GameMenu() {
		this.setBounds(0, 0, 300, 200);
		this.setLayout(new GridLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		newGame = new JButton("Nowa gra");
		newGame.setBounds(0, 0, 150, 200);
		newGame.addActionListener(this);
		this.add(newGame);
		
		loadGame = new JButton("Wczytaj grê");
		loadGame.setBounds(0, 0, 150, 200);
		loadGame.addActionListener(this);
		this.add(loadGame);
		this.revalidate();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == newGame) {
			this.dispose();
			World world = new World(20, 30);
			world.start();
		}
		else if(e.getSource() == loadGame) {
			this.dispose();
			World world = new World();
			world.loadGame();
			world.start();
		}
	}
	
}
