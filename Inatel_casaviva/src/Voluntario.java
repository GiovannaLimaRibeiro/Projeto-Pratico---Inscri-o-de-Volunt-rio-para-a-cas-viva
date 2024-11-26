import java.util.ArrayList;
import java.util.List;

public class Voluntario extends AlunoInatel implements Funcoes {
    private String sexo;
    private String endereco;
    private int numDeHorasRegistradas;
    private List<String> funcoes = new ArrayList<>();

    public Voluntario(String nome, String cpf, int matricula, String email, String dataNascimento, String curso,
                      String sexo, String endereco, int numDeHorasRegistradas) {
        super(nome, cpf, matricula, email, dataNascimento, curso);
        this.sexo = sexo;
        this.endereco = endereco;
        this.numDeHorasRegistradas = numDeHorasRegistradas;
    }

    public String infoVoluntarios() {
        return infoAlunoInatel() + ", Sexo: " + sexo + ", Endereço: " + endereco +
                ", Horas Registradas: " + numDeHorasRegistradas + ", Funções: " + funcoes;
    }

    public void addFuncao(String funcao) {
        funcoes.add(funcao);
    }

    public String getSexo() {
        return sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public String setEndereco(String endereco) {
        return this.endereco;
    }

    public int getNumDeHorasRegistradas() {
        return numDeHorasRegistradas;
    }

    public int setNumDeHorasRegistradas(int i) {
        return numDeHorasRegistradas;
    }


    public List<String> getFuncoes() {
        return funcoes;
    }

    @Override
    public void ministrarAulas() {
        System.out.println("O voluntário está ministrando aulas.");
    }

    @Override
    public void atividadesLixoEletronico() {
        System.out.println("O voluntário está realizando atividades relacionadas ao lixo eletrônico.");
    }
}
