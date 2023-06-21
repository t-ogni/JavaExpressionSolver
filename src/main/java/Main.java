import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        Expression expression = parser.parse("(x + 5) / 10 * ((z + 5) - (y / 5))");
        double result = expression.execute(Map.of("x", 1.0, "y", 2.0, "z", 3.0));

        System.out.println(result); // Выводит 4.56
    }
}