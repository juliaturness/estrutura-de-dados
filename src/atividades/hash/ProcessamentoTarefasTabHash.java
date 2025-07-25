package atividades.hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import esd.TabHash;

public class ProcessamentoTarefasTabHash {

    public static void main(String[] args) {
        // Criando a TabHash para armazenar as tarefas
        TabHash<String, String[]> tarefas = new TabHash<>();

        try (BufferedReader br = new BufferedReader(new FileReader("tarefas.txt"))) {
            String linha;

            // Lendo cada linha do arquivo e processando
            while ((linha = br.readLine()) != null) {
                // Divide a linha pelo separador vírgula
                String[] dados = linha.split(", ");

                String nomeTarefa = dados[0];
                String status = dados[1];
                String dataConclusao = dados[2];
                int prioridade = Integer.parseInt(dados[3]);

                // Cria um array com os dados da tarefa (status, data de conclusão, prioridade)
                String[] tarefa = new String[3];
                tarefa[0] = status;
                tarefa[1] = dataConclusao;
                tarefa[2] = String.valueOf(prioridade);

                // Adiciona a tarefa na TabHash com o nome como chave
                tarefas.adiciona(nomeTarefa, tarefa);
            }

            // Exemplo de consulta: Verificando o status de uma tarefa
            String nomeBusca = "Tarefa1";
            if (tarefas.contem(nomeBusca)) {
                String[] tarefa = tarefas.obtem(nomeBusca);
                System.out.println("Status de " + nomeBusca + ": " + tarefa[0]);
                System.out.println("Data de conclusão: " + tarefa[1]);
                System.out.println("Prioridade: " + tarefa[2]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
