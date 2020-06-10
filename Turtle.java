import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public final class Turtle extends Animal {

	public Turtle(ArrayList<Integer> position, World world) {
		super("Turtle", 2, 1, position, world, new Color(53, 89, 29));
		
	}

	@Override
	public Boolean action() {
		// TODO Auto-generated method stub
		Random random = new Random();
		this.move(Animal.getMovement().get(random.nextInt(4)), 1);
		return true;
	}
	
	@Override
	public void collision(Organism other) {
		// TODO Auto-generated method stub
		if(other.getStrength() < 5) {
			this.getWorld().getSpectator().addNewComment("Turtle protects itself against " + other.toString());
			return;
		}
		super.collision(other);
	}

}
