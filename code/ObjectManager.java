import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
ObjectManager class:
Manages all the objects in the scene including:
the player, the alien manager, the projectiles, and
any obstacles.
*/

public class ObjectManager {

    private GamePanel gamePanel;//Reference to the gamePanel for passing score updates.
    private Player player;//The player object.
    private AlienManager alienManager;//The collection of aliens that are managed as a collective.
    private List<Projectile> projectiles;//The projectiles that are currently active moving up or down.
    private List<Obstacle> obstacles;//Obstacles that block projectiles.

    public ObjectManager(GamePanel gamePanel) throws IOException {
        this.gamePanel = gamePanel;
        projectiles = new ArrayList<>();
        obstacles = new ArrayList<>();
        player = new Player(this);
        alienManager = new AlienManager(this);
        spawnObstacles();
    }

    //Resets all the object states back to default.
    public void reset() {
        alienManager.reset();
        player.reset();
        projectiles.clear();
    }

    //Updates the player, aliens, and projectiles.
    //deltaTime - time since last update.
    public void update(int deltaTime) {
        player.update(deltaTime);
        alienManager.update(deltaTime);
        updateProjectiles(deltaTime);
    }

    //Draws the player, aliens, obstacles, and projectiles.
    public void paint(Graphics g) {
        obstacles.forEach(o -> o.paint(g));
        player.paint(g);
        alienManager.paint(g);
        projectiles.forEach(p -> p.paint(g));
    }

    //Counts the number of aliens currently remaining in the alien manager.
    public int getAlienCount() {
        return alienManager.getAlienCount();//Return the number of aliens that are currently active.
    }

    //Checks if the lowest alien is too low down near the player.
    public boolean getAlienTooFarDown() {
        return alienManager.getLowestAlienY() >= player.position.y - Alien.HEIGHT;
        //return true when the game should end due to an alien invasion.
    }

    //Passes the increase score call up to the game panel.
    //amount - amount to increase the score by.
    public void increaseScore(int amount) {
        gamePanel.increaseScore(amount);
    }

    //Gets the player object.
    public Player getPlayer() {
        return player;//return a reference to the player object.
    }


    //spawnFromObject - the object that is being spawned from.
    public void addProjectile(Rectangle spawnFromObject) {
        //Spawns one projectile using the object that it is being spawned from
        //as the way to determine direction and start position.
        projectiles.add(new Projectile(spawnFromObject, this));
    }

    /*
    Tests the aliens, then the player, and then the obstacles to check if there are collisions with the projectile.

    Projectiles do not check collisions on their own type.
    Eg, player projectiles do not hit players, and alien projectiles
    do not hit the aliens.
    */
    public Rectangle getCollision(Projectile projectile) {

        //projectile - the projectile to test for collisions.
        Rectangle parentObj = projectile.getParentObject();

        if(parentObj instanceof Player) {
            Rectangle alienCollision = alienManager.getCollision(projectile);
            if(alienCollision != null) {
                return alienCollision;
            }
        } else if(player.isIntersecting(projectile)) {
            return player;
        }

        for (Obstacle obstacle : obstacles) {
            if(obstacle.isIntersecting(projectile)) {
                return obstacle;
            }
        }
        return null;//return a reference to the first object collided with, or null.
    }

    //deltaTime - time since last update.
    private void updateProjectiles(int deltaTime) {

        for(int i = 0; i < projectiles.size(); i++) {

            //Updates the projectiles and if they have hit something
            //or left the screen to expire they will be removed.

            projectiles.get(i).update(deltaTime);

            if(projectiles.get(i).isExpired()) {
                projectiles.remove(i);
                i--;
            }
        }
    }

    //Spawns obstacles.
    private void spawnObstacles() throws IOException {
        obstacles.add(new Obstacle(new Position(50,player.position.y - 100),50, 20));
        obstacles.add(new Obstacle(new Position(GamePanel.PANEL_WIDTH/2-25,player.position.y - 100),50, 20));
        obstacles.add(new Obstacle(new Position(GamePanel.PANEL_WIDTH-100,player.position.y - 100),50, 20));
    }
}
