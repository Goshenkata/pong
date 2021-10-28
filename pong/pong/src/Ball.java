import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ball extends JPanel {
    private int x,y;
    private static int xSpeed = 4;
    private static int ySpeed = 2;
    public static final int SIZE = 25;

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        g.fillOval(x, y, SIZE, SIZE);
    }

    public Ball (int x, int y) {
       this.x = x;
       this.y = y;
    }
    public void move(){
        x+=xSpeed;
        y+=ySpeed;
    }

    public void reverseX() {
        xSpeed = -xSpeed;
    }
    public void reverseY() {
        ySpeed = -ySpeed;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
