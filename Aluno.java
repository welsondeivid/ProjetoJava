import java.util.ArrayList;

public class Aluno {
    
    private String nome = null;
    private String tipo = null;
    private String especialista = null;
    private ArrayList <String> tarefas = null;

    public boolean Cadastrar(String name, String type, String espec)
    {
        this.nome = name;
        this.tipo = type;
        this.especialista = espec;


        if (this.nome != null && this.tipo != null && this.especialista != null)   return true;
        else    return false;
    }

    public String getNome()
    {
        return this.nome;
    }

    public String getTipo()
    {
        return this.tipo;
    }

    public String getEspecialista()
    {
        return this.especialista;
    }

    public void setTarefas (String tarefa)
    {
        this.tarefas.add(tarefa);
    }
}