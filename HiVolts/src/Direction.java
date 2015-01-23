import java.awt.*;


/**
 * Represents directions and the associated change when applied to a point.
 */
public enum Direction
{
    Up          (new Point(+0, -1)),
    Down        (new Point(+0, +1)),
    Left        (new Point(-1, +0)),
    Right       (new Point(+1, +0)),

    UpLeft      (new Point(-1, -1)),
    UpRight     (new Point(+1, -1)),
    DownLeft    (new Point(-1, +1)),
    DownRight   (new Point(+1, +1)),

    Sit         (new Point(+0, +0)),
    None        (new Point(+0, +0)),
    Jump        (null);


    public Point transformation;

    Direction(Point trans)
    {
        this.transformation = trans;
    }


    /**
     * Creates a direction from the given key.
     * @param key The key on the keyboard that was pressed.
     * @return
     */
    public static Direction createFromKeyChar(char key)
    {
        Direction dir = Direction.None;

        switch (Character.toLowerCase(key))
        {
            case 'w': dir = Direction.Up;
                break;

            case 'x': dir = Direction.Down;
                break;

            case 'a': dir = Direction.Left;
                break;

            case 'd': dir = Direction.Right;
                break;

            case 'q': dir = Direction.UpLeft;
                break;

            case 'e': dir = Direction.UpRight;
                break;

            case 'z': dir = Direction.DownLeft;
                break;

            case 'c': dir = Direction.DownRight;
                break;

            case 's': dir = Direction.Sit;
                break;

            case 'j': dir = Direction.Jump;
        }

        return dir;
    }


}
