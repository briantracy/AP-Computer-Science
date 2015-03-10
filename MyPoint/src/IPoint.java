/**
 *  This is a very generic interface that represents the essence of a point.
 *  That is, a set of two values that represent a location.
 *
 *  Not every point has to be represented by two integers. They could be floating points, or Strings, or anything else
 *
 *  There is something that all points have in common though, the ability to get and set their X and Y fields.
 *  This behaviour is common among all points whether they be points of Strings or points of Integer
 *
 *  Bringing together common functionality is what interfaces do best and for this reason, I have chosen to make in an
 *  interface to satisfy the requirements given.
 *
 */


public interface IPoint <T> {

    void setX(T x);
    void setY(T y);

    T getX();
    T getY();

    void print();
}
