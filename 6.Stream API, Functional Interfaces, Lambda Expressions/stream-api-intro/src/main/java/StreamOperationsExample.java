import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperationsExample {

    public static void main(String[] args) {
        //intermediate : filter, sorted, map
        //terminal: count, sum, reduce, collect, foreach

        List<String> stringList = Arrays.asList("1g", "2g", "3f");


        Stream<String> stringStream = stringList.stream().map(e-> e.toUpperCase());
        System.out.println(stringStream.collect(Collectors.toList()));

        //streams are not allowed to reuse
        //System.out.println(stringList.stream().map(e->e.toUpperCase()).collect(Collectors.toList()));


        //1 - intermediate
        //filter
        List<String> strings = Arrays.asList("A1", "H2", "D5");
        System.out.println("Count: " +   strings.stream().filter(e->e.length() >= 2).count());
        //sorted
        System.out.println("Direct order:");
        strings.stream().filter(e->e.length() >= 2).sorted().forEach(e-> System.out.println(e));
        System.out.println("Indirect order:");
        strings.stream().filter(e->e.length() >= 2).sorted(Comparator.reverseOrder()).forEach(e-> System.out.println(e));
        //map
        System.out.println("Map :");
        strings.stream().map(e->e + "A").forEach(e -> System.out.println(e));

        //2 - terminal
        //count
        //Sum of all elements
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        Integer resSum = numbers.stream().reduce(0, (sum, p) -> sum = sum + p);
        System.out.println("Sum :" + resSum);
        //reduce
        //Maximum of all elements
        Integer resMax = numbers.stream().reduce(numbers.get(0), (max, p) -> max < p ? p : max);
        System.out.println(resMax); // 7
        //collect to hashMap
        Map<Integer,Integer> intMap = numbers.stream().collect(Collectors.toMap(i -> i, i -> i + 1));
        System.out.println(intMap);
    }


}
