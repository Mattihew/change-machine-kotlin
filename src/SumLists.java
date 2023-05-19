import java.util.List;

public class SumLists {
    public int sum(List<Integer> list) {
        return list.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
