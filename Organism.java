import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;

public abstract class Organism extends Thread {
	private String name;
	private int strength;
	private int initiative;
	private int age;
	private ArrayList<Integer> position;
	private World world;
	private JLabel mapObject;
	private static int width = 20;
	private static int height = 20;
	
	public final String getOrganismName() {
		return name;
	}

	public final int getStrength() {
		return strength;
	}

	public final void increaseStrength(int strength) {
		this.strength += strength;
	}

	public final int getInitiative() {
		return initiative;
	}

	public final int getAge() {
		return age;
	}

	public final void incrementAge() {
		this.age++;
	}

	public final ArrayList<Integer> getPosition() {
		return position;
	}

	public final void updatePosition(ArrayList<Integer> position) {
		this.position = position;
	}

	public final World getWorld() {
		return world;
	}

	public final JLabel getMapObject() {
		return mapObject;
	}

	public static final int getWidth() {
		return width;
	}

	public static final int getHeight() {
		return height;
	}

	public Organism(String name, int strength, int initiative, ArrayList<Integer> position, World world) {
		this.name = name;
		this.strength = strength;
		this.initiative = initiative;
		this.age = 0;
		this.position = position;
		this.world = world;
		this.mapObject = new JLabel(this.name);
		this.mapObject.setBounds(this.position.get(0), this.position.get(1), Organism.width, Organism.height);
		this.mapObject.setOpaque(true);
		this.mapObject.setBackground(Color.black);
		this.mapObject.setForeground(Color.white);
		this.mapObject.setVisible(true);
		this.world.getGame().addNewElement(this.mapObject);
	}
	
	public abstract void draw();
	public abstract void action();
	public abstract void collision();
}
