package com.Cinema_dti_java.CRUD_SQLite;

import com.Cinema_dti_java.CRUD_SQLite.entity.Movie;
import com.Cinema_dti_java.CRUD_SQLite.service.MovieService;
import com.Cinema_dti_java.CRUD_SQLite.service.LogControlService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CrudCinemaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CrudCinemaApplication.class, args);

		MovieService service = context.getBean(MovieService.class);
		LogControlService logControl = context.getBean(LogControlService.class);

		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;

		aguardarEnter(scanner);
		limparConsole();

		while (continuar) {
			System.out.println("||================================||");
			System.out.println("||        SISTEMA DE CINEMA       ||");
			System.out.println("||================================||");

			System.out.println("1 - Cadastrar Filme");
			System.out.println("2 - Listar Todos os Filmes");
			System.out.println("3 - Buscar por Nome");
			System.out.println("4 - Buscar por Diretor");
			System.out.println("5 - Buscar por ID");
			System.out.println("6 - Atualizar Filme");
			System.out.println("7 - Deletar Filme");
			System.out.println("8 - Estatísticas");
			System.out.println("9 - " + (logControl.isDebugEnabled() ? " Desativar" : " Ativar") + " Log SQL");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");

			int opcao = scanner.nextInt();
			scanner.nextLine();

			limparConsole();

			switch (opcao) {
				case 1 -> cadastrarFilme(service, scanner);
				case 2 -> listarFilmes(service);
				case 3 -> buscarPorNome(service, scanner);
				case 4 -> buscarPorDiretor(service, scanner);
				case 5 -> buscarPorId(service, scanner);
				case 6 -> atualizarFilme(service, scanner);
				case 7 -> deletarFilme(service, scanner);
				case 8 -> exibirEstatisticas(service);
				case 9 -> {
					if (logControl.isDebugEnabled()) {
						logControl.disableDebug();
					} else {
						logControl.enableDebug();
					}
				}
				case 0 -> {
					continuar = false;
					System.out.println(" Sistema encerrado. Até logo!");
				}
				default -> System.out.println(" Opção inválida! Tente novamente.");
			}

			if (continuar){
				aguardarEnter(scanner);
				limparConsole();
			}
		}

		scanner.close();
		context.close();
	}

	private static void cadastrarFilme(MovieService service, Scanner scanner) {
		System.out.println("||================================||");
		System.out.println("||          CADASTRAR FILME       ||");
		System.out.println("||================================||");

		System.out.print("Nome do Filme: ");
		String nome = scanner.nextLine();
		scanner.nextLine();

		System.out.print("Nome do Diretor: ");
		String nomeDiretor = scanner.nextLine();

		System.out.print("Data de Lançamento (AAAA-MM-DD): ");
		String dataString = scanner.nextLine();
		LocalDate anoDoFilme = LocalDate.parse(dataString);

		System.out.print("Duração (em minutos): ");
		int duracao = scanner.nextInt();
		scanner.nextLine();

		Movie movie = new Movie(nome, nomeDiretor, anoDoFilme, duracao);

		try {
			var result = service.criarFilme(movie);
			System.out.println(" Filme '" + result.getNameMovie() + "' cadastrado com sucesso!");
		} catch (RuntimeException e) {
			System.out.println(" Erro: " + e.getMessage());
		}
	}

	private static void listarFilmes(MovieService service) {

		System.out.println("||================================||");
		System.out.println("||        FILMES CADASTRADOS      ||");
		System.out.println("||================================||");

		var filmes = service.BuscarTodosFilmes();

		if (filmes.isEmpty()) {
			System.out.println(" Nenhum filme cadastrado ainda.");
		} else {
			filmes.forEach(filme -> {
				System.out.println("    ID: " + filme.getIdMovie());
				System.out.println("    Nome: " + filme.getNameMovie());
				System.out.println("    Diretor: " + filme.getDirector());
				System.out.println("    Lançamento: " + filme.getLaunchYear());
				System.out.println("    Duração: " + filme.getDuration() + " min");
				System.out.println();
			});
		}
	}

	private static void buscarPorNome(MovieService service, Scanner scanner) {

		System.out.println("||================================||");
		System.out.println("||          BUSCAR POR NOME       ||");
		System.out.println("||================================||");

		System.out.print("Digite o nome do filme: ");
		String nome = scanner.nextLine();

		List<Movie> filmes = service.BuscarPorNome(nome);

		if (filmes.isEmpty()) {
			System.out.println(" Nenhum filme encontrado com o nome: " + nome);
		} else {
			System.out.println(" Filmes encontrados:");
			filmes.forEach(filme -> {
				System.out.println("    ID: " + filme.getIdMovie() + " | " + filme.getNameMovie());
				System.out.println("    Diretor: " + filme.getDirector());
				System.out.println("    Lançamento: " + filme.getLaunchYear());
				System.out.println("    Duração: " + filme.getDuration() + " min");
			});
		}
	}

	private static void buscarPorDiretor(MovieService service, Scanner scanner) {
		System.out.println("||================================||");
		System.out.println("||        BUSCAR POR DIRETOR      ||");
		System.out.println("||================================||");

		System.out.print("Digite o nome do diretor: ");
		String diretor = scanner.nextLine();

		List<Movie> filmes = service.BuscarPorDiretor(diretor);

		if (filmes.isEmpty()) {
			System.out.println(" Nenhum filme encontrado do diretor: " + diretor);
		} else {
			System.out.println(" Filmes do diretor " + diretor + ":");
			filmes.forEach(filme -> {
				System.out.println("  ID: " + filme.getIdMovie() + " | " + filme.getNameMovie());
				System.out.println("     Lançamento: " + filme.getLaunchYear());
				System.out.println("     Duração: " + filme.getDuration() + " min");
			});
		}
	}

	private static void buscarPorId(MovieService service, Scanner scanner) {

		System.out.println("||================================||");
		System.out.println("||          BUSCAR POR ID         ||");
		System.out.println("||================================||");

		System.out.print("Digite o ID do filme: ");
		Long id = scanner.nextLong();
		scanner.nextLine();

		try {
			Movie filme = service.BuscarPorId(id);
			if (filme != null) {
				System.out.println(" Filme encontrado:");
				System.out.println( filme.getNameMovie());
				System.out.println("    Diretor: " + filme.getDirector());
				System.out.println("    Lançamento: " + filme.getLaunchYear());
				System.out.println("    Duração: " + filme.getDuration() + " min");
			} else {
				System.out.println(" Filme com ID " + id + " não encontrado.");
			}
		} catch (Exception e) {
			System.out.println(" Erro ao buscar filme: " + e.getMessage());
		}
	}

	private static void atualizarFilme(MovieService service, Scanner scanner) {
		System.out.println("||================================||");
		System.out.println("||          ATUALIZAR             ||");
		System.out.println("||================================||");

		System.out.print("Digite o ID do filme que deseja atualizar: ");
		Long id = scanner.nextLong();
		scanner.nextLine();

		Movie filmeExistente = service.BuscarPorId(id);

		if (filmeExistente == null) {
			System.out.println(" Filme com ID " + id + " não encontrado.");
			return;
		}

		System.out.println("Filme atual: " + filmeExistente.getNameMovie());
		System.out.println("Deixe em branco para manter o valor atual.");

		System.out.print("Novo nome [" + filmeExistente.getNameMovie() + "]: ");
		String novoNome = scanner.nextLine();
		if (!novoNome.isBlank()) {
			filmeExistente.setNameMovie(novoNome);
		}

		System.out.print("Novo diretor [" + filmeExistente.getDirector() + "]: ");
		String novoDiretor = scanner.nextLine();
		if (!novoDiretor.isBlank()) {
			filmeExistente.setDirector(novoDiretor);
		}

		System.out.print("Nova data (AAAA-MM-DD) [" + filmeExistente.getLaunchYear() + "]: ");
		String novaData = scanner.nextLine();
		if (!novaData.isBlank()) {
			filmeExistente.setLaunchYear(LocalDate.parse(novaData));
		}

		System.out.print("Nova duração em minutos [" + filmeExistente.getDuration() + "]: ");
		String novaDuracao = scanner.nextLine();
		if (!novaDuracao.isBlank()) {
			filmeExistente.setDuration(Integer.parseInt(novaDuracao));
		}

		try {
			service.atualizarFilme(id, filmeExistente);
			System.out.println(" Filme atualizado com sucesso!");
		} catch (Exception e) {
			System.out.println(" Erro ao atualizar: " + e.getMessage());
		}
	}

	private static void deletarFilme(MovieService service, Scanner scanner) {
		System.out.println("||================================||");
		System.out.println("||          DELETAR               ||");
		System.out.println("||================================||");

		System.out.print("Digite o ID do filme que deseja deletar: ");
		Long id = scanner.nextLong();
		scanner.nextLine();

		Movie filme = service.BuscarPorId(id);

		if (filme == null) {
			System.out.println(" Filme com ID " + id + " não encontrado.");
			return;
		}

		System.out.println("Filme: " + filme.getNameMovie());
		System.out.print("Tem certeza que deseja deletar? (S/N): ");
		String confirmacao = scanner.nextLine();

		if (confirmacao.equalsIgnoreCase("S")) {
			try {
				service.DeletarMovie(id);
				System.out.println(" Filme deletado com sucesso!");
			} catch (Exception e) {
				System.out.println(" Erro ao deletar: " + e.getMessage());
			}
		} else {
			System.out.println(" Operação cancelada.");
		}
	}

	private static void exibirEstatisticas(MovieService service) {
		System.out.println("||================================||");
		System.out.println("||    ESTATÍSTICAS                ||");
		System.out.println("||================================||");

		long total = service.contarMovies();
		System.out.println("\n Total de filmes cadastrados: " + total);

		if (total > 0) {
			var filmes = service.BuscarTodosFilmes();


			Movie maisLongo = filmes.stream()
					.max((f1, f2) -> Integer.compare(f1.getDuration(), f2.getDuration()))
					.orElse(null);

			if (maisLongo != null) {
				System.out.println("  Filme mais longo: " + maisLongo.getNameMovie() +
						" (" + maisLongo.getDuration() + " min)");
			}


			Movie maisCurto = filmes.stream()
					.min((f1, f2) -> Integer.compare(f1.getDuration(), f2.getDuration()))
					.orElse(null);

			if (maisCurto != null) {
				System.out.println(" Filme mais curto: " + maisCurto.getNameMovie() +
						" (" + maisCurto.getDuration() + " min)");
			}


			double duracaoMedia = filmes.stream()
					.mapToInt(Movie::getDuration)
					.average()
					.orElse(0);

			System.out.println(" Duração média: " + String.format("%.1f", duracaoMedia) + " min");
		}
	}

	private static void limparConsole(){
		try{
			String os = System.getProperty("os.name");

			if(os.contains("Windows")){
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else{
				System.out.println("\033[H\033[2j");
				System.out.flush();
			}
		} catch (Exception e){
			for (int i = 0; i < 50; i++){
				System.out.println();
			}
		}
	}

	private static void aguardarEnter(Scanner scanner){
		System.out.println("[Presione ENTER para continuar]");
		scanner.nextLine();
	}
}
