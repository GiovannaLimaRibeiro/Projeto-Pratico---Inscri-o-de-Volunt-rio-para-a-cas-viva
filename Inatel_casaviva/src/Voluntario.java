import java.util.ArrayList;
import java.util.List;

public class Voluntario extends AlunoInatel implements Funcoes {
    private String sexo;
    private String endereco;
    private int numDeHorasRegistradas;
    private List<String> funcoes = new ArrayList<>();
    private List<String> materias = new ArrayList<>();

    public Voluntario(String nome, String cpf, int matricula, String email, String dataNascimento, String curso,
                      String sexo, String endereco, int numDeHorasRegistradas) {
        super(nome, cpf, matricula, email, dataNascimento, curso);
        this.sexo = sexo;
        this.endereco = endereco;
        this.numDeHorasRegistradas = numDeHorasRegistradas;
    }

    public void adicionarMateria(String materia) {
        if (!materias.contains(materia)) {
            materias.add(materia);
            System.out.println("Matéria adicionada: " + materia);
        } else {
            System.out.println("A matéria já está na lista: " + materia);
        }
    }

    public void removerMateria(String materia) {
        if (materias.remove(materia)) {
            System.out.println("Matéria removida: " + materia);
        } else {
            System.out.println("A matéria não foi encontrada na lista: " + materia);
        }
    }


    public String infoVoluntarios() {
        return infoAlunoInatel() + ", Sexo: " + sexo + ", Endereço: " + endereco +
                ", Horas Totais por Semana: " + numDeHorasRegistradas + ", Funções: " + funcoes +
                ", Matérias: " + materias;
    }

    public void addFuncao(String funcao) {
        funcoes.add(funcao);
    }

    public String setEndereco(String endereco) {
        return this.endereco;
    }


    public int setNumDeHorasRegistradas(int i) {
        return numDeHorasRegistradas;
    }

    public List<String> getMaterias() {
        return materias;
    }


    @Override
    public void ministrarAulas() {
        if (materias.isEmpty()) {
            System.out.println("O voluntário não tem matérias atribuídas para ministrar.");
        } else {
            System.out.println("O voluntário está ministrando aulas de:");
            for (String materia : materias) {
                System.out.println("- " + materia);
            }
        }
    }

    @Override
    public void atividadesLixoEletronico() {
        System.out.println("O voluntário está realizando atividades relacionadas ao lixo eletrônico.");
    }

}
