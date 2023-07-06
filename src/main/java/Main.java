import Solver.Expr.Expression;
import Solver.ExpressionParser;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        ExpressionParser parser = new ExpressionParser();

        String input = "sin(x + 5) / 10 * ((z + 5!) - (y / 5))";
        double x = 1, y = 2, z = 3;

        Expression expression = parser.parse(input);
        double result = expression.execute(Map.of("x", x, "y", y, "z", z));

        System.out.println(input + " = " + String.valueOf(result));
    }
}
