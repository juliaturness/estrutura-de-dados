package atividades.arvore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import esd.APB;
import esd.ListaSequencial;


public class GerenciadorTarefas {

    public static void main(String[] args) {
        APB<String> arvoreTarefas = new APB<String>() {

            public int compara(String tarefa1, String tarefa2) {
                // Comparar apenas pelo nome da tarefa (parte da string)
                return tarefa1.compareTo(tarefa2);
            }
        };

        // Caminho do arquivo de tarefas
        String caminhoArquivo = "tarefas.txt";  // Certifique-se de ter esse arquivo

        // Ler o arquivo e adicionar as tarefas na árvore
        try {
            lerArquivoEAdicionarTarefas(arvoreTarefas, caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Exemplo de consulta
        String tarefaConsulta = "Tarefa1"; // Nome da tarefa para consulta
        String tarefaEncontrada = arvoreTarefas.procura(tarefaConsulta);
        if (tarefaEncontrada != null) {
            System.out.println("Tarefa encontrada: " + tarefaEncontrada);
        } else {
            System.out.println("Tarefa não encontrada.");
        }

        // Listando as tarefas em ordem (por nome)
        ListaSequencial<String> tarefasOrdem = arvoreTarefas.emOrdem();
        System.out.println("Tarefas em ordem alfabética:");
        for (int i = 0; i < tarefasOrdem.comprimento(); i++) {
            String tarefa = tarefasOrdem.obtem(i); // Obtendo o elemento da lista
            System.out.println(tarefa);
        }
    }

    // Função para ler o arquivo e adicionar tarefas à árvore
    public static void lerArquivoEAdicionarTarefas(APB<String> arvoreTarefas, String caminhoArquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;

        while ((linha = br.readLine()) != null) {
            // Dividir a linha em partes (separadas por vírgula)
            String[] partes = linha.split(",");
            if (partes.length == 4) {
                // Criar a string que representa a tarefa
                String nome = partes[0];
                String status = partes[1];
                int prioridade = Integer.parseInt(partes[2]);
                String dataConclusao = partes[3];

                // Concatenar as informações em uma única string
                String tarefaCompleta = nome + "," + status + "," + prioridade + "," + dataConclusao;

                // Adicionar a tarefa completa na árvore
                arvoreTarefas.adiciona(tarefaCompleta);
            }
        }

        br.close();
    }
}

