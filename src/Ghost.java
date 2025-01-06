public class Ghost {
    private float x;
    private float y;
    private float size;
    private float dx, dy;
    private static final float SPEED = 2;

    public Ghost(float x, float y, float size) {
        this.x = x;
        this.y = y;
        this.size = size;

        // ChatGPT taught me how to use Math.random() method here to generate random values between -1 and 1
        dx = (float) (Math.random() * 2 - 1); // Random between -1 and 1
        dy = (float) (Math.random() * 2 - 1); // Random between -1 and 1
    }

    public void move() {
        x += dx * SPEED;
        y += dy * SPEED;

        if (x < size / 2 || x > 600 - size / 2) dx *= -1;
        if (y < size / 2 || y > 600 - size / 2) dy *= -1;
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
}
