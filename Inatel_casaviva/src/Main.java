import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final String FILE_NAME = "C:\\Users\\natan\\Documents\\GitHub\\Projeto-Pratico-Inscricao-de-voluntarios-para-a-casa-viva\\Inatel_casaviva\\src\\voluntarios.txt";
    private static final ArrayList<Voluntario> voluntarios = new ArrayList<>();

    public static void main(String[] args) {
        carregarDados();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Bem-Vindo(a) ao Sistema de Cadastro de Voluntarios da cas@viva!\n");
            System.out.println("Menu:");
            System.out.println("1. Inserir Voluntários");
            System.out.println("2. Pesquisar Voluntário");
            System.out.println("3. Atualizar Informações");
            System.out.println("4. Remover Voluntário");
            System.out.println("5. Atribuir Funções a um Voluntário");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    inserirVoluntario(scanner);
                    salvarDados();
                    break;
                case 2:
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.print("Digite o CPF do voluntário para pesquisar: ");
                    String cpf = scanner.nextLine();
                    pesquisarVoluntario(cpf);
                    salvarDados();
                    break;
                case 3:
                    atualizarVoluntario(scanner);
                    salvarDados();
                    break;
                case 4:
                    removerVoluntario(scanner);
                    salvarDados();
                    break;
                case 5:
                    atribuirFuncoes(scanner);
                    salvarDados();
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
            scanner.nextLine();
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
            System.out.print("Número de Horas Totais por Semana: ");
            int numDeHorasRegistradas = scanner.nextInt();
            scanner.nextLine();

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

    private static void pesquisarVoluntario(String cpf) {
        try (BufferedReader bufferEntrada = new BufferedReader(new FileReader(FILE_NAME))) {
            String leitura;
            boolean encontrado = false;

            while ((leitura = bufferEntrada.readLine()) != null) {
                if (leitura.contains("CPF: " + cpf)) {
                    System.out.println("Voluntário encontrado:");
                    System.out.println(leitura);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Voluntário não encontrado.");
            }
        } catch (IOException e) {
            System.err.printf("Erro ao ler o arquivo: %s%n", e.getMessage());
        }
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

        // Remove o voluntário da lista
        boolean removed = voluntarios.removeIf(voluntario -> voluntario.getCpf().equals(cpf));

        if (removed) {
            // Atualiza o arquivo excluindo a linha correspondente
            atualizarArquivo(cpf);
            System.out.println("Voluntário removido com sucesso!");
        } else {
            System.out.println("Voluntário não encontrado.");
        }
    }

    private static void atualizarArquivo(String cpf) {
        File arquivo = new File(FILE_NAME);
        File arquivoTemp = new File("C:\\Users\\natan\\Documents\\GitHub\\Projeto-Pratico-Inscricao-de-voluntarios-para-a-casa-viva\\Inatel_casaviva\\src\\Temp.txt");  // Arquivo temporário para a reescrita

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoTemp))) {

            String linha;
            while ((linha = leitor.readLine()) != null) {

                if (!linha.contains(cpf)) {
                    escritor.write(linha);
                    escritor.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }

        // Substitui o arquivo original pelo arquivo temporário
        if (arquivo.delete()) {
            if (!arquivoTemp.renameTo(arquivo)) {
                System.out.println("Erro ao substituir o arquivo original.");
            }
        } else {
            System.out.println("Erro ao deletar o arquivo original.");
        }
    }


                private static void atribuirFuncoes(Scanner scanner) {
        System.out.print("Digite o CPF do voluntário para atribuir funções: ");
        String cpf = scanner.nextLine();

        for (Voluntario voluntario : voluntarios) {
            if (voluntario.getCpf().equals(cpf)) {
                System.out.println("Escolha uma função para atribuir:");
                System.out.println("1. Atribuir Função");
                System.out.println("2. Gerencias Matérias");
                int escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1:
                        System.out.println("Escolha uma função para atribuir:");
                        System.out.println("1. Ministrar Aulas");
                        System.out.println("2. Atividades de Lixo Eletrônico");
                        int funcaoEscolha = scanner.nextInt();
                        scanner.nextLine();

                        if (funcaoEscolha == 1) {
                            voluntario.addFuncao("Ministrar Aulas");
                            System.out.println("Função 'Ministrar Aulas' atribuída!");
                        } else if (funcaoEscolha == 2) {
                            voluntario.addFuncao("Atividades de Lixo Eletrônico");
                            System.out.println("Função 'Atividades de Lixo Eletrônico' atribuída!");
                        } else {
                            System.out.println("Opção inválida.");
                        }
                        break;
                    case 2:
                        gerenciarMaterias(scanner, voluntario);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
                return;
            }
        }
        System.out.println("Voluntário não encontrado.");
    }

    private static void gerenciarMaterias(Scanner scanner, Voluntario voluntario) {
        System.out.println("Gerenciar Matérias:");
        System.out.println("1. Adicionar Matéria");
        System.out.println("2. Remover Matéria");
        System.out.println("3. Listar Matérias");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Digite o nome da matéria a ser adicionada: ");
                String novaMateria = scanner.nextLine();
                voluntario.adicionarMateria(novaMateria);
                break;
            case 2:
                System.out.print("Digite o nome da matéria a ser removida: ");
                String materiaRemover = scanner.nextLine();
                voluntario.removerMateria(materiaRemover);
                break;
            case 3:
                System.out.println("Matérias atribuídas ao voluntário:");
                for (String materia : voluntario.getMaterias()) {
                    System.out.println("- " + materia);
                }
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }


    //Java IO: Persistência dos dados
    private static void salvarDados() {
        FileOutputStream fluxoSaida = null;
        OutputStreamWriter geradorFluxoSaida = null;
        BufferedWriter bufferSaida = null;
        try {
            //Inicializando os objetos
            fluxoSaida = new FileOutputStream(FILE_NAME, true); // true para não sobrescrever o arquivo
            geradorFluxoSaida = new OutputStreamWriter(fluxoSaida);
            bufferSaida = new BufferedWriter(geradorFluxoSaida);
            // Escrita no arquivo
            for (Voluntario voluntario : voluntarios) {
                bufferSaida.write(voluntario.infoVoluntarios());
                bufferSaida.newLine();
            }

        }catch (Exception e){
            System.err.printf(String.valueOf(e));
        } finally {
            try {
                // Fechando o buffer
                assert bufferSaida != null;
                bufferSaida.close();
            } catch (IOException e) {
                System.err.printf(String.valueOf(e));
            }
        }
    }

    private static void carregarDados() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Separar os campos pelo delimitador ", "
                String[] campos = line.split(",");
                if (campos.length >= 9) {
                    try {
                        String nome = campos[0].split(": ")[1];
                        String cpf = campos[1].split(": ")[1];
                        int matricula = Integer.parseInt(campos[2].split(": ")[1]);
                        String email = campos[3].split(": ")[1];
                        String dataNascimento = campos[4].split(": ")[1];
                        String curso = campos[5].split(": ")[1];
                        String sexo = campos[6].split(": ")[1];
                        String endereco = campos[7].split(": ")[1];
                        int numDeHorasRegistradas = Integer.parseInt(campos[8].split(": ")[1]);

                        // Criar o objeto Voluntario
                        Voluntario voluntario = new Voluntario(nome, cpf, matricula, email, dataNascimento, curso, sexo, endereco, numDeHorasRegistradas);

                        // Adicionar à lista de voluntários
                        voluntarios.add(voluntario);
                    } catch (Exception e) {
                        System.out.println("Erro ao processar linha: " + line + ". Ignorando...");
                    }
                } else {
                    System.out.println("Linha com formato inválido: " + line);
                }
            }
            System.out.println("Dados carregados com sucesso! Total de voluntários: " + voluntarios.size());
        } catch (IOException e) {
            System.out.println("Nenhum dado encontrado para carregar ou erro ao acessar o arquivo.");
        }
    }
}
