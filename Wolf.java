import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public final class Wolf extends Animal {

	public Wolf(ArrayList<Integer> position, World world) {
		super("Wolf", 9, 5, position, world, new Color(59, 59, 59));
		
	}

	@Override
	public Boolean action() {
		// TODO Auto-generated method stub
		Random random = new Random();
		this.move(Animal.getMovement().get(random.nextInt(4)), 1);
		return true;
	}
	
}
