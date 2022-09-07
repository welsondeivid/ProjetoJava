import java.time.LocalDateTime;
import java.util.ArrayList;

public class Atividade {
    
    private int id = -1;
    private String desc = null;
    private String responsavel = null;
    private LocalDateTime inicio = null;
    private LocalDateTime termino = null;

    private ArrayList<String> profissionais = new ArrayList<String>();

    private ArrayList<String> tarefas = new ArrayList<String>();

    public Atividade(int id, String desc, String responsavel, LocalDateTime inicio, LocalDateTime termino)
    {
        setId(id);
        setDesc(desc);
        setResponsavel(responsavel);
        setInicio(inicio);
        setTermino(termino);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getInicio() {
        return this.inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getTermino() {
        return this.termino;
    }

    public void setTermino(LocalDateTime termino) {
        this.termino = termino;
    }

    public String getResponsavel() {
        return this.responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public ArrayList<String> getProfissionais() {
        return this.profissionais;
    }

    public void setProfissionais(ArrayList<String> profissionais) {
        this.profissionais = profissionais;
    }

    public ArrayList<String> getTarefas() {
        return this.tarefas;
    }

    public void setTarefas(ArrayList<String> tarefas) {
        this.tarefas = tarefas;
    }

    
}