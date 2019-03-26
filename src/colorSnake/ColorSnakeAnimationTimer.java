package colorSnake;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ColorSnakeAnimationTimer extends AnimationTimer{

	private Pane paneBoard;
	private ArrayList<Sprite> snake;
	private double time;
	private boolean foodExists;
	private Sprite food;
	private int scale;
	private boolean cantDraw;
	private boolean isDead;


	public ColorSnakeAnimationTimer(Pane paneBoard, ArrayList<Sprite> snake, int scale) {

		this.paneBoard = paneBoard;
		this.snake = snake;
		this.time = 0;
		this.foodExists = false;
		this.scale = scale;
		this.cantDraw = false;
		this.isDead = false;
	}


	private List<Sprite> getSprites() {
		return paneBoard.getChildren().stream().map(node -> (Sprite)node).collect(Collectors.toList());
	}

	@Override
	public void handle(long now) {
		List<Sprite> sprites = getSprites();
		if (!isDead) {
			update(sprites);
		}
	}


	private void update(List<Sprite> sprites) {
		
		sprites.forEach(sprite -> {
			if(sprite.getType().equals("food")) {
				foodExists = true;
			}
		});
		
		if (!foodExists) {
			
			do {
				int x = (int) Math.floor(Math.random() * 40);
				int y = (int) Math.floor(Math.random() * 40);
				food = new Sprite(x * scale, y * scale, scale, scale, "food", Color.YELLOW, 4);
				sprites.forEach(sprite -> {
					if(sprite.getTranslateX() == food.getTranslateX() && sprite.getTranslateY() == food.getTranslateY()) {
						cantDraw = true;
					}
				});
			} while(cantDraw);
			
			cantDraw = false;
			
			paneBoard.getChildren().add(food);
		}
		
		
		time += 0.162;
		if (time > 2)
		{
			snake.forEach(sprite -> {
				move(sprite);
				time = 0;
			});
		}
		
		if(snake.get(0).getTranslateX() == food.getTranslateX() && snake.get(0).getTranslateY() == food.getTranslateY()) {
			Sprite tail = snake.get(snake.size() - 1);
			Sprite grow = new Sprite ( tail.getOldX(), tail.getOldY(), scale, scale, "snake", Color.GREEN, tail.getDireccion());
			snake.add(grow);
			paneBoard.getChildren().remove(food);
			paneBoard.getChildren().add(grow);
			foodExists = false;
		}
		
		isDead();
	}


	private void isDead() {
		snake.forEach(sprite -> {
			if (snake.indexOf(sprite) != 0) {
				if (sprite.getTranslateX() == snake.get(0).getTranslateX() && sprite.getTranslateY() == snake.get(0).getTranslateY()) {
					isDead = true;
				}
			}
		});
		
		if (snake.get(0).getTranslateX() >= paneBoard.getWidth() || snake.get(0).getTranslateY() >= paneBoard.getHeight()) {
			isDead = true;
		}
	}


	private void move(Sprite sprite) {
		sprite.setOldX((int)sprite.getTranslateX());
		sprite.setOldY((int)sprite.getTranslateY());
		
		int index = snake.indexOf(sprite);
		
		if(index == 0) {
			switch (sprite.getDireccion()) {

				case 0:
		
					sprite.setTranslateX(sprite.getTranslateX() - scale);
		
					break;
		
				case 1:
		
					sprite.setTranslateY(sprite.getTranslateY() - scale);
		
					break;
		
				case 2:
		
					sprite.setTranslateX(sprite.getTranslateX() + scale);
		
					break;
		
				case 3:
		
					sprite.setTranslateY(sprite.getTranslateY() + scale);
		
					break;
			}
		}
		else
		{
			sprite.setTranslateX((int)snake.get(index - 1).getOldX());
			sprite.setTranslateY((int)snake.get(index - 1).getOldY());
		}
	
	}


}
