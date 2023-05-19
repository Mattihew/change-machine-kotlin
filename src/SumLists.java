import java.util.List;

/**
 * We set the class to "final" here to prevent subclassing - which won't make much difference in this project,
 * but can help improve performance at scale
 */
public final class SumLists {

    /**
     * A simple helper function used in Change Machines to sum the contents of a list of integers.
     *
     * @param list of integers to sum
     * @return the sum of integers in the list
     */
    public static int sum(List<Integer> list) {
        return list.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
