import processing.core.PApplet;
import processing.core.PConstants;

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
        this.canvas = c; // Assign canvas
        direction = 0;   // Default direction: right
    }

    public void display() {
        canvas.fill(255, 255, 0);
        canvas.noStroke();

        float startAngle = 0;
        float stopAngle = PConstants.TWO_PI; // Default to closed mouth

        // Adjust angles for the mouth based on the direction
        float mouthAngle = PConstants.PI / 6; // Angle for the open mouth

        if (direction == 0) { // Right
            startAngle = mouthAngle;
            stopAngle = PConstants.TWO_PI - mouthAngle;
        } else if (direction == 1) { // Left
            startAngle = PConstants.PI + mouthAngle;
            stopAngle = PConstants.PI + PConstants.TWO_PI - mouthAngle;
        } else if (direction == 2) { // Up
            startAngle = 3 * PConstants.PI / 50 - 2 + mouthAngle;
            stopAngle = 3 * PConstants.PI / 2 - mouthAngle;
        } else if (direction == 3) { // Down
            startAngle = PConstants.PI / 40 - 5  + mouthAngle;
            stopAngle = PConstants.PI / 2 - mouthAngle;
        }

        // Draw Pacman with the arc
        canvas.arc(x, y, size, size, startAngle, stopAngle, PConstants.PIE);
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
