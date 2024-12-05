public class Pacman {
    private float x;
    private float y;
    private float size;

    public Pacman(float x, float y, float size) {
        this.x = x;
        this.y = y;
        this.size = size;
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
    }

    public void moveRight(float step) {
        x += step;
    }

    public void moveUp(float step) {
        y -= step;
    }

    public void moveDown(float step) {
        y += step;
    }


}
