import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public final class Fox extends Animal {

	public Fox(ArrayList<Integer> position, World world) {
		super("Fox", 3, 7, position, world, new Color(158, 81, 14));
		
	}

	@Override
	public Boolean action() {
		// TODO Auto-generated method stub
		Random random = new Random();
		this.move(Animal.getMovement().get(random.nextInt(4)), 1);
		return true;
	}

}
