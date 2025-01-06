import processing.core.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class App extends PApplet {
    Pacman pacman;
    ArrayList<Coin> coins;
    ArrayList<Ghost> ghosts;

    int count = 75;
    int screen = 0;
    int score = 0;


    public static void main(String[] args) { 
        PApplet.main("App");
    }

    public void settings() {
        size(600, 600);
    }

    public void setup() {
        background(0, 0, 40);
        coins = new ArrayList<>();
        ghosts = new ArrayList<>();

        // Initialize coins
        for (int i = 0; i < 75; i++) {
            float x = random(50, width - 50);
            float y = random(50, height - 50);
            coins.add(new Coin(x, y, 25));
        }

        // Initialize ghosts
        for (int g = 0; g < 3; g++) {
            float gx, gy;
            do {
                gx = random(50, width - 50);
                gy = random(50, height - 50);
            } while (dist(gx, gy, width / 2, height / 2) < 100);
            ghosts.add(new Ghost(gx, gy, 30));
        }

        // Initialize Pacman
        pacman = new Pacman(width / 2, height / 2, 50, this);
    }

    public void draw() {
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

    public void drawStartScreen() {
        background(0, 0, 40);
        textAlign(CENTER, CENTER);
        textSize(25);
        fill(255);
        text("Instructions:", width / 2, 100);
        text("You are Pacman", width / 2, 150);
        text("Collect all the coins without touching a ghost", width / 2, 200);
        text("Press SPACE to start", width / 2, 250);
    }

    public void drawGameScreen() {
        background(0, 0, 40);

        textSize(20);
        fill(255);
        text("Score: " + score, 50, 20); 
    
        // Draw and update coins
        for (int i = coins.size() - 1; i >= 0; i--) {
            Coin c = coins.get(i);
            drawCoin(c);
    
            if (isTouching(pacman, c)) {
                coins.remove(i);
                count--;
                score++;
            }
        }
    
        // Draw and update ghosts
        for (int g = 0; g < ghosts.size(); g++) {
            Ghost ghost = ghosts.get(g);
    
            // Move the ghost
            ghost.move();  // Call the move() method to update ghost position
    
            // Draw the ghost
            drawGhost(ghost);
    
            // Check if Pacman touches the ghost
            if (isTouchingGhost(pacman, ghost)) {
                screen = 3; // Game over
            }
        }
    
        // Draw Pacman
        pacman.display();
    
        // Check for win condition
        if (coins.isEmpty()) {
            screen = 2; // Win
        }
    }
    

    public void drawWinScreen() {
        background(0);
        textAlign(CENTER, CENTER);
        textSize(25);
        fill(255);
        System.out.println("Current Score: " + score);
        System.out.println("Win Screen - Current Score: " + score);
        text("You Win!", width / 2, 200);
        text("Your Score: " + score, width / 2, 250);
        text("Press SPACE to play again", width / 2, 300);
    }
    
    public void drawGameOverScreen() {
        background(0);
        textAlign(CENTER, CENTER);
        textSize(25);
        fill(255);
        System.out.println("Current Score: " + score);
        System.out.println("Win Screen - Current Score: " + score);
        text("Game Over!", width / 2, 200);
        text("Your Score: " + score, width / 2, 250);
        text("Press SPACE to play again", width / 2, 300);
    }
    

    public void resetGame() {
        coins.clear();
        ghosts.clear();
        score = 0;

        // Recreate coins
        for (int i = 0; i < 75; i++) {
            float x, y;
            do {
                x = random(50, width - 50);
                y = random(50, height - 50);
            } while (dist(x, y, pacman.getX(), pacman.getY()) < 75);
            coins.add(new Coin(x, y, 25));
        }

        // Recreate ghosts
        for (int g = 0; g < 3; g++) {
            float gx, gy;
            do {
                gx = random(50, width - 50);
                gy = random(50, height - 50);
            } while (dist(gx, gy, pacman.getX(), pacman.getY()) < 100);
            ghosts.add(new Ghost(gx, gy, 30));
        }

        // Reset Pacman position
        pacman = new Pacman(width / 2, height / 2, 50, this);
        count = 75; // Reset coin count
    }

    public void keyPressed() {
        float step = 5;

        if (screen == 0 || screen == 2 || screen == 3) {
            if (key == ' ') {
                screen = 1;
                resetGame();
            }
        }

        if (keyCode == LEFT) {
            pacman.moveLeft(step);
        } else if (keyCode == RIGHT) {
            pacman.moveRight(step);
        } else if (keyCode == UP) {
            pacman.moveUp(step);
        } else if (keyCode == DOWN) {
            pacman.moveDown(step);
        }
    }

    public void drawCoin(Coin coin) {
        fill(255, 205, 0);
        stroke(0);
        ellipse(coin.getX(), coin.getY(), coin.getSize(), coin.getSize());
    }

    public void drawGhost(Ghost ghost) {
        fill(255, 0, 0);
        stroke(0);
        ellipse(ghost.getX(), ghost.getY(), ghost.getSize(), ghost.getSize());
    }

    public boolean isTouching(Pacman pacman, Coin coin) {
        float distance = dist(pacman.getX(), pacman.getY(), coin.getX(), coin.getY());
        return distance < (pacman.getSize() / 2 + coin.getSize() / 2);
    }

    public boolean isTouchingGhost(Pacman pacman, Ghost ghost) {
        float distance = dist(pacman.getX(), pacman.getY(), ghost.getX(), ghost.getY());
        return distance < (pacman.getSize() / 2 + ghost.getSize() / 2);
    }
    
}