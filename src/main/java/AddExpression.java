import java.util.Map;

public class AddExpression implements Expression {
    private final Expression leftExpression;
    private final Expression rightExpression;

    public AddExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double execute(Map<String, Double> params) {
        return leftExpression.execute(params) + rightExpression.execute(params);
    }
}
