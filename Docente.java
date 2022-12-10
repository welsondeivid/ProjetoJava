import java.time.LocalDateTime;

public class Docente extends Usuario{
    
    private boolean coord = false;

    public Docente (String nome, String email, String senha, int tipo, int id)
    {
        super(nome, email, senha, tipo, id);
        this.setCoord(true); //teste, apague essa linha
    }

    @Override
    public void IngressarProjeto(Projeto project) throws Exception
    {
        if (this.getProjeto() == 0)
        {
            if (!this.getCoord())
            {
                this.setProjeto(project.getId());
                project.setProjetistas(this);
                this.setDiaIngresso(LocalDateTime.now());
            }
            else
            {
                throw new RuntimeException("Usuario eh Coordenador");
            }
        }
        else
        {
            throw new RuntimeException("Usuario ja se encontra em projeto");
        }
    }
    @Override
    public void SairProjeto(Projeto project, Atividade ativ) throws Exception
    {
        if (this.getCoord())
        {
            throw new RuntimeException("Coordenador n pode sair do projeto");
        }
        
        project.getProjetistas().remove(this);
        this.setProjeto(0);

        if (ativ != null)
        {
            ativ.getUsuarios().remove(this);
            this.setAtividade(0);
            this.getTarefas().clear();
        }
    }
    
    public boolean getCoord() {
        return this.coord;
    }

    public void setCoord(boolean coord) {
        this.coord = coord;
    }

    @Override
    public String toString ()
    {
        String tipo = null;

        if (this.getTipo() == 4)
        {
            tipo = "Professor";
        }
        else if (this.getTipo() == 5)
        {
            tipo = "Pesquisador";
        }
        if(this.getCoord())
        {
            tipo += "Coordenador";
        }

        return tipo + "\n"+super.toString();
    }
}
