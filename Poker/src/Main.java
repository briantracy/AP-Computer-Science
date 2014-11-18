import java.util.AbstractCollection;

/**
 * Entry point for this application. Used to gather statistics on how frequently certain hands are attained.
 *
 * For the extra case I added Royal Flush, the most elusive poker hand. You may have to increase the iteration count to see one.
 *
 */
public class Main {

    public static void main(String[] args) {

        Deck d = new Deck();


        final long iterCount = 1000000L;
        int flushCount = 0;
        int threeKindCount = 0;
        int pairCount = 0;
        int royalsCount = 0;

        for (int i = 1; i < iterCount; i++)
        {
            AbstractCollection

            PokerHand pokerHand = d.deal();

            if (pokerHand.hasFlush()) {
                flushCount++;

            }

            if (pokerHand.hasRoyalFlush()) {
                royalsCount++;
                pokerHand.print();
            }

            if (pokerHand.hasThreeKind()) {
                threeKindCount++;
            }
            else if (pokerHand.hasPair()) {
                pairCount++;
            }
        }

        System.out.println("flushPercentage = " + ((double)flushCount / iterCount) * 100);
        System.out.println("threeKindPercentage = " + ((double)threeKindCount / iterCount) * 100);
        System.out.println("pairPercentage = " + ((double)pairCount / iterCount) * 100);
        System.out.println("royalFlushPercentage = " + ((double)royalsCount / iterCount) * 100);
    }
}
