/***********************************************************
* Exercise 08
* This is an exercise that uses Javafx to create a GUI minesweeper game.
* @created_date 2020-12-01 12:16
***********************************************************/
package application;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Element {
	private String type;
	protected Image img;
	protected ImageView imgView;
	protected GameStage gameStage;
	protected int row, col;

	public final static Image FLAG_IMAGE = new Image("images/flag.gif",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image BOMB_IMAGE = new Image("images/bomb.gif",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image LAND_IMAGE = new Image("images/land.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);

	public final static Image ONE_IMAGE = new Image("images/one.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image TWO_IMAGE = new Image("images/two.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image THREE_IMAGE = new Image("images/three.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);
	public final static Image FOUR_IMAGE = new Image("images/four.png",GameStage.CELL_WIDTH,GameStage.CELL_WIDTH,false,false);

	public final static int IMAGE_SIZE = 70;

	public final static String FLAG_TYPE = "flag";
	public final static String BOMB_TYPE = "bomb";
	public final static String LAND_TYPE = "land";
	public final static String LAND_FLAG_TYPE = "landToFlag";

	public Element(String type, GameStage gameStage) {
		this.type = type;
		this.gameStage = gameStage;

		// load image depending on the type
		switch(this.type) {
			case Element.FLAG_TYPE: this.img = Element.FLAG_IMAGE; break;
			case Element.LAND_TYPE: this.img = Element.LAND_IMAGE; break;
			case Element.BOMB_TYPE: this.img = Element.BOMB_IMAGE; break;
		}

		this.setImageView();
		this.setMouseHandler();
	}

	protected void loadImage(String filename,int width, int height){
		try{
			this.img = new Image(filename,width,height,false,false);
		} catch(Exception e){}
	}


	String getType(){
		return this.type;
	}

	int getRow() {
		return this.row;
	}

	int getCol() {
		return this.col;
	}


	protected ImageView getImageView(){
		return this.imgView;
	}

	void setType(String type){
		this.type = type;
	}

	void initRowCol(int i, int j) {
		this.row = i;
		this.col = j;
	}

	private void setImageView() {
		// initialize the image view of this element
		this.imgView = new ImageView();
		this.imgView.setImage(this.img);
		this.imgView.setLayoutX(0);
		this.imgView.setLayoutY(0);
		this.imgView.setPreserveRatio(true);

		if(this.type.equals(Element.FLAG_TYPE)) {
			this.imgView.setFitWidth(GameStage.FLAG_WIDTH);
			this.imgView.setFitHeight(GameStage.FLAG_HEIGHT);
		}else {
			this.imgView.setFitWidth(GameStage.CELL_WIDTH);
			this.imgView.setFitHeight(GameStage.CELL_HEIGHT);
		}
	}

	private void setMouseHandler(){
		Element element = this;
		this.imgView.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e) {
                switch(element.getType()) {
	                case Element.FLAG_TYPE: 		// if flag, set flagClicked to true
								                	System.out.println("FLAG clicked!");
								    	            gameStage.setFlagClicked(true);
								    	            break;
	    			case Element.LAND_TYPE:
			    									System.out.println("LAND clicked!");
								    				if(!gameStage.isFlagClicked()) {
								    					if(!gameStage.isBomb(element)){				// if not a bomb, clear image
								    						imgNum(element);
								    						gameStage.setLandCount();       		//count land clicked

								    						if(gameStage.getLandCount() == 66){ 	//if clicked all safe land tiles, game result is winner
								    							gameStage.flashGameOver(1);
								    						}

								    					}
								    					else {
								    						changeImage(element,Element.BOMB_IMAGE); // if bomb, change image to bomb
								    						gameStage.flashGameOver(0);
								    					}


								    	            } else {
								    	            	if(gameStage.isBomb(element)){		 		//if bomb flag counter + 1
								    	            		gameStage.setFlagCount();
								    	            	}
								    	            	changeImage(element,Element.FLAG_IMAGE);	// if flag was clicked before hand, change image to flag
								    	            	element.setType(LAND_FLAG_TYPE);			// change type to landToFlag
								    	            	gameStage.setFlagClicked(false);	    	// reset flagClicked to false
								    	            	if(gameStage.getFlagCount() == 15){			//check if all bombs are flagged
								    	            		gameStage.flashGameOver(1);
								    	            	}

								    	            }
								    				break;
	    			case Element.LAND_FLAG_TYPE:

	    											changeImage(element,Element.LAND_IMAGE);		// if flag is clicked, change image back to land
								    				if(gameStage.isBomb(element)){
								    					gameStage.setFlagCountMinus();
								    				}
							    	            	element.setType(LAND_TYPE);
							    	            	break;
                }
			}	//end of handle()
		});
	}

	private void clearImage(Element element) {
		imgView.setImage(null);
	}

	private void changeImage(Element element, Image image) {
		this.imgView.setImage(image);

	}

	private void imgNum(Element element){
		if(gameStage.numNearBomb(element) == 1){ //display 1
			changeImage(element, ONE_IMAGE);
		}
		else if(gameStage.numNearBomb(element) == 2){ //display 2
			changeImage(element, TWO_IMAGE);
		}
		else if(gameStage.numNearBomb(element) == 3){ //display 3
			changeImage(element, THREE_IMAGE);
		}
		else if(gameStage.numNearBomb(element) == 4){ //display 4
			changeImage(element, FOUR_IMAGE);
		} else {
			clearImage(element);
		}

	}
}
