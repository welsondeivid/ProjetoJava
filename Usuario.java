import java.util.ArrayList;

public class Usuario
{
    private int id = -1;
    private String nome = null;
    private String email = null;
    private String senha = null;
    private String tipo = null;

    private String projeto = null;
    private String atividade = null;
    private ArrayList <String> tarefas = new ArrayList<String>();

    public Usuario(String nome, String email, String senha, String tipo, int id)
    {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        setTipo(tipo);
        this.id = id;

    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProjeto() {
        return this.projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public String getAtividade() {
        return this.atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public ArrayList<String> getTarefas() {
        return this.tarefas;
    }

    public void setTarefas(ArrayList<String> tarefas) {
        this.tarefas = tarefas;
    }

}
