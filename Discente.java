import java.time.LocalDateTime;

public class Discente extends Usuario{
    
    private int projInterCam = 0;
    private int ativInterCam = 0;
    private LocalDateTime diaPag = null;
    private boolean pass = false;

    public Discente (String nome, String email, String senha, int tipo, int id)
    {
        super(nome, email, senha, tipo, id);
    }

    @Override
    public void IngressarProjeto(Projeto project)
    {
        if (project != null && this.getProjeto() == 0)
        {
            this.setProjeto(project.getId());
            project.setProjetistas(this);
            this.setDiaIngresso(LocalDateTime.now());
            this.setDiaPag(LocalDateTime.now());
        }
        else
        {
            System.out.println("Usuario ja se encontra em projeto");
        }
    }
    
    public int getProjInterCam() {
        return this.projInterCam;
    }

    public void setProjInterCam(int projInterCam) {
        this.projInterCam = projInterCam;
    }

    public int getAtivInterCam() {
        return this.ativInterCam;
    }

    public void setAtivInterCam(int ativInterCam) {
        this.ativInterCam = ativInterCam;
    }

    public LocalDateTime getDiaPag()
    {
        return this.diaPag;
    }

    public void setDiaPag(LocalDateTime diaPag)
    {
        this.diaPag = diaPag;
    }

    public boolean getPass()
    {
        return this.pass;
    }

    public void setPass(Boolean pass)
    {
        this.pass = pass;
    }
    
    @Override
    public String toString()
    {
        String tipo = null, intercambista = null;

        if (this.getTipo() == 1)
        {
            tipo = "Aluno Graduando";
        }
        else if (this.getTipo() == 2)
        {
            tipo = "Aluno Mestrando";
        }
        else if (this.getTipo() == 3)
        {
            tipo = "Aluno Doutorando";
        }

        if (this.getProjInterCam() != 0)
        {
            intercambista = "\nFaz intercambio no projeto: "+this.getProjInterCam()+"\n";
            intercambista += "Designado para a atividade: "+this.getAtivInterCam()+"\n";
        }
        else
        {
            intercambista = "\nDisponivel para intercambio\n";
        }

        return tipo +"\n"+ super.toString() + intercambista;
    }
}
