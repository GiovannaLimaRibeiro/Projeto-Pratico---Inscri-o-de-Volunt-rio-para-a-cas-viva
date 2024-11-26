import java.io.*;
import java.util.*;

public abstract class AlunoInatel {
    private String nome;
    private String cpf;
    private int matricula;
    private String email;
    private String dataNascimento;
    private String curso;

    public AlunoInatel(String nome, String cpf, int matricula, String email, String dataNascimento, String curso) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.curso = curso;
    }

    public String getCpf() {
        return cpf;
    }

    public String infoAlunoInatel() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Matrícula: " + matricula +
                ", Email: " + email + ", Data de Nascimento: " + dataNascimento + ", Curso: " + curso;
    }
}
