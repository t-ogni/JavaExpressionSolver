import java.util.*;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();


        double x = 1, y = 2, z = 3;
        Map<String, Double> tests = Map.of(
                    "(x + 5) / 10 * ((z + 5) - (y / 5))", (x + 5) / 10 * ((z + 5) - (y / 5)),
                    "sin(x)", Math.sin(x),
                    "sin(x * y) / 4!", Math.sin(x * y) / (4*3*2)
                );

        for (var entry : tests.entrySet()) {

            Expression expression = parser.parse(entry.getKey());
            double real = expression.execute(Map.of("x", x, "y", y, "z", z));
            System.out.println(entry.getKey() + " = " + String.valueOf(entry.getValue()));
            System.out.print(" Правильный ответ: ");
            System.out.println(real);
            assert (entry.getValue() == real);
            System.out.println();
        }
    }
}