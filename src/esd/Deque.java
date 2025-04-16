package esd;
//extrai_final: extrai um dado do final
//extrai_inicio: extrai do início
//acessa: acessa um dado de uma posição qualquer

public class Deque <T> {

        T[] area;
        int inicio = 0;
        int fim = 0;
        int len = 0;
        final int TAMANHO = 4;

        @SuppressWarnings("unchecked")
        public Deque() {
            area = (T[]) new Object[TAMANHO];
        }


    public int comprimento() {
            return len;
        }

        public int capacidade() {
            return area.length;
        }

        public void adiciona(T algo) {

            if (len == area.length) {
                expande();
            }

            area[fim] = algo;
            len++;
            // incremento circular
            fim = (fim + 1) % area.length;
        }

        public void insere(T algo) {
            if (len == area.length) {
                expande();
            }
            inicio = (inicio - 1 + area.length) % area.length;
            area[inicio] = algo;
            len++;

        }

    public T extrai_inicio() {
        if (len == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T algo = area[inicio];
        area[inicio] = null;
        inicio = (inicio + 1) % area.length;
        len--;
         return algo;
    }

    public T extrai_final(){
            if (len == 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            fim = (fim - 1 + area.length) % area.length;
            T algo = area[fim];
            area[fim] = null;
            len--;
            return algo;
    }



        public T acessa_inicio() {
            if (len == 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            return area[inicio];

        }

        public T acessa_final() {
            if (len == 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int pos = (fim - 1 + area.length) % area.length;
            return area[pos];
        }

        public T acessa(int index) {
            if (index < 0 || index >= len) {
                throw new ArrayIndexOutOfBoundsException();
            }

            return area[(index + inicio) % area.length];
        }

        public boolean esta_vazia() {
            return len == 0;
        }

        public void limpa() {
            for (int i = 0; i < len; i++) {
                area[(inicio + i) % area.length] = null;
            }

            inicio = 0;
            fim = 0;
            len = 0;
        }

        public void expande() {
            T[] novo = (T[]) new Object[area.length * 2];

            for (int i = 0; i < len; i++) {
                novo[i] = area[(inicio + i) % area.length];
            }
            area = novo;
            fim = len;
            inicio = 0;
        }
}