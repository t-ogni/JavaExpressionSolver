import java.util.*;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();

        System.out.println(parser.parse("123    /5+43!-sin(12*3)")); // Выводит 4.56

//        String input = "a + 7 * (x-4) - sin(10) + a!";
//        Expression expression = parser.parse("(x + 5) / 10 * ((z + 5) - (y / 5))");
//        double result = expression.execute(Map.of("x", 1.0, "y", 2.0, "z", 3.0));
//        System.out.println(result); // не выводит 4.56 ((
    }
}