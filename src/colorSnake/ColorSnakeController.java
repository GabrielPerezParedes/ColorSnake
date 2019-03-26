package colorSnake;


import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorSnakeController {
	
	@FXML
	private Pane paneBoard;
	private ColorSnakeAnimationTimer timer;
	private ArrayList<Sprite> snake = new ArrayList<Sprite>();
	private int scale;

	@FXML
	public void initialize()
	{
		System.out.println("ColorSnakeController initializer");
		scale = 20;
		/*
		 * Limitar el espacio de la Cancha, para que la
		 * figura no se dibuje fuera
		 */
		Rectangle clip = new Rectangle(0, 0, 0, 0);
		clip.widthProperty().bind(paneBoard.widthProperty());
		clip.heightProperty().bind(paneBoard.heightProperty());
		paneBoard.setClip(clip);
		
		snake.add(new Sprite (20 * scale, 20 * scale, scale, scale, "snake", Color.RED, 2));
		snake.add(new Sprite (19 * scale , 20 * scale, scale, scale, "snake", Color.GREEN, 2));
		snake.add(new Sprite (18 * scale , 20 * scale, scale, scale, "snake", Color.GREEN, 2));
		
		snake.forEach(sprite -> {
			paneBoard.getChildren().add(sprite);
		});
		
		timer = new ColorSnakeAnimationTimer(paneBoard, snake, scale);
		timer.start();

	}

	@FXML
	public void botonSalir()
	{
		System.out.println("Boton Salir Presionado.");
		System.exit(0);
	}

	@FXML
	public void keyMoveHnd(KeyEvent key)
	{

		switch(key.getCode())
		{
		case W:
			if (snake.get(0).getDireccion() != 3) {
				snake.get(0).setDireccion(1);
			}
			break;
		case S:
			if (snake.get(0).getDireccion() != 1) {
				snake.get(0).setDireccion(3);
			}
			break;
		case A:

			if (snake.get(0).getDireccion() != 2) {
				snake.get(0).setDireccion(0);
			}
			break;
		case D:

			if (snake.get(0).getDireccion() != 0) {
				snake.get(0).setDireccion(2);
			}
			break;
		default:
			System.out.println("KeyMoveHnd:" + key.getCode() );
			break;
		}
		
		key.consume();
		
	}
	
	
}


