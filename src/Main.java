import esd.TabHash;

public class Main {

    public static void main(String[] args) {
        // Cria duas tabelas hash
        TabHash<Integer, String> tab1 = new TabHash<>();
        TabHash<Integer, String> tab2 = new TabHash<>();

        // Adiciona alguns elementos à tab1
        tab1.adiciona(1, "um");
        tab1.adiciona(2, "dois");
        tab1.adiciona(3, "três");

        // Exibe o conteúdo da tab1
        System.out.println("Conteúdo de tab1 antes da cópia:");
        System.out.println(tab1);

        // Adiciona alguns elementos à tab2
        tab2.adiciona(4, "quatro");
        tab2.adiciona(5, "cinco");

        // Exibe o conteúdo da tab2
        System.out.println("Conteúdo de tab2 antes da cópia:");
        System.out.println(tab2);

        // Copia o conteúdo de tab2 para tab1
        tab1.copia(tab2);

        // Exibe o conteúdo de tab1 após a cópia
        System.out.println("\nConteúdo de tab1 após a cópia:");
        System.out.println(tab1);

        // Verifica se tab1 contém os elementos de tab2
        System.out.println("\nVerificando se tab1 contém os elementos de tab2:");
        System.out.println("Contém 4? " + tab1.contem(4));
        System.out.println("Contém 5? " + tab1.contem(5));
    }
}
