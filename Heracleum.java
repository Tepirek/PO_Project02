import java.awt.Color;
import java.util.ArrayList;

public final class Heracleum extends Plant {
	
	private static int UP = 87;
	private static int DOWN = 83;
	private static int LEFT = 65;
	private static int RIGHT = 68;

	public Heracleum(ArrayList<Integer> position, World world) {
		super("Heracleum", 10, 0, position, world, new Color(255, 0, 0), 1);
		
	}

	@Override
	public void collision(Organism other) {
		// TODO Auto-generated method stub
		this.getWorld().removeOrganism(this);
		if(!(other instanceof Cybersheep)) other.getWorld().removeOrganism(other);
	}
	
	@Override
	public Boolean action() {
		// TODO Auto-generated method stub
		ArrayList<Integer> oldPosition = this.getPosition();
		ArrayList<Integer> position = new ArrayList<Integer>();
		for(int i = 0; i < Animal.getMovement().size(); i++) {
			int key = Animal.getMovement().get(i);
			if(key == UP) {
				position.add(oldPosition.get(0));
				position.add(oldPosition.get(1) - 1);
			}
			else if(key == DOWN) {
				position.add(oldPosition.get(0));
				position.add(oldPosition.get(1) + 1);
			}
			else if(key == LEFT) {
				position.add(oldPosition.get(0) - 1);
				position.add(oldPosition.get(1));
			}
			else if(key == RIGHT) {
				position.add(oldPosition.get(0) + 1);
				position.add(oldPosition.get(1));
			}
			if(this.getWorld().canMove(position)) {
				if(!this.getWorld().validPosition(position)) {
					Organism other = this.getWorld().getOrganismAt(position);
					if(!(other instanceof Cybersheep) && !(other instanceof Plant)) {
						this.getWorld().removeOrganism(other);
						// System.out.println("REMOVING " + other.getOrganismName());
					}
				}
			}
			position.clear();
		}
		super.action();
		return true;
	}
}
