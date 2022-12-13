import java.time.LocalDateTime;
import java.util.ArrayList;

public class Login extends VarGlobais
{
    ArrayList<ComandosDiscente> cmdDisc = new ArrayList<ComandosDiscente>();
    ArrayList<ComandosDocente> cmdDoce = new ArrayList<ComandosDocente>();

    public void LoginOn(Usuario userLog, Manager m) throws Exception
    {
        IniciarCommands();

        int cmdLogin = -1;

        if (userLog instanceof Docente)
        {
            Docente user = (Docente)userLog;
            while (cmdLogin != 0)
            {
                try {
                    menu.MenuUsuarioDocente();
                    cmdLogin = U.LerInt();

                    if (cmdLogin > 0 && cmdLogin < 10)
                    {   
                        this.cmdDoce.get(cmdLogin-1).Option(this, m, user);
                    }               

                    else
                    {
                        if (cmdLogin != 0)   throw new RuntimeException ("Erro: Valor invalido");
                    }
                    U.Continue();
                    System.out.println("Digite o proximo comando: ");

                } catch (Exception e) {
                    System.out.println("Erro: "+e.getMessage());
                }
            }
        }
        else if (userLog instanceof Discente)
        {
            Discente user = (Discente)userLog;
            while (cmdLogin != 0)
            {
                try {

                    menu.MenuUsuarioDiscente();
                    cmdLogin = U.LerInt();

                    if (cmdLogin > 0 && cmdLogin < 5)
                    {
                        this.cmdDisc.get(cmdLogin-1).Option(this, m, user);
                    }
                    else
                    {
                        if (cmdLogin != 0)   throw new RuntimeException ("Erro: Valor invalido");
                    }
                    U.Continue();
                    System.out.println("Digite o proximo comando: ");
                } catch (Exception e) {
                    System.out.println("Erro: "+e.getMessage());
                }
            }
        }
    }

    private void IniciarCommands()
    {
        this.cmdDisc.add(new OpEditarUsuarioDiscente());
        this.cmdDisc.add(new OpConsultaDiscente());
        this.cmdDisc.add(new OpRelatorioDiscente());
        this.cmdDisc.add(new OpEditarAtividadeDiscente());

        this.cmdDoce.add(new OpCriarProjetoDocente());
        this.cmdDoce.add(new OpEditarProjetoDocente());
        this.cmdDoce.add(new OpEditarAtividadeDocente());
        this.cmdDoce.add(new OpEditarUsuarioDocente());
        this.cmdDoce.add(new OpAlterarStatusProjeto());
        this.cmdDoce.add(new OpConsultaDocente());
        this.cmdDoce.add(new OpRelatorioDocente());
        this.cmdDoce.add(new OpPagamento());
        this.cmdDoce.add(new OpIntercambiar());

    }
    
    public void OpCriarProjeto(Manager m) throws Exception
    {
        boolean check = false;
        while (!check)
        {
            try {
                check = CriarProjeto(m);
            } catch (Exception e) {
                System.out.println("Falha ao criar projeto: "+e.getMessage());
            }
        }
    }

