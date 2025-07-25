package atividades.hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import esd.Conjunto;

public class ProcessamentoTarefasConjunto {

    public static void main(String[] args) {
        // Criando o conjunto para armazenar tarefas concluídas
        Conjunto<String> tarefasConcluidas = new Conjunto<>();

        try (BufferedReader br = new BufferedReader(new FileReader("tarefas.txt"))) {
            String linha;

            // Lendo cada linha do arquivo e processando
            while ((linha = br.readLine()) != null) {
                // Divide a linha pelo separador vírgula
                String[] dados = linha.split(", ");

                String nomeTarefa = dados[0];
                String status = dados[1];

                // Se a tarefa estiver concluída, adiciona no conjunto
                if (status.equals("concluída")) {
                    tarefasConcluidas.adiciona(nomeTarefa);
                }
            }

            // Exemplo de consulta: Verificando se uma tarefa foi concluída
            String nomeBusca = "Tarefa1";
            if (tarefasConcluidas.contem(nomeBusca)) {
                System.out.println(nomeBusca + " já foi concluída.");
            } else {
                System.out.println(nomeBusca + " ainda não foi concluída.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
