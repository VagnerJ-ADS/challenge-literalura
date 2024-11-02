package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Gutendex;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.AutorService;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import br.com.alura.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    @Autowired
    private LivroService livroService;

    @Autowired
    private AutorService autorService;

    private final String ENDERECO = "https://gutendex.com/books/?search=";

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    ----------------------------
                    Escolha o número de sua opção:
                    1- buscar livro pelo título
                    2- listar livros registrados
                    3- listar autores registrados
                    4- listar autores vivos em um determinado ano
                    5- listar livros em um determinado idioma
                    0- sair                                 
                    ----------------------------
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroApi();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosNoAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public void buscarLivroApi() {
        System.out.println("Insira o nome do livro que você deseja procurar:");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));

        Gutendex dados = conversor.obterDados(json, Gutendex.class);

        if (dados.results() != null && !dados.results().isEmpty()) {
            for (DadosLivro livro : dados.results()) {
                System.out.println(livro);

                if (livroService.buscarPorTitulo(livro.título()).isEmpty()) {
                    livroService.salvarLivro(livro);
                } else {
                    System.out.println("O livro '" + livro.título() + "' já está registrado no banco de dados.");
                }
            }
        } else {
            System.out.println("Nenhum resultado encontrado.");
        }
    }

    public void listarLivrosRegistrados() {
        List<String> livrosFormatados = livroService.listarLivros();
        if (!livrosFormatados.isEmpty()) {
            for (String livro : livrosFormatados) {
                System.out.println(livro);
            }
        } else {
            System.out.println("Nenhum livro registrado.");
        }
    }

    public void listarAutoresRegistrados() {
        List<String> autoresFormatados = autorService.listarAutores();
        if (!autoresFormatados.isEmpty()) {
            for (String autor : autoresFormatados) {
                System.out.println(autor);
            }
        } else {
            System.out.println("Nenhum autor registrado.");
        }
    }

    public void listarAutoresVivosNoAno() {
        System.out.println("Insira o ano que deseja pesquisar");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<String> autoresVivos = autorService.listarAutoresVivosNoAno(ano);
        if (!autoresVivos.isEmpty()) {
            for (String autor : autoresVivos) {
                System.out.println(autor);
            }
        } else {
            System.out.println("Nenhum autor estava vivo no ano especificado.");
        }
    }


    public void listarLivrosPorIdioma() {
        System.out.println("Insira o idioma para realizar a busca:");
        System.out.println("es- espanhol");
        System.out.println("en- inglês");
        System.out.println("fr- francês");
        System.out.println("pt- português");

        String idiomaSelecionado = leitura.nextLine();


        List<Livro> livros = livroService.buscarLivrosPorIdioma(idiomaSelecionado);
        if (!livros.isEmpty()) {
            for (Livro livro : livros) {
                System.out.println("-------------- Livro ----------------");
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autores: " + livro.getAutores().stream()
                        .map(autor -> autor.getNome()) // Formata os nomes dos autores
                        .reduce((a, b) -> a + ", " + b) // Junta os nomes em uma string
                        .orElse("Nenhum autor encontrado")); // Caso não haja autor
                System.out.println("Idioma: " + livro.getIdioma());
                System.out.println("Número de Downloads: " + livro.getNumeroDownloads());
                System.out.println("--------------------------------------");
            }
        } else {
            System.out.println("Não existem livros nesse idioma no banco de dados.");
        }
    }
}
