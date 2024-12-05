import processing.core.*;
import java.util.ArrayList;

public class App extends PApplet{

    Coin coin;
    ArrayList<Coin> coins;
    public static void main(String[] args)  {
        PApplet.main("App");
    }



    public void setup(){
        background(0);
        coins = new ArrayList<>();

        for(int i = 0; i < 75; i++) {
            float x = random(50, width - 50);
            float y = random(50, height - 50);
            float size = 25;
            coins.add(new Coin(x, y, size));
        }
    }

    public void settings(){
        size(600, 600);
    }

    public void draw(){
        for (Coin c: coins) {
            drawCoin(c);
        }
    }

    public void drawCoin(Coin coin){
        fill(255, 255, 0);
        stroke(0);
        ellipse(coin.getX(), coin.getY(), coin.getSize(), coin.getSize());
    }
}
