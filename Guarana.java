import java.awt.Color;
import java.util.ArrayList;

public final class Guarana extends Plant {

	public Guarana(ArrayList<Integer> position, World world) {
		super("Guarana", 0, 0, position, world, new Color(138, 47, 73), 1);
		
	}
	
	@Override
	public void collision(Organism other) {
		// TODO Auto-generated method stub
		int old = other.getStrength();
		other.increaseStrength(3);
		this.getWorld().getSpectator().addNewComment(other.toString() + " eats Guarana and gains strength: (" + old + ") => (" + (old + 3) + ")");
		super.collision(other);
	}
}