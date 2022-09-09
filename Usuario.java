import java.util.ArrayList;

public class Usuario
{
    private int id = -1;
    private String nome = null;
    private String email = null;
    private String senha = null;
    private String tipo = null;

    private int projeto = 0;
    private int atividade = 0;
    private ArrayList <Tarefa> tarefas = new ArrayList<Tarefa>();

    public Usuario(String nome, String email, String senha, String tipo, int id)
    {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        setTipo(tipo);
        setId(id);

    }

    /*public void EditarUsuario()
    {

    }*/

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getProjeto() {
        return this.projeto;
    }

    public void setProjeto(int projeto) {
        this.projeto = projeto;
    }

    public int getAtividade() {
        return this.atividade;
    }

    public void setAtividade(int atividade) {
        this.atividade = atividade;
    }

    public ArrayList<Tarefa> getTarefas() {
        return this.tarefas;
    }

    public void setTarefas(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

}
