import java.time.LocalDateTime;
import java.util.ArrayList;

public class Atividade {
    
    private int id = -1;
    private String desc = null;
    private LocalDateTime inicio = null;
    private LocalDateTime termino = null;

    private String responsavel = null;
    private ArrayList<String> profissionais = new ArrayList<String>();

    private ArrayList<String> tarefas = new ArrayList<String>();

    public int setID(int id)
    {
        this.id = id;
        return this.id;
    }
    public String setDesc(String desc)
    {
        this.desc = desc;
        return this.desc;
    }
    public LocalDateTime setInicio(LocalDateTime inicio)
    {
        this.inicio = inicio;
        return this.inicio;
    }
    public LocalDateTime setTermino(LocalDateTime termino)
    {
        this.termino = termino;
        return this.termino;
    }
    public String setResponsavel(String responsavel)
    {
        this.responsavel = responsavel;
        return this.responsavel;
    }
    public void setProfissionais(String profissional)
    {
        this.profissionais.add(profissional);
    }

    public void setTarefas(String tarefa)
    {
        this.tarefas.add(tarefa);
    }
}