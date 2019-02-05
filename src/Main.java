import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String Expression = reader.readLine();
        double answer = Calculator.RPNtoAnswer(Calculator.ExpressionToRPN(Expression));
        System.out.println(answer);
    }
}
