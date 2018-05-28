import javafx.scene.text.Text;

public class Score extends Text{

	private int value;
	private int level;
	
	public Score() {
		value = 0;
		level=1;
		this.setStyle("-fx-font: 64 arial;");
		this.setFill(javafx.scene.paint.Color.WHITE);
		setScore(value);
	}
	
	public void updateDisplay() {
		this.setText("Score: " + value +"\nLevel: " + level);
	}
	
	public int getScore() {
		return value;
	}
	
	public void setScore(int a) {
		value = a;
		updateDisplay();
	}
	
	public void incrementLevel() {
		level++;
		updateDisplay();
	}
}
