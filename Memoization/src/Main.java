
public class Main
{
    public static void main(String[] args) {

        final int ITERATIONS = 5000000;
        Timer nonMem = new Timer("Non Memoized Algorithm");
        Timer mem    = new Timer("    Memoized Algorithm");

        CollatzNonMemoized nonMemAlg = new CollatzNonMemoized(ITERATIONS);
        CollatzMemoized       MemAlg = new CollatzMemoized   (ITERATIONS);


        nonMem.start();
                            nonMemAlg.run();
        nonMem.stop();
        nonMem.display();



//        try {
//            System.out.println("zzzzz");
//            Thread.sleep(2000);
//        } catch (Exception e) {}


        mem.start();
                            MemAlg.run();
        mem.stop();
        mem.display();



    }

}






