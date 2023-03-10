import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
Player class:
Defines a player that can be moved left/right and fire projectiles with space.
*/

public class Player extends Rectangle implements CollidableObject {

    private BufferedImage player;

    private static final int WIDTH = 45;//Width of the player.
    private static final int HEIGHT = 20;//Height of the player.

    private ObjectManager objectManager;//Reference to the object manager for spawning projectiles.

    private boolean fireShot;//When true this will fire a shot in the next update.
    private boolean keyLeftIsPressed, keyRightIsPressed;
    //Status of the keys for left/right to determine if movement should happen during updates.

    private final int moveRate = 10;//The magnitude of movement translation for left/right movement.
    private Position startPosition;//Start position to allow resetting.

    private int lives;//Remaining lives that the player has.

    //Creates the player and prepares them with a default 3 lives.
    public Player(ObjectManager objectManager) {

        super(new Position(GamePanel.PANEL_WIDTH/2-WIDTH/2,GamePanel.PANEL_HEIGHT-HEIGHT),WIDTH,HEIGHT);//Places player at its position.

        this.objectManager = objectManager;//Reference to create projectiles.
        this.startPosition = new Position(position);

        keyLeftIsPressed = false;
        keyRightIsPressed = false;
        fireShot = false;
        lives = 3;

        try {
            player = ImageIO.read(new File("images/shipOther.png"));//Creates image for the player.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Draws the image of the player.
    public void paint(Graphics g) {
        g.drawImage(player, position.x - 3, position.y - 38, player.getWidth()/10, player.getHeight()/10, null);
    }

    //Fires a shot if necessary. Moves left/right if input was applied for left/right movement.
    //deltaTime - time since last update.
    public void update(int deltaTime) {

        if(fireShot) {
            fireShot = false;
            objectManager.addProjectile(this);
        }

        if(keyLeftIsPressed) {
            moveWithinBounds(new Position(-moveRate,0), GamePanel.PANEL_WIDTH-width, GamePanel.PANEL_HEIGHT);
        }

        if(keyRightIsPressed) {
            moveWithinBounds(new Position(moveRate,0), GamePanel.PANEL_WIDTH-width, GamePanel.PANEL_HEIGHT);
        }
    }

    //Resets all properties back to default with 3 lives.
    public void reset() {
        keyLeftIsPressed = false;
        keyRightIsPressed = false;
        fireShot = false;
        lives = 3;
        position = new Position(startPosition);
    }

    //keyCode - the key that was interacted with.
    //isPressed - when true it indicates the key was pressed, false indicates it was released.
    public void handleInput(int keyCode, boolean isPressed) {

        //Pressing/Releasing Left/Right arrows updates with the correct movement state.
        //Pressing space will fire a shot during the next update.

        if(keyCode == KeyEvent.VK_LEFT) {
                keyLeftIsPressed = isPressed;
        } else
            if(keyCode == KeyEvent.VK_RIGHT) {
                keyRightIsPressed = isPressed;
        } else
            if(keyCode == KeyEvent.VK_SPACE && isPressed) {
                fireShot = true;
        }
    }

    //Gets the current number of lives. This will always be 0 or greater.
    public int getLives() {
        return lives;
    }

    //When hit the player takes 1 life off.
    @Override
    public void hit() {
        lives = Math.max(0, lives - 1);
    }

    //Moves based on the translationVector, but clamps the movement within the bounds of the play space.
    private void moveWithinBounds(Position translationVector, int maxX, int maxY) {

        int newX = position.x + translationVector.x;//Added to position to calculate the new position.
        int newY = position.y + translationVector.y;//Added to position to calculate the new position.

        //Clamps the movement within the bounds of the play space.
        if(newX < 0) {
            newX = 0;
        }
        else if(newX > maxX) {
            newX = maxX;
        }

        if(newY < 0) {
            newY = 0;
        }
        else if(newY > maxY) {
                newY = maxY;
            }

        position.setPosition(newX, newY);
    }
}