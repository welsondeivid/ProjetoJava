import java.util.Scanner;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Utilidades implements Lista{
	    
    public int LerInt(Scanner input)
    {
        int num = Integer.parseInt(input.next());
        input.nextLine();
        return num;
    }
    
    public float LerFloat(Scanner input)
    {
        float num = input.nextFloat();
        input.nextLine();
        return num;
    }   

    public void CriarProjeto(Scanner input, ArrayList<Projeto> projs, ArrayList<Usuario> users, DateTimeFormatter format)
    {
        System.out.println("Digite o ID do projeto: ");
        int idProject = LerInt(input);

        if (BuscarProjeto(projs, idProject) != null)
        {
            System.out.println("ID de projeto ja consta no sistema");
            System.out.println("Falha ao criar projeto");
            return;
        }

        System.out.println("Digite a descricao do projeto: ");
        String descProject = input.nextLine();

        System.out.println ("Digite a data de inicio no formato: HH:mm dd/MM/yyyy");
        LocalDateTime inicio = LocalDateTime.parse(input.nextLine(), format);

        System.out.println ("Digite a data de termino no formato: HH:mm dd/MM/yyyy");
        LocalDateTime termino = LocalDateTime.parse(input.nextLine(), format);

        System.out.println("Defina o Coordenador do projeto, somente Professor/Pesquisador");
        int idCoord = LerInt(input);

        Usuario userCoord = BuscarUsuario(users, idCoord);

        if (userCoord != null && userCoord instanceof Docente)
        {
            if (idProject > 0 && descProject != null && inicio != null && termino != null)
            {
                Projeto project = new Projeto(idProject, descProject, inicio, termino, "Em processo de criacao", idCoord);
                projs.add(project);
            }
        }
        else
        {
            System.out.println("Discente Nao pode ser Coordenador");
        }
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

    public void Consultar (Scanner input, ArrayList<Projeto> projs, ArrayList<Usuario> users)
    {
        int cmdConsulta = LerInt(input);

        if (cmdConsulta == 1)
        {
                System.out.println("Digite o RG do usuario:");
                ListarUsers (users);

                int checkIdU = LerInt(input);
                Usuario usuario = BuscarUsuario(users, checkIdU);

                if (usuario != null)
                {
                    System.out.println("Dados do usuario encontrado: ");
                    DadosUser(usuario);
                }
        }
        else if (cmdConsulta == 2)
        {
                System.out.println(("Digite o id do projeto onde a atividade esta localizda: "));
                ListarProjs(projs);

                int checkIdP = LerInt(input);
                Projeto project = BuscarProjeto(projs, checkIdP);

                if (project != null)
                {
                    System.out.println("Digite o id da atividade: ");
                    ListarAtivs(project.getAtividades());

                    int checkIdA = LerInt(input);
                    Atividade atividade = BuscarAtividade(project.getAtividades(), checkIdA);

                    if (atividade != null)
                    {
                        System.out.println("Dados da atividade encontrada: ");
                        DadosAtiv(atividade);
                    }
                }
        }
        else if (cmdConsulta == 3)
        {
                System.out.println(("Digite o id do projeto: "));
                ListarProjs(projs);

                int checkIdP = LerInt(input);
                Projeto project = BuscarProjeto(projs, checkIdP);

                if (project != null)
                {
                    System.out.println("Dados do projeto encontrado: ");
                    DadosProj(project);
                }
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
        /*System.out.println("Nome: "+user.getNome());
        System.out.println("Email: "+user.getEmail());

        if (user.getTipo().equals("Grad"))
        {
            System.out.print("Aluno Graduando");
        }
        else if (user.getTipo().equals("Mest"))
        {
            System.out.print("Aluno Mestrando");
        }
        else if (user.getTipo().equals("Dout"))
        {
            System.out.print("Aluno Doutorando");
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

        if (!user.getCoord() &&  user.getFunc() != null)
        {
            if (user.getFunc().equals("Devp"))
            {
                System.out.println(" e Desenvolvedor");
            }
            else if (user.getFunc().equals("Test"))
            {
                System.out.println(" e Tecnico");
            }
            else if (user.getFunc().equals("Anlt"))
            {
                System.out.println(" e Analista");
            }
            else if (user.getFunc().equals("Tecn"))
            {
                System.out.println(" e Tecnico");
            }
        }
        System.out.println();

        if (user.getProjeto() != 0)
        {
            System.out.println("Projeto associado: "+user.getProjeto());
            System.out.println("Dia de ingresso: ");
            MostrarDataHora(user.getDiaPag());
            System.out.println("Atividade associada: "+user.getAtividade());

            System.out.println("Lista de Tarefas:");
            for (Tarefa item : user.getTarefas())
            {
                System.out.println(item.getDesc());
            }
        }
        else
        {
            System.out.println("Sem Projeto no momento");
        }
        

        if (user.getProjInterCam() != 0)
        {
            System.out.println("Faz intercambio no projeto: "+user.getProjInterCam());
            System.out.println("Designado para a atividade: "+user.getAtivInterCam());
        }
        else
        {
            if (!user.getTipo().equals("Prof") && !user.getTipo().equals("Pesq"))
            {
                System.out.println("Disponivel para intercambio");
            }
        }*/
    }

    public void DadosAtiv(Atividade ativ)
    {
        System.out.println(ativ);
        /*System.out.println("Atividade descrita: ");
        System.out.println(ativ.getDesc());

        System.out.print("Responsavel pela Atividade: ");
        System.out.println(BuscarUsuario(ativ.getUsuarios(), ativ.getIdResponsavel()).getNome());

        System.out.println("Inicio da Atividade: ");
        MostrarDataHora(ativ.getInicio());

        System.out.println("Termino da Atividade: ");
        MostrarDataHora(ativ.getTermino());*/
    }

    public void DadosProj(Projeto proj)
    {
        System.out.println(proj);
        /*System.out.println("Projeto descrito: ");
        System.out.println(proj.getDesc());

        System.out.print("Status do Projeto: "+proj.getStatus());

        System.out.println("Inicio do Projeto: ");
        MostrarDataHora(proj.getInicio());
        
        System.out.println("Termino do Projeto: ");
        MostrarDataHora(proj.getTermino());

        System.out.println("Coordenador do Projeto: ");
        System.out.println(BuscarUsuario(proj.getProjetistas(), proj.getIdCoordenador()).getNome());*/
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
        MostrarDataHora(proj.getInicio());
        
        System.out.println("Termino do Projeto: ");
        MostrarDataHora(proj.getTermino());

        System.out.println("Coordenador do Projeto: ");
        System.out.println(BuscarUsuario(proj.getProjetistas(), proj.getIdCoordenador()));


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

        return null;
    }
}
