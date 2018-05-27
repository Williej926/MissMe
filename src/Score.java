import com.sun.prism.paint.Color;

import javafx.scene.text.Text;

public class Score extends Text{

	private int value;

	
	public Score() {
		value = 0;
		this.setStyle("-fx-font: 64 arial;");
		this.setFill(javafx.scene.paint.Color.WHITE);
		setScore(value);
	}
	
	public void updateDisplay() {
		this.setText("Score: " + value);
	}
	
	public int getScore() {
		return value;
	}
	
	public void setScore(int a) {
		value = a;
		updateDisplay();
	}
}
