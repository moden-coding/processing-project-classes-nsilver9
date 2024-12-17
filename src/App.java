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

        pacman = new Pacman(300, 300, 50, this);
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
        } else if (screen == 3) {
            drawGameOverScreen();
        }
    }

    public void drawStartScreen(){
        background(0, 0, 40);

        stroke(0);
        textSize(25);
        text("Intructions:", 50, 100);
        text("You are pacman", 50, 150);
        text("Collect all the coins without touching a ghost", 50, 200);
        text("Press space to start", 50, 250);
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
                screen = 3; // Game over
            }
        }
        if (coins.isEmpty()) {
            screen = 2;
        }
        }
    



    public void drawWinScreen(){
        background(0);
        textSize(25);
        text("You win!", 275, 300);
        text("Press space to play again", 275, 350);
    }

    public void drawGameOverScreen(){
        background(0);
        textSize(25);
        text("Game over!", 275, 300);
        text("Press space to play again", 275, 350);

       
    }

    public void resetGame() {
        // Clear current coins and ghosts
        coins.clear();
        ghosts.clear();
    
        // Recreate coins
        for (int i = 0; i < 75; i++) {
            float x, y;
            
            // Avoid spawning coins too close to Pacman
            do {
                x = random(50, width - 50);
                y = random(50, height - 50);
            } while (dist(x, y, pacman.getX(), pacman.getY()) < 75);
            
            coins.add(new Coin(x, y, 25));
        }
    
        // Recreate ghosts
        for (int g = 0; g < 3; g++) {
            float gx = random(50, width - 50);
            float gy = random(50, height - 50);
            ghosts.add(new Ghost(gx, gy, 30));
        }
    
        // Reset Pacman to the default starting position (center of the screen)
        pacman = new Pacman(width / 2, height / 2, 50, this);
    
        // Reset any other game variables as needed
        count = 75; // Reset coin count
    }
    

    public void keyPressed() {
        float step = 5;

        if(screen == 3 || screen == 2 || screen == 0) {
            if(key == ' ') {
                screen = 1;
                resetGame();
            }
        }

        if(keyCode == LEFT) {
            pacman.moveLeft(step);
        } else if (keyCode == RIGHT) {
            pacman.moveRight(step);
        } else if (keyCode == UP) {
            pacman.moveUp(step);
        } else if (keyCode == DOWN) {
            pacman.moveDown(step);
        }

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
