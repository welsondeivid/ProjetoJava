import java.time.LocalDateTime;
import java.util.ArrayList;

public class Login extends VarGlobais implements Lista
{
    public void LoginOn(Usuario userLog, Manager m) throws Exception
    {
        int cmdLogin = -1;

        if (userLog instanceof Docente) //Continue daqui, trate os Exceptions
        {
            Docente user = (Docente)userLog;
            while (cmdLogin != 0)
            {
                menu.MenuUsuarioDocente();
                cmdLogin = U.LerInt();

                if (cmdLogin == 1)
                {   
                    CriarProjeto(m);
                }

                else if (cmdLogin == 2)
                {
                    System.out.println("Somente Coordenadores podem editar projetos e somente o proprio");
                    
                    Projeto project = U.BuscarProjeto(m.getProjetos(), user.getProjeto());

                    if (user.getId() == project.getIdCoordenador())
                    {
                        System.out.println("Coordenador identificado");

                        int cmdProjeto = -1;

                        while (cmdProjeto != 0)
                        {
                            menu.MenuProjeto();
                            cmdProjeto = U.LerInt();

                            if (cmdProjeto == 1)
                            {
                                project.EditarProjeto(m.getUsuarios());
                            }
                            else if (cmdProjeto == 2)
                            {
                                project.DesignarCoordenador(m.getUsuarios());
                            }
                            else if (cmdProjeto == 3)
                            {
                                menu.MenuAddRemove();
                                int num = U.LerInt();

                                if (num == 1)
                                {
                                    project.AdicionarUsuarios(m.getUsuarios());
                                }
                                else if (num == 2)
                                {
                                    project.RemoverUsuarios(project.getProjetistas());
                                }
                            }
                            else if (cmdProjeto == 4)
                            {
                                project.RemoverIntercambista();
                            }
                            else if (cmdProjeto == 5)
                            {
                                menu.MenuAddRemove();
                                int num = U.LerInt();

                                if (num == 1)
                                {
                                    project.AdicionarAtividades();
                                }
                                else if (num == 2)
                                {

                                    project.RemoverAtividades();
                                }
                            }
                            else if (cmdProjeto == 6)
                            {
                                project.DefinirBolsa("Desenvolvedor");
                            }
                            else if (cmdProjeto == 7)
                            {
                                project.DefinirBolsa("Testador");
                            }
                            else if (cmdProjeto == 8)
                            {
                                project.DefinirBolsa("Analista"); 
                            }
                            else if (cmdProjeto == 9)
                            {
                                project.DefinirPrazoBolsa("Desenvolvedor");
                            }
                            else if (cmdProjeto == 10)
                            {
                                project.DefinirPrazoBolsa("Testador");
                            }
                            else if (cmdProjeto == 11)
                            {
                                project.DefinirPrazoBolsa("Analista");
                            }
                        }
                    }
                }

                else if (cmdLogin == 3)
                {
                    Projeto project = U.BuscarProjeto(m.getProjetos(), user.getProjeto());

                    Atividade atividade = U.BuscarAtividade(project.getAtividades(), user.getAtividade());
                    
                    menu.MenuAtividade();
                    int cmdAtividade = -1;      

                    while (cmdAtividade != 0)
                    {
                        cmdAtividade = U.LerInt();

                        if (user.getId() == atividade.getIdResponsavel() || user.getId() == project.getIdCoordenador())
                        {
                            if (cmdAtividade == 1)
                            {
                                atividade.EditarAtividade(project);
                            }
                            else if (cmdAtividade == 2)
                            {
                                atividade.DefinirResponsavel();
                            }
                            if (cmdAtividade == 3)
                            {
                                atividade.AdicionarUsuarios(project);
                            }
                            else if (cmdAtividade == 4)
                            {
                                atividade.RemoverUsuarios();
                            }
                            else if (cmdAtividade == 5)
                            {
                                atividade.AdicionarTarefas();
                            }
                            else if (cmdAtividade == 6)
                            {
                                atividade.RemoverTarefas();
                            }
                        }
                    }
                }

                else if (cmdLogin == 4)
                {
                    int cmdUsuario = -1;

                    while (cmdUsuario != 0)
                    {
                        menu.MenuEditarUsuario(true);
                        cmdUsuario = U.LerInt();

                        if (cmdUsuario == 1)
                        {
                            user.AlterarSenha();
                        }
                        else if (cmdUsuario == 2)
                        {
                            user.EditarTarefas();
                        }
                        else if (cmdUsuario == 3)
                        {
                            System.out.println("Digite o ID do projeto ao qual gostaria de ingressar: ");
                            ListarProjs(m.getProjetos());
                            int checkIdP = U.LerInt();

                            Projeto project = U.BuscarProjeto(m.getProjetos(), checkIdP);

                            user.IngressarProjeto(project);
                        }
                        else if (cmdUsuario == 4)
                        {
                            Projeto project = U.BuscarProjeto(m.getProjetos(), user.getProjeto());
                            
                            user.IngressarAtividade(project);
                        }
                    }
                }
                
                else if (cmdLogin == 5)
                {
                    System.out.println("Somente Coordenadores podem alterar o Status de um projeto: ");

                    if (user.getCoord())
                    {
                        System.out.println("Coordenador identificado");

                        Docente coordenador = user;
                        Projeto project = U.BuscarProjeto(m.getProjetos(), coordenador.getProjeto());

                        if (project.getIdCoordenador() == coordenador.getProjeto())
                        {
                            System.out.println("Seu projeto é o : "+project.getDesc());
                            System.out.println("Status atual: "+project.getStatus());
                            System.out.println("Gostaria de alterar? 1 para sim");

                            int decisao = U.LerInt();

                            if (decisao == 1)
                            {
                                System.out.println("Escolha para qual Status gostaria de alterar: ");
                                menu.MenuStatusProjeto();

                                decisao = U.LerInt();
                                if (decisao == 1)
                                {
                                    boolean check = m.ChecarStatusDoProjeto(project);

                                    if (check)
                                    {
                                        System.out.println("Status alterado com sucesso");
                                        project.setStatus("Em andamento");
                                    }
                                    else
                                    {
                                        System.out.println("Adicione as infos que faltam");
                                    }
                                }
                                else if (decisao == 2)
                                {
                                    ArrayList<Atividade> ativs = new ArrayList<Atividade>();

                                    for (Atividade item : project.getAtividades())
                                    {
                                        if (!item.getStatus().equals("Concluida"))  ativs.add(item);
                                    }
                                    if (ativs.isEmpty()) 
                                    {
                                        System.out.println("Status alterado para 'Concluído'");
                                        project.setStatus("Concluído");
                                    }
                                    else
                                    {
                                        System.out.println("Alguma(s) atividades ainda não foram concluídas");
                                        for (Atividade item : ativs)
                                        {
                                            System.out.println(item.getId()+": "+item.getDesc());
                                        }
                                    }
                                }
                            }    
                        }
                    }
                    else
                    {
                        System.out.println("Erro: usuario nao é coordenador");
                    }
                }

                else if (cmdLogin == 6)
                {
                    System.out.println("O que gostaria de consultar?");
                    menu.MenuConsulta();

                    U.Consultar(m.getProjetos(), m.getUsuarios());            
                }                

                else if (cmdLogin == 7)
                {
                    System.out.println("Somente Coordenadores podem checar o relatorio de um projeto");
                    System.out.println("Somente Coordenadores podem checar os relatorios das atividades de um projeto");
                    System.out.println("Responsaveis podem checar o relatorio de sua propria atividade");
                    
                    Docente userCheck = user;
                    Projeto project = U.BuscarProjeto(m.getProjetos(), user.getProjeto());

                    if (userCheck.getCoord())
                    {
                        System.out.println("Coordenador identificado");

                        menu.MenuRelatorio();

                        int decisao = U.LerInt();
                        
                        if (decisao == 1)
                        {
                            U.RelatorioProj(project);
                        }
                        else if (decisao == 2)
                        {     
                            for (Atividade item : project.getAtividades())
                            {
                                U.RelatorioAtiv(item);
                            }                    
                        }
                    }
                    else
                    {
                        Atividade atividade = U.BuscarAtividade(project.getAtividades(), userCheck.getAtividade());

                        if (user.getId() == atividade.getIdResponsavel())
                        {
                            System.out.println("Responsavel identificado");
                            U.RelatorioAtiv(atividade);
                        }
                        else
                        {
                            System.out.println("Erro: Sem acesso permitido para relatorios");
                        }
                    }
                }
                
                else if (cmdLogin == 8)
                {
                    System.out.println("Somente Coordenadores podem fazer o pagamento");
                    Docente coordenador = user;

                    if (coordenador.getCoord())
                    {
                        System.out.println("Coordenador identificado");

                        Projeto project = U.BuscarProjeto(m.getProjetos(), coordenador.getProjeto());
                        ArrayList <Discente> pagar = new ArrayList<Discente>();

                        System.out.println("Lista dos bolsistas que podem receber a bolsa: ");

                        pagar.addAll(U.ChecarPagamento(project.getDesenvolvedores(), "Desenvolvedores: \n"));
                        
                        pagar.addAll(U.ChecarPagamento(project.getTestadores(), "Testadores:  \n"));
                        
                        pagar.addAll(U.ChecarPagamento(project.getAnalistas(), "Analistas:  \n"));

                        System.out.println("Gostaria de fazer o pagamento?");
                        menu.MenuPagamento();

                        int cmdPag = U.LerInt();

                        if (cmdPag == 1)
                        {
                            for (Discente item : pagar)
                            {
                                item.setDiaPag(LocalDateTime.now());
                            }

                            System.out.println("Pagamento Realizado a todos");
                        }
                        else if (cmdPag == 2)
                        {
                            for (Discente item : pagar)
                            {
                                System.out.println("Gostaria de pagar? 1 para sim");
                                System.out.println(item.getNome());
                                System.out.println(item.getId());

                                int num = U.LerInt();
                                if (num == 1)   item.setDiaPag(LocalDateTime.now());
                            } 
                        }
                        pagar = null;
                    }
                }

                else if (cmdLogin == 9)
                {
                    System.out.println("Somente Coordenadores podem adicionar intercambistas em projetos");
                    Docente coordenador = user;
                    
                    if (coordenador.getCoord())
                    {
                        System.out.println("Coordenador identificado");
                        Projeto project = U.BuscarProjeto(m.getProjetos(), coordenador.getProjeto());

                        System.out.println("Digite o RG do usuario que deseja adicionar:");
                        Usuario checkUser = U.BuscarUsuario(m.getUsuarios(), U.LerInt());

                        if (checkUser instanceof Discente)
                        {
                            Discente intercamb = (Discente)checkUser;

                            if (intercamb.getProjInterCam() == 0)
                            {
                                try {
                                    Projeto projInterCam = U.BuscarProjeto(m.getProjetos(), intercamb.getProjeto());

                                    if (projInterCam != project)
                                    {
                                        System.out.println("Para qual atividade ele sera atribuido?");
                                        int checkIdA = U.LerInt();
                                        Atividade atividade = U.BuscarAtividade(project.getAtividades(), checkIdA);

                                        atividade.setUsuarios(intercamb);
                                        project.setIntercambista(intercamb);
                                        intercamb.setProjInterCam(project.getId());
                                        intercamb.setAtivInterCam(atividade.getId());
                                    }
                                    else
                                    {
                                        System.out.println("Usuario ja esta alocado nesse projeto");
                                    }
                                } catch (Exception e) {
                                    throw new RuntimeException("Usuario sem projeto alocado, incapaz de fazer intercambio");
                                }
                            }
                            else
                            {
                                System.out.println("Usuario ja faz intercambio");
                            }
                        }
                    }
                }
                System.out.println("Digite o proximo comando: ");
            }
        }
        else if (userLog instanceof Discente)
        {
            Discente user = (Discente)userLog;
            while (cmdLogin != 0)
            {
                menu.MenuUsuarioDiscente();
                cmdLogin = U.LerInt();

                if (cmdLogin == 1)
                {
                    int cmdUsuario = -1;

                    while (cmdUsuario != 0)
                    {
                        menu.MenuEditarUsuario(false);
                        
                        cmdUsuario = U.LerInt();

                        if (cmdUsuario == 1)
                        {
                            user.AlterarSenha();
                        }
                        else if (cmdUsuario == 2)
                        {
                            user.EditarTarefas();
                        }
                    }
                }
                else if (cmdLogin == 2)
                {
                    System.out.println("O que gostaria de consultar?");
                    menu.MenuConsulta();

                    U.Consultar(m.getProjetos(), m.getUsuarios());
                }
                else if (cmdLogin == 3)
                {
                    System.out.print("Um Discente apenas tem acesso a um relatorio de atividade se for o respnsavel");
                    
                    try {
                        Projeto proj = U.BuscarProjeto(m.getProjetos(), user.getProjeto());
                        
                        Atividade ativ = U.BuscarAtividade(proj.getAtividades(), user.getAtividade());
                        if (ativ.getIdResponsavel() == user.getId())
                        {
                            System.out.println("Acesso liberado");
                            U.RelatorioAtiv(ativ);
                        }
                        else
                        {
                            System.out.println("Acesso negado: Usuario nao eh o responsavel");
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("Usuario nao esta alocado em um projeto");
                    }
                }
            }
        }
    
        return;
    }

    @Override
    public void ListarProjs(ArrayList<Projeto> projs)
    {
        System.out.println("        Lista de projetos disponiveis");
        for (Projeto item : projs)
        {
            System.out.println("ID do projeto: "+item.getId());
            System.out.println("Descricao: "+item.getDesc());
            System.out.println("Coordenador: "+U.BuscarUsuario(item.getProjetistas(), item.getIdCoordenador()).getNome());
        }
    }

    @Override
    public void ListarAtivs(ArrayList<Atividade> ativs) {
        
        
    }

    @Override
    public void ListarTasks(ArrayList<Tarefa> tasks) {
        
        
    }

    @Override
    public void ListarUsers(ArrayList<Usuario> users) {
        
        
    }

    @Override
    public void ListarDocentes(ArrayList<Usuario> users) {
        
        
    }

    @Override
    public void ListarDiscentes(ArrayList<Usuario> users) {
        
        
    }

    public void CriarProjeto(Manager m) throws Exception
    {
        System.out.println("Digite o ID do projeto: ");
        int idProject = U.LerInt();

        try {
            U.BuscarProjeto(m.getProjetos(), idProject);
        } catch (Exception e) {
            System.out.println("ID disponivel");
        }

        System.out.println("Digite a descricao do projeto: ");
        String descProject = input.nextLine();

        System.out.println ("Digite a data de inicio no formato: HH:mm dd/MM/yyyy");
        LocalDateTime inicio = LocalDateTime.parse(input.nextLine(), format);

        System.out.println ("Digite a data de termino no formato: HH:mm dd/MM/yyyy");
        LocalDateTime termino = LocalDateTime.parse(input.nextLine(), format);

        System.out.println("Defina o Coordenador do projeto, somente Professor/Pesquisador");
        U.ListarDocentes(m.getUsuarios());
        int idCoord = U.LerInt();

        Usuario userCoord = U.BuscarUsuario(m.getUsuarios(), idCoord);

        if (userCoord instanceof Docente)
        {
            Docente user = (Docente)userCoord;

            if (idProject > 0 && descProject != null && inicio != null && termino != null)
            {
                Projeto project = new Projeto(idProject, descProject, inicio, termino, "Em processo de criacao", user);
                
                user.setProjeto(idProject);
                user.setCoord(true);
                user.setDiaIngresso(LocalDateTime.now());
                m.getProjetos().add(project);
            }
        }
        else
        {
            System.out.println("Discente nao pode ser Coordenador");
        }
    }
}