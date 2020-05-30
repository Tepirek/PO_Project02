import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public final class World {
	private static ArrayList<String> animalNames = new ArrayList<String>(Arrays.asList("Wolf", "Sheep", "Fox", "Turtle", "Antelope", "Cybersheep"));
	private static ArrayList<String> plantNames = new ArrayList<String>(Arrays.asList("Grass", "Dandelion", "Guarana", "Wolfberry", "Heracleum"));
	private ArrayList<Organism> organisms = new ArrayList<Organism>();
	private Game game;
	private ArrayList<Integer> dimension = new ArrayList<Integer>();
	
	public final Game getGame() {
		return this.game;
	}
	
	public final ArrayList<Integer> getDimension() {
		return this.dimension;
	}
	
	public World(int width, int height) {
		this.dimension.add(width - 15);
		this.dimension.add(height);
		this.game = new Game(width, height);
	}
	
	public void init() {
		Random rand = new Random();
		ArrayList<Integer> position;
		for(int i = 0; i < 2; i++) {
			for(String name : World.animalNames) {
				position = new ArrayList<Integer>(Arrays.asList(rand.nextInt(this.dimension.get(0) - Organism.getWidth()), rand.nextInt(this.dimension.get(1) - Organism.getHeight())));
				Organism newOrganism = this.createNewOrganism(name, position);
				this.organisms.add(newOrganism);
				// newOrganism.start();
			}
			for(String name : World.plantNames) {
				position = new ArrayList<Integer>(Arrays.asList(rand.nextInt(this.dimension.get(0) - Organism.getWidth()), rand.nextInt(this.dimension.get(1) - Organism.getHeight())));
				Organism newOrganism = this.createNewOrganism(name, position);
				this.organisms.add(newOrganism);
				// newOrganism.start();
			}	
		}
	}
	
	public void gameLoop() {
		init();
		for(int i = 0; i < 10; i++)
			draw();
	}
	
	private Organism createNewOrganism(String name, ArrayList<Integer> position) {
		switch (name) {
			case "Wolf":
				return new Wolf(position, this);
			case "Sheep":
				return new Sheep(position, this);
			case "Fox":
				return new Fox(position, this);
			case "Turtle":
				return new Turtle(position, this);
			case "Antelope":
				return new Antelope(position, this);
			case "Cybersheep":
				return new Cybersheep(position, this);
			case "Grass":
				return new Grass(position, this);
			case "Dandelion":
				return new Dandelion(position, this);
			case "Guarana":
				return new Guarana(position, this);
			case "Wolfberry":
				return new Wolfberry(position, this);
			case "Heracleum":
				return new Heracleum(position, this);
		}
		return null;
	}
	
	public void draw() {
		for(Organism o : this.organisms) {
			ArrayList<Integer> lastPos = o.getPosition();
			o.getMapObject().setBounds(lastPos.get(0) + 10, lastPos.get(1) - 5, Organism.getWidth(), Organism.getHeight());
			// update lastPosition
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
