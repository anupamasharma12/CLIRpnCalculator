import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import static java.util.Arrays.asList;

public class CLIRpmCalculator {

    public static void main(String[] args) throws IOException {
        Stack<String> tokens = new Stack<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (;;) {
            String s = in.readLine();
            if (s == null) break;
            tokens.addAll(asList(s.trim().split("[ \t]+")));
            if (tokens.peek().equals("")) continue;
            try {
                double r = evaluate(tokens);
                tokens.push(String.valueOf(r));
                System.out.println(r);
            } catch (Exception e) {
                System.out.println("error");
            }
        }
    }

    private static double evaluate(Stack<String> tks) throws Exception {
        String tk = tks.pop();
        double op1, op2;
        try {
            op1 = Double.parseDouble(tk);
        } catch (Exception e) {
            op2 = evaluate(tks);
            op1 = evaluate(tks);
            switch (tk) {
                case "+":
                    op1 += op2;
                    break;
                case "-":
                    op1 -= op2;
                    break;
                case "*":
                    op1 *= op2;
                    break;
                case "/":
                    op1 /= op2;
                    break;
                default:
                    throw new Exception();
            }
        }
        return op1;
    }

}
