import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MagicChangeMachine implements IChangeMachine {

    final SumLists sl = new SumLists();
    public List<Integer> change(List<Integer> coins, int amount) {
        // Same reason as the HumanChangeMachine, we need to order these. Just in case someone feeds in a bucket of coins
        Collections.sort(coins, Collections.reverseOrder());
        return findCombination(coins, amount, 0, new ArrayList<>(), new ArrayList<>());
    }

    private List<Integer> findCombination(List<Integer> coins, final int target, int startIndex, List<Integer> currentCombination, List<Integer> bestCombination) {

        // This sums the totals from each list object. It should work if it's an ArrayList, LinkedList, or anything else.
        final int currentSum = sl.sum(currentCombination);
        final int bestSum = sl.sum(bestCombination);

        // There are two primary ways of determining a better coin match
        // Either it hits the target, and uses fewer coins than the current combination
        boolean a = target - currentSum == 0 && currentCombination.size() < bestCombination.size();

        // Or it has a lower absolute deviation from the target
        boolean b = Math.abs(target - currentSum) < Math.abs(target - bestSum);

        // In either case, we clear the current best combination and update it with the values from current
        // This is necessary because this recursive class makes use of list objects by-reference
        if (a || b) {
            bestCombination.clear();
            bestCombination.addAll(currentCombination);
        }

        // Iterate over all the coins we have left to check, and see if that produces a better result
        for (int i = startIndex; i < coins.size(); i++) {
            int coin = coins.get(i);
            if (coin <= target - currentSum) {
                currentCombination.add(coin);

                //Recursion!
                findCombination(coins, target, i + 1, currentCombination, bestCombination);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }

        // I could return null here, but generally I'm against mixed return types; I'd rather use an empty ArrayList here
        return bestCombination.isEmpty() ? new ArrayList<>() : bestCombination;
    }
}
