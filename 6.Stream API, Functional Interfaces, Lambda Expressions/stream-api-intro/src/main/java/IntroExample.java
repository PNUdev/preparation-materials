import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IntroExample {
    public static void main(String[] args) {
        List<Integer> integersList = Arrays.asList(1, 1, 0, -3, -5, 6, 0);

        //1
        Set<Integer> integersSet = new HashSet<>(integersList);
        int sum = 0;
        for (Integer e: integersSet) {
            if (e > 0){
                sum += e;
            }
        }
        System.out.println("Elements: " + integersSet);
        System.out.println("Sum: " + sum);


        //2
        int streamSum = integersList.stream().distinct().filter(e->e > 0).mapToInt(e->e).sum();
        System.out.println("Sum: " + streamSum);
    }
}
