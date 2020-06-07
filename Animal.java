import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class Animal extends Organism {
	
	private static int UP = 87;
	private static int DOWN = 83;
	private static int LEFT = 65;
	private static int RIGHT = 68;
	private static ArrayList<Integer> movement = new ArrayList<Integer>(Arrays.asList(UP, DOWN, LEFT, RIGHT));
	private int copulationCooldown;
	
	public static final ArrayList<Integer> getMovement() {
		return movement;
	}

	public Animal(String name, int strength, int initiative, ArrayList<Integer> position, World world,
			Color color) {
		super(name, strength, initiative, position, world, color);
		this.copulationCooldown = 10;
	}
	
	public void reduceCopulationCooldown() {
		if(this.copulationCooldown > 0) this.copulationCooldown--;
	}
	
	public int getCopulationCooldown() {
		return this.copulationCooldown;
	}
	
	public void setCopulationCooldown(int value) {
		this.copulationCooldown = value;
	}
	
	public Boolean canCopulate() {
		return (this.copulationCooldown == 0);
	}
	
	public void move(int key, int step) {
		this.reduceCopulationCooldown();
		ArrayList<Integer> newPosition = new ArrayList<Integer>();
		if(key == UP) {
			newPosition.add(getPosition().get(0));
			newPosition.add(getPosition().get(1) - step);
		}
		else if(key == DOWN) {
			newPosition.add(getPosition().get(0));
			newPosition.add(getPosition().get(1) + step);
		}
		else if(key == LEFT) {
			newPosition.add(getPosition().get(0) - step);
			newPosition.add(getPosition().get(1));
		}
		else if(key == RIGHT) {
			newPosition.add(getPosition().get(0) + step);
			newPosition.add(getPosition().get(1));
		}
		if(this.getWorld().canMove(newPosition)) {
			if(this.getWorld().validPosition(newPosition)) {
				if(this instanceof Turtle) {
					Random random = new Random();
					int chance = random.nextInt(100);
					if(chance > 25) {
						// System.out.println("Turtle will not move");
						return;
					}
				}
				this.changePosition(newPosition);
			}
			else {
				Organism other = this.getWorld().getOrganismAt(newPosition);
				if(this.getOrganismName().equalsIgnoreCase(other.getOrganismName())) {
					this.copulate(other);
				}
				else {
					if(this instanceof Fox) {
						if(other.getStrength() > this.getStrength()) {
							System.out.println("Lis nie ruszy siê na pole na którym stoi " + other.toString());
							return;
						}
					}
					other.collision(this);
				}
			}
		}
	}
	
	public void copulate(Organism other) {
		Animal otherAnimal = (Animal) other;
		if(!(this.canCopulate()) || !(otherAnimal.canCopulate())) {
			// System.out.println("cannot copulate " + this.getCopulationCooldown() + " and " + otherAnimal.getCopulationCooldown());
			return;
		}
		System.out.println("COPULATE " + this.getOrganismName());
		ArrayList<Integer> checkedPositions = new ArrayList<Integer>();
		Random random = new Random();
		for(int i = 0; i < 4; i++) {
			int key = 0;
			while(true) {
				if(checkedPositions.size() == 4) break;
				key = Animal.getMovement().get(random.nextInt(4));
				if(!checkedPositions.contains(key)) {
					checkedPositions.add(key);
					break;
				}
			}
			ArrayList<Integer> thisPosition = new ArrayList<Integer>();
			ArrayList<Integer> otherPosition = new ArrayList<Integer>();
			if(key == UP) {
				thisPosition.add(this.getPosition().get(0));
				thisPosition.add(this.getPosition().get(1) - 1);
				otherPosition.add(other.getPosition().get(0));
				otherPosition.add(other.getPosition().get(1) - 1);
			}
			else if(key == DOWN) {
				thisPosition.add(this.getPosition().get(0));
				thisPosition.add(this.getPosition().get(1) + 1);
				otherPosition.add(other.getPosition().get(0));
				otherPosition.add(other.getPosition().get(1) + 1);
			}
			else if(key == LEFT) {
				thisPosition.add(this.getPosition().get(0) - 1);
				thisPosition.add(this.getPosition().get(1));
				otherPosition.add(other.getPosition().get(0) - 1);
				otherPosition.add(other.getPosition().get(1));
			}
			else if(key == RIGHT) {
				thisPosition.add(this.getPosition().get(0) + 1);
				thisPosition.add(this.getPosition().get(1));
				otherPosition.add(other.getPosition().get(0) + 1);
				otherPosition.add(other.getPosition().get(1));
			}
			int parent = random.nextInt(2);
			if(parent == 1) {
				if(this.getWorld().canMove(thisPosition) && this.getWorld().validPosition(thisPosition)) {
					this.getWorld().createOrganism(this.getOrganismName(), thisPosition, false);
					this.setCopulationCooldown(10);
					otherAnimal.setCopulationCooldown(10);
					return;
				}
			}
			else {
				if(other.getWorld().canMove(otherPosition) && other.getWorld().validPosition(otherPosition)) {
					other.getWorld().createOrganism(other.getOrganismName(), otherPosition, false);
					this.setCopulationCooldown(10);
					otherAnimal.setCopulationCooldown(10);
					return;
				}
			}
			thisPosition.clear();
			otherPosition.clear();
		}
	}

	@Override
	public void collision(Organism other) {
		// TODO Auto-generated method stub
		if(this.getStrength() < other.getStrength()) {
			ArrayList<Integer> position = this.getPosition();
			other.changePosition(position);
			this.getWorld().removeOrganism(this);
			System.out.println("Fight: " + other.toString() + " vs. " + this.toString() + " winner = " + other.getOrganismName());
		}
		else if(this.getStrength() >= other.getStrength()) {
			ArrayList<Integer> position = other.getPosition();
			this.changePosition(position);
			other.getWorld().removeOrganism(other);
			System.out.println("Fight: " + this.toString() + " vs. " + other.toString() + " winner = " + this.getOrganismName());
		}

	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

}
