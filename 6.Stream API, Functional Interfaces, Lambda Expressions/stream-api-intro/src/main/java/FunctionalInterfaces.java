import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        //1.1
        Function<Integer, String> function1 = new Function<Integer, String>() {
            @Override
            public String apply(Integer element) {
                return element.toString();
            }
        };

        //1.2
        String number = function1.apply(5);
        System.out.println(number);

        Function<Integer, String> function2 = element -> element.toString();
        System.out.println(function2.apply(57));

        //2
        Predicate<Integer> predicate = e -> e % 2 == 0;
        System.out.println("Is Even? - " + predicate.test(6));
        System.out.println("Is Even? - " + predicate.test(7));

        //3
        Supplier<LocalDateTime> supplier = () -> LocalDateTime.now();
        System.out.println("Current time: " + supplier.get());


        //4
        Consumer<List<Integer>> consumer = list -> list.stream().map(e-> e + "*").forEach(e -> System.out.println(e));
        consumer.accept(Arrays.asList(1, 2, 3));
    }
}
