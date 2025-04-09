import java.util.Stack;

public class Calculadora {

    Stack<Double> pilha;

    public Calculadora() {
        pilha = new Stack<>();
    }

    public void calcula(String expressao) {

        StringBuilder digito = new StringBuilder();

        for (int i = 0; i < expressao.length(); i++)  {

            char c = expressao.charAt(i);

            if (Character.isDigit(c)) {
                digito.append(c);
            }

            if (c == ' ' && !digito.isEmpty()) {
                pilha.push(Double.parseDouble(digito.toString()));
                digito = new StringBuilder();
            }

            if (c == '+' || c == '/' || c == '*' || c == '-' || c == '^') {
                fazOperacao(c);
            }

        }

    }

    public void fazOperacao(char operacao) {
        double b = pilha.pop();
        double a = pilha.pop();

        switch (operacao) {
            case '+':
                pilha.push(a + b);
                break;

            case '-':
                pilha.push(a - b);
                break;

            case '*':
                pilha.push(a * b);
                break;

            case '/':
                pilha.push(a / b);
                break;

            case '^':
                pilha.push(Math.pow(a, b));
                break;
        }
    }

    public void resultado() {
        if (compelta()) {
            while (!pilha.isEmpty()) {
                System.out.println(pilha.pop());
            }
        }
    }

    public void limpa() {
        pilha.empty();
    }

    public boolean compelta() {
        return pilha.size() == 1;
    }

    public static void main(String[] args) {

        String expressao = "1 2 + 3 * 2 ^ 27 / 3 -";
        System.out.println(expressao);

        Calculadora c = new Calculadora();
        c.calcula(expressao);
        c.resultado();
        c.limpa();

    }

}