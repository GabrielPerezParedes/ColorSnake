package colorSnake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sprite extends Rectangle {

	private int direccion;
	private int oldX;
	private int oldY;
	private String type;

		public Sprite(int x, int y, int width, int height, String type, Color color, int direction) {
			super(width, height, color);
			
			this.direccion = direction;
			
			this.type = type;
			
			this.oldX = x;
			this.oldY = y;
			
			setTranslateX(x);
			setTranslateY(y);
			
			
		}

		public int getDireccion() {
			return direccion;
		}

		public void setDireccion(int direccion) {
			this.direccion = direccion;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getOldX() {
			return oldX;
		}

		public void setOldX(int oldX) {
			this.oldX = oldX;
		}

		public int getOldY() {
			return oldY;
		}

		public void setOldY(int oldY) {
			this.oldY = oldY;
		}
		
		
		
		
		
}


