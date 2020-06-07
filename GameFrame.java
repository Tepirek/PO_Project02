import java.awt.Color;import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public final class GameFrame extends JFrame {
	
	private int width, height;
	private static int cellSize = 20;
	private JLabel gameField;
	private JLabel legendBox;
	private JLabel commentBox;
	private ArrayList<String> organismNames = new ArrayList<String>(Arrays.asList("Human", "Wolf", "Sheep", "Fox", "Turtle", "Antelope", "Cybersheep", "Grass", "Dandelion", "Guarana", "Wolfberry", "Heracleum"));
	private ArrayList<Color> organismColors = new ArrayList<Color>(Arrays.asList(new Color(255, 255, 255), new Color(59, 59, 59), new Color(199, 167, 167), new Color(158, 81, 14), new Color(53, 89, 29), new Color(102, 79, 21), new Color(46, 217, 168), new Color(78, 181, 9), new Color(255, 247, 15), new Color(138, 47, 73), new Color(80, 13, 105), new Color(255, 0, 0)));
	
	public GameFrame(int width, int height) {
		this.width = width;
		this.height = height;
		this.setBounds(0, 0, this.width * 20 + 150, this.height * 20 + 150);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.gameField = new JLabel();
		this.gameField.setBounds(0, 0, this.width * 20, this.height * 20);
		this.gameField.setOpaque(true);
		this.gameField.setBackground(Color.LIGHT_GRAY);
		this.gameField.setVisible(true);
		this.add(gameField);
		// this.createGrid();
		this.createLegendBox();
		this.createCommentBox();
	}
	
	private void createGrid() {
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				JLabel cell = new JLabel();
				cell.setBounds(i * cellSize, j * cellSize, cellSize, cellSize);
				cell.setOpaque(true);
				cell.setBackground(Color.CYAN);
				Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
				cell.setBorder(border);
				cell.setVisible(true);
				this.gameField.add(cell);
			}
		}
		this.revalidate();
		this.repaint();
	}
	
	private void createLegendBox() {
		legendBox = new JLabel();
		legendBox.setBounds(this.width * cellSize, 0, 135, this.height * cellSize);
		legendBox.setOpaque(true);
		legendBox.setBackground(Color.BLACK);
		legendBox.setVisible(true);
		this.add(legendBox);
		for(int i = 0; i < this.organismNames.size(); i++) {
			JLabel legendEntry = new JLabel();
			legendEntry.setBounds(5, 5 + i * (cellSize + 5), 125, cellSize + 2);
			legendEntry.setOpaque(true);
			legendEntry.setBackground(Color.WHITE);
			legendEntry.setVisible(true);
			legendBox.add(legendEntry);
			JLabel colorBox = new JLabel();
			colorBox.setBounds(1, 1, cellSize, cellSize);
			colorBox.setOpaque(true);
			colorBox.setBackground(this.organismColors.get(i));
			colorBox.setVisible(true);
			legendEntry.add(colorBox);
			JLabel textBox = new JLabel(this.organismNames.get(i));
			textBox.setBounds(cellSize + 2, 1, cellSize + 82, cellSize);
			textBox.setOpaque(true);
			textBox.setBackground(Color.BLUE);
			textBox.setForeground(Color.WHITE);
			textBox.setVisible(true);
			legendEntry.add(textBox);
		}
		JLabel humanBox = new JLabel();
		humanBox.setBounds(5, 310, 125, 100);
		humanBox.setOpaque(true);
		humanBox.setBackground(Color.WHITE);
		humanBox.setVisible(true);
		legendBox.add(humanBox);
	}
	
	private void createCommentBox() {
		commentBox = new JLabel();
		commentBox.setBounds(0, this.height * cellSize, this.width * cellSize + 135, 115);
		commentBox.setOpaque(true);
		commentBox.setBackground(Color.BLACK);
		commentBox.setVisible(true);
		this.add(commentBox);
	}
	
	public void addNewElement(JLabel newElement) {
		this.gameField.add(newElement);
		this.revalidate();
		this.repaint();
	}
	
	public void removeOldElement(JLabel oldElement) {
		this.gameField.remove(oldElement);
		this.revalidate();
		this.repaint();
	}

}
