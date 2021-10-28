import javax.swing.*;
import java.awt.*;

public class Player extends JPanel {
    private int x, y;
    private int score = 0;
    public static final int WIDTH = 25;
    public static final int HEIGHT = 150;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int ySpeed) {
        y += ySpeed;
    }

    public void incrementScore() {
        this.score++;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
}
