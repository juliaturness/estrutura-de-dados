package atividades.arvore;

import esd.APB;

import java.io.FileReader;
import java.util.Scanner;

public class ConsultaNome {

    private Scanner arquivo;
    private APB<String> arvore = new APB<>();

    public ConsultaNome(String caminho) {
        arquivo = scan(caminho);
    }

    public Scanner scan(String caminho) {
        try {
            return new Scanner(new FileReader(caminho));
        }  catch (Exception e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + caminho, e);
        }
    }

    public void read() {
        while (arquivo.hasNextLine()) {
            String linha = arquivo.nextLine().trim().toLowerCase();
            if (!linha.isEmpty()) arvore.adiciona(linha);;
        }
    }

    public void consulta() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String entrada = scanner.nextLine().trim().toLowerCase();

            if (entrada.isEmpty())  break;

            String resultado = (arvore.procura(entrada) != null) ? "EXISTE" : "INEXISTENTE";
            System.out.println(entrada + ": " + resultado);
        }

        scanner.close();
        System.out.println("Programa encerrado.");
    }

    public static void main(String[] args) {
        if (args.length != 1)
            throw new RuntimeException("<ConsultaNome> requer 1 argumento (caminho do arquivo)");

        ConsultaNome cc = new ConsultaNome(args[0]);

        cc.read();
        cc.consulta();
    }

}


