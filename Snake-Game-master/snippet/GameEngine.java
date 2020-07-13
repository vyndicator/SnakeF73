public class GameEngine {
    
    // MauMau ist ein guter.

    private void drawSnake(Graphics graphics) {
        rightmouth = new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this, graphics, snakexlength[0], snakeylength[0]);

        drawHead(graphics);
        drawTail(graphics);

    }

    private void drawTail(Graphics graphics) {
        for (int i = 1; i < lengthofsnake; i++) {
            snakeimage = new ImageIcon("snakeimage.png");
            snakeimage.paintIcon(this, graphics, snakexlength[i], snakeylength[i]);
        }
    }

    private void drawHead(Graphics graphics) {
        switch (currentDirection) {
            case RIGHT:
                rightmouth = new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this, graphics, snakexlength[0], snakeylength[0]);
                break;
            case LEFT:
                leftmouth = new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this, graphics, snakexlength[0], snakeylength[0]);
                break;
            case DOWN:
                downmouth = new ImageIcon("downmouth.png");
                downmouth.paintIcon(this, graphics, snakexlength[0], snakeylength[0]);
                break;
            case UP:
                upmouth = new ImageIcon("upmouth.png");
                upmouth.paintIcon(this, graphics, snakexlength[0], snakeylength[0]);
                break;
            default:
                rightmouth = new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this, graphics, snakexlength[0], snakeylength[0]);
                break;
        }
    }
    
}