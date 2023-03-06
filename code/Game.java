import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/*
Game class: Creates the JFrame and receives the keyboard input.
*/

public class Game implements KeyListener {

    //Entry point for the application to create an instance of the Game class.
    public static void main(String[] args) throws IOException {
        Game game = new Game();
    }

    private GamePanel gamePanel;//Reference to the GamePanel object to pass key events to.

    //Creates the JFrame with a GamePanel inside it, attaches a key listener, and makes everything visible.
    public Game() throws IOException {

        JFrame frame = new JFrame("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Used to exit the application.
        frame.setResizable(false);//Prevents the user from re-sizing the frame.

        gamePanel = new GamePanel();//Creates a new game panel (object).
        frame.getContentPane().add(gamePanel);//Adds created object to the content pane.
        //The content pane is an object created by the Java run time environment.

        frame.addKeyListener(this);//Adds the KeyListener to the frame.
        frame.pack();//Sizes the frame so that all its contents are at or above their preferred sizes.
        frame.setLocationRelativeTo(null);//Centers the frame.
        frame.setVisible(true);//Makes the frame appear on the screen.
    }

    //Called when the key is pressed down. Passes the key press on to the GamePanel.
    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.handleInput(e.getKeyCode(), true);
        //e.getKeyCode(), true - information about what key was pressed
    }

    //Called when the key is released. Passes the key release on to the GamePanel.
    @Override
    public void keyReleased(KeyEvent e) {
        gamePanel.handleInput(e.getKeyCode(), false);
        //e.getKeyCode(), false - information about what key was unpressed
    }

    //Not used.
    @Override
    public void keyTyped(KeyEvent e) {}
}