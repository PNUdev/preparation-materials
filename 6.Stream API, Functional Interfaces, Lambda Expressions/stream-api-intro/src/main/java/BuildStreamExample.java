import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildStreamExample {
    public static void main(String[] args) {
        //1
        Stream<String> streamFromValues = Stream.of("1", "2", "3");

        //2
        String[] array = {"1", "2", "3"};
        Stream<String> streamFromArray = Arrays.stream(array);

        //3
        List<String> list = Arrays.asList("1", "2", "3");
        Stream<String> streamFromList = list.stream();
        Stream<String> parallelStreamFromList1 = list.parallelStream();
        Stream<String> parallelStreamFromList2 = list.stream().parallel();

        //4
        IntStream streamFromString = "123".chars();

        //5
        Stream.Builder<String> builder = Stream.builder();
        Stream<String> streamFromBuilder = builder.add("1").add("2").add("3").build();
    }
}
