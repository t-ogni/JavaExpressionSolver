import java.util.Map;

public class TanExpression implements Expression {
    private final Expression expression;

    public TanExpression(Expression Expression) {
        this.expression = Expression;
    }

    @Override
    public double execute(Map<String, Double> params) {
        return Math.tan(expression.execute(params));
    }
}