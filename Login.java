import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
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
                        menu.MenuProjeto();

                        int cmdProjeto = -1;

                        while (cmdProjeto != 0)
                        {
                            cmdProjeto = U.LerInt(input);

                            if (cmdProjeto == 1)
                            {
                                project.EditarProjeto(m.getUsuarios(), project, input, format);
                            }
                            else if (cmdProjeto == 2)
                            {
                                project.DesignarCoordenador(m.getUsuarios(), input, project);
                            }
                            else if (cmdProjeto == 3)
                            {
                                System.out.println ("Deseja 1 = Adicionar ou 2 = Remover ?");
                                int num = U.LerInt(input);

                                if (num == 1)
                                {
                                    project.AdicionarUsuarios(m.getUsuarios(), input, project);
                                }
                                else if (num == 2)
                                {
                                    project.RemoverUsuarios(project.getProjetistas(), input, project);
                                }
                            }
                            else if (cmdProjeto == 4)
                            {
                                System.out.println ("Deseja 1 = Adicionar ou 2 = Remover ?");
                                int num = U.LerInt(input);

                                if (num == 1)
                                {
                                    project.AdicionarAtividades(format, input, project);
                                }
                                else if (num == 2)
                                {

                                    project.RemoverAtividades(input, project);
                                }
                            }
                            else if (cmdProjeto == 5)
                            {
                                project.DefinirBolsa("Desenvolvedor", project, input);
                            }
                            else if (cmdProjeto == 6)
                            {
                                project.DefinirBolsa("Testador", project, input);
                            }
                            else if (cmdProjeto == 7)
                            {
                                project.DefinirBolsa("Analista", project, input); 
                            }
                            else if (cmdProjeto == 8)
                            {
                                project.DefinirPrazoBolsa("Desenvolvedor", project, input, format);
                            }
                            else if (cmdProjeto == 9)
                            {
                                project.DefinirPrazoBolsa("Testador", project, input, format);
                            }
                            else if (cmdProjeto == 10)
                            {
                                project.DefinirPrazoBolsa("Analista", project, input, format);
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
                                        atividade.EditarAtividade(project, atividade, input);
                                    }
                                    else if (cmdAtividade == 2)
                                    {
                                        atividade.DefinirResponsavel(atividade, input);
                                    }
                                    if (cmdAtividade == 3)
                                    {
                                        atividade.AdicionarUsuarios(project, atividade, input);
                                    }
                                    else if (cmdAtividade == 4)
                                    {
                                        atividade.RemoverUsuarios(atividade, input);
                                    }
                                    else if (cmdAtividade == 5)
                                    {
                                        atividade.AdicionarTarefas(atividade, input);
                                    }
                                    else if (cmdAtividade == 6)
                                    {
                                        atividade.RemoverTarefas(atividade, input);
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
                                user.AlterarSenha(user, input);
                            }
                            else if (cmdUsuario == 2)
                            {
                                System.out.println("Digite o ID do projeto ao qual gostaria de ingressar: ");
                                int checkIdP = U.LerInt(input);
                                Projeto project = U.BuscarProjeto(m.getProjetos(), checkIdP);

                                user.IngressarProjeto(project);
                            }
                            else if (cmdUsuario == 3)
                            {
                                System.out.println("Digite o ID do projeto em que a atividade esta localizada: ");
                                int checkIdP = U.LerInt(input);
                                Projeto project = U.BuscarProjeto(m.getProjetos(), checkIdP);

                                user.IngressarAtividade(project, input);
                            }

                            else if (cmdUsuario == 4)
                            {
                                user.EditarTarefas(input);
                            }
                        }
                    }
                }
                
                else if (cmdLogin == 5) // continuar daqui
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
                                    //Tratar, talvez uma lista
                                }
                            }    
                        }
                    }
                    else
                    {
                        System.out.println("Hi");
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
                    
                    Usuario userCheck = U.BuscarUsuario(m.getUsuarios(), user.getId());
                    Projeto project = U.BuscarProjeto(m.getProjetos(), user.getProjeto());

                    if (userCheck != null && userCheck instanceof Docente)
                    {
                        Docente usuario = (Docente)user;
                        if (usuario.getCoord())
                        {
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
                            Atividade atividade = U.BuscarAtividade(project.getAtividades(), usuario.getAtividade());

                            if (atividade != null && user.getId() == atividade.getIdResponsavel())
                            {
                                U.RelatorioAtiv(atividade);
                            }
                        }
                    }
                }
                
                else if (cmdLogin == 8)
                {
                    System.out.println("Somente Coordenadores podem fazer o pagamento");
                    Usuario coord = U.BuscarUsuario(m.getUsuarios(), user.getId());

                    if (coord != null && coord instanceof Docente)
                    {
                        Docente coordenador = (Docente)coord;
                        if (coordenador.getCoord())
                        {
                            Projeto project = U.BuscarProjeto(m.getProjetos(), coordenador.getProjeto());
                            ArrayList <Discente> pagar = new ArrayList<Discente>();
                            LocalDateTime dtAtual = LocalDateTime.now();

                            System.out.println("Lista dos bolsistas que podem receber a bolsa: ");
                            System.out.println("Desenvolvedores: ");
                            
                            for (Usuario item : project.getDesenvolvedores())
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

                            System.out.println("Testadores: ");
                            for (Usuario item : project.getTestadores())
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

                            System.out.println("Analistas: ");
                            for (Usuario item : project.getAnalistas())
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
                }

                else if (cmdLogin == 9)
                {
                    System.out.println("Somente Coordenadores podem adicionar intercambistas em projetos");
                    Usuario coordenador = U.BuscarUsuario(m.getUsuarios(), user.getId());
                    if (coordenador != null)
                    {
                        if (coordenador.getCoord())
                        {
                            Projeto project = U.BuscarProjeto(m.getProjetos(), coordenador.getProjeto());

                            System.out.println("Digite o RG do usuario que deseja adicionar:");
                            Usuario intercamb = U.BuscarUsuario(m.getUsuarios(), U.LerInt(input));

                            if (intercamb != null)
                            {
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

    public void CriarProjeto(Scanner input)
    {
        System.out.println("Digite o ID do projeto: ");
        int idProject = U.LerInt(input);

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