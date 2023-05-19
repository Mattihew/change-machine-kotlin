import java.util.List;


public interface IChangeMachine {
    /**
     * An automaton responsible for providing the fewest coins to satisfy a target cost
     *
     * @param coinsList A list of integers representing the value on each face of the coin.
     *                  Duplicates represent instances of coins.
     * @param target    The target cost (probably in pence)
     * @return A list of integers representing the minimum number of coins required to achieve the target, without
     * exceeding it.
     * If there are not enough coins available, it will return all the coins.
     * If there are enough coins but it will go over, it will return fewer coins to stay below the target.
     */
    public List<Integer> change(List<Integer> coinsList, int target);
}
