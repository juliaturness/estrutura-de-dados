package atividades.hash;

import java.util.*;

public class SistemaDeAtendimento {

    // Classe Cliente para armazenar a informação de cada cliente
    static class Cliente implements Comparable<Cliente> {
        String classe; // Classe de prioridade (A, B, C, etc.)
        String senha;  // Senha gerada
        int contador;   // Contador para o número de clientes à frente
        int ordemChegada; // Ordem de chegada do cliente

        public Cliente(String classe, String senha, int contador, int ordemChegada) {
            this.classe = classe;
            this.senha = senha;
            this.contador = contador;
            this.ordemChegada = ordemChegada;
        }

        // Método de comparação para garantir a ordem de atendimento (prioridade e ordem de chegada)
        @Override
        public int compareTo(Cliente outro) {
            // Primeiramente compara pela classe (A = maior prioridade)
            if (!this.classe.equals(outro.classe)) {
                return this.classe.compareTo(outro.classe); // "A" < "B" < "C" < ... < "F"
            }
            // Se as classes forem iguais, compara pela ordem de chegada
            return Integer.compare(this.ordemChegada, outro.ordemChegada);
        }

        @Override
        public String toString() {
            return this.senha + " " + this.contador;
        }
    }

    // Fila de prioridade (min-heap), vai armazenar os clientes
    private static PriorityQueue<Cliente> filaDeAtendimento = new PriorityQueue<>();

    // Contador global para a quantidade de clientes
    private static int contadorClientes = 0;

    // Para manter o controle da ordem de chegada
    private static int ordemChegada = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(">");
            String comando = scanner.nextLine().trim();

            if (comando.equals("?")) {
                // Se houver cliente na fila, mostra a senha do próximo e remove da fila
                if (!filaDeAtendimento.isEmpty()) {
                    Cliente cliente = filaDeAtendimento.poll();
                    System.out.println("Próximo cliente: " + cliente);
                } else {
                    System.out.println("Não há clientes na fila.");
                }
            } else if (comando.equals("sair")) {
                // Se o comando for "sair", encerra o programa
                System.out.println("Sistema encerrado.");
                break;
            } else if (comando.length() == 1 && comando.charAt(0) >= 'A' && comando.charAt(0) <= 'F') {
                // Se a letra for de A a F, gera uma nova senha para o cliente
                String classe = comando.toUpperCase();
                String senha = classe + String.format("%03d", ++contadorClientes);
                int clientesNaFrente = filaDeAtendimento.size();

                // Cria um cliente e o adiciona à fila
                Cliente cliente = new Cliente(classe, senha, clientesNaFrente, ordemChegada++);
                filaDeAtendimento.add(cliente);

                // Exibe a senha gerada e quantos clientes estão na frente
                System.out.println("Senha gerada: " + senha + " - Clientes na frente: " + clientesNaFrente);
            } else {
                System.out.println("Comando inválido.");
            }
        }

        scanner.close();
    }
}

