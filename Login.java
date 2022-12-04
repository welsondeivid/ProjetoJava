import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Login implements Lista
{
    DateTimeFormatter format = DateTimeFormatter.ofPattern ("HH:mm dd/MM/yyyy");
    Utilidades U = new Utilidades();
    Manager m = new Manager();
    Menu menu = new Menu();

    public void LoginOn(Scanner input, Usuario userLog, ArrayList<Usuario> usersMain, ArrayList<Projeto> projsMain)
    {
        int cmdLogin = -1;

        if (userLog instanceof Docente)
        {
            Docente user = (Docente)userLog;
            while (cmdLogin != 0)
            {
                menu.MenuUsuarioDocente();
                cmdLogin = U.LerInt(input);

                if (cmdLogin == 1)
                {   
                    U.CriarProjeto(input, projsMain, usersMain, format);
                }

                else if (cmdLogin == 2)
                {
                    System.out.println("Somente Coordenadores podem editar projetos e somente o proprio");
                    
                    Projeto project = U.BuscarProjeto(projsMain, user.getProjeto());

                    if (project != null && user.getId() == project.getIdCoordenador())
                    {
                        System.out.println("Coordenador identificado");

                        menu.MenuProjeto();

                        int cmdProjeto = -1;

                        while (cmdProjeto != 0)
                        {
                            cmdProjeto = U.LerInt(input);

                            if (cmdProjeto == 1)
                            {
                                project.EditarProjeto(usersMain, input, format);
                            }
                            else if (cmdProjeto == 2)
                            {
                                project.DesignarCoordenador(usersMain, input);
                            }
                            else if (cmdProjeto == 3)
                            {
                                menu.MenuAddRemove();
                                int num = U.LerInt(input);

                                if (num == 1)
                                {
                                    project.AdicionarUsuarios(usersMain, input);
                                }
                                else if (num == 2)
                                {
                                    project.RemoverUsuarios(project.getProjetistas(), input);
                                }
                            }
                            else if (cmdProjeto == 4)
                            {
                                project.RemoverIntercambista(input);
                            }
                            else if (cmdProjeto == 5)
                            {
                                menu.MenuAddRemove();
                                int num = U.LerInt(input);

                                if (num == 1)
                                {
                                    project.AdicionarAtividades(format, input);
                                }
                                else if (num == 2)
                                {

                                    project.RemoverAtividades(input);
                                }
                            }
                            else if (cmdProjeto == 6)
                            {
                                project.DefinirBolsa("Desenvolvedor", input);
                            }
                            else if (cmdProjeto == 7)
                            {
                                project.DefinirBolsa("Testador", input);
                            }
                            else if (cmdProjeto == 8)
                            {
                                project.DefinirBolsa("Analista", input); 
                            }
                            else if (cmdProjeto == 9)
                            {
                                project.DefinirPrazoBolsa("Desenvolvedor", input, format);
                            }
                            else if (cmdProjeto == 10)
                            {
                                project.DefinirPrazoBolsa("Testador", input, format);
                            }
                            else if (cmdProjeto == 11)
                            {
                                project.DefinirPrazoBolsa("Analista", input, format);
                            }
                            
                            menu.MenuProjeto();
                        }
                    }

                    else
                    {
                        System.out.println("Erro: Projeto fora do Sistema");
                    }
                }

                else if (cmdLogin == 3)
                {
                    Projeto project = U.BuscarProjeto(projsMain, user.getProjeto());

                    if (project != null)
                    {
                        Atividade atividade = U.BuscarAtividade(project.getAtividades(), user.getAtividade());

                        if (atividade != null)
                        {
                            menu.MenuAtividade();

                            int cmdAtividade = -1;

                            while (cmdAtividade != 0)
                            {
                                cmdAtividade = U.LerInt(input);

                                if (user.getId() == atividade.getIdResponsavel() || user.getId() == project.getIdCoordenador())
                                {
                                    if (cmdAtividade == 1)
                                    {
                                        atividade.EditarAtividade(project, input);
                                    }
                                    else if (cmdAtividade == 2)
                                    {
                                        atividade.DefinirResponsavel(input);
                                    }
                                    if (cmdAtividade == 3)
                                    {
                                        atividade.AdicionarUsuarios(project, input);
                                    }
                                    else if (cmdAtividade == 4)
                                    {
                                        atividade.RemoverUsuarios(input);
                                    }
                                    else if (cmdAtividade == 5)
                                    {
                                        atividade.AdicionarTarefas(input);
                                    }
                                    else if (cmdAtividade == 6)
                                    {
                                        atividade.RemoverTarefas(input);
                                    }
                                }
                            }
                        }
                    }
                }

                else if (cmdLogin == 4)
                {
                    if (user != null)
                    {
                        int cmdUsuario = -1;

                        while (cmdUsuario != 0)
                        {
                            menu.MenuEditarUsuario(true);
                            cmdUsuario = U.LerInt(input);

                            if (cmdUsuario == 1)
                            {
                                user.AlterarSenha(input);
                            }
                            else if (cmdUsuario == 2)
                            {
                                user.EditarTarefas(input);
                            }
                            else if (cmdUsuario == 3)
                            {
                                System.out.println("Digite o ID do projeto ao qual gostaria de ingressar: ");
                                ListarProjs(projsMain);
                                int checkIdP = U.LerInt(input);

                                Projeto project = U.BuscarProjeto(projsMain, checkIdP);

                                user.IngressarProjeto(project);
                            }
                            else if (cmdUsuario == 4)
                            {
                                Projeto project = U.BuscarProjeto(projsMain, user.getProjeto());
                                
                                if (project != null )   user.IngressarAtividade(project, input);
                            }
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
                        Projeto project = U.BuscarProjeto(projsMain, coordenador.getProjeto());

                        if (project != null && project.getIdCoordenador() == coordenador.getProjeto())
                        {
                            System.out.println("Seu projeto é o : "+project.getDesc());
                            System.out.println("Status atual: "+project.getStatus());
                            System.out.println("Gostaria de alterar? 1 para sim");

                            int decisao = U.LerInt(input);

                            if (decisao == 1)
                            {
                                System.out.println("Escolha para qual Status gostaria de alterar: ");
                                menu.MenuStatusProjeto();

                                decisao = U.LerInt(input);
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

                    U.Consultar(input, projsMain, usersMain);            
                }                

                else if (cmdLogin == 7)
                {
                    System.out.println("Somente Coordenadores podem checar o relatorio de um projeto");
                    System.out.println("Somente Coordenadores podem checar os relatorios das atividades de um projeto");
                    System.out.println("Responsaveis podem checar o relatorio de sua propria atividade");
                    
                    Docente userCheck = user;
                    Projeto project = U.BuscarProjeto(projsMain, user.getProjeto());

                    if (userCheck.getCoord())
                    {
                        System.out.println("Coordenador identificado");

                        menu.MenuRelatorio();

                        int decisao = U.LerInt(input);
                        
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

                        if (atividade != null && user.getId() == atividade.getIdResponsavel())
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

                        Projeto project = U.BuscarProjeto(projsMain, coordenador.getProjeto());
                        ArrayList <Discente> pagar = new ArrayList<Discente>();

                        System.out.println("Lista dos bolsistas que podem receber a bolsa: ");

                        pagar.addAll(U.ChecarPagamento(project.getDesenvolvedores(), "Desenvolvedores: \n"));
                        
                        pagar.addAll(U.ChecarPagamento(project.getTestadores(), "Testadores:  \n"));
                        
                        pagar.addAll(U.ChecarPagamento(project.getAnalistas(), "Analistas:  \n"));

                        System.out.println("Gostaria de fazer o pagamento?");
                        menu.MenuPagamento();

                        int cmdPag = U.LerInt(input);

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

                                int num = U.LerInt(input);
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
                        Projeto project = U.BuscarProjeto(projsMain, coordenador.getProjeto());

                        System.out.println("Digite o RG do usuario que deseja adicionar:");
                        Usuario checkUser = U.BuscarUsuario(usersMain, U.LerInt(input));

                        if (checkUser != null && checkUser instanceof Discente)
                        {
                            Discente intercamb = (Discente)checkUser;

                            if (intercamb.getProjInterCam() == 0)
                            {
                                Projeto projInterCam = U.BuscarProjeto(projsMain, intercamb.getProjeto());
                                
                                if (projInterCam != null)
                                {
                                    if (projInterCam != project)
                                    {
                                        System.out.println("Para qual atividade ele sera atribuido?");
                                        int checkIdA = U.LerInt(input);
                                        Atividade atividade = U.BuscarAtividade(project.getAtividades(), checkIdA);

                                        if (atividade != null)
                                        {
                                            atividade.setUsuarios(intercamb);
                                            project.setIntercambista(intercamb);
                                            intercamb.setProjInterCam(project.getId());
                                            intercamb.setAtivInterCam(atividade.getId());
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Usuario ja esta alocado nesse projeto");
                                    }
                                }
                                else
                                {
                                    System.out.println("Usuario sem projeto alocado, incapaz de fazer intercambio");
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
                cmdLogin = U.LerInt(input);

                if (cmdLogin == 1)
                {
                    if (user != null)
                    {
                        int cmdUsuario = -1;

                        while (cmdUsuario != 0)
                        {
                            menu.MenuEditarUsuario(false);
                            cmdUsuario = U.LerInt(input);

                            if (cmdUsuario == 1)
                            {
                                user.AlterarSenha(input);
                            }
                            else if (cmdUsuario == 2)
                            {
                                user.EditarTarefas(input);
                            }
                        }
                    }
                }
                else if (cmdLogin == 2)
                {
                    System.out.println("O que gostaria de consultar?");
                    menu.MenuConsulta();

                    U.Consultar(input, projsMain, usersMain);
                }
                else if (cmdLogin == 3)
                {
                    System.out.print("Um Discente apenas tem acesso a um relatorio de atividade se for o respnsavel");
                    
                    Projeto proj = U.BuscarProjeto(projsMain, user.getProjeto());

                    if (proj != null)
                    {
                        Atividade ativ = U.BuscarAtividade(proj.getAtividades(), user.getAtividade());

                        if (ativ != null && ativ.getIdResponsavel() == user.getId())
                        {
                            System.out.println("Acesso liberado");
                            U.RelatorioAtiv(ativ);
                        }
                        else
                        {
                            System.out.println("Acesso negado: Usuario nao eh o responsavel");
                        }
                    }
                    else
                    {
                        System.out.println("Usuario nao esta alocado em um projeto");
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
}