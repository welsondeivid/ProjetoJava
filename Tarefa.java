public class Tarefa {
    
    private String desc = null;
    private String profissional = null;

    public Tarefa(String desc, String profissional)
    {
        this.desc = desc;
        this.profissional = profissional;
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

}
