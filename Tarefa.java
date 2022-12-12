public class Tarefa implements Busca{
    
    private String desc = null;
    private int idProf = 0;
    private String status = null;

    public Tarefa(String desc, int idProf)
    {
        this.setNome(desc);
        this.setId(idProf);
        this.setStatus("Atribuida");
    }

    public String getNome() {
        return this.desc;
    }

    public void setNome(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return this.idProf;
    }

    public void setId(int idProf) {
        this.idProf = idProf;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
