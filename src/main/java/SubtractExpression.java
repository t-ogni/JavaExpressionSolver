import java.util.Map;

public class SubtractExpression implements Expression {
    private final Expression leftExpression;
    private final Expression rightExpression;

    public SubtractExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double execute(Map<String, Double> params) {
        return leftExpression.execute(params) - rightExpression.execute(params);
    }
}
