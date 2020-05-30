import java.util.ArrayList;
import java.util.Arrays;

public abstract class Animal extends Organism {

	public Animal(String name, int strength, int initiative, ArrayList<Integer> position, World world) {
		super(name, strength, initiative, position, world);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		int step = 1;
		ArrayList<Integer> newPosition;
		while(true) {
			newPosition = new ArrayList<Integer>(Arrays.asList(this.getPosition().get(0) + 1, this.getPosition().get(1)));
			if(this.getPosition().get(0) >= 0 && this.getPosition().get(0) < this.getWorld().getDimension().get(0) - Organism.getWidth()) {
				this.getMapObject().setBounds(newPosition.get(0), newPosition.get(1), Organism.getWidth(), Organism.getHeight());
				this.updatePosition(newPosition);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
