import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Utilidades implements Lista{
	    
    GerenciadorBolsa gerBolsa = new GerenciadorBolsa();
    Scanner input = new Scanner(System.in);

    public int LerInt()
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
    
    public float LerFloat()
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

        throw new RuntimeException("Usuario fora do sistema");
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

        throw new RuntimeException("Projeto fora do sistema");
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

        throw new RuntimeException("Atividade fora do sistema");
    }

    public void Consultar (ArrayList<Projeto> projs, ArrayList<Usuario> users)
    {
        int cmdConsulta = LerInt();

        if (cmdConsulta == 1)
        {
            System.out.println("Digite o RG do usuario:");
            ListarUsers (users);

            int checkIdU = LerInt();
            Usuario usuario = BuscarUsuario(users, checkIdU);

            System.out.println("Dados do usuario encontrado: ");
            DadosUser(usuario);
        }
        else if (cmdConsulta == 2)
        {
            System.out.println(("Digite o id do projeto onde a atividade esta localizda: "));
            ListarProjs(projs);

            int checkIdP = LerInt();
            Projeto project = BuscarProjeto(projs, checkIdP);

            System.out.println("Digite o id da atividade: ");
            ListarAtivs(project.getAtividades());

            int checkIdA = LerInt();
            Atividade atividade = BuscarAtividade(project.getAtividades(), checkIdA);

            System.out.println("Dados da atividade encontrada: ");
            DadosAtiv(atividade);
        }
        else if (cmdConsulta == 3)
        {
            System.out.println(("Digite o id do projeto: "));
            ListarProjs(projs);

            int checkIdP = LerInt();
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
        System.out.print(MostrarDataHora(ativ.getInicio()));

        System.out.println("Termino da Atividade: ");
        System.out.print(MostrarDataHora(ativ.getTermino()));

        System.out.println("Lista de usuarios: ");
        
        if (!ativ.getUsuarios().isEmpty())
        {
            for(Usuario item : ativ.getUsuarios())
            {
                System.out.println("Nome: "+item.getNome());
                System.out.println("Func: "+item.getFunc());
            }
        }
        else    System.out.println("Sem usuario no momento");

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
        System.out.println(proj.getDesc()+"\n");

        System.out.println("Status do Projeto: "+proj.getStatus()+"\n");

        System.out.println("Inicio do Projeto: ");
        System.out.print(MostrarDataHora(proj.getInicio())+"\n");
        
        System.out.println("Termino do Projeto: ");
        System.out.print(MostrarDataHora(proj.getTermino())+"\n");

        System.out.println("Coordenador do Projeto: ");
        String coordenador = BuscarUsuario(proj.getProjetistas(), proj.getIdCoordenador()).getNome();
        System.out.println(coordenador+"\n");


        System.out.println("Lista de Desenvolvedores: ");
        if (!proj.getDesenvolvedores().isEmpty())   for (Usuario item : proj.getDesenvolvedores())    System.out.println(item.getNome());
        else    System.out.println("Sem Desenvolvedores"+"\n");
        
        System.out.println("Lista de Testadores: ");
        if (!proj.getTestadores().isEmpty())   for (Usuario item : proj.getTestadores())    System.out.println(item.getNome());
        else    System.out.println("Sem Testadores"+"\n");
        
        System.out.println("Lista de Analistas: ");
        if (!proj.getAnalistas().isEmpty())   for (Usuario item : proj.getAnalistas())    System.out.println(item.getNome());
        else    System.out.println("Sem Analistas"+"\n");

        System.out.println("Lista de Intercambistas: ");
        if (!proj.getIntercambistas().isEmpty())    for (Usuario item : proj.getIntercambistas())    System.out.println(item.getNome());

        System.out.println("Tecnico do Projeto: ");
        String tecnico = "Sem Tecnico";
        if (proj.getIdTecnico() != 0)   tecnico = BuscarUsuario(proj.getProjetistas(), proj.getIdTecnico()).getNome();
        System.out.println(tecnico+"\n");  

        System.out.println("Lista de Atividades: ");
        if (!proj.getAtividades().isEmpty())    for (Atividade item : proj.getAtividades()) DadosAtiv(item);
        else    System.out.println("Sem atividades no momento");

        System.out.println("\n"+gerBolsa);
    }

    public String MostrarDataHora(LocalDateTime tempo)
    {
        if (tempo != null)
        {
            DateTimeFormatter dataForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        DateTimeFormatter horaForm = DateTimeFormatter.ofPattern("HH:mm");

            return  "Data: "+dataForm.format(tempo)+"\n"+
                    "Hora: "+horaForm.format(tempo)+"\n";
        }
        else
        {
            return "Falta Definir\n";
        }
    }

    public LocalDateTime DefinirDataHora() throws Exception
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern ("HH:mm dd/MM/yyyy");
        System.out.println ("Digite a data do novo prazo no formato: HH:mm dd/MM/yyyy");

        try {
            LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
            return tempoBolsa;
        } catch (Exception e) {
            throw new RuntimeException ("Formato de Data/Hora invalido\n");
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
    
    public String ChecarTipoUsuario (int tipo)
    {
        if (tipo == 1 || tipo == 2 || tipo == 3)
        {
            return "Disc";
        }
        else if (tipo == 4 || tipo == 5)
        {
            return "Doce";
        }

        throw new RuntimeException("Erro: Valor Invalido\nTente Novamente: ");
    }
}
