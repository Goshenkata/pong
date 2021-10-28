import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    Canvas canvas;
    Player playerOne;
    Player playerTwo;
    Ball ball;
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    public Main() {
        Container cp = getContentPane();
        playerOne = new Player(0, HEIGHT / 2);
        playerTwo = new Player(WIDTH - Player.WIDTH - 20, HEIGHT / 2);
        ball = new Ball((WIDTH / 2) - Ball.SIZE, (HEIGHT / 2) - Ball.SIZE);
        canvas = new Canvas();
        canvas.setLayout(new FlowLayout());
        canvas.setBackground(Color.BLACK);
        canvas.add(playerOne);
        canvas.add(playerTwo);
        cp.add(canvas);
        repaint();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("X: " + e.getX() + "--Y: " + e.getY());
            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                int speed = 2;
                switch (key) {
                    case KeyEvent.VK_W -> {
                        for (int i = 0; i < 20; i++) {
                            if (playerOne.getY() >= 0) {
                                playerOne.move(-speed);
                            }
                        }
                    }
                    case KeyEvent.VK_S -> {
                        for (int i = 0; i < 20; i++) {
                            if (playerOne.getY() <= 610) {
                                playerOne.move(speed);
                            }
                        }
                    }
                    case KeyEvent.VK_UP -> {
                        for (int i = 0; i < 20; i++) {
                            if (playerTwo.getY() >= 0) {
                                playerTwo.move(-speed);
                            }
                        }
                    }
                    case KeyEvent.VK_DOWN -> {
                        for (int i = 0; i < 20; i++) {
                            if (playerTwo.getY() <= 610) {
                                playerTwo.move(speed);
                            }
                        }
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }
        });
        Timer timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkCollisions();
                ball.move();
                repaint();
            }
        });
        timer.setRepeats(true);
        timer.start();
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pong");
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }

    public void checkCollisions() {
        final int Y_UPPER = 30;
        final int Y_LOWER = 790;
        final int X_LEFT = 10;
        final int X_RIGHT = 1190;
        boolean isUpperOrLowerBoundHit = (ball.getY() <= 0) || (ball.getY() >= 735);
        boolean isPlayerOneTouched = (ball.getY() >= playerOne.getY()) && (ball.getY() <= playerOne.getY() + Player.HEIGHT) && (ball.getX() <= Player.WIDTH);
        boolean isPlayerTwoTouched = (ball.getY() >= playerTwo.getY()) && (ball.getY() <= playerTwo.getY() + Player.HEIGHT) && (ball.getX() >= X_RIGHT - Player.WIDTH * 2);
        boolean isLeftWallHit = !isPlayerOneTouched && ball.getX() <= X_LEFT;
        boolean isRightWallHit = !isPlayerTwoTouched && ball.getX() >= X_RIGHT;
        if (isUpperOrLowerBoundHit) {
            ball.reverseY();
            System.out.println("isUpperOrLowerBoundHit");
        } else if (isPlayerOneTouched) {
            ball.reverseX();
            System.out.println("isPlayerOneTouched");
        } else if (isPlayerTwoTouched) {
            System.out.println("isPlayerTwoTouched");
            ball.reverseX();
        } else if (isLeftWallHit) {
            System.out.println("isLeftWallHit");
            ball.reverseX();
            playerTwo.incrementScore();
            //ball.setX((WIDTH / 2) - Ball.SIZE);
            //ball.setY((HEIGHT / 2) - Ball.SIZE);
        } else if (isRightWallHit) {
            System.out.println("isRightWallHit");
            ball.reverseX();
            playerOne.incrementScore();
            //ball.setX((WIDTH / 2) - Ball.SIZE);
            //ball.setY((HEIGHT / 2) - Ball.SIZE);
        }
    }

    public class Canvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            ball.paintComponents(g);
            playerOne.paintComponents(g);
            playerTwo.paintComponents(g);
            g.setFont(new Font(Font.SERIF, Font.BOLD, 64));
            g.drawString("" + playerOne.getScore(), 200, 50);
            g.drawString("" + playerTwo.getScore(), 800, 50);
        }
    }

}
