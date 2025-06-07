/***********************************************************
* Exercise 08
* This is an exercise that uses Javafx to create a GUI minesweeper game.
* @created_date 2020-12-01 12:16
***********************************************************/
package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage){
		GameStage theGameStage = new GameStage();
		theGameStage.setStage(stage);
	}

}
