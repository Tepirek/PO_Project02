import java.util.ArrayList;

public final class Spectator {

	private ArrayList<String> comments;
	private World world;
	
	public Spectator(World world) {
		this.world = world;
		this.comments = new ArrayList<String>();
	}

	public void addNewComment(String comment) {
		this.comments.add(comment);
	}
	
	public void displayComments() {
		int index = 1;
		String text = "<html>";
		for(String comment : this.comments) {
			text += "<p>" + index + ". " + comment + "</p>";
			index++;
		}
		text += "</html>";
		this.world.getGameFrame().getCommentBox().setText(text);
	}
	
}
