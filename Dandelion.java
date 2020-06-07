import java.awt.Color;
import java.util.ArrayList;

public final class Dandelion extends Plant {

	public Dandelion(ArrayList<Integer> position, World world) {
		super("Dandelion", 0, 0, position, world, new Color(255, 247, 15), 1);
		
	}
	
	@Override
	public Boolean action() {
		for(int i = 0; i < 3; i++) {
			super.action();
		}
		return true;
	}

}
