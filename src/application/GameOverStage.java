/***********************************************************
* Exercise 08
* This is an exercise that uses Javafx to create a GUI minesweeper game.
* @created_date 2020-12-01 12:16
***********************************************************/
package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOverStage {
	private StackPane pane;
	private Scene scene;
	private GraphicsContext gc;
	private Canvas canvas;

	GameOverStage(int x){
		this.pane = new StackPane();
		this.scene = new Scene(pane, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.setProperties(x);

	}

	private void setProperties(int result){
		if(result == 0){
			this.gc.setFill(Color.BLUE);										//set font color of text
			this.gc.fillRect(0,0,GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
			Font theFont = Font.font("Calibri",FontWeight.BOLD,60);//set font type, style and size
			this.gc.setFont(theFont);

			this.gc.setFill(Color.YELLOW);										//set font color of text
			this.gc.fillText("YOU LOSE!", GameStage.WINDOW_WIDTH*0.35, GameStage.WINDOW_HEIGHT*0.4);						//add a hello world to location x=60,y=50

			Button playbtn = new Button("Play Again?");
			this.addEventHandler(playbtn);

			pane.getChildren().add(this.canvas);
			pane.getChildren().add(playbtn);
		}else {
			this.gc.setFill(Color.PINK);										//set font color of text
			this.gc.fillRect(0,0,GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
			Font theFont = Font.font("Calibri",FontWeight.BOLD,60);//set font type, style and size
			this.gc.setFont(theFont);

			this.gc.setFill(Color.BLACK);										//set font color of text
			this.gc.fillText("YOU WIN!", GameStage.WINDOW_WIDTH*0.35, GameStage.WINDOW_HEIGHT*0.4);						//add a hello world to location x=60,y=50

			Button playbtn = new Button("Exit Game");
			this.addEventHandler(playbtn);

			pane.getChildren().add(this.canvas);
			pane.getChildren().add(playbtn);
		}
	}

	private void addEventHandler(Button btn) {
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				System.exit(0);
			}
		});

	}

	Scene getScene(){
		return this.scene;
	}
}
