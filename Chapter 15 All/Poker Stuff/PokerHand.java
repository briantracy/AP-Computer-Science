import com.sun.xml.internal.ws.util.StringUtils;

import java.util.*;

/**
 * Created by Brian on 10/21/14.
 */
public class PokerHand extends Deck {


    /**
     * Default constructor that takes the number of cards in this hand.
     * @param numCards
     */
    public PokerHand(int numCards)
    {
        super(numCards);
    }


    /**
     * A flush is five of the same suit. This method finds the suit of the first card in the deck. It then checks this suit
     * against all the others in the hand. If one card has a different suit, then the hand is not flush.
     * @return
     */
    public boolean hasFlush()
    {
        int firstSuit = cards[0].suit;

        for (Card card : cards)
        {
            if (card.suit != firstSuit) {
                return false;
            }
        }

        return true;
    }

    /**
     * The following methods for threeOfKind and Pair are quite self documenting
     * @return
     */
    public boolean hasThreeKind()
    {
        return hasAnyOfKind(3);
    }
    public boolean hasPair()
    {
        return hasAnyOfKind(2);
    }


    /**
     * This method determines whether or not there are `any` number of the same rank in this hand.
     * @param any number of cards to have a repetition of.
     * @return
     */
    private boolean hasAnyOfKind(int any)
    {
        List<String> rep = new ArrayList<String>();
        for (Card card : cards)
        {
            rep.add(Integer.toString(card.rank));
        }


        final int maxRank = 13;
        for (int i = 0; i < maxRank; i++)
        {
            if (Collections.frequency(rep, Integer.toString(i)) >= any) {
                return true;
            }
        }

        return false;
    }


    /**
     * Determines whether a given deck has the best hand in poker. Uses a set to represent A, 10-K to compare to our hand
     * First checks if we have a flush before checking to see if we have a royal flush.
     *
     */
    public boolean hasRoyalFlush()
    {
        if (hasFlush()) {

            Set<Integer> royals = new HashSet<Integer>(); // rare usage of a set for unordered collection
            royals.add(1);
            royals.add(10);
            royals.add(11);
            royals.add(12);
            royals.add(13);

            Set<Integer> myHand = new HashSet<Integer>();

            for (Card card : cards) {
                myHand.add(card.rank);
            }

            return royals.equals(myHand);

        }
        return false;
    }






}
