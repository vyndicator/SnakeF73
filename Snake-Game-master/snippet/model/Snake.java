package snippet.model;



import javax.swing.ImageIcon;

/**
 * 
 */
public class Snake {

    private int[] xCoordinates = new int[750];
    private int[] yCoordinates = new int[750];
    
    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;

    private int lengthOfSnake = 3;

    public void grow() {
        this.lengthOfSnake++;
    }

    public boolean isDead() {
        for (int i = 1; i < lengthOfSnake; i++) {
            if (isHeadOnBodyPart(i)) {
                return true;
            }

        }
        return false;
    }

    private boolean isHeadOnBodyPart(int bodyPartIndex) {
        return xCoordinates[bodyPartIndex] == xCoordinates[0] && yCoordinates[bodyPartIndex] == yCoordinates[0];
    }

}