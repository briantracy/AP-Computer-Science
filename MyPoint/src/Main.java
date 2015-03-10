/**
 *  This class corresponds to an interface that supports a getX, setX, getY, and setY method.
 *  This should be very easy.
 */


public class Main
{

    public static void main(String[] args)
    {

        /**
         *  There is one critical difference between the two following variable declarations and that
         *  is the static type of the variables.
         *
         *      iPoint - Static Type =  IPoint, Dynamic Type = MyPoint
         *     myPoint - Static Type = MyPoint, Dynamic Type = MyPoint
         *
         *  At first this does not seem like a big deal, but because the static type of iPoint is IPoint, we can
         *  only call methods that are declared in the IPoint interface on it.
         *
         *  The only method that the class MyPoint class declares that is not in the IPoint interface is the transform()
         *  method. This means that we can only call `transform()` on myPoint and not iPoint.
         */

        IPoint<Integer> iPoint   = new MyPoint();
        MyPoint myPoint          = new MyPoint(10, 10);


        int iPointX  = iPoint.getX();
        int myPointX = myPoint.getX();

        int iPointY  = iPoint.getY();
        int myPointY = myPoint.getY();

        iPoint. setX(iPointY);
        myPoint.setX(myPointY);

        iPoint. setY(iPointX);
        myPoint.setY(myPointY);

        /**
         *  As you can see, the basic functionality ensured by the IPoint interface is shown by the MyPoint class.
         *
         *  The previous section demonstrated the get/set functionality of the MyPoint class and highlighted the
         *  similarities between IPoint and MyPoint. The next section will demonstrate their differences.
         */

        // will cause error because the Static Type of iPoint (IPoint) does not declare the method `transform` even
        // though iPoint is actually an INSTANCE of MyPoint.
        iPoint.transform(3);

        // this will not cause an error because the Static Type of myPoint (MyPoint) does declare the method `transform`.
        myPoint.transform(3);


        iPoint.print();
        myPoint.print();
    }
}
