import java.awt.Point;
import java.util.ArrayList;

public class Game
{

    /**
     * Some basic rules. Very customizable and scalable.
     */
    public static class GameRules
    {
        static final int DIMENSION = 12;
        static final int INITIAL_MHOS = 12;
        static final int INNER_FENCES = 20;
    }


    private Entity[][] grid; // how we store the pieces

    private Point playerLocation;

    private int   liveMhoCount = GameRules.INITIAL_MHOS;
    private Point[] mhoPositions;

    private GameState gameState;



    private RandomPointGenerator randomPointGenerator;


    public GameState getGameState()
    {
        return gameState;
    }
    public Entity[][] getEntities() { return grid; }
    public int getLiveMhoCount() { return liveMhoCount; }


    public Game()
    {
        allocateVariables();
        initializeGrid();
        setUpOutsideFences();
        setUpInnerFences();
        setUpPlayer();
        setUpMhos();

    }


    /**
     * Allocate all instances we will be using
     */
    private void allocateVariables()
    {
        grid = new Entity[GameRules.DIMENSION][GameRules.DIMENSION];

        randomPointGenerator = new RandomPointGenerator(1, GameRules.DIMENSION - 2, 1, GameRules.DIMENSION - 2);

        mhoPositions = new Point[GameRules.INITIAL_MHOS];

        gameState = GameState.InProgress;


    }


    /**
        Set all entities to Space as a "clean slate"

     */
    private void initializeGrid()
    {
        for (int y = 0; y < GameRules.DIMENSION; y++)
        {
            for (int x = 0; x < GameRules.DIMENSION; x++)
            {
                grid[x][y] = Entity.Space;
            }
        }
    }


    /**
     * Sets up fences bounding the grid
     */
    private void setUpOutsideFences()
    {
        for (int i = 0; i < GameRules.DIMENSION; i++)
        {
            grid[0][i] = Entity.Fence;
            grid[i][0] = Entity.Fence;

            grid[GameRules.DIMENSION - 1][i] = Entity.Fence;
            grid[i][GameRules.DIMENSION - 1] = Entity.Fence;
        }
    }


    /**
     * Places player, assigns playerLocation
     */
    private void setUpPlayer()
    {
        playerLocation = randomPointGenerator.nextPoint();
        grid[playerLocation.x][playerLocation.y] = Entity.Player;
    }


    /**
     * Places mhos, adds them to list
     */
    private void setUpMhos()
    {
        for (int mho = 0; mho < GameRules.INITIAL_MHOS; mho++)
        {
            Point mhoPos = randomPointGenerator.nextPoint();
            grid[mhoPos.x][mhoPos.y] = Entity.Mho;
            mhoPositions[mho] = mhoPos;
        }
    }


    /**
     * Sets up inner fences.
     */
    private void setUpInnerFences()
    {
        for (int fences = 0; fences < GameRules.INNER_FENCES; fences++)
        {

            Point randomPoint = randomPointGenerator.nextPoint();
            grid[randomPoint.x][randomPoint.y] = Entity.Fence;

        }
    }


    /**
     * Moves the player in teh specified direction.
     * @param dir
     */
    public void movePlayerInDirction(Direction dir) {

        if (dir == Direction.Jump) {
            Point p = randomPointGenerator.randomPoint();
            while (grid[p.x][p.y] == Entity.Fence) {
                p = randomPointGenerator.randomPoint();
            }



            if (grid[p.x][p.y] == Entity.Mho) {
                gameState = GameState.GameLost;
                grid[playerLocation.x][playerLocation.y] = Entity.Space;
            }
            else {
                grid[playerLocation.x][playerLocation.y] = Entity.Space;
                grid[p.x][p.y] = Entity.Player;
                playerLocation = p;
            }
            return;
        }

        int deltaX = dir.transformation.x;
        int deltaY = dir.transformation.y;


        Point to = new Point();
        to.x = playerLocation.x + deltaX;
        to.y = playerLocation.y + deltaY;


        if (dir == Direction.Sit) { return; }


        if (grid[to.x][to.y] == Entity.Space)
        {
            grid[to.x][to.y] = Entity.Player;
            grid[playerLocation.x][playerLocation.y] = Entity.Space;

            playerLocation.x = to.x;
            playerLocation.y = to.y;
        }
        else {
            grid[playerLocation.x][playerLocation.y] = Entity.Space;
            gameState = GameState.GameLost;
        }



    }


    /**
     * Loops through teh Mhos and moves them accordingly
     */
    public void moveMhos()
    {
        for (int i = 0; i < mhoPositions.length; i++)
        {
            if (mhoPositions[i] != null) {
                mhoLogic(mhoPositions[i].x, mhoPositions[i].y, i);
            }

        }

        if (liveMhoCount == 0) {
            gameState = GameState.GameWon;
        }
    }

