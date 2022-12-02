import java.time.LocalDateTime;

public class Docente extends Usuario{
    
    private boolean coord = false;

    public Docente (String nome, String email, String senha, String tipo, int id)
    {
        super(nome, email, senha, tipo, id);
    }

    public void IngressarProjeto(Projeto project)
    {
        if (project != null && this.getProjeto() == 0)
        {
            this.setProjeto(project.getId());
            project.setProjetistas(this);
            this.setDiaIngresso(LocalDateTime.now());
        }
        else
        {
            System.out.println("Usuario ja se encontra em projeto");
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

        if (this.getTipo().equals("Prof"))
        {
            tipo = "Professor";
        }
        else if (this.getTipo().equals("Pesq"))
        {
            tipo = "Pesquisador";
        }
        if(this.getCoord())
        {
            tipo.concat("Coordenador");
        }

        return tipo + super.toString();
    }
}
