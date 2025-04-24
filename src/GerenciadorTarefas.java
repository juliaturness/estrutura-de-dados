import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GerenciadorTarefas {

    public static void gerencia (String caminho) throws IOException {

        Path path = Paths.get(caminho).toAbsolutePath();
        System.out.println("Verificando arquivo: " + path);

        if (!Files.exists(path)) {
            System.out.println("Erro: Arquivo não encontrado.");
            return;
        }

        BufferedReader reader = Files.newBufferedReader(path);
        List<Queue<String>> filas = new ArrayList<>();

        for(int i =0; i<3; i++){
            filas.add(new LinkedList<>());
        }
         String linha;
        while ((linha = reader.readLine()) != null) {
            String[] parte = linha.split(":");
            int prioridade = Integer.parseInt(parte[0].trim());
            filas.get(prioridade).add(linha);
        }

        for(Queue<String> fila : filas){
            while(!fila.isEmpty()){
                System.out.println(fila.poll());
            }
        }
        reader.close();
    }

    public static void main(String[] args) throws IOException {
       try {
           String arquivo = "src/teste.txt";
           gerencia(arquivo);

       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
