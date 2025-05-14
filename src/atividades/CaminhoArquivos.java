package atividades;

import java.util.Scanner;
import java.util.Stack;


public class CaminhoArquivos {

    public static String reduz_caminho(String caminho) {
        if (caminho.isEmpty()) {
            return "";
        }
        
        String[] parte = caminho.split("/");
        Stack<String> pilha = new Stack<>();
        
        for (String celula : parte) {
            if (celula.isEmpty() || celula.equals(".")) {
                continue;
            }
            
            if (celula.equals("..")) {
                if (!pilha.isEmpty()) {
                    pilha.pop();
                }
            } else {
                pilha.push(celula);
            }
        }
        
        StringBuilder caminhoReduzido = new StringBuilder();
        
        for (String andar : pilha) {
            caminhoReduzido.append("/").append(andar);
        }
        
        return caminhoReduzido.toString() ;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o caminho a ser reduzido:");
        String caminho = sc.nextLine();
    
        String reduzido = reduz_caminho(caminho);

        System.out.println(reduzido);
        
        
    }
}