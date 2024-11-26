import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Main {

    private static final String FILE_NAME = "voluntarios.txt";
    private static ArrayList<Voluntario> voluntarios = new ArrayList<>();

    public static void main(String[] args) {
        carregarDados();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nBem-Vindo ao Cadastro de Voluntarios da cas@viva!\n");
            System.out.println("Menu:");
            System.out.println("1. Inserir Voluntários");
            System.out.println("2. Pesquisar Voluntário");
            System.out.println("3. Atualizar Informações");
            System.out.println("4. Remover Voluntário");
            System.out.println("5. Atribuir Funções a um Voluntário");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    inserirVoluntario(scanner);
                    break;
                case 2:
                    pesquisarVoluntario(scanner);
                    break;
                case 3:
                    atualizarVoluntario(scanner);
                    break;
                case 4:
                    removerVoluntario(scanner);
                    break;
                case 5:
                    atribuirFuncoes(scanner);
                    break;
                case 6:
                    salvarDados();
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }

    private static void inserirVoluntario(Scanner scanner) {
        while (true) {
            System.out.println("Insira as informações do voluntário:");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Matrícula: ");
            int matricula = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Data de Nascimento: ");
            String dataNascimento = scanner.nextLine();
            System.out.print("Curso: ");
            String curso = scanner.nextLine();
            System.out.print("Sexo: ");
            String sexo = scanner.nextLine();
            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();
            System.out.print("Número de Horas Registradas: ");
            int numDeHorasRegistradas = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            Voluntario voluntario = new Voluntario(nome, cpf, matricula, email, dataNascimento, curso, sexo, endereco, numDeHorasRegistradas);
            voluntarios.add(voluntario);
            System.out.println("Voluntário inserido com sucesso!");

            System.out.print("Deseja adicionar outro voluntário? (s/n): ");
            String resposta = scanner.nextLine();
            if (!resposta.equalsIgnoreCase("s")) {
                break;
            }
        }
    }

    private static void pesquisarVoluntario(Scanner scanner) {
        System.out.print("Digite o CPF do voluntário para pesquisar: ");
        String cpf = scanner.nextLine();

        for (Voluntario voluntario : voluntarios) {
            if (voluntario.getCpf().equals(cpf)) {
                System.out.println("Voluntário encontrado:");
                System.out.println(voluntario.infoVoluntarios());
                return;
            }
        }
        System.out.println("Voluntário não encontrado.");
    }

    private static void atualizarVoluntario(Scanner scanner) {
        System.out.print("Digite o CPF do voluntário para atualizar: ");
        String cpf = scanner.nextLine();

        for (Voluntario voluntario : voluntarios) {
            if (voluntario.getCpf().equals(cpf)) {
                System.out.println("Atualize as informações (deixe em branco para manter o valor atual):");

                System.out.print("Novo Endereço: ");
                String endereco = scanner.nextLine();
                if (!endereco.isEmpty()) voluntario.setEndereco(endereco);

                System.out.print("Novo Número de Horas Registradas: ");
                String horas = scanner.nextLine();
                if (!horas.isEmpty()) voluntario.setNumDeHorasRegistradas(Integer.parseInt(horas));

                System.out.println("Informações atualizadas com sucesso!");
                return;
            }
        }
        System.out.println("Voluntário não encontrado.");
    }

    private static void removerVoluntario(Scanner scanner) {
        System.out.print("Digite o CPF do voluntário para remover: ");
        String cpf = scanner.nextLine();

        voluntarios.removeIf(voluntario -> voluntario.getCpf().equals(cpf));
        System.out.println("Voluntário removido com sucesso!");
    }

    private static void atribuirFuncoes(Scanner scanner) {
        System.out.print("Digite o CPF do voluntário para atribuir funções: ");
        String cpf = scanner.nextLine();

        for (Voluntario voluntario : voluntarios) {
            if (voluntario.getCpf().equals(cpf)) {
                System.out.println("Escolha uma função para atribuir:");
                System.out.println("1. Ministrar Aulas");
                System.out.println("2. Atividades de Lixo Eletrônico");
                int escolha = scanner.nextInt();
                scanner.nextLine(); // Consumir quebra de linha

                switch (escolha) {
                    case 1:
                        voluntario.addFuncao("Ministrar Aulas");
                        System.out.println("Função 'Ministrar Aulas' atribuída!");
                        break;
                    case 2:
                        voluntario.addFuncao("Atividades de Lixo Eletrônico");
                        System.out.println("Função 'Atividades de Lixo Eletrônico' atribuída!");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
                return;
            }
        }
        System.out.println("Voluntário não encontrado.");
    }

    private static void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Voluntario voluntario : voluntarios) {
                writer.write(voluntario.infoVoluntarios());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static void carregarDados() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Implementar lógica de recriação de objetos a partir do arquivo
            }
        } catch (IOException e) {
            System.out.println("Nenhum dado encontrado para carregar.");
        }
    }
}
