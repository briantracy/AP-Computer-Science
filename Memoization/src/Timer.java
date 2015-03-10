
public class Timer {

    private String name;

    private long start;
    private long elapsed;

    public Timer(String name)
    {
        this.name = name;
    }

    public void start()
    {
        start = System.currentTimeMillis();
    }

    public void stop()
    {
        elapsed = System.currentTimeMillis() - start;
    }

    public void display()
    {
        System.out.printf("Operation [%s] took %dms\n", name, elapsed);
    }
}
