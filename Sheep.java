import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Sheep extends Animal {

	public Sheep(ArrayList<Integer> position, World world) {
		super("Sheep", 4, 4, position, world, new Color(199, 167, 167));
		
	}
	
	public Sheep(String name, ArrayList<Integer> position, World world) {
		super(name, 11, 4, position, world, new Color(46, 217, 168));
		
	}

	@Override
	public Boolean action() {
		// TODO Auto-generated method stub
		Random random = new Random();
		this.move(Animal.getMovement().get(random.nextInt(4)), 1);
		return true;
	}

}
