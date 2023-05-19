import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// This implementation of ChangeMachine fails to meet the specification, but is an approximation using human analogy
// If using this method to pass change to a cashier, it would mostly work out okay. Good enough for corner-shop usage.

// However, this basic strategy fails to handle cases where you are required to skip a coin in order to achieve the
// correct pattern. This is why we have a MagicChangeMachine.
public class HumanChangeMachine implements IChangeMachine {

    public List<Integer> change(List<Integer> coinsFromPocket, final int costOfItem) {
        final ArrayList<Integer> myCoinsFromPocket = new ArrayList<Integer>(coinsFromPocket);
        // Initially sort the change by descending order. Coins fished out of pockets rarely stack up neatly.
        Collections.sort(myCoinsFromPocket, Collections.reverseOrder());

        // Make a little space in your palm to count your coins onto
        List<Integer> palmedCoins = new ArrayList<Integer>();

        // Check each coin in turn, and palm it if it'll get you closer to your goal without going over
        // Because of this simplistic approach, it means that it will fail if you need to skip over a coin;
        // You could resolve this a couple of different ways; recursion being my favourite option, but arguably
        // it's not the best way around the problem.
        // An alternative would be to remove some smaller coins and keep trying different combinations in reverse.
        // Eg, if you're looking for 13 within (5, 5, 2, 2, 2), this by default would attempt (5, 5, 2) and then see
        // the extra 2 would be too much. So you could pop entries from the top, and try matching the target by
        // skipping different coins in reverse order.
        myCoinsFromPocket.forEach((coin) -> {
            if (costOfItem - SumLists.sum(palmedCoins) >= coin) {
                palmedCoins.add(coin);
            }
        });

        return palmedCoins;
    }
}
