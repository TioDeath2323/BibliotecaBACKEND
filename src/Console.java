import java.io.File;
import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Pattern;

public class Console  {


    public static void main(String[] args) {

        List<Livro> livros = Livro.carregarLivros();

                 String regexTexto = "^[A-Za-zÀ-ÿ\\s]+$";
                 String regexAutor = "^[A-Za-zÀ-ÿ]+(\\s[A-Za-zÀ-ÿ]+)+$";
                 String regexCategoria = "^[A-Za-zÀ-ÿ\\s]{1,20}$";

        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("""
                    1 - Cadastrar Livro
                    2 - Listar Todos os Livros
                    3 - Listar Livros Disponíveis
                    4 - Buscar por Título
                    5 - Buscar por Categoria
                    6 - Emprestar Livro
                    7 - Devolver Livro
                    8 - Remover Livro
                    0 - Sair                    
                    \n """);

             int opcao = sc.nextInt();
             sc.nextLine();

             if (opcao == 1) {

                 int idT = livros.size() + 1;

                 System.out.println("Título do Livro: ");
                 String tituloT = sc.nextLine();
                 while (!tituloT.matches(regexTexto)) {
                     System.out.println("O título está invalido, digite novamente.");
                     tituloT = sc.nextLine();
                 }

                 System.out.println("Autor: ");
                 String autorT = sc.nextLine();

                 while (!autorT.matches(regexAutor)) {
                     System.out.println("O nome ou sobrenome está invalido, digite novamente.");
                     autorT = sc.nextLine();
                 }

                 System.out.println("Ano de publicação: ");
                 int anoPublicacaoT = sc.nextInt();
                 sc.nextLine();

                 System.out.println("Categoria: ");
                 String categoriaT = sc.nextLine();

                 while (!categoriaT.matches(regexCategoria)) {
                     System.out.println("Categoria inválida, digite novamente.");
                     categoriaT = sc.nextLine();
                 }

                 Livro livrosCadastrados = new Livro(idT, tituloT, autorT, anoPublicacaoT, categoriaT, true);
                 livros.add(livrosCadastrados);
                 Livro.salvarLivro(livros);
             }
             if (opcao == 2) {
                 for (Livro livro : livros) {
                     System.out.println(livro + "\n");
                 }
             }
             if (opcao == 3) {
                 livros.sort(Comparator.comparing(Livro::getTitulo));
                 for (Livro livro : livros) {
                     if (livro.isDisponivel()) {
                         System.out.println("\n Disponível:");
                         System.out.println("\n" + livro.toString() + "\n");
                     }
                 }
             }
             if (opcao == 4) {

                 System.out.println("Digite o nome do livro: ");
                 String nome = sc.nextLine();

                 livros.sort(Comparator.comparing(Livro::getTitulo));
                 for (Livro livro : livros) {
                     if (livro.getTitulo().toLowerCase().contains(nome.toLowerCase())) {
                         System.out.println("\n" + livro + "\n");
                     }
                 }
             }

             if (opcao == 5) {

                 System.out.println("Qual categoria deseja buscar?");
                 String cSelec = sc.nextLine();

                 for (Livro livro : livros) {
                     if (livro.getCategoria().toLowerCase().contains(cSelec.toLowerCase())) {
                         System.out.println("\n" + livro.getTitulo() + "\n");
                     }
                 }
             }

            if (opcao == 6) {

                System.out.println("Digite o nome do livro: ");
                String nome = sc.nextLine();
                for (Livro livro : livros) {
                    if (livro.getTitulo().contains(nome.toLowerCase())) {
                        livro.emprestar();
                        Livro.salvarLivro(livros);
                    }
                }
            }

            if (opcao == 7) {

                System.out.println("Digite o nome do livro: ");
                String nome = sc.nextLine();
                for (Livro livro : livros) {
                    if (livro.getTitulo().contains(nome.toLowerCase())) {
                        livro.isDisponivel();
                        livro.devolver();
                        Livro.salvarLivro(livros);
                    }
                }

            }
            if (opcao == 8) {

                System.out.println("Digite o nome do livro: ");
                String nome = sc.nextLine();
                livros.removeIf(livro -> livro.getTitulo().equalsIgnoreCase(nome));
                Livro.salvarLivro(livros);
            }
            if (opcao == 0) {
                break;
            }

        }
        sc.close();

             }
        }




