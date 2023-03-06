import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

/*
GamePanel class:
Shows the current game state, controls the ObjectManager, game state,
and keeps the timer going for regular updates on set intervals.
*/

public class GamePanel extends JPanel implements ActionListener {

    public static final int TIME_INTERVAL = 20;//Time between updates.

    public static final int PANEL_HEIGHT = 600;//Height of the panel.
    public static final int PANEL_WIDTH = 400;//Width of the panel.

    private ObjectManager objectManager;//The object manager that controls all the individual elements of the game.
    private Timer gameTimer;//The timer used to force updates to happen at fixed intervals.
    private Player player;//A reference to the player to pass input to.

    private int score;//The current score.
    private boolean gameOver;//When true the game has ended as a win or loss represented by the gameOverMessage.
    private String gameOverMessage;//Shows the message when the game has ended that will be set as either a win or loss.

    //Configures the game ready to play and starts it right away.
    public GamePanel() throws IOException {

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));//Set size of panel.
        setBackground(Color.BLACK);//Set color of background.

        objectManager = new ObjectManager(this);
        player = objectManager.getPlayer();
        gameTimer = new Timer(TIME_INTERVAL, this);
        gameTimer.start();
        gameOver = false;
    }

    //Draws all the game elements. These are mostly contained in the objectManager.
    //Then all the text elements for lives, score, and if necessary game over are drawn.
    public void paint(Graphics g) {
        super.paint(g);
        objectManager.paint(g);
        drawScore(g);
        drawLives(g);
        if(gameOver) {
            drawGameOver(g);
        }
    }

    //keyCode - the key that was pressed or released.
    //isPressed - true indicates the key is pressed, false indicates released.
    public void handleInput(int keyCode, boolean isPressed) {

        //Handles the input by checking first for Escape to quit, R to restart;
        if(keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else
            if(keyCode == KeyEvent.VK_R && isPressed) {
                objectManager.reset();
                score = 0;
                gameOver = false;
        } else {
                //and otherwise passes the input handling to the player.
                player.handleInput(keyCode, isPressed);
            }
    }

    //amount - amount to increase the score by.
    public void increaseScore(int amount) {
        score += amount;//Increases the score by the amount specified.
    }

    /*
    Called by the gameTimer on regular intervals. If the
    game is not ended it will update the game via the object manager.
    Then checks for game over state changes and updates as necessary.

    @param e Information about the event.
    */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(!gameOver) {
            objectManager.update(TIME_INTERVAL);

            if(player.getLives() == 0) {

                gameOver = true;
                gameOverMessage = "You lost all lives! R to Restart.";
            } else if(objectManager.getAlienCount() == 0) {

                gameOver = true;
                gameOverMessage = "You won! R to Restart.";
            } else if(objectManager.getAlienTooFarDown()) {

                gameOver = true;
                gameOverMessage = "You lost by Invasion! R to Restart.";
            }
        }
        repaint();
    }

    //Draws the score centred at the top of the panel.
    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String scoreStr = "Score: " + score;
        int strWidth = g.getFontMetrics().stringWidth(scoreStr);
        g.drawString(scoreStr, PANEL_WIDTH / 2 - strWidth / 2, 30);
    }

    //Draws the lives left aligned in the top left corner.
    private void drawLives(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String livesStr = "Lives: " + player.getLives();
        g.drawString(livesStr, 15, 30);
    }

    //Draws a background with game over message centred in the middle of the panel.
    private void drawGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,PANEL_HEIGHT/2, PANEL_WIDTH, 40);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        int strWidth = g.getFontMetrics().stringWidth(gameOverMessage);
        g.drawString(gameOverMessage, PANEL_WIDTH/2-strWidth/2, PANEL_HEIGHT/2+10);
    }
}