import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
Alien class:
Defines an alien that will be controlled by the Alien Manager.
Aliens die after they are hit by a projectile, and can fire
projectiles of their own on a random interval of 30s or less.
*/

public class Alien extends Rectangle implements CollidableObject {

    public static final int WIDTH = 20;//Width of the alien.
    public static final int HEIGHT = 20;//Height of the alien.

    private boolean isExpired;//Used to determine if the Alien is ready to be destroyed.
    private ObjectManager objectManager;//Reference to the objectManager for creating projectiles and applying score on death.

    private int type;//The type used to determine both colour and amount of score on destruction.
    private ActionTimer fireTimer;//Timer to track how long between firing projectiles.

    private BufferedImage alien;

    //Creates an alien with the specified properties ready to start playing.
    public Alien(Position position, ObjectManager objectManager, int type) {

        super(position, WIDTH, HEIGHT);//Initial position to spawn the alien at.
        isExpired = false;

        this.objectManager = objectManager;//Reference to the ObjectManager for spawning projectiles and applying score changes.
        this.type = type;//The type to make the alien. Changes colour and score associated.

        fireTimer = new ActionTimer((int)(Math.random() * 20000));//Timer to track how long between firing projectiles.

        try {
            alien = ImageIO.read(new File("images/ufoPink.png"));//Creates image for the aliens.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Updates the timer for firing shots.
    //deltaTime - time since last update.
    public void update(int deltaTime) {

        fireTimer.update(deltaTime);
        if(fireTimer.isTriggered()) {

            objectManager.addProjectile(this);//When it triggers, a projectile is fired down
            fireTimer.setTimer((int)(Math.random()*20000));//Timer is reset with a new random interval.
        }
    }

    //Draws the alien.
    public void paint(Graphics g) {
        g.drawImage(alien, position.x, position.y, alien.getWidth()/17, alien.getHeight()/17, null);
    }

    //Checks if the alien has been destroyed.
    public boolean isExpired() {
        return isExpired;//True when the alien should be destroyed.
    }

    //When hit with a projectile the alien should expire and increase the score relative to the type of the alien.
    @Override
    public void hit() {
        isExpired = true;
        objectManager.increaseScore((type + 1) * 3);
    }
}