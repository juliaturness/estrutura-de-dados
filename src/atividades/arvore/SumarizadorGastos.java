package atividades.arvore;

import esd.APB;
import esd.ListaSequencial;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class SumarizadorGastos {

    public class Compra {
        public Date data;
        public double valor;

        public Compra(Date data, double valor) {
            this.data = data;
            this.valor = valor;
        }
    }


    public class Cliente implements Comparable<Cliente> {
        public String nome;
        public ListaSequencial<Compra> compras = new ListaSequencial<>();

        public Cliente(String nome) {
            this.nome = nome;
        }

        public void adicionarCompra(Date data, double valor) {
            compras.adiciona(new Compra(data, valor));
        }

        public double totalPorMes(int mes) {
            double total = 0;

            for (int i = 0; i < compras.comprimento(); i++) {
                Compra c = compras.obtem(i);
                if (c.data.getMonth() + 1 == mes) // --> retorna [0–11]
                    total += c.valor;
            }

            return total;
        }

        @Override
        public int compareTo(Cliente outro) {
            return this.nome.compareTo(outro.nome);
        }
    }

    private Scanner arquivo;
    private APB<Cliente> clientes;

    public SumarizadorGastos(String caminho) {
        arquivo = scan(caminho);
        clientes = new APB<>();
    }

    private Scanner scan(String caminho) {
        try {
            return new Scanner(new FileReader(caminho));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Não foi possível ler arquivo no caminho: " + caminho);
        }
    }

    public void read() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        while (arquivo.hasNext()) {

            String linha = arquivo.nextLine();
            if (linha.trim().isEmpty()) continue;

            String[] colunas = linha.split(" ", 3);
            if (colunas.length < 3) continue;

            String data = colunas[0];
            double valor = Double.parseDouble(colunas[1]);
            String nome = colunas[2];

            Date timestamp;
            try {
                timestamp = sdf.parse(data);
            } catch (Exception e) {
                continue;
            }

            Cliente aux = new Cliente(nome);
            Cliente cliente = clientes.procura(aux);

            if (cliente == null) {
                cliente = aux;
                cliente.adicionarCompra(timestamp, valor);
                clientes.adiciona(cliente);
                continue;
            }

            cliente.adicionarCompra(timestamp, valor);

        }

    }

    public void consulta() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine().trim();
        System.out.print("Digite o número do mês (ex: 10): ");
        int mes = scanner.nextInt();

        Cliente consulta = clientes.procura(new Cliente(nomeCliente));

        if (consulta == null) {
            System.out.println("Cliente não encontrado.");
        } else {
            double total = consulta.totalPorMes(mes);
            System.out.printf("Total gasto por %s no mês %02d: R$ %.2f\n", nomeCliente, mes, total);
        }
    }

    public static void main(String[] args) {

        if (args.length != 1)
            throw new RuntimeException("<SumarizadorGastos> Requer 1 argumento.");

        SumarizadorGastos sg = new SumarizadorGastos(args[0]);
        sg.read();
        sg.consulta();

    }

}
