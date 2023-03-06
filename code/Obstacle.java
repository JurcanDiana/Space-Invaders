import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
Obstacle class:
Defines a simple obstacle that just draws the image of the obstacles.
*/

public class Obstacle extends Rectangle {

    private BufferedImage obstacle;

    public Obstacle(Position position, int width, int height) throws IOException {
        super(position, width, height);//From Rectangle class.
        obstacle = ImageIO.read(new File("images/obstacles.png"));//Creates image for obstacles.
    }

    public void paint(Graphics g) {
        //Draws image to fill in the obstacle.
        g.drawImage(obstacle, position.x, position.y, obstacle.getWidth()/10, obstacle.getHeight()/10, null);
    }

}