import esd.APB;

public class Main {

    public static void main(String[] args) throws Exception {
        APB<Integer> arv = new APB<>();
        int vals[] = {5, 3, 2, 4, 7, 6, 8};
        int _vals[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (var k: vals) {
            arv.adiciona(k);
        }

        for (var k: _vals) {
            Integer val = arv.procura(k);
            if (val != null) {
                System.out.printf("%d: %d\n", k, val);
            } else {
                System.out.printf("%d: null\n", k);
            }
        }
    }
}