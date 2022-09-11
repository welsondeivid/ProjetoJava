import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Utilidades {
    
    DateTimeFormatter dataForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter horaForm = DateTimeFormatter.ofPattern("HH:mm");
	    
    public int LerInt(Scanner input)
    {
        int num = input.nextInt();
        input.nextLine();
        return num;
    }

    public Usuario BuscarUsuario(ArrayList<Usuario> users, Scanner input)
    {
        Usuario user = null;
        int checkId = LerInt(input);

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

    public Projeto BuscarProjeto(ArrayList<Projeto> projs, Scanner input)
    {
        Projeto proj = null;
        int checkId = LerInt(input);

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

    public Atividade BuscarAtividade(ArrayList<Atividade> ativs, Scanner input)
    {
        Atividade ativ = null;
        int checkId = LerInt(input);

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

        System.out.print("Responsavel pela atividade: ");
        for (Usuario item : ativ.getUsuarios())
        {
            if (ativ.getResponsavel() == item.getId())
            {
                System.out.println(item.getNome());
            }
        }

        System.out.println("Hora de inicio: "+horaForm.format(ativ.getInicio()));
        System.out.println("Data de inicio: "+dataForm.format(ativ.getInicio()));
        
        System.out.println("Hora de termino: "+horaForm.format(ativ.getTermino()));
        System.out.println("Data de termino: "+dataForm.format(ativ.getTermino()));

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
}
