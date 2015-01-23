/**
 * The following class demonstrates encapsulation through data and behaviour hiding.
 */
public class Encapsulation
{
    private int secret;         // only access through getters and setters
    public  int numberOfRequests;

    /**
     * This is a standard setter for a private variable
     * @param secret the new value of secret
     */
    public void setSecret(int secret) {
        this.secret = secret;
    }

    /**
     *  <summary>
     *      This looks like a normal getter at first, but it performs more than just getting the value of secret.
     *
     *      This is the encapsulation of behaviour. Each time this getter is called, the numberOfRequests is incremented.
     *      This is a behaviour that the use is not aware of because of its encapsulation.
     *
     *      Getters and setters are usually seen as a form of encapsulation, but to me this is pretty weak. If the only thing
     *      a getter and setter is doing is changing an instance variable, not much encapsulation is going on.
     *
     *      By adding functionality to the getter as I will do in the following method, there is now encapsulation. The
     *      behaviour of the getter has been modified, unbeknownst to the user. This is encapsulation because behaviour is
     *      being hidden inside of the standard getter.
     *  </summary>
     */
    public int getSecret() {
        numberOfRequests++;
        return secret;
    }

    public void demonstrate()
    {
        Main.header("Demonstrating Encapsulation");

        Encapsulation encapsulation = new Encapsulation();
        encapsulation.setSecret(10);

        int var = encapsulation.getSecret();
        var     = encapsulation.getSecret();
        var     = encapsulation.getSecret();

        System.out.println("The value of secret is " + encapsulation.getSecret());

        int requests = encapsulation.numberOfRequests;

        System.out.println("Number of requests we have made for the secret variable: " + requests);

    }
}
