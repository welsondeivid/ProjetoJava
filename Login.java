import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Login
{
    DateTimeFormatter format = DateTimeFormatter.ofPattern ("HH:mm dd/MM/yyyy");
    Utilidades U = new Utilidades();
    Manager m = new Manager();
    Menu menu = new Menu();

    public void LoginOn(Scanner input, Usuario userLog)
    {
        int cmdLogin = -1;

        if (userLog instanceof Docente)
        {
            Docente user = (Docente)userLog;
            while (cmdLogin != 0)
            {
                cmdLogin = U.LerInt(input);

                if (cmdLogin == 1)
                {   
                    CriarProjeto(input);
                }

                else if (cmdLogin == 2)
                {
                    System.out.println("Somente Coordenadores podem editar projetos e somente o proprio");
                    
                    Projeto project = U.BuscarProjeto(m.getProjetos(), user.getProjeto());

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
                                project.EditarProjeto(m.getUsuarios(), input, format);
                            }
                            else if (cmdProjeto == 2)
                            {
                                project.DesignarCoordenador(m.getUsuarios(), input);
                            }
                            else if (cmdProjeto == 3)
                            {
                                menu.MenuAddRemove();
                                int num = U.LerInt(input);

                                if (num == 1)
                                {
                                    project.AdicionarUsuarios(m.getUsuarios(), input);
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
                    Projeto project = U.BuscarProjeto(m.getProjetos(), user.getProjeto());

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
                            menu.MenuEditarUsuario();
                            cmdUsuario = U.LerInt(input);

                            if (cmdUsuario == 1)
                            {
                                user.AlterarSenha(input);
                            }
                            else if (cmdUsuario == 2)
                            {
                                System.out.println("Digite o ID do projeto ao qual gostaria de ingressar: ");
                                U.ListarProjs(m.getProjetos());
                                int checkIdP = U.LerInt(input);

                                Projeto project = U.BuscarProjeto(m.getProjetos(), checkIdP);

                                user.IngressarProjeto(project);
                            }
                            else if (cmdUsuario == 3)
                            {
                                Projeto project = U.BuscarProjeto(m.getProjetos(), user.getProjeto());
                                
                                if (project != null )   user.IngressarAtividade(project, input);
                            }

                            else if (cmdUsuario == 4)
                            {
                                user.EditarTarefas(input);
                            }
                        }
                    }
                }
                
                else if (cmdLogin == 5) // continue daqui, procurar por possiveis listas e menus
                {
                    System.out.println("Somente Coordenadores podem alterar o Status de um projeto: ");

                    if (user.getCoord())
                    {
                        Docente coordenador = user;
                        Projeto project = U.BuscarProjeto(m.getProjetos(), coordenador.getProjeto());

                        if (project != null && project.getIdCoordenador() == coordenador.getProjeto())
                        {
                            System.out.println("Seu projeto é o : "+project.getDesc());
                            System.out.println("Status atual: "+project.getStatus());
                            System.out.println("Gostaria de alterar? 1 para sim");

                            int decisao = U.LerInt(input);

                            if (decisao == 1)
                            {
                                System.out.println("Escolha para qual Status gostaria de alterar: ");
                                System.out.println("Digite 1 para 'Iniciado': ");
                                System.out.println("Digite 2 para 'Concluido': ");

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
                    System.out.println("Digite 1 para consultar um usuario");
                    System.out.println("Digite 2 para consultar uma atividade");
                    System.out.println("Digite 3 para consultar um projeto");

                    int cmdConsulta = U.LerInt(input);

                    if (cmdConsulta == 1)
                    {
                            System.out.println("Digite o RG do usuario:");
                            int checkIdU = U.LerInt(input);
                            Usuario usuario = U.BuscarUsuario(m.getUsuarios(), checkIdU);

                            if (usuario != null)
                            {
                                System.out.println("Dados do usuario encontrado: ");
                                U.DadosUser(usuario);
                            }
                    }
                    else if (cmdConsulta == 2)
                    {
                            System.out.println(("Digite o id do projeto onde a atividade esta localizda: "));
                            int checkIdP = U.LerInt(input);
                            Projeto project = U.BuscarProjeto(m.getProjetos(), checkIdP);

                            if (project != null)
                            {
                                System.out.println("Digite o id da atividade: ");
                                int checkIdA = U.LerInt(input);
                                Atividade atividade = U.BuscarAtividade(project.getAtividades(), checkIdA);

                                if (atividade != null)
                                {
                                    System.out.println("Dados da atividade encontrada: ");
                                    U.DadosAtiv(atividade);
                                }
                            }
                    }
                    else if (cmdConsulta == 3)
                    {
                            System.out.println(("Digite o id do projeto: "));
                            int checkIdP = U.LerInt(input);
                            Projeto project = U.BuscarProjeto(m.getProjetos(), checkIdP);

                            if (project != null)
                            {
                                System.out.println("Dados do projeto encontrado: ");
                                U.DadosProj(project);
                            }
                    }
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

                        System.out.println("Digite 1 para: Relatorio de Projeto");
                        System.out.println("Digite 2 para: Relatorios das Atividades");

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

                        Projeto project = U.BuscarProjeto(m.getProjetos(), coordenador.getProjeto());
                        ArrayList <Discente> pagar = new ArrayList<Discente>();

                        System.out.println("Lista dos bolsistas que podem receber a bolsa: ");

                        pagar.addAll(U.ChecarPagamento(project.getDesenvolvedores(), "Desenvolvedores: \n"));
                        
                        pagar.addAll(U.ChecarPagamento(project.getTestadores(), "Testadores:  \n"));
                        
                        pagar.addAll(U.ChecarPagamento(project.getAnalistas(), "Analistas:  \n"));

                        System.out.println("Gostaria de fazer o pagamento?");
                        System.out.println("0 para: nenhum");
                        System.out.println("1 para: todos");
                        System.out.println("2 para: alguns");

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
                        Projeto project = U.BuscarProjeto(m.getProjetos(), coordenador.getProjeto());

                        System.out.println("Digite o RG do usuario que deseja adicionar:");
                        Usuario checkUser = U.BuscarUsuario(m.getUsuarios(), U.LerInt(input));

                        if (checkUser != null && checkUser instanceof Discente)
                        {
                            Discente intercamb = (Discente)checkUser;

                            if (intercamb.getProjInterCam() == 0)
                            {
                                Projeto projInterCam = U.BuscarProjeto(m.getProjetos(), intercamb.getProjeto());
                                
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
                menu.MenuUsuarioDocente();
            }
        }
        else if (userLog instanceof Discente)
        {
            Discente user = (Discente)userLog;
        }
    }

    public void CriarProjeto(Scanner input) //falar com yuri
    {
        System.out.println("Digite o ID do projeto: ");
        int idProject = U.LerInt(input);

        if (U.BuscarProjeto(m.getProjetos(), idProject) != null)
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
        int idCoord = U.LerInt(input);

        Usuario userCoord = U.BuscarUsuario(m.getUsuarios(), idCoord);

        if (userCoord != null && userCoord instanceof Docente)
        {
            if (idProject > 0 && descProject != null && inicio != null && termino != null)
            {
                Projeto project = new Projeto(idProject, descProject, inicio, termino, "Em processo de criacao", idCoord);
                m.getProjetos().add(project);
            }
        }
        else
        {
            System.out.println("Discente Nao pode ser Coordenador");
        }
    }
}