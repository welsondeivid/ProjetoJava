import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Utilidades {
	    
    public int LerInt(Scanner input)
    {
        int num = input.nextInt();
        input.nextLine();
        return num;
    }

    public Usuario BuscarUsuario(ArrayList<Usuario> users, int checkId)
    {
        Usuario user = null;

        for (Usuario item: users)
        {
            if (checkId == item.getId())
            {
                user = item;
                break;
            }
        }

        return user;
    }

    public Projeto BuscarProjeto(ArrayList<Projeto> projs, int checkId)
    {
        Projeto proj = null;

        for (Projeto item : projs)
        {
            if (checkId == item.getId())
            {
                proj = item;
                break;
            } 
        }

        return proj;
    }

    public Atividade BuscarAtividade(ArrayList<Atividade> ativs, int checkId)
    {
        Atividade ativ = null;

        for (Atividade item : ativs)
        {
            if (checkId == item.getId())
            {
                ativ = item;
                break;
            }    
        }

        return ativ;
    }

    public void DadosUser(Usuario user)
    {
        System.out.println("Nome: "+user.getNome());
        System.out.println("Email: "+user.getEmail());

        if (user.getTipo().equals("Grad"))
        {
            System.out.print("Aluno Graduando e ");
        }
        else if (user.getTipo().equals("Mest"))
        {
            System.out.print("Aluno Mestrando e ");
        }
        else if (user.getTipo().equals("Dout"))
        {
            System.out.print("Aluno Doutorando e ");
        }
        else if (user.getTipo().equals("Prof"))
        {
            System.out.print("Professor ");

            if(user.getCoord())
            {
                System.out.println("Coordenador");
            }
        }
        else if (user.getTipo().equals("Pesq"))
        {
            System.out.print("Pesquisador ");

            if(user.getCoord())
            {
                System.out.println("Coordenador");
            }
        }

        if (!user.getCoord())
        {
            if (user.getFunc().equals("Devp"))
            {
                System.out.println("Desenvolvedor");
            }
            else if (user.getFunc().equals("Test"))
            {
                System.out.println("Tecnico");
            }
            else if (user.getFunc().equals("Anlt"))
            {
                System.out.println("Analista");
            }
            else if (user.getFunc().equals("Tecn"))
            {
                System.out.println("Tecnico");
            }
        }
        System.out.println("Projeto associado: "+user.getProjeto());
        System.out.println("Atividade associada: "+user.getAtividade());

        System.out.println("Lista de Tarefas:");
        for (Tarefa item : user.getTarefas())
        {
            System.out.println(item.getDesc());
        }
    }

    public void DadosAtiv(Atividade ativ)
    {
        System.out.println("Atividade descrita: ");
        System.out.println(ativ.getDesc());

        System.out.print("Responsavel pela Atividade: ");
        System.out.println(BuscarUsuario(ativ.getUsuarios(), ativ.getIdResponsavel()).getNome());

        System.out.println("Inicio da Atividade: ");
        MostrarDataHora(ativ.getInicio());

        System.out.println("Termino da Atividade: ");
        MostrarDataHora(ativ.getTermino());

        System.out.println("Lista de usuarios: ");
        for(Usuario item : ativ.getUsuarios())
        {
            System.out.println(item.getNome());
        }

        System.out.println("Lista de tarefas: ");
        for(Tarefa item : ativ.getTarefas())
        {
            System.out.println(item.getDesc());
        }
    }

    public void DadosProj(Projeto proj)
    {
        System.out.println("Projeto descrito: ");
        System.out.println(proj.getDesc());

        System.out.println("Status do Projeto: "+proj.getStatus());

        System.out.println("Inicio do Projeto: ");
        MostrarDataHora(proj.getInicio());
        
        System.out.println("Termino do Projeto: ");
        MostrarDataHora(proj.getTermino());

        System.out.println("Coordenador do Projeto: ");
        System.out.println(BuscarUsuario(proj.getProjetistas(), proj.getIdCoordenador()));

        System.out.println("Lista de Projetistas: ");
        for (Usuario item : proj.getProjetistas())    System.out.println(item.getNome());

        System.out.println("Lista de Desenvolvedores: ");
        for (Usuario item : proj.getDesenvolvedores())    System.out.println(item.getNome());

        System.out.println("Lista de Testadores: ");
        for (Usuario item : proj.getTestadores())    System.out.println(item.getNome());

        System.out.println("Lista de Analistas: ");
        for (Usuario item : proj.getAnalistas())    System.out.println(item.getNome());

        System.out.println("Tecnico do Projeto: ");
        System.out.println(BuscarUsuario(proj.getProjetistas(), proj.getIdTecnico()));

        System.out.println("Lista de Atividades: ");
        for (Atividade item : proj.getAtividades()) System.out.println(item.getDesc());

        System.out.println("Valor da Bolsa-Desenvolvedor: "+proj.getBolsaDesenvolvedor());
        System.out.println("Valor da Bolsa-Testador: "+proj.getBolsaTestador());
        System.out.println("Valor da Bolsa-Analista: "+proj.getBolsaAnalista());

        System.out.println("Prazo da Bolsa-Desenvolvedor: ");
        MostrarDataHora(proj.getTempoBolsaDesenvolvedor());
        
        System.out.println("Prazo da Bolsa-Testador: ");
        MostrarDataHora(proj.getTempoBolsaTestador());

        System.out.println("Prazo da Bolsa-Analista: ");
        MostrarDataHora(proj.getTempoBolsaAnalista());
    }

    public void MostrarDataHora (LocalDateTime tempo)
    {
        DateTimeFormatter dataForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    DateTimeFormatter horaForm = DateTimeFormatter.ofPattern("HH:mm");

        System.out.println("Prazo da Bolsa-Desenvolvedor: "+dataForm.format(tempo));
        System.out.println("Hora da Bolsa-Desenvolvedor: "+horaForm.format(tempo));
    }
}
