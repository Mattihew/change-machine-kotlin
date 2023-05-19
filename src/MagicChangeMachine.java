import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MagicChangeMachine implements IChangeMachine {

    /**
     * @param coins  A list of integers representing the value on each face of the coin.
     *               Duplicates represent instances of coins.
     * @param amount The target cost (probably in pence)
     * @return
     */
    public List<Integer> change(final List<Integer> coins, final int amount) {
        // Avoids side-effects of modifying the original parameter by copying it
        final List<Integer> myCoins = new ArrayList<Integer>(coins);

        // Same reason as the HumanChangeMachine, we need to order these. Just in case someone feeds in a bucket of coins
        Collections.sort(myCoins, Collections.reverseOrder());

        return findCombination(myCoins, amount, 0, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * @param coins              - list of coins used throughout all recursions
     * @param target             - target cost
     * @param startIndex         - start index for recursion
     * @param currentCombination - currently tracked combination of coins
     * @param bestCombination    - best known combination of coins
     * @return
     */
    private List<Integer> findCombination(final List<Integer> coins, final int target, final int startIndex, final List<Integer> currentCombination, final List<Integer> bestCombination) {

        final ArrayList<Integer> myCurrentCombination = new ArrayList<Integer>(currentCombination);
        final ArrayList<Integer> myBestCombination = new ArrayList<Integer>(bestCombination);


        // This sums the totals from each list object. It should work if it's an ArrayList, LinkedList, or anything else.
        final int currentSum = SumLists.sum(currentCombination);
        final int bestSum = SumLists.sum(bestCombination);

        // There are two primary ways of determining a better coin match
        // Either it hits the target, and uses fewer coins than the current combination
        final boolean a = target - currentSum == 0 && currentCombination.size() < bestCombination.size();

        // Or it has a lower absolute deviation from the target
        final boolean b = Math.abs(target - currentSum) < Math.abs(target - bestSum);

        // In either case, we clear the current best combination and update it with the values from current
        if (a || b) {
            // This clear() is necessary because we are holding a copy of the previous run's best combination here, and we want
            // to replace the values, not append them.
            bestCombination.clear();
            bestCombination.addAll(currentCombination);
        }

        // Iterate over all the coins we have left to check, and see if any of those routes produce a better result
        for (int i = startIndex; i < coins.size(); i++) {
            final int coin = coins.get(i);
            if (coin <= target - currentSum) {
                currentCombination.add(coin);

                //Recursion!
                findCombination(coins, target, i + 1, currentCombination, bestCombination);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }

        // I could return null here, but generally I'm against mixed return types; I'd rather use an empty ArrayList here
        return bestCombination.isEmpty() ? Collections.emptyList() : bestCombination;
    }
}
