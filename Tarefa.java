public class Tarefa {
    
    private String desc = null;
    private int profissional = 0;
    private String status = null;

    public Tarefa(String desc, int profissional)
    {
        this.setDesc(desc);
        this.setProfissional(profissional);
        this.setStatus("Atribuida");
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getProfissional() {
        return this.profissional;
    }

    public void setProfissional(int profissional) {
        this.profissional = profissional;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
