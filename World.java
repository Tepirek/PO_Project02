import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

public final class World {

	ArrayList<String> animalNames = new ArrayList<String>(Arrays.asList("Wolf", "Sheep", "Fox", "Turtle", "Antelope", "Cybersheep"));
	ArrayList<String> plantNames = new ArrayList<String>(Arrays.asList("Grass", "Dandelion", "Guarana", "Wolfberry", "Heracleum"));
	ArrayList<Organism> organisms;
	ArrayList<Organism> addAfter;
	ArrayList<Organism> removeAfter;
	Organism map[][];
	int width;
	int height;
	GameFrame gameFrame;
	Organism player;
	private int days;
	
	public final GameFrame getGameFrame() {
		return gameFrame;
	}

	public World(int width, int height) {
		this.days = 0;
		this.width = width;
		this.height = height;
		this.organisms = new ArrayList<Organism>();
		this.addAfter = new ArrayList<Organism>();
		this.removeAfter = new ArrayList<Organism>();
		this.gameFrame = new GameFrame(this.width, this.height);
		this.map = new Organism[this.height][this.width];
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				this.map[i][j] = null;
			}
		}
		this.player = this.createOrganism("Human", new ArrayList<Integer>(Arrays.asList(this.width / 2, this.height / 2)), true);
	}
	
	public World() {
		ArrayList<String> output = new ArrayList<String>();
		File file = new File("save.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("save.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] s = null;
		try {
			s = br.readLine().split(", ");
			this.width = Integer.parseInt(s[0]);
			this.height = Integer.parseInt(s[1]);
			this.days = Integer.parseInt(s[2]);
			this.organisms = new ArrayList<Organism>();
			this.addAfter = new ArrayList<Organism>();
			this.removeAfter = new ArrayList<Organism>();
			this.gameFrame = new GameFrame(this.width, this.height);
			this.map = new Organism[this.height][this.width];
			while((s = br.readLine().split(", ")) != null) {
				ArrayList<Integer> position = new ArrayList<Integer>(Arrays.asList(Integer.parseInt(s[4]), Integer.parseInt(s[5])));
				if(s[0] == "Human") {
					Human h = (Human) this.createOrganism("Human", position, true);
					h.setStrength(Integer.parseInt(s[1]));
					h.setAge(Integer.parseInt(s[3]));
					h.setSkillCooldown(Integer.parseInt(s[6]));
					h.setSkillDuration(Integer.parseInt(s[7]));
					this.player = h;
					h.changePosition(h.getPosition());
				}
				else {
					Organism o = this.createOrganism(s[0], position, true);
					o.setStrength(Integer.parseInt(s[1]));
					o.setAge(Integer.parseInt(s[3]));
					o.changePosition(o.getPosition());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveGame() {
		File file = new File("save.txt");
		if(file.exists()) file.delete();
		try {
			file.createNewFile();
			FileWriter fw = new FileWriter("save.txt", true);
			fw.write(this.width + ", " + this.height + ", " + this.days + "\n");
			for(Organism o : this.organisms) {
				if(o instanceof Human) {
					Human oHuman = (Human) o;
					fw.write(o.getOrganismName() + ", " + o.getStrength() + ", " + o.getInitiative() + ", " + o.getAge() + ", " + o.getPosition().get(0) + ", " + o.getPosition().get(1) + ", " + oHuman.getSkillCooldown() + ", "  + oHuman.getSkillDuration() + "\n");
				}
				else fw.write(o.getOrganismName() + ", " + o.getStrength() + ", " + o.getInitiative() + ", " + o.getAge() + ", " + o.getPosition().get(0) + ", " + o.getPosition().get(1) + "\n");
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gameLoop() {
		init();
		while(this.player.isOrganismAlive()) {
			for(Organism organism : this.organisms) {
				// if(organism instanceof Human) System.out.println("HUMAN TURN");
				organism.action();
				organism.incrementAge();
				// System.out.println("CURRENT = " + organism.getOrganismName());
//				try {
//					Thread.sleep(30);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			this.organisms.addAll(this.addAfter);
			this.addAfter.clear();
			this.organisms.removeAll(this.removeAfter);
			this.removeAfter.clear();
			this.sortOrganisms();
			this.saveGame();
			this.days++;
			// System.out.println(days);
		}
		// this.getGameFrame().dispose();
	}
	
	public Boolean canMove(ArrayList<Integer> positon) {
		if(positon.get(0) >= 0 && positon.get(0) < this.width && positon.get(1) >= 0 && positon.get(1) < this.height) return true;
		return false;
	}
	
	public final Boolean validPosition(ArrayList<Integer> position) {
		if(this.map[position.get(1)][position.get(0)] == null) return true;
		return false;
	}
	
	public final Organism getOrganismAt(ArrayList<Integer> position) {
		return this.map[position.get(1)][position.get(0)];
	}
	
	private void init() {
		for(String name : this.animalNames) {
			int loopSize = (name == "Wolf") ? 2 : 2;
			for(int i = 0; i < loopSize; i++) {
				Random random = new Random();
			ArrayList<Integer> position;
			while(true) {
				position = new ArrayList<Integer>(Arrays.asList(random.nextInt(this.width), random.nextInt(this.height)));
				if(this.validPosition(position)) break;
			}
				this.createOrganism(name, position, true);
			}
		}
		for(String name : this.plantNames) {
			for(int i = 0; i < 2; i++) {
				Random random = new Random();
				ArrayList<Integer> position;
				while(true) {
					position = new ArrayList<Integer>(Arrays.asList(random.nextInt(this.width), random.nextInt(this.height)));
					if(this.validPosition(position)) break;
				}
				this.createOrganism(name, position, true);
			}
		}
	}
	
	public Organism createOrganism(String name, ArrayList<Integer> position, Boolean init) {
		Organism newOrganism = null;
		switch(name) {
			case "Human" : 
				newOrganism = new Human(position, this);
				break;
			case "Wolf": 
				newOrganism = new Wolf(position, this);
				break;
			case "Sheep": 
				newOrganism = new Sheep(position, this);
				break;
			case "Fox": 
				newOrganism = new Fox(position, this);
				break;
			case "Turtle": 
				newOrganism = new Turtle(position, this);
				break;
			case "Antelope": 
				newOrganism = new Antelope(position, this);
				break;
			case "Cybersheep": 
				newOrganism = new Cybersheep(position, this);
				break;
			case "Grass": 
				newOrganism = new Grass(position, this);
				break;
			case "Dandelion": 
				newOrganism = new Dandelion(position, this);
				break;
			case "Guarana": 
				newOrganism = new Guarana(position, this);
				break;
			case "Wolfberry": 
				newOrganism = new Wolfberry(position, this);
				break;
			case "Heracleum": 
				newOrganism = new Heracleum(position, this);
				break;
		}
		this.map[position.get(1)][position.get(0)] = newOrganism;
		if(init) this.organisms.add(newOrganism);
		else this.addAfter.add(newOrganism);
		this.sortOrganisms();
		// this.printOrganisms();
		// System.out.println(newOrganism.toString());
		return newOrganism;
	}

	public void removeOrganism(Organism organism) {
		organism.kill();
		this.map[organism.getPosition().get(1)][organism.getPosition().get(0)] = null;
		this.getGameFrame().removeOldElement(organism.getMapObject());
		this.removeAfter.add(organism);
	}
	
	private void sortOrganisms() {
		this.organisms = (ArrayList<Organism>) this.organisms.stream()
				.sorted(Comparator.comparing(Organism::getInitiative).reversed())
				.collect(Collectors.toList());
	}
	
	private void printOrganisms() {
		for(Organism o : this.organisms) {
			System.out.print(o.getInitiative() + ", ");
		}
		System.out.println();
	}
	
}
