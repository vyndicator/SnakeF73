package snippet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import snippet.model.Snake;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private static final int LOWER_BOUND = 625;
    private static final int UPPER_BOUND = 75;
    private static final int RIGHT_BOUND = 850;
    private static final int LEFT_BOUND = 25;
    private static final int SNAKE_WIDTH = 25;

    private Direction currentDirection = Direction.RIGHT;

    private Snake snake = new Snake();

    private Timer timer;
    private int delay = 100;
    private ImageIcon snakeimage;

    private int[] enemyxpos = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
            475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850 };
    private int[] enemyypos = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500,
            525, 550, 575, 600, 625 };
    private ImageIcon enemyimage;
    private Random random = new Random();
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);

    private int score = 0;
    private int moves = 0;

    private ImageIcon titleImage;

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics graphics) {
        initSnakeLengths();

        drawGameField(graphics);
        drawSnake(graphics);
        drawEnemy(graphics);

        checkLoseCondition(graphics);

        graphics.dispose();
    }

    private void checkLoseCondition(Graphics graphics) {
        if (snake.isDead()) {
            currentDirection = Direction.NONE;

            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("arial", Font.BOLD, 50));
            graphics.drawString("GAME OVER", 300, 300);

            graphics.setFont(new Font("arial", Font.BOLD, 20));
            graphics.drawString("Press Space to Restart", 350, 340);
        }
    }

    private void drawEnemy(Graphics graphics) {
        enemyimage = new ImageIcon("enemy.png");

        if ((enemyxpos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0])) {
            snake.grow();
            score++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }

        enemyimage.paintIcon(this, graphics, enemyxpos[xpos], enemyypos[ypos]);
    }

    private void drawGameField(Graphics graphics) {
        drawTitleBorder(graphics);
        drawTitleImage(graphics);

        drawGameplayBorder(graphics);
        drawGameplayBackground(graphics);

        drawScore(graphics);
        drawSnakeLength(graphics);
    }

    private void drawSnakeLength(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("arial", Font.PLAIN, 14));
        graphics.drawString("Length: " + lengthofsnake, 780, 50);
    }

    private void drawScore(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("arial", Font.PLAIN, 14));
        graphics.drawString("Scores: " + score, 780, 30);
    }

    private void drawGameplayBackground(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(25, 75, 850, 575);
    }

    private void drawGameplayBorder(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.drawRect(24, 74, 851, 577);
    }

    private void drawTitleImage(Graphics graphics) {
        titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, graphics, 25, 11);
    }

    private void drawTitleBorder(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect(24, 10, 851, 51);
    }

    private void initSnakeLengths() {
        if (moves == 0) {
            snakexlength[2] = 50;
            snakexlength[1] = 75;
            snakexlength[0] = 100;

            snakeylength[2] = 100;
            snakeylength[1] = 100;
            snakeylength[0] = 100;

        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            moves = 0;
            score = 0;
            lengthofsnake = 3;
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves++;
            changeDirection(Direction.RIGHT);

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moves++;
            changeDirection(Direction.LEFT);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moves++;
            changeDirection(Direction.UP);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moves++;
            changeDirection(Direction.DOWN);
        }
    }

    private void changeDirection(Direction newDirection) {
        if (currentDirection.isInverse(newDirection)) {
            return;
        }
        currentDirection = newDirection;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        switch (currentDirection) {
            case RIGHT:
                for (int r = lengthofsnake - 1; r >= 0; r--) {
                    snakeylength[r + 1] = snakeylength[r];
                }
                for (int r = lengthofsnake; r >= 0; r--) {
                    if (r == 0) {
                        snakexlength[r] = snakexlength[r] + SNAKE_WIDTH;
                    } else {
                        snakexlength[r] = snakexlength[r - 1];
                    }
                    if (snakexlength[r] > RIGHT_BOUND) {
                        snakexlength[r] = LEFT_BOUND;
                    }
                }
                repaint();
                break;
            case LEFT:
                for (int r = lengthofsnake - 1; r >= 0; r--) {
                    snakeylength[r + 1] = snakeylength[r];
                }
                for (int r = lengthofsnake; r >= 0; r--) {
                    if (r == 0) {
                        snakexlength[r] = snakexlength[r] - SNAKE_WIDTH;
                    } else {
                        snakexlength[r] = snakexlength[r - 1];
                    }
                    if (snakexlength[r] < LEFT_BOUND) {
                        snakexlength[r] = RIGHT_BOUND;
                    }
                }
                repaint();
                break;
            case UP:
                for (int r = lengthofsnake - 1; r >= 0; r--) {
                    snakexlength[r + 1] = snakexlength[r];
                }
                for (int r = lengthofsnake; r >= 0; r--) {
                    if (r == 0) {
                        snakeylength[r] = snakeylength[r] - SNAKE_WIDTH;
                    } else {
                        snakeylength[r] = snakeylength[r - 1];
                    }
                    if (snakeylength[r] < UPPER_BOUND) {
                        snakeylength[r] = LOWER_BOUND;
                    }
                }
                repaint();
                break;
            case DOWN:
                for (int r = lengthofsnake - 1; r >= 0; r--) {
                    snakexlength[r + 1] = snakexlength[r];
                }
                for (int r = lengthofsnake; r >= 0; r--) {
                    if (r == 0) {
                        snakeylength[r] = snakeylength[r] + SNAKE_WIDTH;
                    } else {
                        snakeylength[r] = snakeylength[r - 1];
                    }
                    if (snakeylength[r] > LOWER_BOUND) {
                        snakeylength[r] = UPPER_BOUND;
                    }
                }
                repaint();
                break;

            default:
                break;
        }
    }
}
