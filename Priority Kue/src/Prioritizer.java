/*
    We need a way to quantify an object's priority, relative to that object. What about this specific
    object makes it more important than another. For example, take Strings. One implementation might say that
    a String's priority is based on its length. Another might say it is based on the number of 'a's it contains.

    This kind of customization is the driving force behind the Prioritizer interface. It allows total control over
    how items are ordered in a PriorityQueue
 */


public abstract interface Prioritizer<T>
{
    /**
     *  This is the only method to be implemented by subclasses, allowing sleek in-line instantiation of Prioritizers
     * @param object   The object who's priority will be evaluated by the following algorithm
     * @return      How high of a priority this object shall be given.
     */
    public abstract int prioritize(T object);
}
