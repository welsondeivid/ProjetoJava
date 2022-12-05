import java.util.Scanner;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Utilidades implements Lista{
	    
    public int LerInt(Scanner input) 
    {
        int num = -1;
        try 
        {
            num = input.nextInt();
        } 
        catch (Exception e)
        {
            throw new RuntimeException("Digite um valor inteiro\n");      
        }
        finally
        {
            input.nextLine();
        }        
        return num;
    }
    
    public float LerFloat(Scanner input)
    {
        float num = -1.0f;
        try
        {
            num = input.nextFloat();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Digite um valor real\n");
        }
        finally
        {
            input.nextLine();
        }
        return num;
    }   

    public Usuario BuscarUsuario(ArrayList<Usuario> users, int checkId)
    {
        for (Usuario item: users)
        {
            if (checkId == item.getId())
            {
                return item;
            }
        }

        throw new RuntimeException("Usuario fora do sistema\n");
    }

    public Projeto BuscarProjeto(ArrayList<Projeto> projs, int checkId)
    {
        for (Projeto item : projs)
        {
            if (checkId == item.getId())
            {
                return item;
            } 
        }

        throw new RuntimeException("Projeto fora do sistema\n");
    }

    public Atividade BuscarAtividade(ArrayList<Atividade> ativs, int checkId)
    {
        for (Atividade item : ativs)
        {
            if (checkId == item.getId())
            {
                return item;
            }    
        }

        throw new RuntimeException("Atividade fora do sistema\n");
    }

    public void Consultar (Scanner input, ArrayList<Projeto> projs, ArrayList<Usuario> users)
    {
        int cmdConsulta = LerInt(input);

        if (cmdConsulta == 1)
        {
            System.out.println("Digite o RG do usuario:");
            ListarUsers (users);

            int checkIdU = LerInt(input);
            Usuario usuario = BuscarUsuario(users, checkIdU);

            System.out.println("Dados do usuario encontrado: ");
            DadosUser(usuario);
        }
        else if (cmdConsulta == 2)
        {
            System.out.println(("Digite o id do projeto onde a atividade esta localizda: "));
            ListarProjs(projs);

            int checkIdP = LerInt(input);
            Projeto project = BuscarProjeto(projs, checkIdP);

            System.out.println("Digite o id da atividade: ");
            ListarAtivs(project.getAtividades());

            int checkIdA = LerInt(input);
            Atividade atividade = BuscarAtividade(project.getAtividades(), checkIdA);

            System.out.println("Dados da atividade encontrada: ");
            DadosAtiv(atividade);
        }
        else if (cmdConsulta == 3)
        {
            System.out.println(("Digite o id do projeto: "));
            ListarProjs(projs);

            int checkIdP = LerInt(input);
            Projeto project = BuscarProjeto(projs, checkIdP);

            System.out.println("Dados do projeto encontrado: ");
            DadosProj(project);
        }
    }
    
    @Override
    public void ListarTasks (ArrayList<Tarefa> tasks)
    {
        
    }
    
    @Override
    public void ListarUsers(ArrayList<Usuario> users)
    {
        System.out.println("        Lista de usuarios disponiveis");
        for (Usuario item : users)
        {
            System.out.println("Nome: "+item.getNome());
            System.out.println("ID: "+item.getId());
        }
    }

    @Override
    public void ListarDocentes (ArrayList<Usuario> users)
    {
        System.out.println("        Lista de Docentes disponiveis");
        for (Usuario item : users)
        {
            if (item instanceof Docente)
            {
                System.out.println("Nome: "+item.getNome());
                System.out.println("ID: "+item.getId());
            }
        }
    }

    @Override
    public void ListarDiscentes (ArrayList<Usuario> users)
    {
        
    }
    
    @Override
    public void ListarAtivs(ArrayList<Atividade> ativs)
    {
        System.out.println("        Lista de atividades disponiveis");
        for (Atividade item : ativs)
        {
            System.out.println("Descricao: "+item.getDesc());
            System.out.println("ID da atividade: "+item.getId());
        }
    }

    @Override
    public void ListarProjs(ArrayList<Projeto> projs)
    {
        System.out.println("        Lista de projetos disponiveis");
        for (Projeto item : projs)
        {
            System.out.println("Descricao: "+item.getDesc());
            System.out.println("ID do projeto: "+item.getId());
        }
    }

    public void DadosUser(Usuario user)
    {
        System.out.println(user);
    }

    public void DadosAtiv(Atividade ativ)
    {
        System.out.println(ativ);
    }

    public void DadosProj(Projeto proj)
    {
        System.out.println(proj);
    }

    public void RelatorioAtiv (Atividade ativ)
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
            System.out.println("Nome: "+item.getNome());
            System.out.println("Func: "+item.getFunc());
        }

        System.out.println("Lista de tarefas: ");
        ArrayList<Tarefa> tasks = new ArrayList<Tarefa>();
        
        for(Tarefa item : ativ.getTarefas())
        {
            if (!item.getStatus().equals("Finalizada"))
            {
                tasks.add(item);
            }

            System.out.println("Desc: "+item.getDesc());
            System.out.println("Responsavel: "+BuscarUsuario (ativ.getUsuarios(), item.getProfissional()));
            System.out.println("Status: "+item.getStatus());
        }

        System.out.println("Status atual da atividade: "+ativ.getStatus());

        if (tasks.isEmpty())
        {
            System.out.println("Todas as tarefas Finalizadas, alterando Status para Concluida");
            ativ.setStatus("Concluida");
        }
        else
        {
            System.out.println("Tarefas restantes para concluir a atividade: ");
            for (Tarefa item : tasks)
            {
                System.out.println("Desc: "+item.getDesc());
                System.out.println("Responsavel: "+BuscarUsuario (ativ.getUsuarios(), item.getProfissional()));
            }
        }
    }

    public void RelatorioProj(Projeto proj)
    {
        System.out.println("Projeto descrito: ");
        System.out.println(proj.getDesc());

        System.out.println("Status do Projeto: "+proj.getStatus());

        System.out.println("Inicio do Projeto: ");
        System.out.println(MostrarDataHora(proj.getInicio()));
        
        System.out.println("Termino do Projeto: ");
        System.out.println(MostrarDataHora(proj.getTermino()));

        System.out.println("Coordenador do Projeto: ");
        String fodase = BuscarUsuario(proj.getProjetistas(), proj.getIdCoordenador()).getNome();
        System.out.println(fodase);


        System.out.println("Lista de Desenvolvedores: ");
        for (Usuario item : proj.getDesenvolvedores())    System.out.println(item.getNome());

        System.out.println("Lista de Testadores: ");
        for (Usuario item : proj.getTestadores())    System.out.println(item.getNome());

        System.out.println("Lista de Analistas: ");
        for (Usuario item : proj.getAnalistas())    System.out.println(item.getNome());

        if (!proj.getIntercambistas().isEmpty())
        {
            System.out.println("Lista de Intercambistas: ");
            for (Usuario item : proj.getIntercambistas())    System.out.println(item.getNome());
        }

        System.out.println("Tecnico do Projeto: ");
        System.out.println(BuscarUsuario(proj.getProjetistas(), proj.getIdTecnico()));

        System.out.println("Lista de Atividades: ");
        for (Atividade item : proj.getAtividades()) DadosAtiv(item);

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

    public String MostrarDataHora(LocalDateTime tempo)
    {
        if (tempo != null)
        {
            DateTimeFormatter dataForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        DateTimeFormatter horaForm = DateTimeFormatter.ofPattern("HH:mm");

            return  "Data: "+dataForm.format(tempo)+"\n"+
                    "Hora: "+horaForm.format(tempo);
        }
        else
        {
            return "Falta Definir";
        }
    }

    public ArrayList<Discente> ChecarPagamento(ArrayList<Usuario> users, String func)
    {
        ArrayList <Discente> pagar = new ArrayList<Discente>();
        System.out.println(func);
        LocalDateTime dtAtual = LocalDateTime.now();

        for (Usuario item : users)
        {
            if (item instanceof Discente)
            {
                Discente permitido = (Discente)item;
                LocalDateTime dtBolsista = permitido.getDiaPag();
                long dias = Duration.between(dtBolsista, dtAtual).toDays();

                if (dias >= 30)
                {
                    System.out.println("Nome: "+item.getNome());
                    System.out.println("Nome: "+item.getId());
                    pagar.add(permitido);
                }
            }
        }
        return pagar;
    }
    
    public String ChecarTipoUsuario (String tipo)
    {
        if (tipo.equals("Grad") || tipo.equals("Mest") || tipo.equals("Dout"))
        {
            return "Disc";
        }
        else if (tipo.equals("Prof") || tipo.equals("Pesq"))
        {
            return "Doce";
        }

        throw new RuntimeException("Erro: Tipo Invalido");
    }
}
