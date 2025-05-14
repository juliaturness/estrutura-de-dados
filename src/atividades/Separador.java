package atividades;

import esd.Deque;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Separador {

    public static void separa (String caminho) throws IOException {
        Deque<String> maiusculas = new Deque<>();
        Deque<String> minusculas = new Deque<>();


        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] espacos = linha.split("\\s+");

                for (String palavra : espacos) {
                    if(Character.isUpperCase(palavra.charAt(0))) {
                        maiusculas.adiciona(palavra);
                    } else {
                        minusculas.adiciona(palavra);
                    }
                }
            }
        }
        while (!maiusculas.esta_vazia()){
        System.out.print(maiusculas.extrai_inicio() + " ");}
        System.out.println();
        while (!minusculas.esta_vazia()){
        System.out.print(minusculas.extrai_inicio() + " ");
        }
        System.out.println();

    }
    public static void main(String[] args) throws IOException {
        try {
            String arquivo = "src/teste.txt";
            separa(arquivo);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
