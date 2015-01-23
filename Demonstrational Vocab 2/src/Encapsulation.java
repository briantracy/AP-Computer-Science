/**
 * The following class demonstrates encapsulation through data-hiding.
 */
public class Encapsulation
{
    private int secret;
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

        System.out.println("The value of secret is " + encapsulation.getSecret());

        int requests = encapsulation.numberOfRequests;

        System.out.println("Number of requests we have made for the secret variable: " + requests);

    }
}
