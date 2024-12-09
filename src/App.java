import processing.core.*;
import java.util.ArrayList;

public class App extends PApplet{

    Pacman pacman;

    Coin coin;
    ArrayList<Coin> coins;
    public static void main(String[] args)  {
        PApplet.main("App");
    }

    int count  = 75;

    public void setup(){
        background(0, 0, 40);
        coins = new ArrayList<>();

        for(int i = 0; i < 75; i++) {
            float x = random(50, width - 50);
            float y = random(50, height - 50);
            float size = 25;
            coins.add(new Coin(x, y, size));

        pacman = new Pacman(300, 300, 50);
        }
    }

    public void settings(){
        size(600, 600);
    }

    public void draw(){

        int screen = 1;
        if (screen == 0) {
            drawStartScreen();
        } else if (screen == 1) {
            drawGameScreen();
        }
    }

    public void drawStartScreen(){
        background(0, 0, 40);
        }
    


    public void drawGameScreen(){
        background(0, 0, 40);
         
        for (int i = coins.size() - 1; i >= 0; i--) { 
            Coin c = coins.get(i);
            drawCoin(c);

            if (isTouching(pacman, c)) {
                coins.remove(i); // Remove the coin if Pac-Man touches it
                count--;
            }

        fill (255, 255, 0);
        ellipse(pacman.getX(), pacman.getY(), pacman.getSize(), pacman.getSize());
        }
    }

    public void keyPressed() {
        float step = 5;

        if(keyCode == LEFT) {
            pacman.moveLeft(step);
        } else if (keyCode == RIGHT) {
            pacman.moveRight(step);
        } else if (keyCode == UP) {
            pacman.moveUp(step);
        } else if (keyCode == DOWN) {
            pacman.moveDown(step);
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

    public boolean isTouching(Pacman pacman, Coin coin) {
        float distance = dist(pacman.getX(), pacman.getY(), coin.getX(), coin.getY());
        return distance < (pacman.getSize() / 2 + coin.getSize() / 2); // Check if distances are less than radii sum
    }

    }