    // prepare for pain.

    /**
     * Moves a single mho
     * @param x
     * @param y
     * @param mhoNumber
     */
    private void mhoLogic(int x, int y, int mhoNumber)
    {

        // CASE 1: MHO IS LINEAR IN ACCORDANCE WITH THE PLAYER
        if (isMhoLinear(x, y))
        {
            Point toCoordinates = new Point(x, y);

            int xAxis = playerLocation.x - x;
            int yAxis = playerLocation.y - y;

            if (xAxis == 0) {
                if (playerLocation.y > y) {
                    toCoordinates.y += 1;
                } else {
                    toCoordinates.y += -1;
                }
            }

            if (yAxis == 0) {
                if (playerLocation.x > x) {
                    toCoordinates.x += 1;
                } else {
                    toCoordinates.x += -1;
                }
            }

            if (forceMhoMoveLinear(x, y, toCoordinates)) {
               mhoPositions[mhoNumber] = toCoordinates;
            }
            else {

                mhoPositions[mhoNumber] = null;

            }
        }
        else {  // mho is not linear, whole different ball game
             if (!mhoMoveNonLinear(x, y, Entity.Space, false, mhoNumber)) {
                 mhoMoveNonLinear(x, y, Entity.Fence, false, mhoNumber);
             }
        }


    }


    /**
     * Moves the Mho assuming it is not in line with the player.
     * @param x
     * @param y
     * @param onto
     * @param hasToMove
     * @param mhoNumber
     * @return
     */
    private boolean mhoMoveNonLinear(int x, int y, Entity onto, boolean hasToMove, int mhoNumber) {
        boolean lives = false;

        int pX = playerLocation.x;
        int pY = playerLocation.y;

        int distanceX = Math.abs(pX - x);
        int distanceY = Math.abs(pY - y);

        int deltaX;
        int deltaY;

        if (x > pX) { deltaX = -1; }
        else { deltaX = 1; }

        if (y > pY) { deltaY = -1; }
        else { deltaY = 1; }

        if (grid[x + deltaX][y + deltaY] == onto) {   // Move Diagonal
            grid[x][y] = Entity.Space;
            if (grid[x + deltaX][y + deltaY] == Entity.Fence && onto == Entity.Fence) {
                mhoPositions[mhoNumber] = null;
                liveMhoCount--;
                return false;
            }

            grid[x + deltaX][y + deltaY] = Entity.Mho;
            mhoPositions[mhoNumber] = new Point(x + deltaX, y + deltaY);
            lives = true;
        }

        else if (distanceX >= distanceY) {  // move horiz

            if (grid[x + deltaX][y] == onto) {
                grid[x][y] = Entity.Space;

                if (grid[x + deltaX][y] == Entity.Fence && onto == Entity.Fence) {
                    mhoPositions[mhoNumber] = null;
                    liveMhoCount--;
                    return false;
                }

                grid[x + deltaX][y] = Entity.Mho;
                mhoPositions[mhoNumber] = new Point(x + deltaX, y);
                lives = true;
            }

        }
        else {  // move vert
            if (grid[x][y + deltaY] == onto) {
                grid[x][y] = Entity.Space;

                if (grid[x][y + deltaY] == Entity.Fence && onto == Entity.Fence) {
                    mhoPositions[mhoNumber] = null;
                    liveMhoCount--;
                    return false;
                }

                grid[x][y + deltaY] = Entity.Mho;
                mhoPositions[mhoNumber] = new Point(x, y + deltaY);
                lives = true;
            }
            else if (hasToMove) {
                grid[x][y] = Entity.Space;
                mhoPositions[mhoNumber] = null;
                liveMhoCount--;
            }

        }

        return lives;


    }


    /**
     * Moves the mho if it is inline with the player
     * @param x
     * @param y
     * @param to
     * @return
     */
    private boolean forceMhoMoveLinear(int x, int y, Point to)
    {

        boolean lives = false;
        grid[x][y] = Entity.Space;

        if (grid[to.x][to.y] == Entity.Player) {
            grid[to.x][to.y] = Entity.Mho;
            gameState = GameState.GameLost;
            System.out.println("GAME OVER");

        }
        else if (grid[to.x][to.y] == Entity.Fence) {
            liveMhoCount--;
        }
        else if (grid[to.x][to.y] == Entity.Mho) {
            liveMhoCount--;
        }
        else {
            grid[to.x][to.y] = Entity.Mho;
            System.out.println("moving mho to = " + to);
            lives = true;
        }

        return lives;

    }


    /**
     * Determines whether the passed location is linear with respect with the player.
     * @param x
     * @param y
     * @return
     */
    private boolean isMhoLinear(int x, int y)
    {
        return (x == playerLocation.x) || (y == playerLocation.y);
    }



}
