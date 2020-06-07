import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Plant extends Organism {
	
	private int spawnRate;
	
	public Plant(String name, int strength, int initiative, ArrayList<Integer> position, World world,
			Color color, int spawnRate) {
		super(name, strength, initiative, position, world, color);
		this.spawnRate = spawnRate;
	}

	@Override
	public Boolean action() {
		// TODO Auto-generated method stub
		Random random = new Random();
		int chance = random.nextInt(100);
		// System.out.println(chance);
		if(chance > this.spawnRate) return false;
		ArrayList<Integer> options = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
		int option = options.get(random.nextInt(4));
		ArrayList<Integer> newPosition = new ArrayList<Integer>();
		if(option == 1) {
			newPosition.add(getPosition().get(0));
			newPosition.add(getPosition().get(1) - 1);
		}
		else if(option == 2) {
			newPosition.add(getPosition().get(0));
			newPosition.add(getPosition().get(1) + 1);
		}
		else if(option == 3) {
			newPosition.add(getPosition().get(0) - 1);
			newPosition.add(getPosition().get(1));
		}
		else if(option == 4) {
			newPosition.add(getPosition().get(0) + 1);
			newPosition.add(getPosition().get(1));
		}
		if(this.getWorld().canMove(newPosition) && this.getWorld().validPosition(newPosition)) {
			this.getWorld().createOrganism(this.getOrganismName(), newPosition, false);
			return true;
		}
		return false;
	}

	@Override
	public void collision(Organism other) {
		// TODO Auto-generated method stub
		ArrayList<Integer> position = this.getPosition();
		other.changePosition(position);
		this.getWorld().removeOrganism(this);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

}
