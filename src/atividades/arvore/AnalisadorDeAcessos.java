package atividades.arvore;

import esd.APB;
import esd.ListaSequencial;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AnalisadorDeAcessos {

    Scanner arquivo;
    APB<Registro> usuarios = new APB<>();

    public AnalisadorDeAcessos(String caminho) {
        arquivo = scan(caminho);
    }

    private Scanner scan(String caminho) {
        try {
            return new Scanner(new FileReader(caminho));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo não encontrado.");
        }
    }

    private void read() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        while (arquivo.hasNext()) {

            String linha = arquivo.nextLine();
            if (linha.isEmpty()) continue;

            String[] coluna = linha.split(" ", 4);
            if (coluna.length != 4) continue;

            String data = coluna[0] + " " + coluna[1];
            String nome = coluna[2];
            String tipo = coluna[3];

            Date timestamp;
            try {
                // Exception exige que a linha de código seja protegida por um try catch
                timestamp = sdf.parse(data);
            } catch (ParseException e) {
                continue;
            }

            Registro aux = new Registro(nome);
            Registro registro = usuarios.procura(aux);

            if (registro == null) {
                registro = aux;
                usuarios.adiciona(registro);
            }

            if (tipo.equals("INICIO")) {
                registro.inicioAtual = timestamp;
                continue;
            }

            if (tipo.equals("FIM") && registro.inicioAtual != null) {
                long duracao = timestamp.getTime() - registro.inicioAtual.getTime();
                registro.tempoTotal += duracao;
                registro.acessos++;
                registro.inicioAtual = null;
            }
        }
    }

    public void printLogs() {
        String usuarioMaisAcessos = null;
        String usuarioMaiorTempo = null;
        int maxAcessos = 0;
        long maxTempo = 0;

        ListaSequencial<Registro> lista = usuarios.emOrdem();

        for (int i = 0; i < lista.comprimento(); i++) {
            Registro registro = lista.obtem(i);

            if (registro.acessos > maxAcessos) {
                maxAcessos = registro.acessos;
                usuarioMaisAcessos = registro.nome;
            }

            if (registro.tempoTotal > maxTempo) {
                maxTempo = registro.tempoTotal;
                usuarioMaiorTempo = registro.nome;
            }
        }

        System.out.println("Usuário com mais acessos: " + usuarioMaisAcessos + " (" + maxAcessos + " vezes)");
        System.out.println("Usuário com maior tempo de uso: " + usuarioMaiorTempo + " (" + (maxTempo / 1000) + " segundos)");

    }

    static class Registro implements Comparable<Registro> {
        public String nome;
        public int acessos = 0;
        public long tempoTotal = 0;
        public Date inicioAtual = null;

        public Registro(String nome) { this.nome = nome; }

        @Override
        public int compareTo(Registro outro) { return this.nome.compareTo(outro.nome); }
    }

    public static void main(String[] args) {

        if (args.length != 1)
            throw new RuntimeException("Uso: java Registro <arquivo>");

        AnalisadorDeAcessos aa = new AnalisadorDeAcessos(args[0]);
        aa.read();
        aa.printLogs();
    }

}
