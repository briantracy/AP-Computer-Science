/**
 *  This class has a single instance variable named noise which is printed when its
 *  `makeNoise` method is called.
 */
class Animal {
    protected String noise = "default noise";

    /**
     * This prints the animals noise. In the case of the Animal, it will print "default noise"
     * In the case of the Cow, multiple things happen.
     *      1. The cow has set its noise to "MOOOO"
     *      2. Cow has not implemented this method, so the superclass's implementation is called
     *      3. When called on a cow, `noise` is now "MOOOO", so this line will print the correct sound for any subclass
     */
    protected void makeNoise() {
        System.out.println(noise);
    }
}

/**
 * This class is a subclass of animal and simply changes its noise. It does not need to implement
 * the `makeNoise` method because as part of extending Animal, any methods not implemented will simply be
 * called on the super class.
 */
class Cow extends Animal{
    public Cow() {
        noise = "MOOOO";
    }
}

/**
 *  This class uses the above classes to demonstrate a critical aspect of inheritance. Inheritance is the act of inheriting
 *  behaviour from a superclass. They critical point in this demonstration is the fact that Cow does not implement makeNoise,
 *  but it inherits it from its super class.
 */
public class Inheritance {

    public void demonstrate()
    {
        Main.boxtext("Demonstrating Inheritance");

        /// Make an animal. Animals have the makeNoise method.
        Animal animal = new Animal();
        animal.makeNoise();

        /// Now make a cow which does not implement noise, but its super class does.
        Cow cow = new Cow();
        cow.makeNoise();
    }

}