//// inverte a árvore (troca os filhos esquerdo e direito)
//public void inverte() {
//    raiz = inverteRec(raiz);
//}
//
//// metodo recursivo para inverter a árvore
//private APB.NodoAPB inverteRec(APB.NodoAPB nodo) {
//    if (nodo == null) return null;
//
//    APB.NodoAPB esquerdaInvertida = inverteRec(nodo.esq);  // inverte a subárvore esquerda
//    APB.NodoAPB direitaInvertida = inverteRec(nodo.dir);  // inverte a subárvore direita
//
//    // troca os filhos esquerdo e direito
//    nodo.esq = direitaInvertida;
//    nodo.dir = esquerdaInvertida;
//
//    return nodo;
//}
//
//// compara duas árvores e retorna 0 se forem diferentes, 1 se tiverem a mesma estrutura e 2 se forem idênticas
//public int compara_arvores(APB<T> outraArvore) {
//    return comparaNodos(this.raiz, outraArvore.raiz);
//}
//
//// metodo recursivo para comparar dois nós
//private int comparaNodos(APB.NodoAPB nodo1, APB.NodoAPB nodo2) {
//    if (nodo1 == null && nodo2 == null) {
//        return 2;  // ambos os nós são nulos, as árvores são idênticas
//    }
//
//    if (nodo1 == null || nodo2 == null) {
//        return 0;  // um dos nós é nulo, as árvores são diferentes
//    }
//
//    if (!nodo1.valor.equals(nodo2.valor)) {
//        return 0;  // os valores dos nós são diferentes
//    }
//
//    // compara as subárvores esquerdas e direitas
//    int esq = comparaNodos(nodo1.esq, nodo2.esq);
//    int dir = comparaNodos(nodo1.dir, nodo2.dir);
//
//    if (esq == 2 && dir == 2) {
//        return 2;  // as subárvores esquerda e direita são idênticas
//    }
//
//    return 1;  // as árvores têm a mesma estrutura mas são diferentes
//}
//
//// clona a árvore e retorna uma nova árvore
//public APB<T> clone() {
//    APB<T> novaArvore = new APB<>();
//    novaArvore.raiz = _clone(this.raiz, null);
//    return novaArvore;
//}
//
//// metodo recursivo para clonar a árvore
//private APB.NodoAPB _clone(APB.NodoAPB nodo, APB.NodoAPB pai) {
//    if (nodo == null) return null;
//
//    APB.NodoAPB novoNodo = new APB.NodoAPB(nodo.valor, pai);  // cria um novo nó com o mesmo valor e pai
//
//    // clona as subárvores esquerdas e direitas
//    novoNodo.esq = _clone(nodo.esq, novoNodo);
//    novoNodo.dir = _clone(nodo.dir, novoNodo);
//
//    return novoNodo;
//}
//
//// método público que retorna uma lista contendo todos os valores das folhas da árvore
//public ListaSequencial<T> lista_folhas() {
//    ListaSequencial<T> folhas = new ListaSequencial<>();  // cria uma lista para armazenar as folhas
//    _listarFolhas(raiz, folhas);  // chama o recursivo passando a raiz da árvore
//    return folhas;  // retorna a lista
//}
//
//// método recursivo que percorre a árvore e adiciona os valores das folhas na lista
//private void _listarFolhas(NodoAPB nodo, ListaSequencial<T> folhas) {
//    if (nodo == null) {
//        return;  // se o nó atual for nulo, faz nada
//    }
//    // verifica se o nó é uma folha (não tem filhos à esquerda nem à direita)
//    if (nodo.esq == null && nodo.dir == null) {
//        folhas.adiciona(nodo.valor);  // adiciona o valor da folha na lista
//    }
//    // chama recursivamente para verificar as subárvores esquerda e direita
//    _listarFolhas(nodo.esq, folhas);
//    _listarFolhas(nodo.dir, folhas);
//}
//
//public void ordena() {
//    if (len <= 1) return;
//
//    // cria a árvore binária
//    APB<T> arvore = new APB<>();
//
//    //insere todos os valores da lista na árvore
//    Lista.Node atual = guarda.proximo;
//    while (atual != guarda) {
//        arvore.adiciona(atual.valor);  // insere na árvore
//        atual = atual.proximo;
//    }
//
//    // obtem os valores ordenados em uma lista
//    ListaSequencial<T> valoresOrdenados = arvore.emOrdem();
//
//    // substitui os valores da lista pelos valores ordenados
//    Lista.Node nodo = guarda.proximo;
//    for (int i = 0; i < valoresOrdenados.comprimento(); i++) {
//        nodo.valor = valoresOrdenados.obtem(i);  // substitui o valor na lista
//        nodo = nodo.proximo;  // move para o próximo nó
//    }
//}
//
//
//// Método para clonar a tabela hash
//public TabHash<K, V> clona() {
//    TabHash<K, V> novaTabela = new TabHash<>();
//    for (int i = 0; i < capacidade; i++) {
//        ListaSequencial<TabHash.Par> lista = tabela.obtem(i);
//        for (int j = 0; j < lista.comprimento(); j++) {
//            TabHash.Par par = lista.obtem(j);
//            novaTabela.adiciona(par.obtemChave(), par.obtemValor());
//        }
//    }
//    return novaTabela;
//}
//
//// Inverte a ordem dos elementos na tabela hash
//
//public void inverte() {
//    for (int i = 0; i < capacidade; i++) {
//        ListaSequencial<TabHash.Par> lista = tabela.obtem(i);
//        lista.inverte();  // Supondo que a classe ListaSequencial tenha o método 'inverte'
//    }
//}
//
//
////Embaralha os elementos na tabela hash
//
//public void embaralha() {
//    Random rand = new Random();
//
//    for (int i = 0; i < capacidade; i++) {
//        ListaSequencial<TabHash.Par> lista = tabela.obtem(i);
//        lista.embaralha();  // Supondo que a classe ListaSequencial tenha o método 'embaralha'
//    }
//}
//
//public void copia(TabHash<K, V> outra) {
//    // Itera sobre todos os buckets da outra tabela
//    for (int i = 0; i < outra.capacidade; i++) {
//        ListaSequencial<TabHash.Par> listaOrigem = outra.tabela.obtem(i);
//
//        // Para cada par na lista, adiciona na tabela atual
//        for (int j = 0; j < listaOrigem.comprimento(); j++) {
//            TabHash.Par par = listaOrigem.obtem(j);
//            this.adiciona(par.obtemChave(), par.obtemValor());
//        }
//    }
//}

