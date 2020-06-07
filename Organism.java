import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

public abstract class Organism {

	private String name;
	private int strength;
	private Integer initiative;
	private int age;
	private ArrayList<Integer> position;
	private Boolean alive;
	private World world;
	private JLabel mapObject;
	private Color mapObjectColor;
	private static int organismSize = 20;
	
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

	public final void setPosition(ArrayList<Integer> position) {
		this.position = position;
	}

	public final Boolean isOrganismAlive() {
		return alive;
	}

	public final void kill() {
		this.alive = false;
	}

	public final World getWorld() {
		return world;
	}

	public final JLabel getMapObject() {
		return mapObject;
	}

	public final void setMapObject(JLabel mapObject) {
		this.mapObject = mapObject;
	}

	public final Color getMapObjectColor() {
		return mapObjectColor;
	}

	public final void setMapObjectColor(Color mapObjectColor) {
		this.mapObjectColor = mapObjectColor;
	}

	public static final int getOrganismSize() {
		return organismSize;
	}

	public static final void setOrganismSize(int organismSize) {
		Organism.organismSize = organismSize;
	}

	public Organism(String name, int strength, int initiative, ArrayList<Integer> position, World world, Color color) {
		this.name = name;
		this.strength = strength;
		this.initiative = initiative;
		this.age = 0;
		this.position = position;
		this.alive = true;
		this.world = world;
		this.mapObjectColor = color;
		this.mapObject = new JLabel();
		this.mapObject.setBounds(this.position.get(0) * Organism.organismSize, this.position.get(1) * Organism.organismSize, Organism.organismSize, Organism.organismSize);
		this.mapObject.setOpaque(true);
		this.mapObject.setBackground(this.mapObjectColor);
		this.mapObject.setVisible(true);
		this.world.getGameFrame().addNewElement(this.mapObject);
	}
	
	@Override
	public String toString() {
		String output = this.name + "(" + this.strength + ")";
		return output;
	}
	
	public final void setStrength(int strength) {
		this.strength = strength;
	}

	public final void setAge(int age) {
		this.age = age;
	}

	public void changePosition(ArrayList<Integer> position) {
		this.getMapObject().setBounds(position.get(0) * Organism.getOrganismSize(), position.get(1) * Organism.getOrganismSize(), Organism.getOrganismSize(), Organism.getOrganismSize());
		this.getWorld().map[this.getPosition().get(1)][this.getPosition().get(0)] = null;
		this.getWorld().map[position.get(1)][position.get(0)] = this;
		this.setPosition(position);
	}

	public abstract Boolean action();
	public abstract void collision(Organism other);
	public abstract void draw();
	
}
