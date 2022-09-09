public class Tarefa {
    
    private String desc = null;
    private String profissional = null;
    private String status = null;

    public Tarefa(String desc, String profissional)
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

    public String getProfissional() {
        return this.profissional;
    }

    public void setProfissional(String profissional) {
        this.profissional = profissional;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
