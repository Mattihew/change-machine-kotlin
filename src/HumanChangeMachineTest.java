import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class HumanChangeMachineTest {

    static IChangeMachine changeMachine;

    @BeforeAll
    static void startup() {
        // Change which concrete class used in testing here!
        changeMachine = new HumanChangeMachine();
    }

    @Test
    void test1() {
        List<Integer> change = changeMachine.change(Arrays.asList(200, 100, 50, 20, 10, 5, 2, 1), 13);
        Stream<Integer> array = Stream.of(10, 2, 1);
        List<Integer> expectedResult = array.collect(toList());
        Assertions.assertEquals(expectedResult, change);
    }

    /**
     * This test fails! Check the notes in the HumanChangeMachine class as to why.
     */
    @Test
    void test2() {
        List<Integer> change = changeMachine.change(Arrays.asList(5, 5, 2, 2, 2), 11);
        Stream<Integer> array = Stream.of(5, 2, 2, 2);
        List<Integer> expectedResult = array.collect(toList());
        Assertions.assertEquals(expectedResult, change);
    }


    @Test
    void test3() {
        List<Integer> change = changeMachine.change(Arrays.asList(200, 100, 20, 200, 10, 5, 50, 2, 1, 1, 2), 574);
        Stream<Integer> array = Stream.of(200, 200, 100, 50, 20, 2, 2);
        List<Integer> expectedResult = array.collect(toList());
        Assertions.assertEquals(expectedResult, change);
    }

    @Test
    void test4() {
        List<Integer> change = changeMachine.change(Arrays.asList(200, 100, 20, 200, 10, 5, 50, 2, 1, 1), 574);
        Stream<Integer> array = Stream.of(200, 200, 100, 50, 20, 2, 1, 1);
        List<Integer> expectedResult = array.collect(toList());
        Assertions.assertEquals(expectedResult, change);
    }

    @Test
    void test6() { //close but ran out of coins
        try {
            List<Integer> change = changeMachine.change(Arrays.asList(200, 100, 20, 200, 10, 5, 50, 2, 1), 574);
            Stream<Integer> array = Stream.of(200, 200, 100, 50, 20, 2, 1);
            List<Integer> expectedResult = array.collect(toList());
            Assertions.assertEquals(expectedResult, change);
        } catch (RuntimeException e) {
            Stream<Integer> array = Stream.of(200, 200, 100, 50, 20, 2, 1);
            List<Integer> expectedResult = array.collect(toList());
            List<Integer> change = new ArrayList<Integer>();
            for (String item : e.getMessage().split(",")) {
                change.add(Integer.decode(item));
            }
            Assertions.assertEquals(expectedResult, change);
        }
    }
}
