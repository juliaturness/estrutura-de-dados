package atividades.arvore;

import esd.APB;
import esd.ListaSequencial;
import java.util.Scanner;

public class FilaAtendimentoComPrioridades {

    private static int contadorGlobal = 0;
    private static int contadorChegada = 0;
    private static APB<Cliente> filaPrioritaria = new APB<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String comando = scanner.nextLine();

            if (comando.equals("sair")) {
                break;
            } else if (comando.equals("?")) {
                Cliente cliente = atenderProximoCliente();
                if (cliente != null) {
                    System.out.println("Cliente atendido: " + cliente);
                } else {
                    System.out.println("Nenhum cliente na fila.");
                }
            } else if (comando.matches("[A-F]")) {
                gerarSenhaCliente(comando.charAt(0));
            } else {
                System.out.println("Comando inválido!");
            }
        }

        scanner.close();
    }

    // Método para gerar senha para o cliente
    private static void gerarSenhaCliente(char classe) {
        int id = contadorGlobal++;
        Cliente novoCliente = new Cliente(classe, id, contadorChegada++);

        // Adiciona o cliente à fila
        filaPrioritaria.adiciona(novoCliente);

        // Exibe a senha e quantos clientes estão à frente
        int posicao = getPosicaoNaFila(novoCliente);
        System.out.println("Senha gerada: " + novoCliente + " | " + posicao + " clientes à sua frente.");
    }

    // Método para obter a posição na fila
    private static int getPosicaoNaFila(Cliente cliente) {
        int posicao = 0;
        ListaSequencial<Cliente> lista = filaPrioritaria.emOrdem(); // Método que retorna a lista em ordem

        // Iterando sobre a lista para encontrar a posição do cliente
        for (int i = 0; i < lista.comprimento(); i++) {
            Cliente c = lista.obtem(i);
            if (c.equals(cliente)) {
                posicao = i; // Atualiza a posição quando encontrar o cliente
                break;
            }
        }
        return posicao;
    }





    // Método para atender o próximo cliente
    private static Cliente atenderProximoCliente() {
        if (filaPrioritaria.esta_vazia()) {
            return null;
        }

        // Remove e retorna o próximo cliente da fila
        Cliente proximoCliente = (Cliente) filaPrioritaria.remove(filaPrioritaria.obtem_raiz());
        return proximoCliente;
    }

}

class Cliente implements Comparable<Cliente> {
    char classe;  // Classe A, B, C, D, E, F
    int id;       // Identificador único global do cliente (número da senha)
    int chegada;  // Ordem de chegada ao banco (usada em caso de empate)

    Cliente(char classe, int id, int chegada) {
        this.classe = classe;
        this.id = id;
        this.chegada = chegada;
    }

    @Override
    public int compareTo(Cliente outro) {
        // Priorização: Classe A > B > C > ... > F
        if (this.classe != outro.classe) {
            return Character.compare(this.classe, outro.classe);
        }
        // Caso de empate: ordem de chegada
        return Integer.compare(this.chegada, outro.chegada);
    }

    @Override
    public String toString() {
        return String.format("%c%03d", classe, id);
    }
}
