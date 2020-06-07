import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public final class Human extends Animal {

	private int keyPressed;
	private int skillCooldown;
	private int skillDuration;
	private int step;
	
	public Human(ArrayList<Integer> position, World world) {
		super("Human", 5, 4, position, world, new Color(255, 255, 255));
		this.skillCooldown = 0;
		this.skillDuration = 0;
		this.step = 1;
	}
	
	public final int getSkillCooldown() {
		return skillCooldown;
	}

	public final void setSkillCooldown(int skillCooldown) {
		this.skillCooldown = skillCooldown;
	}

	public final int getSkillDuration() {
		return skillDuration;
	}

	public final void setSkillDuration(int skillDuration) {
		this.skillDuration = skillDuration;
	}

	public void updateSkill() {
		if(this.skillCooldown == 0 && this.skillDuration == 0) this.step = 1;
		if(this.skillCooldown == 0 && this.skillDuration > 0) this.skillDuration--;
		if(this.skillCooldown > 0) this.skillCooldown--;
	}
	
	public void specialSkill() {
		if(this.skillDuration == 0 && this.skillCooldown == 0) this.skillDuration = 5;
		if(this.skillDuration > 0) this.step = 2;
		if(this.skillDuration == 0) {
			this.skillCooldown = 5;
			this.step = 1;
		}
	}
	
	@Override
	public Boolean action() { 
		this.getWorld().getGameFrame().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				keyPressed = e.getKeyCode();
			}
		});
		while(true) {
			if(Animal.getMovement().contains(this.keyPressed)) break;
			if(this.keyPressed == 82) break;
		}
		if(this.keyPressed == 82) {
			System.out.println("SPECIAL SKILL");
			if(this.skillCooldown == 0 && this.skillDuration == 0) this.specialSkill();
			this.keyPressed = -1;
			return true;
		}
		move(keyPressed, this.step);
		this.keyPressed = -1;
		// System.out.println("CD: " + this.skillCooldown + ", CDD: " + this.skillDuration);
		this.updateSkill();
		return true;
	}
	
}
