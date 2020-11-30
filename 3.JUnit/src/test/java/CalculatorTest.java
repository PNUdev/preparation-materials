import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    public void addTest() {

        int a = 2;
        int b = 4;
        int result = calculator.add(a ,b);
        Assertions.assertEquals(6, result, "Add method fails");

    }
}
