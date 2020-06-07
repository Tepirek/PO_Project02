import java.awt.Color;
import java.util.ArrayList;

public final class Wolfberry extends Plant {

	public Wolfberry(ArrayList<Integer> position, World world) {
		super("Wolfberry", 99, 0, position, world, new Color(80, 13, 105), 1);
		
	}

	@Override
	public void collision(Organism other) {
		// TODO Auto-generated method stub
		this.getWorld().removeOrganism(this);
		other.getWorld().removeOrganism(other);
	}
	
}
