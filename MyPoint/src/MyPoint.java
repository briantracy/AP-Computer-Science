/**
 *  This class is a concrete implementation of the IPoint interface that most closely matches our everyday
 *  representation of a point. That is, it is a collection of two Integers that behave as coordinates on a plane.
 *
 *  Note that this is not the only possible implementation of IPoint, just one that seems natural.
 */
public class MyPoint implements IPoint<Integer> {

    private Integer x, y;

    /**
     *  Concise Constructor if the user knows exactly what values this point should contain.
     */
    public MyPoint(Integer x, Integer y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     *  Default Constructor. Calls the concise constructor with 0, 0
     */
    public MyPoint()
    {
        this(0, 0);
    }


    /**
     *  The next five methods are simply implementations of the methods declared in the IPoint interface.
     */

    public void setX(Integer x) { this.x = x; }

    public void setY(Integer y) { this.y = y; }

    public Integer getX()       { return x;   }

    public Integer getY()       { return y;   }

    public void print()
    {
        System.out.format("(%d, %d)\n", x, y);
    }


    /**
     *  This last method is not found in the IPoint interface and is used as a demonstration in the main method.
     *  Only instances with a Static Type of MyPoint will be able to call this method. This differentiates it
     *  from instances of IPoint which have every other method in this class.
     */

    public void transform(int factor)
    {
        this.x *= factor;
        this.y *= factor;
    }
}
