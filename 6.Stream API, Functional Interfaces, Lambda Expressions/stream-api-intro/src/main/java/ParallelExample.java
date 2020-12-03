import java.util.stream.IntStream;

public class ParallelExample {
    public static void main(String[] args) {
        //parallel execution
        long startTime = System.currentTimeMillis();
        IntStream intStream = IntStream.range(0, 20);
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");

        intStream.parallel().forEach(e ->{
            try{
                Thread.sleep(100);
            }catch (Exception exception){ }
        });

        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime));
    }
}
