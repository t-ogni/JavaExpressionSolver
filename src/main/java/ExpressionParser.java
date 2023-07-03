import java.util.*;
import java.util.regex.*;
// https://ru.wikipedia.org/wiki/Алгоритм_сортировочной_станции
enum Token {
    Const("\\d+"),
    Variable("\\w+"),
    Operator("[-+*\\/]"),
    UnaryFunction("sin|cos|tan"),
    PostfixFunction("!"),
    Branch("[()]");

    public final String pattern;

    Token(String pattern) {
        this.pattern = pattern;
    }

    public static Pattern getTokenPattern(){
        StringBuilder patternString = new StringBuilder();
        for (Token token : Token.values()) {
            patternString.append(token.pattern).append("|");
        }
        String pattern = patternString.substring(0, patternString.length() - 1); // последняя палочка лишняя

        return Pattern.compile("\\s*("+pattern+")\\s*");
    }
}

class TokenLexeme {
    public Token token;
    public String lexeme;

    public TokenLexeme(Token token, String lexeme) {
        this.token = token;
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return "{" + token +": '" + lexeme + "'}";
    }
}

public class ExpressionParser {

    // функция возвращает массив из токенов в постфиксном формате
    public Deque<TokenLexeme> tokenize(String expr){
        Deque<TokenLexeme> tokens = new ArrayDeque<>();
        Deque<TokenLexeme> stack = new ArrayDeque<>();
        Matcher m = Token.getTokenPattern().matcher(expr);
        while (m.find()) {
            String token = m.group().trim();
//            System.out.print(token);

            if (token.matches(Token.Const.pattern)) {
                tokens.push(new TokenLexeme(Token.Const, token));
//                System.out.println(" - const - to T");
            } else if (token.matches(Token.PostfixFunction.pattern)) {
                tokens.push(new TokenLexeme(Token.PostfixFunction, token));
//                System.out.println(" - postfix - to T");
            } else if (token.matches(Token.UnaryFunction.pattern)) {
                stack.push(new TokenLexeme(Token.UnaryFunction, token));
//                System.out.println(" - Func - to S");
            } else if (token.matches(Token.Operator.pattern)) {
                while (!stack.isEmpty() && stack.peek().token == Token.Operator) {
                    tokens.push(stack.pop());
                }
                stack.push(new TokenLexeme(Token.Operator, token));
//                System.out.println(" - Oper - to S");
            } else if (token.equals("(")) {
                stack.push(new TokenLexeme(Token.Branch, token));
//                System.out.println(" - Lbranch - to S");
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !(stack.peek().token == Token.Branch)) {
                    tokens.push(stack.pop());
                }
                stack.pop();
//                System.out.println(" - Rbranch - Del LB");
                if (!stack.isEmpty() && stack.peek().token == Token.UnaryFunction) {
                    tokens.push(stack.pop());
                }
            } else if (token.matches(Token.Variable.pattern)) {
                tokens.push(new TokenLexeme(Token.Variable, token));
//                System.out.println(" - Var");
            } else {
                System.out.println("IDK WTF: "+token);
            }
        }
        while (!stack.isEmpty()) {
            tokens.push(stack.pop());
        }


        return tokens;
    }

    /*
    * in > 2 + 3 - sin(4*5)
    * out < [
    *       [c, 2]
    *       [c, 3]
    *       [op, +]
    *       [c, 4]
    *       [c, 5]
    *       [op, *]
    *       [uf, sin]
    *       [op, -]
    *   ]
    */


    public Expression parse(String expr) {
        Deque<TokenLexeme> ReversePolish = this.tokenize(expr);
        Iterator<TokenLexeme> itr = ReversePolish.descendingIterator();
        while (itr.hasNext()) {
            TokenLexeme token = itr.next();
//            System.out.println(token);
            Expression exp;
            switch (token.token){
                case Const:
                    exp = new NumberExpression(Double.parseDouble(token.lexeme));
                    break;
                case Variable:
                    exp = new VariableExpression(token.lexeme);
                    break;
                case Operator:
                    switch (token.lexeme){
                        case "+":
                            exp = new AddExpression();
                            break;
                        case "-":
                            exp = new SubtractExpression();
                            break;
                        case "*":
                            exp = new MultiplyExpression();
                            break;
                        case "/":
                            exp = new DivideExpression();
                            break;
                        default:
                            break;
                    }
                    break;
                case UnaryFunction:
                case PostfixFunction:
            }
        }
        return null;
    }
}
