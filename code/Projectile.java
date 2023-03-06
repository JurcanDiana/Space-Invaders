import java.awt.*;

/*
Projectile class:
Defines a projectile that moves in a direction.
*/

public class Projectile extends Rectangle {

    private static final int MOVE_SPEED = 300;//Speed to move at. Distance in pixels to move over 1000ms.

    private static final int WIDTH = 4;//Width of the projectile.
    private static final int HEIGHT = 10;//Height of the projectile.

    private Rectangle parentObj;//Reference to the object that spawned the projectile.
    private Position moveVector;//The unit vector representing direction of projectile movement.

    private boolean isExpired;//When true, the projectile has hit something or moved off the screen and should be removed.
    private ObjectManager objectManager;//Reference to the object manager to check for collisions.


    public Projectile(Rectangle parentObj, ObjectManager objectManager) {

        super(parentObj.getCentre(), WIDTH, HEIGHT);//Creates the projectile centred inside the parent object.

        this.objectManager = objectManager;//Reference to the object manager to check for collisions.
        this.parentObj = parentObj;//Reference to the object that spawned this projectile.

        //The direction is set to either up if the parent is the player, or down otherwise.
        if(parentObj instanceof Player) {
            moveVector = new Position(Position.UP);
        } else {
            moveVector = new Position(Position.DOWN);
        }
        isExpired = false;//Projectile is spawned.
    }

    /*
    Updates the projectile by moving in the direction defined at creation.
    Checks for collisions with objects or leaving the screen to make it expire ready for removal.
    deltaTime - time since last update.
    */
    public void update(int deltaTime) {

        Position relativeMove = new Position(moveVector);//New position of the projectile.
        relativeMove.multiply(MOVE_SPEED * deltaTime / 1000);//Distance = velocity * time.
        position.add(relativeMove);//Add to the position.

        Rectangle collision = objectManager.getCollision(this);

        if(position.y <= -height || position.y >= GamePanel.PANEL_HEIGHT || collision != null) {
            isExpired = true;//Projectile is destroyed after it passes an object without collision, before it reaches the top.
        }

        if(collision != null && collision instanceof CollidableObject) {
            ((CollidableObject)collision).hit();//If an object, that uses the CollidableObject interface,
            //is collided with, it will call the hit() method.
        }
    }

    //Draws the projectile at its current location as either white for the player or pink for any other parent.
    //g - reference to the Graphics object for rendering.
    public void paint(Graphics g) {
        g.setColor(parentObj instanceof Player ? Color.WHITE : Color.PINK);//Instanceof tests if an object is a player.
        g.fillRect(position.x, position.y, width, height);//Draws projectile.
    }

    //Gets the parent object reference to whatever spawned this projectile.
    public Rectangle getParentObject() {
        return parentObj;//return a reference to the parent object.
    }

    //A projectile expires when it either leaves the screen or when it has collided with any collidable object.
    public boolean isExpired() {
        return isExpired;//return True if the projectile is scheduled to be destroyed.
    }
}