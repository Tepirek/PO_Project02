import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public final class Antelope extends Animal {

	public Antelope(ArrayList<Integer> position, World world) {
		super("Antelope", 4, 4, position, world, new Color(102, 79, 21));
		
	}

	@Override
	public Boolean action() {
		// TODO Auto-generated method stub
		Random random = new Random();
		this.move(Animal.getMovement().get(random.nextInt(4)), 2);
		return true;
	}
	
	@Override
	public void collision(Organism other) {
		// TODO Auto-generated method stub
		super.collision(other);
	}
}
