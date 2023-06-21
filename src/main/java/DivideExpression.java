import java.util.*;

public class DivideExpression implements Expression {
    private final Expression leftExpression;
    private final Expression rightExpression;

    public DivideExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double execute(Map<String, Double> params) {
        return leftExpression.execute(params) / rightExpression.execute(params);
    }
}