/**
 * @author Sijia Meng
 * Catching Disks
 * 2018/4/28
 */

import processing.core.PApplet;

public class MyGame extends PApplet {
    // Timer
    int timer;
    
    // Game length in milliseconds
    int gameDuration = 60 * 1000;
    
    // Keep track of current score
    static int score = 0;

    // Canvas size
    final int canvasWidth  = 500;
    final int canvasHeight = 500;

    // Declare disks
    float[] xValue = {100,200,300,400};
    float[] yValue = {70, 170, 250, 380};
    float[] red = {255,	238	,205,139};
    float[] green = {248,232,200,136};
    float[] blue = {220,205,177,120};
    float[] xSpeed = {3,2,6,(float) 4.5};
    int[] pointValue = {20,10,100,50};
    
    Disk[] d = new Disk[4];
    
    // Setup runs one time at the beginning of your program
    public void setup() {
        size(canvasWidth, canvasHeight);
        smooth();
        
        // Set time now
        timer = millis() + gameDuration;
        
        for (int i = 0; i < d.length; i++) {
        		int randomVal = (int) (Math.random() * 10);
        		if (randomVal <= 4) {
        			xSpeed[i] *=-1;
        		}
            d[i] = new Disk(red[i],green[i],blue[i],xValue[i],yValue[i],xSpeed[i],pointValue[i]);
        }        
    }
    
    // Draw is called in an infinite loop.
    // By default, it is called about 60 times per second.
    public void draw() {
        // Erase the background, if you don't, the previous shape will 
        // still be displayed
        eraseBackground();
        text("Score: "+score , 10,500);
        text("Time: "+(int) (timer-millis())/1000, 10, 480);
        // Move the shape, i.e. calculate the next x and y position
        // where the shape will be drawn. Draw the shape, display point
        // value.
        for (int j = 0; j < d.length; j++) {
            d[j].calcCoords();
            d[j].drawShape();
            // Display the point value on the shape
        }
        
        // Display player's score
        //TODO: Display score
        
        if (millis() >= timer) {
            // Clear the canvas
            background(230,230,250);
            
            // Output the final score
            // TODO: Output final score
            displayPointValue();

            // Let the user click when finished reading score
            // TODO: Output message to click mouse to exit
            text("Click Screen To Exit.", 100,50);
      
            if (this.mousePressed) {        
              // Exit
              System.exit(0);
            }
        }
    }
    
    public void eraseBackground() {
        background(255,255,240);
    }
    
    // mousePressed is a PApplet method that you can override.
    // This method is designed to be called one time when the mouse is pressed
    public void mousePressed() {
      // Draw a circle wherever the mouse is
      int mouseWidth  = 20;
      int mouseHeight = 20;
      fill(0, 0, 0);
      ellipse(this.mouseX, this.mouseY, mouseWidth, mouseHeight);

      // Check whether the click occurred within range of the shape
      //TODO: Did the player click on the shape? 
      //TODO: If so, increase their score.
      for(int i = 0; i < d.length; i++) {
    	  	if(d[i].isPressed(mouseX, mouseY)){
    	  		score += d[i].getPointValue();
    	  	}
      
      }
    }
    
    public void displayPointValue() {
        // Draw the text at computed x, y location
        //TODO:
    		text("Your final score is " + score, 100,250);
    }

    class Disk {
    	
    		public boolean isPressed(final int mouseX, final int mouseY) {
    			boolean x_range = Math.abs(this.x-mouseX) <= this.shapeWidth / 2;
    			boolean y_range = Math.abs(this.y-mouseY) <= this.shapeHeight / 2;
    			return x_range && y_range;
    		}
    		
    		public int getPointValue() {
    			return pointValue;
    		}
  
        // Shape size
        final int shapeWidth  = 80;
        final int shapeHeight = 50;

        // Shape value
        final static int defaultValue = 10;
        int pointValue = defaultValue;

        // Keep track of ball's x and y position
        float x = 300;
        float y = 250;

        // Horizontal speed
        float xSpeed = 2;

        // It's hard to click a precise position, to make it easier, 
        // require the click to be somewhere on the shape
        int targetRange = Math.round((min(shapeWidth, shapeHeight)) / 2);

        float red;
        float green;
        float blue;

        Disk(float red, float green, float blue, float x, float y, 
             float xSpeed, int pointValue) {
            // TODO: Initialize instance variables as needed
        		this.red = red;
        		this.green = green;
        		this.blue = blue;
        		fill(this.red,this.green,this.blue);
        		this.xSpeed = xSpeed;
        		this.x = x;
        		this.y = y;
        		this.pointValue = pointValue;

            // TODO: Compute pointValue
        		if(mousePressed) {
        			pointValue += pointValue;
        		}
            System.out.println("Constructor pointValue = " + this.pointValue);
        }

        Disk() {
            this(0, 0, 255, 300, 250, 2, defaultValue);
        }

        public void calcCoords() {      
            // Compute the x position where the shape will be drawn
            this.x += this.xSpeed; 

            // If the x position is off the canvas, reverse direction of 
            // movement
            if (this.x > canvasWidth) {
                System.out.println("<===  Change direction, go left because x = " + this.x);
                this.xSpeed = -1 * this.xSpeed;
            }

            // If the x position is off the canvas, reverse direction of 
            // movement
            if (this.x < 0) {
                System.out.println("     ===> Change direction, go right because x = " + this.x + "\n");
                this.xSpeed = -1 * this.xSpeed;
            } 
        }
        
        

        public void drawShape() {
            // Select color, then draw the shape at computed x, y location
            //TODO: Add fill color
            ellipse(this.x, this.y, this.shapeWidth, this.shapeHeight);
            fill(this.red, this.green,this.blue);
            textSize(26);
            text(pointValue,this.x - 15,this.y + 10);
        }

    }
}