    public void OpEditarProjeto(Manager m, Docente user) throws Exception
    {
        System.out.println("Somente Coordenadores podem editar projetos e somente o proprio\n");
                    
        try {

            Projeto project = U.Buscar(m.getProjetos(), user.getProjeto());

            if (user.getId() == project.getIdCoordenador())
            {
                System.out.println("Coordenador identificado");

                int cmdProjeto = -1;

                while (cmdProjeto != 0)
                {
                    try {
                        
                        menu.MenuProjeto();
                        cmdProjeto = U.LerInt();

                        if (cmdProjeto == 1)
                        {
                            project.Editar(m.getUsuarios(), user);
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
                                if (project.getProjetistas().size() > 0)
                                {
                                    project.RemoverUsuarios(project.getProjetistas());
                                }
                                else
                                {
                                    System.out.println("Erro: Lista de usuarios do projeto vazia");
                                }
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
                                project.AdicionarAtividades(user);
                            }
                            else if (num == 2)
                            {
                                if (project.getAtividades().size() > 0)
                                {
                                    project.RemoverAtividades();
                                }
                                else
                                {
                                    System.out.println("Erro: Lista de atividades do projeto vazia");
                                }
                                
                            }
                        }
                        else if (cmdProjeto == 6)
                        {
                            gerBolsa.DefinirBolsa("Desenvolvedor");
                        }
                        else if (cmdProjeto == 7)
                        {
                            gerBolsa.DefinirBolsa("Testador");
                        }
                        else if (cmdProjeto == 8)
                        {
                            gerBolsa.DefinirBolsa("Analista"); 
                        }
                        else if (cmdProjeto == 9)
                        {
                            gerBolsa.DefinirPrazoBolsa("Desenvolvedor");
                        }
                        else if (cmdProjeto == 10)
                        {
                            gerBolsa.DefinirPrazoBolsa("Testador");
                        }
                        else if (cmdProjeto == 11)
                        {
                            gerBolsa.DefinirPrazoBolsa("Analista");
                        }
                        else
                        {
                            if (cmdProjeto != 0)   throw new RuntimeException ("Erro: Valor invalido");
                        }
                    } catch (Exception e) {
                        System.out.println("Falha ao Editar Projeto: "+e.getMessage());
                    } 
                }
            }
            else
            {
                System.out.println("Usuario sem autorizacao para editar");
            }
        } catch (Exception e) {
            
            System.out.println("Erro: Usuario sem projeto associado");
            return;
        }
    }

    public void OpEditarUsuarioDocente(ArrayList<Projeto> projs, Docente user) throws Exception
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
                U.Listar(projs);
                int checkIdP = U.LerInt();

                Projeto project = U.Buscar(projs, checkIdP);

                user.IngressarProjeto(project);
            }
            else if (cmdUsuario == 4)
            {
                Projeto project = U.Buscar(projs, user.getProjeto());
                
                user.IngressarAtividade(project);
            }
            else
            {
                if (cmdUsuario != 0)   throw new RuntimeException ("Erro: Valor invalido");
            }
        }
    }

    public void OpAlterarStatusProjeto(Manager m, Docente user) throws Exception
    {
        System.out.println("Somente Coordenadores podem alterar o Status de um projeto: ");

        if (user.getCoord())
        {
            System.out.println("Coordenador identificado");

            Projeto project = U.Buscar(m.getProjetos(), user.getProjeto());

            if (project.getIdCoordenador() == user.getId())
            {
                System.out.println("Entrou");
                project.AlterarStatus(gerBolsa);
            }
        }
        else
        {
            System.out.println("Erro: usuario nao é coordenador");
        }
    }
    
    public void OpRelatorioDocente(Manager m, Docente user) throws Exception
    {
        System.out.println("Somente Coordenadores podem checar o relatorio de um projeto");
        System.out.println("Somente Coordenadores podem checar os relatorios das atividades de um projeto");
        System.out.println("Responsaveis podem checar o relatorio de sua propria atividade\n");
        
        Projeto project = U.Buscar(m.getProjetos(), user.getProjeto());

        if (user.getCoord())
        {
            System.out.println("Coordenador identificado");

            menu.MenuRelatorio();

            int decisao = U.LerInt();
            
            if (decisao == 1)
            {
                U.RelatorioProj(project, gerBolsa);
            }
            else if (decisao == 2)
            {     
                for (Atividade item : project.getAtividades())
                {
                    U.RelatorioAtiv(item);
                }                    
            }
            else
            {
                throw new RuntimeException ("Erro: Valor invalido");
            }
        }
        else
        {
            Atividade atividade = U.Buscar(project.getAtividades(), user.getAtividade());

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
    
    public void OpPagamento(Manager m, Docente user) throws Exception
    {
        System.out.println("Somente Coordenadores podem fazer o pagamento");

        if (user.getCoord())
        {
            System.out.println("Coordenador identificado");

            Projeto project = U.Buscar(m.getProjetos(), user.getProjeto());
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
            else
            {
                if (cmdPag != 0)   throw new RuntimeException ("Erro: Valor invalido");
            }
            pagar = null;
        }
    }

    public void OpIntercambiar(Manager m, Docente user) throws Exception
    {
        System.out.println("Somente Coordenadores podem adicionar intercambistas em projetos");
        
        if (user.getCoord())
        {
            System.out.println("Coordenador identificado");
            Projeto project = U.Buscar(m.getProjetos(), user.getProjeto());

            System.out.println("Digite o RG do usuario que deseja adicionar:");
            Usuario checkUser = U.Buscar(m.getUsuarios(), U.LerInt());

            if (checkUser instanceof Discente)
            {
                Discente intercamb = (Discente)checkUser;

                if (intercamb.getProjInterCam() == 0)
                {
                    try {
                        Projeto projInterCam = U.Buscar(m.getProjetos(), intercamb.getProjeto());

                        if (projInterCam != project)
                        {
                            System.out.println("Para qual atividade ele sera atribuido?");
                            int checkIdA = U.LerInt();
                            Atividade atividade = U.Buscar(project.getAtividades(), checkIdA);

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

    public void OpEditarUsuarioDiscente(Discente user) throws Exception
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
            else
            {
                if (cmdUsuario != 0)   throw new RuntimeException ("Erro: Valor invalido");
            }
        }
    }

    public void OpConsulta(ArrayList<Projeto> projs, ArrayList<Usuario> users) throws Exception
    {
        System.out.println("O que gostaria de consultar?");
        menu.MenuConsulta();

        int cmdConsulta = U.LerInt();
        if (cmdConsulta == 1)
        {
            U.Consultar(users);
        }
        else if (cmdConsulta == 2)
        {
            System.out.println(("Digite o id do projeto onde a atividade esta localizda: "));
            U.Listar(projs);

            int checkIdP = U.LerInt();
            Projeto project = U.Buscar(projs, checkIdP);

            U.Consultar(project.getAtividades());
        }
        else if (cmdConsulta == 3)
        {
            U.Consultar(projs);
        }
        else
        {
            throw new RuntimeException ("Erro: Valor invalido");
        }
    }

    public void OpRelatorioDiscente(ArrayList<Projeto> projs, Discente user) throws Exception
    {
        System.out.println("Um Discente apenas tem acesso a um relatorio de atividade se for o responsavel");
                    
        try {
            Projeto proj = U.Buscar(projs, user.getProjeto());
            
            Atividade ativ = U.Buscar(proj.getAtividades(), user.getAtividade());
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

    public void OpEditarAtividade(ArrayList<Projeto> projs, Usuario user) throws Exception
    {
        System.out.println("Somente os Responsaveis por uma atividade podem edita-la");
        Projeto project = U.Buscar(projs, user.getProjeto());

        Atividade atividade = U.Buscar(project.getAtividades(), user.getAtividade());
        
        if (user.getId() == atividade.getIdResponsavel())
        {
            System.out.println("Responsavel identificado\n");

            menu.MenuAtividade();
            int cmdAtividade = -1;

            while (cmdAtividade != 0)
            {
                cmdAtividade = U.LerInt();

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
                else
                {
                    if (cmdAtividade != 0)   throw new RuntimeException ("Erro: Valor invalido");
                }
            }
        }
    }

    private boolean CriarProjeto(Manager m) throws Exception
    {
        Cadastro cadastro = new Cadastro();
        Projeto project = cadastro.CadastrarProjeto(m.getProjetos(), m.getUsuarios());

        if (project != null)
        {
            m.getProjetos().add(project);
            return true;
        }

        return false;
    }
}