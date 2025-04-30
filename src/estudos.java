import esd.Deque;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class estudos {


    public static void main(String[] args) {
        String Arquivo = args[0];
        File arquivo = new File(Arquivo);

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            Deque<String> A = new Deque<>();
            Deque<String> B = new Deque<>();

            String linha;

            while ((linha = br.readLine())!= null) {
                if (linha.isEmpty()) continue;


                String[] palavras = linha.split("\\s+");
                for (String palavra : palavras) {
                    if (palavra.isEmpty()) continue;
                    if (terminaComVogal((palavra))){
                    A.adiciona(palavra);
                     } else if (!terminaComVogal(palavra)){
                    B.adiciona(palavra);
                     }
                }
            }



            while (!A.esta_vazia()){
                System.out.print(A.extrai_inicio() + " ");
            }
            System.out.println();
            while (!B.esta_vazia()){
                System.out.print(B.extrai_inicio() + " ");
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println( "invalido");
        }

    }
    public static boolean terminaComVogal(String palavra) {
        if (palavra.isEmpty()) return false;

        char ultimaLetra = palavra.charAt(palavra.length() - 1);
        return "aeiou".indexOf(ultimaLetra) >= 0;
    }
}

