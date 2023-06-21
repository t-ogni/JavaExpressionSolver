import java.util.*;
// https://ru.wikipedia.org/wiki/Алгоритм_сортировочной_станции

public class ExpressionParser {

    private static enum Token {
        XZ, PrefF, PostF, Unary, Binary, Trinary, Num, Var, OpenBracket, CloseBracket,
    }



    public Map<Token, String> tokenize(String expr){
        Map<Token, String> tokens =  new HashMap<Token, String>();
        char[] expr2 = expr.toCharArray();
        Token type = Token.XZ;
        String lexema = "";
        for (int i = 0; i < expr.length(); i++){
            if(Character.isDigit(expr2[i])){
                do {
                    lexema += String.valueOf(expr2[i]);
                } while (Character.isDigit(expr2[i]));
                type = Token.Num;
            } else if (Character.isLetter(expr2[i])) {
                do {
                    lexema += String.valueOf(expr2[i]);
                } while (Character.isDigit(expr2[i]));
            } else {
                continue;
            }
            tokens.put(type, lexema);
            lexema = "";
        }
        return tokens;
    }

    private String to_RPN(String expr){
        Deque<String> tokens =  new ArrayDeque<>();
        String POLISH = "";
        char[] expr2 = expr.toCharArray();

        return POLISH;
    }

    public Expression parse(String expr) {
        String output = "";
        return null;
    }
}
