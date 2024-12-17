import processing.core.PApplet;
public class Pacman {
    private float x;
    private float y;
    private float size;
    public int direction;
    private PApplet canvas;

    public Pacman(float x, float y, float size, PApplet c) {
        this.x = x;
        this.y = y;
        this.size = size;
        direction = 0;
    }

    public void display(){
        canvas.fill(255, 255, 0); 
        canvas.noStroke();
            // I used ChatGPT here to show me how to create an arc instead of a full circle for pacman's mouth
    
            float startAngle = 0, stopAngle = canvas.TWO_PI; // Default to a closed circle
            if (direction == 0) { // Right
                startAngle = canvas.PI / 6;         
                stopAngle = canvas.TWO_PI - canvas.PI / 6;
            } else if (direction == 1) { // Left
                startAngle = canvas.PI + canvas.PI / 6;    
                stopAngle = canvas.PI + canvas.TWO_PI - canvas.PI / 6;
            } else if (direction == 2) { // Up
                startAngle = 3 * canvas.PI / 2 + canvas.PI / 6;  
                stopAngle = canvas.PI + 7 / 1;  
            } else if (direction == 3) { // Down
                startAngle = canvas.TWO_PI / 3;                
                stopAngle = canvas.TWO_PI - canvas.PI + 25 / 6;
            }
            canvas.arc(x, y, size, size, startAngle, stopAngle);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }

    public void moveLeft(float step) {
        x -= step;
        direction = 1;
    }

    public void moveRight(float step) {
        x += step;
        direction = 0;
    }

    public void moveUp(float step) {
        y -= step;
        direction = 2;
    }

    public void moveDown(float step) {
        y += step;
        direction = 3;
    }
}
