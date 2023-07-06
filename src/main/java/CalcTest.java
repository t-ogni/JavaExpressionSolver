import Solver.Expr.Expression;
import Solver.ExpressionParser;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class CalcTest {


    double x = 1, y = 2, z = 3;
    Map<String, Double> tests = Map.of(
            "(x + 5) / 10 * ((z + 5) - (y / 5))", (x + 5) / 10 * ((z + 5) - (y / 5)),
            "sin(x)", Math.sin(x),
            "sin(x * y) / 4!", Math.sin(x * y) / (4*3*2)
    );

    @Test
    public void testAll() {
        ExpressionParser parser = new ExpressionParser();

        for (var entry : tests.entrySet()) {
            Expression expression = parser.parse(entry.getKey());
            double result = expression.execute(Map.of("x", x, "y", y, "z", z));
            assertEquals(Optional.of(entry.getValue()), Optional.of(result));
            System.out.println();
        }
    }
}
