import java.util.*;

public class Tokenizer {
    public enum Token {
        PREFIX_FUNCTION,
        POSTFIX_FUNCTION,
        UNARY_OPERATOR,
        BINARY_OPERATOR,
        NUMBER,
        VARIABLE,
        OPENING_BRACKET,
        CLOSING_BRACKET
    }

    public static Map<Token, String> tokenize(String input) {
        Map<Token, String> tokenMap = new HashMap<>();
        StringBuilder tokenBuilder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (Character.isWhitespace(c)) {
                continue;
            }

            if (Character.isLetter(c)) {
                // Проверка на переменную
                tokenBuilder.append(c);
                while (i + 1 < input.length() && Character.isLetterOrDigit(input.charAt(i + 1))) {
                    tokenBuilder.append(input.charAt(i + 1));
                    i++;
                }
                String token = tokenBuilder.toString();
                tokenBuilder.setLength(0);
                if (isPrefixFunction(token)) {
                    tokenMap.put(Token.PREFIX_FUNCTION, token);
                } else if (isPostfixFunction(token)) {
                    tokenMap.put(Token.POSTFIX_FUNCTION, token);
                } else {
                    tokenMap.put(Token.VARIABLE, token);
                }
            } else if (Character.isDigit(c)) {
                // Проверка на число
                tokenBuilder.append(c);
                while (i + 1 < input.length() && (Character.isDigit(input.charAt(i + 1)) || input.charAt(i + 1) == '.')) {
                    tokenBuilder.append(input.charAt(i + 1));
                    i++;
                }
                String token = tokenBuilder.toString();
                tokenBuilder.setLength(0);
                tokenMap.put(Token.NUMBER, token);
            } else if (isUnaryOperator(String.valueOf(c))) {
                // Проверка на унарный оператор
                tokenMap.put(Token.UNARY_OPERATOR, String.valueOf(c));
            } else if (isBinaryOperator(String.valueOf(c))) {
                // Проверка на бинарный оператор
                tokenMap.put(Token.BINARY_OPERATOR, String.valueOf(c));
            } else if (c == '(') {
                // Проверка на открывающуюся скобку
                tokenMap.put(Token.OPENING_BRACKET, String.valueOf(c));
            } else if (c == ')') {
                // Проверка на закрывающуюся скобку
                tokenMap.put(Token.CLOSING_BRACKET, String.valueOf(c));
            }
        }

        return tokenMap;
    }

    private static boolean isPrefixFunction(String token) {
        return token.matches("sin|cos|sqrt");
    }
    private static boolean isPostfixFunction(String token) {
        return token.matches("!|%");
    }

    private static boolean isUnaryOperator(String token) {
        return token.matches("[+-]");
    }

    private static boolean isBinaryOperator(String token) {
        return token.matches("[+\\-*/]");
    }

    private static boolean isNumber(String token) {
        // Проверка на число
        // Верните true, если токен является числом, иначе false
        // Например, можно использовать метод `Double.parseDouble` для проверки
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isVariable(String token) {
        return token.equals("(");
    }
}
