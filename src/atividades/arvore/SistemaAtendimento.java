//package atividades.arvore;
//
//import esd.APB;
//
//import java.util.Scanner;
//
//public class SistemaAtendimento {
//
//    public static void main(String[] args) {
//        APB<Cliente> filaDeAtendimento = new APB<>();
//        int contadorClientes = 0;
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.print("> ");
//            String comando = scanner.nextLine().trim();
//
//            if (comando.equals("?")) {
//                Cliente cliente = removerCliente(filaDeAtendimento);
//                if (cliente != null) {
//                    System.out.println("Atendendo cliente: " + cliente.getSenha());
//                } else {
//                    System.out.println("Não há clientes na fila.");
//                }
//            } else if (comando.equals("sair")) {
//                System.out.println("Programa terminado.");
//                break;
//            } else if (comando.matches("[A-F]")) {
//                contadorClientes++;
//                String senha = comando + String.format("%03d", contadorClientes);
//                Cliente novoCliente = new Cliente(senha, comando.charAt(0), contadorClientes);
//
//                filaDeAtendimento.adiciona(novoCliente);
//                int clientesNaFrente = contarClientesNaFrente(filaDeAtendimento, novoCliente);
//
//                System.out.println("Senha gerada: " + senha + ". Clientes na fila: " + clientesNaFrente);
//            }
//        }
//        scanner.close();
//    }
//
//    // Remove e retorna o próximo cliente a ser atendido
//    private static Cliente removerCliente(APB<Cliente> filaDeAtendimento) {
//        // Aqui estamos pegando o primeiro cliente (raiz da árvore) e removendo ele
//        return filaDeAtendimento.remove(filaDeAtendimento.obtem_raiz());
//    }
//
//    // Conta quantos clientes estão na frente de um determinado cliente
//    private static int contarClientesNaFrente(APB<Cliente> filaDeAtendimento, Cliente cliente) {
//        return filaDeAtendimento.tamanho() - filaDeAtendimento.emOrdem().comprimento() + 1;
//    }
//}
//
//class Cliente implements Comparable<Cliente> {
//    private String senha;
//    private char classe;
//    private int numeroCliente;
//
//    public Cliente(String senha, char classe, int numeroCliente) {
//        this.senha = senha;
//        this.classe = classe;
//        this.numeroCliente = numeroCliente;
//    }
//
//    public String getSenha() {
//        return senha;
//    }
//
//    public int getNumeroCliente() {
//        return numeroCliente;
//    }
//
//    @Override
//    public int compareTo(Cliente outro) {
//        int prioridade = this.classe - outro.classe;
//        if (prioridade == 0) {
//            return Integer.compare(this.numeroCliente, outro.numeroCliente);
//        }
//        return prioridade;
//    }
//
//    @Override
//    public String toString() {
//        return senha;
//    }
//}
