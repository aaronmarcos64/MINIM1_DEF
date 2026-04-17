package components;

import java.util.Stack;

public class ReversePolishNotationImpl implements ReversePolishNotation {

    @Override
    public Double calcular(String expression) {
        Stack<Double> pila = new Stack<>();
        String[] tokens = expression.trim().split("\\s+");

        for (String token : tokens) {
            switch (token) {
                case "+":
                    Double s1 = pila.pop();
                    Double s2 = pila.pop();
                    pila.push(s1 + s2);
                    break;
                case "-":

                    Double r1 = pila.pop();
                    Double r2 = pila.pop();
                    pila.push(r2 - r1);
                    break;
                case "*":
                    Double m1 = pila.pop();
                    Double m2 = pila.pop();
                    pila.push(m1 * m2);
                    break;
                case "/":
                    Double d1 = pila.pop();
                    Double d2 = pila.pop();
                    pila.push(d2 / d1);
                    break;
                default:
                    pila.push(Double.parseDouble(token));
                    break;
            }
        }

        return pila.pop();
    }
}