public class Voluntario extends AlunoInatel implements Funcoes {
    private String sexo;
    private String endereco;
    private int numDeHorasRegistradas;

    public Voluntario(String nome, String cpf, int matricula, String email, String dataNascimento, String curso, String sexo, String endereco, int numDeHorasRegistradas) {
        super(nome, cpf, matricula, email, dataNascimento, curso);
        this.sexo = sexo;
        this.endereco = endereco;
        this.numDeHorasRegistradas = numDeHorasRegistradas;
    }

    @Override
    public void infoAlunoInatel() {

    }

    @Override
    public void ministrarAulas() {

    }

    @Override
    public void atividadesLixoEletronico() {

    }
}
