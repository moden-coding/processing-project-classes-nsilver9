import processing.core.*;
import java.util.ArrayList;

public class App extends PApplet{

    Pacman pacman;

    Coin coin;
    ArrayList<Coin> coins;
    ArrayList<Ghost> ghosts;
    public static void main(String[] args)  {
        PApplet.main("App");
    }

    int count  = 75;
    int screen = 0;

    int direction;

    public void setup(){
        background(0, 0, 40);
        coins = new ArrayList<>();

        ghosts = new ArrayList<>();

        for(int i = 0; i < 75; i++) {
            float x = random(50, width - 50);
            float y = random(50, height - 50);
            float size = 25;
            coins.add(new Coin(x, y, size));
    }
         
        for(int g = 0; g < 3; g++) {
            float gx = random(50, width - 50);
            float gy = random(50, height - 50);
            ghosts.add(new Ghost(gx, gy, 30));

        pacman = new Pacman(300, 300, 50);
        }
    }

    public void settings(){
        size(600, 600);
    }


    public void draw(){
       
        if (screen == 0) {
            drawStartScreen();
        } else if (screen == 1) {
            drawGameScreen();
        } else if (screen == 2) {
            drawWinScreen();
        }
    }

    public void drawStartScreen(){
        background(0, 0, 40);

        stroke(0);
        textSize(25);
        text("Intructions:", 50, 100);
        text("You are pacman", 50, 150);
        text("Collect all the coins without touching a ghost", 50, 200);
        text("Press any key to start", 50, 250);

        if(keyPressed) {
            screen = 1;
        }
        }

    public void drawGameScreen(){
        background(0, 0, 40);
         
        for (int i = coins.size() - 1; i >= 0; i--) {
            Coin c = coins.get(i);
            drawCoin(c);
        
            if (isTouching(pacman, c)) {
                coins.remove(i);
                count--;
            }
        }
        
        for (int g = ghosts.size() - 1; g >= 0; g--) {
            Ghost ghost = ghosts.get(g);
            drawGhost(ghost);
        
            if (isTouchingGhost(pacman, ghost)) {
                screen = 2; // Game over
                return; // Exit early to stop further processing
            }

        }



            fill(255, 255, 0); 
            noStroke();
            // I used ChatGPT here to show me how to create an arc instead of a full circle for pacman's mouth
    
            float startAngle = 0, stopAngle = TWO_PI; // Default to a closed circle
            if (direction == 0) { // Right
                startAngle = PI / 6;         
                stopAngle = TWO_PI - PI / 6;
            } else if (direction == 1) { // Left
                startAngle = PI + PI / 6;    
                stopAngle = PI + TWO_PI - PI / 6;
            } else if (direction == 2) { // Up
                startAngle = 3 * PI / 2 + PI / 6;  
                stopAngle = PI + 7 / 1;  
            } else if (direction == 3) { // Down
                startAngle = TWO_PI / 3;                
                stopAngle = TWO_PI - PI + 25 / 6;
            }
            arc(pacman.getX(), pacman.getY(), pacman.getSize(), pacman.getSize(), startAngle, stopAngle);
        if (coins.isEmpty()) {
            screen = 2;
        }
        }
    



    public void drawWinScreen(){
        textSize(25);
        text("You win!", 275, 300);
        text("Press any key to play again", 275, 350);

        if(keyPressed){
            resetGame();
            screen = 1;
        }
    }

    public void resetGame(){
        coins.clear();
        for (int i = 0; i < 75; i++) {
        float x;
        float y;
        
        do {
            x = random(50, width - 50);
            y = random(50, width - 50);
        } while (dist(x, y, pacman.getX(), pacman.getY()) < 75);
        coins.add(new Coin(x, y, 25));
    }
        for (int g = 0; g < 3; g++) {
            float gx = random(50, width - 50);
            float gy = random(50, height - 50);
            ghosts.add(new Ghost(gx, gy, 30));
        }
    }

    public void keyPressed() {
        float step = 5;

        if(keyCode == LEFT) {
            pacman.moveLeft(step);
            direction = 1;
        } else if (keyCode == RIGHT) {
            pacman.moveRight(step);
            direction = 0;
        } else if (keyCode == UP) {
            pacman.moveUp(step);
            direction = 2;
        } else if (keyCode == DOWN) {
            pacman.moveDown(step);
            direction = 3;
        }

        //I used ChatGPT here to make contstrains so that pacman can't leave the screen
        pacman = new Pacman(
            constrain(pacman.getX(), pacman.getSize() / 2, width - pacman.getSize() / 2),
            constrain(pacman.getY(), pacman.getSize() / 2, height - pacman.getSize() / 2),
            pacman.getSize()
        );
    }

    public void drawCoin(Coin coin){
        fill(255, 205, 0);
        stroke(0);
        ellipse(coin.getX(), coin.getY(), coin.getSize(), coin.getSize());
    }

    public void drawGhost(Ghost ghost) {
        fill(255, 0, 0);
        stroke(0);
        triangle(
            ghost.getX(), ghost.getY() - ghost.getSize() / 2,
            ghost.getX() - ghost.getSize() / 2, ghost.getY() + ghost.getSize() / 2,
            ghost.getX() + ghost.getSize() / 2, ghost.getY() + ghost.getSize() / 2
        );
    }

    public boolean isTouching(Pacman pacman, Coin coin) {
        float distance = dist(pacman.getX(), pacman.getY(), coin.getX(), coin.getY());
        return distance < (pacman.getSize() / 2 + coin.getSize() / 2); // Check if distances are less than radii sum
    }

    public boolean isTouchingGhost(Pacman pacman, Ghost ghost) {
        float distance = dist(pacman.getX(), pacman.getY(), ghost.getX(), ghost.getY());
        return distance < (pacman.getSize() / 2 + ghost.getSize() / 2); // Check if distances are less than radii sum
    }

}
