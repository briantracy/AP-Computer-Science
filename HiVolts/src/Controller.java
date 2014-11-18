
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;


/**
 * This class is the main application frame. It deals with UI and UX through display and key detection
 */
public class Controller extends JFrame implements KeyListener
{
    private boolean isPlayerTurn = true;

    private static class LayoutDimensions
    {
        static final int TOP_INSET = 5;    // how far from the top the board is inset.
        static final int BOTTOM_INSET = 10; // how far from the bottom the board is inset.

    }

    private Game game; // the logical aspect of the game.


    /**
     *  The point at which the content should  start if it is to be centered.
     * @return
     */
    private Point getContentStart()
    {
        return new Point(getContentInsetLeftEdge(), getInsets().top + LayoutDimensions.TOP_INSET);
    }


    /**
     * How far we should move the game board over so it is centered
     * @return
     */
    private int getContentInsetLeftEdge()
    {
        int width = getWidth();
        int extraSpace = width - getContentHeight();


        return extraSpace / 2;
    }


    /**
     * Determines the height of the board. This is made so it is the biggest possible square area.
     * @return
     */
    private int getContentHeight()
    {
        int height = getHeight();
        return height - (LayoutDimensions.TOP_INSET + LayoutDimensions.BOTTOM_INSET);
    }


    /**
     * Size of each space on the board. This will be filled with a sprite.
     * @return
     */
    private int getSpaceSize()
    {
        int contentHeight = getContentHeight();
        int spaces = game.getEntities().length;

        int spaceSize = contentHeight / spaces;

        return spaceSize;
    }

    /**
     * Size of each sprite to be drawn. Calculated relative to screen size. Slightly useless as no offset is used.
     * @return
     */
    private int getImageSize()
    {
        int offset = 0;
        return getSpaceSize() - offset;
    }


    /**
     * Constructor - add self as key listener, make the game, set the bgcolor.
     */
    public Controller()
    {
        addKeyListener(this);
        game = new Game();

        setBackground(Color.black);

    }

    /**
     * Called by system or this class when the UI should be updated.
     * @param g
     */
    @Override
    public void paint(Graphics g)
    {

        g.clearRect(0, 0, getWidth(), getHeight());

        drawGrid(g);

    }

    /**
     * <summary>This method enumerates through the logical grid and draws the appropriate sprite in teh appropriate position.</summary>
     * @param g The graphics on which to be drawn
     */
    private void drawGrid(Graphics g)
    {
        Entity[][] entities = game.getEntities();


        for (int y = 0; y < entities.length; y++)
        {
            for (int x = 0; x < entities.length; x++)
            {
                Point position = getContentStart();
                position.x += (x * getSpaceSize());
                position.y += y * getSpaceSize();

                if (entities[x][y] != Entity.Space)
                {
                    String imageName = entities[x][y].toString() + ".png";
                    drawImage(g, position, ImageCollection.getImageByName(imageName));
                }
            }
        }

        displayInfo(g);
    }

    private void drawImage(Graphics g, Point position, BufferedImage image)
    {
        g.drawImage(image, position.x, position.y, getImageSize(), getImageSize(), null);
    }


    /**
     * <summary>This method draws the turn indicator, remaining mhos, and "r to reset". Uses Swing drawString.</summary>
     * @param g The graphics on which to be drawn
     */
    private void displayInfo(Graphics g)
    {
        int fontSize = 40, inset = 5;
        g.setColor(new Color(255,135,0));
        g.setFont(new Font("Menlo", Font.PLAIN, fontSize));


        g.drawString(String.format("%d Mhos", game.getLiveMhoCount()), inset, getHeight() / 2);


        g.setFont(new Font("Menlo", Font.PLAIN, 20));
        g.drawString("r to reset", inset, getHeight() / 2 - 40);

        if (isPlayerTurn) {
            g.drawString("Your Turn", inset, getHeight() / 2 - 70);
        }
    }


    /*
        KeyListener Methods - intercept keys pressed on this frame
     */


    /**
     * Unused Method
     * @param keyEvent
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }


    /**
     * Unused Method
     * @param keyEvent
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }


    /**
     * <summary>This method is part of the KeyListener interface and is called when the user fully presses and releases a key. From this
     * Key press, we can determine a key code</summary>
     * @param keyEvent The key that is pressed
     */
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if (Character.toLowerCase(keyEvent.getKeyChar()) == 'r')
        {
            reset();
        }
        else if (isPlayerTurn) {
            Direction dir = Direction.createFromKeyChar(keyEvent.getKeyChar());

            game.movePlayerInDirction(dir);

            if (game.getGameState() != GameState.GameLost) {
                if (dir != Direction.Jump) {
                    game.moveMhos();
                }
            }
            else {
                isPlayerTurn = false;
            }
            repaint();

            if (game.getGameState() == GameState.GameLost) {

                JOptionPane.showMessageDialog(this, "Game Over", "You have lost", JOptionPane.ERROR_MESSAGE);
                isPlayerTurn = false;
            }
            else if (game.getGameState() == GameState.GameWon) {
                JOptionPane.showMessageDialog(this, "You have won");
                isPlayerTurn = false;
            }

            repaint();




        }


    }


    /**
     * <summary>Reset the game. Reset logic + reset display</summary>
     */
    private void reset()
    {
        game = new Game();
        isPlayerTurn = true;
        repaint();
    }







}
