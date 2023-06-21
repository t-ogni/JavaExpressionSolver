import java.util.Map;

public class MultiplyExpression implements Expression {
    private final Expression leftExpression;
    private final Expression rightExpression;

    public MultiplyExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double execute(Map<String, Double> params) {
        return leftExpression.execute(params) * rightExpression.execute(params);
    }
}
