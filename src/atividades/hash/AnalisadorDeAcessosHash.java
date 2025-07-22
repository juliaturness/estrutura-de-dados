package atividades.hash;
import java.io.*;
import java.util.*;
import java.text.*;

public class AnalisadorDeAcessosHash {
        // Classe para armazenar os dados de cada usuário
        static class Usuario {
            int acessos;  // Contador de acessos
            long tempoTotal;  // Tempo total de uso em milissegundos

            Usuario() {
                this.acessos = 0;
                this.tempoTotal = 0;
            }

            // Incrementa número de acessos
            void incrementarAcessos() {
                this.acessos++;
            }

            // Adiciona o tempo de uso em milissegundos
            void adicionarTempo(long tempo) {
                this.tempoTotal += tempo;
            }
        }

        public static void main(String[] args) throws Exception {
            // O arquivo de dados
            String arquivo = "teste.txt";
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;
            HashMap<String, Usuario> usuarios = new HashMap<>();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            String usuarioAtual = null;
            Date inicio = null;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ");
                String dataHora = dados[0] + " " + dados[1];  // Data + Hora
                usuarioAtual = dados[2];
                String tipoAcesso = dados[3];  // INICIO ou FIM

                Date dataAcesso = sdf.parse(dataHora);  // Converte para objeto Date
                long timestamp = dataAcesso.getTime();  // Obtém o timestamp em milissegundos

                // Se o usuário não existir no HashMap, adicionamos ele
                if (!usuarios.containsKey(usuarioAtual)) {
                    usuarios.put(usuarioAtual, new Usuario());
                }

                Usuario usuario = usuarios.get(usuarioAtual);

                if (tipoAcesso.equals("INICIO")) {
                    // Quando inicia o acesso, não faz nada, só registra o horário
                    inicio = dataAcesso;
                    usuario.incrementarAcessos();
                } else if (tipoAcesso.equals("FIM") && inicio != null) {
                    // Quando o acesso termina, calcula a diferença e acumula o tempo
                    long tempoUso = timestamp - inicio.getTime();
                    usuario.adicionarTempo(tempoUso);
                    inicio = null;  // Reseta a data de início para o próximo acesso
                }
            }

            br.close();

            // Variáveis para armazenar o usuário com mais acessos e o com maior tempo total
            String usuarioMaisAcessos = null;
            int maxAcessos = 0;
            String usuarioMaiorTempo = null;
            long maiorTempo = 0;

            // Verifica qual usuário teve mais acessos e qual teve mais tempo de uso
            for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
                String usuario = entry.getKey();
                Usuario u = entry.getValue();

                if (u.acessos > maxAcessos) {
                    maxAcessos = u.acessos;
                    usuarioMaisAcessos = usuario;
                }

                if (u.tempoTotal > maiorTempo) {
                    maiorTempo = u.tempoTotal;
                    usuarioMaiorTempo = usuario;
                }
            }

            // Exibe os resultados
            System.out.println("Usuário que acessou o sistema mais vezes: " + usuarioMaisAcessos);
            System.out.println("Usuário com maior tempo total de uso: " + usuarioMaiorTempo);
        }
    }
