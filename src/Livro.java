import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String categoria;
    private boolean disponivel;

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public Livro(int id, String titulo, String autor, int anoPublicacao, String categoria, boolean disponivel) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.categoria = categoria.toLowerCase();
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + ", " + this.getTitulo() + ", Autor: " + this.getAutor() + ", Ano publicado: " + this.getAnoPublicacao();
    }

    public String toCSV(){
        return  id + ";" + titulo + ";" + autor + ";" + anoPublicacao + ";" + categoria + ";" + disponivel;
    }

    public static Livro fromCSV(String linha){
        String[] dados = linha.split(";");

        return new Livro(
                Integer.parseInt(dados[0]),
                dados[1],
                dados[2],
                Integer.parseInt(dados[3]),
                dados[4],
                Boolean.parseBoolean(dados[5])
        );
    }

    public static List<Livro> carregarLivros(){
        List<Livro> livros = new ArrayList<>();

        try(Scanner sc = new Scanner(new File("livros.txt"))){
            while(sc.hasNextLine()){
                String linha = sc.nextLine();
                livros.add(fromCSV(linha));
            }
        } catch (Exception e){
            System.out.println("Nenhum arquivo encontrado ou lista vazia");
        }
        return livros;
    }

    public static void salvarLivro(List<Livro> livros) {
        try(PrintWriter out = new PrintWriter(new File("livros.txt"))){
            for(Livro livro : livros){
                out.println(livro.toCSV());
            }
        } catch (Exception e){
            System.out.println("Erro ao salvar o livro");
        }
    }

    public void emprestar(){
        if(!this.disponivel){
            System.out.println("O livro já foi emprestado!");
        } else{
            this.disponivel = false;
            System.out.println("Livro emprestado com sucesso!");
        }
    }

    public void devolver(){
        if(this.disponivel){
            System.out.println("O livro já está disponível!");
        } else {
            this.disponivel = true;
            System.out.println("Livro devolvido com sucesso!");
        }
    }

    public int compareTo(Livro outro){
        return this.titulo.compareTo(outro.getTitulo());
    }
}

