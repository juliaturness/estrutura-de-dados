//package atividades;
//
//pubic class teste{
//  import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Registro {
//
//    Scanner arquivo;
//    List<Usuario> usuarios = new ArrayList<>();
//
//    public Registro(String caminho) {
//        arquivo = scan(caminho);
//    }
//
//    private Scanner scan(String caminho) {
//        try {
//            return new Scanner(new FileReader(caminho));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException("Arquivo não encontrado.");
//        }
//    }
//
//    private void read() {
//
//        List<Acesso> listaAcessos = new ArrayList<>();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//
//        while (arquivo.hasNext()) {
//
//            String linha = arquivo.nextLine();
//            if (linha.isEmpty()) continue;
//
//            String[] coluna = linha.split(" ", 4);
//            if (coluna.length != 4) continue;
//
//            String data = coluna[0] + " " + coluna[1];
//            String nome = coluna[2];
//            String tipo = coluna[3];
//
//            long timestamp;
//            try {
//                timestamp = sdf.parse(data).getTime();
//            } catch (ParseException e) {
//                continue;
//            }
//
//            if (tipo.equals("INICIO")) {
//                listaAcessos.add(new Acesso(nome, timestamp));
//                continue;
//            }
//
//            if (listaAcessos.isEmpty()) continue;
//
//            Acesso acesso = (Acesso) find(listaAcessos, nome);
//            long duracao = timestamp - acesso.timestamp;
//
//            Usuario usuario = (Usuario) find(usuarios, acesso.nome);
//            if (usuario == null) {
//                usuario = new Usuario(nome, duracao);
//                usuarios.add(usuario);
//                continue;
//            }
//
//            usuario.logar();
//            usuario.tempo(duracao);
//        }
//    }
//
//    public void printLogs() {
//        usuarios.sort(null);
//        for (int i = 0; i < usuarios.size(); i++) {
//            System.out.println(usuarios.get(i));
//        }
//    }
//
//    public <T extends Nome> Nome find(List<T> lista, String nome) {
//        if (lista.isEmpty()) return null;
//
//        for (int i = 0; i < lista.size(); i++) {
//            T item = lista.get(i);
//            if (item.getNome().equals(nome)) return item;
//        }
//
//        return null;
//    }
//
//    static class Acesso implements Nome {
//        private final String nome;
//        private final long timestamp;
//
//        public Acesso(String nome, long timestamp) {
//            this.nome = nome;
//            this.timestamp = timestamp;
//        }
//
//        @Override
//        public String getNome() {
//            return nome;
//        }
//    }
//
//    static class Usuario implements Nome, Comparable<Usuario> {
//
//        private final String nome;
//        private int freq;
//        private long tempo;
//
//        public Usuario(String nome, long tempo) {
//            this.nome = nome;
//            this.freq = 1;
//            this.tempo = tempo;
//        }
//
//        public void setTempo(long tempo) {
//            this.tempo = tempo;
//        }
//
//        public void setFreq(int freq) {
//            this.freq = freq;
//        }
//
//        @Override
//        public String getNome() {
//            return nome;
//        }
//
//        public void logar() {
//            freq++;
//        }
//
//        public void tempo(long mili) {
//            tempo += mili;
//        }
//
//        @Override
//        public String toString() {
//            String uppercase = String.valueOf(nome.charAt(0)).toUpperCase() + nome.substring(1) ;
//            if (freq == 1) {
//                return uppercase + " logou " + freq + " vez, durante " +  (tempo / (1000 * 60)) + " minutos.";
//            }
//            return uppercase + " logou " + freq + " vezes, durante " +  (tempo / (1000 * 60)) + " minutos.";
//        }
//
//        @Override
//        public int compareTo(Usuario o) {
//            return this.nome.compareTo(o.nome);
//        }
//    }
//
//    interface Nome {
//        String getNome();
//    }
//
//
//    public static void main(String[] args) {
//
//        if (args.length != 1)
//            throw new RuntimeException("Uso: java Registro <arquivo>");
//
//        Registro r = new Registro(args[0]);
//        r.read();
//        r.printLogs();
//    }
//
//}
//}
