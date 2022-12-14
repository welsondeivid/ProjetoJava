import java.util.ArrayList;
import java.util.Scanner;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Polimento: Printar as listas para opcao
//Login e recuperacao de senha
//tratar os erros nos else's
//

public class Manager {

    public static void main (String[] args) {
        
        Utilidades U = new Utilidades();
        Manager m = new Manager();

        ArrayList<Projeto> projetos = new ArrayList<Projeto>();
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        String[] tipos = {"Grad", "Mest", "Dout", "Prof", "Pesq"};

        DateTimeFormatter format = DateTimeFormatter.ofPattern ("HH:mm dd/MM/yyyy");
	    
        Scanner input = new Scanner(System.in);

        int cmd = -1;
        
        m.MenuPrincipal();
        cmd = U.LerInt(input);

        while (cmd != 0)
        {
            if (cmd == 1)
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

                if (U.BuscarUsuario(usuarios, idCoord) != null)
                {
                    if (idProject > 0 && descProject != null && inicio != null && termino != null)
                    {
                        Projeto project = new Projeto(idProject, descProject, inicio, termino, "Em processo de criacao", idCoord);
                        projetos.add(project);
                    }
                }
                else
                {
                    System.out.println("Usuario Nao pode ser Coordenador");
                }
            }

            else if (cmd == 2)
            {
                System.out.println("Digite seu nome: ");
                String nomeUser = input.nextLine();
                
                System.out.println("Digite seu RG, usaremos como seu id: ");
                int idUser = U.LerInt(input);

                System.out.println("Digite seu email: "); //Falta Formatar
                String emailUser = input.nextLine();

                System.out.println("Digite sua senha: "); //Crie uma confirma????o
                String senhaUser = input.nextLine();

                System.out.println("Digite o seu tipo de usuario: "); // Especifique os tipos permitidos
                for (int i = 0; i < tipos.length; i++)
                {
                    System.out.println(tipos[i]);
                }
                String tipoUser = input.nextLine();

                Usuario usuario = new Usuario(nomeUser, emailUser, senhaUser, tipoUser, idUser);
                usuarios.add(usuario);
            }

            else if (cmd == 3)
            {
                System.out.println("Digite o ID do projeto que deseja editar: ");
                int checkIdP = U.LerInt(input);
                Projeto project = U.BuscarProjeto(projetos, checkIdP);

                if (project != null)
                {
                    m.MenuProjeto();

                    int cmdProjeto = -1;

                    while (cmdProjeto != 0)
                    {
                        cmdProjeto = U.LerInt(input);

                        if (cmdProjeto == 1)
                        {
                            project.EditarProjeto(usuarios, project, input, format);
                        }
                        else if (cmdProjeto == 2)
                        {
                            System.out.println("Coordenador atual do projeto: " +project.getIdCoordenador());
                            System.out.println("Gostaria de alterar? 1 para S, 0 para N");
                            int decisao = U.LerInt(input);

                            if (decisao == 1)
                            {
                                System.out.println("Somente Pesquisadores ou Professores podem coordenar um projeto");
                                System.out.println("Digite o RG do novo coordenador do projeto: ");
                                int checkIdU = U.LerInt(input);
                                Usuario usuario = U.BuscarUsuario(usuarios, checkIdU);
                                if (usuario != null)
                                {
                                    if (usuario.getTipo().equals("Prof") || usuario.getTipo().equals("Pesq"))
                                    {
                                        project.setIdCoordenador(usuario.getId());
                                        usuario.setCoord(true);
                                        usuario.setProjeto(project.getId());
                                    }
                                }
                            }
                        }
                        else if (cmdProjeto == 3)
                        {
                            System.out.println ("Deseja 1 = Adicionar ou 2 = Remover ?");
                            int num = U.LerInt(input);

                            if (num == 1)
                            {
                                System.out.println("Qual sera a quantidade de usuarios adicionados?");
                                int quant = U.LerInt(input);

                                for (int i = 0; i < quant; i++)
                                {
                                    System.out.println("Digite o RG do usuario que deseja adicionar: ");
                                    int checkIdU = U.LerInt(input);
                                    Usuario usuario = U.BuscarUsuario(usuarios, checkIdU);

                                    if (usuario != null)
                                    {
                                        System.out.println("Designe a funcao dele no projeto: ");
                                        System.out.println("Digite 1 para: Desenvolvedor");
                                        System.out.println("Digite 2 para: Testador");
                                        System.out.println("Digite 3 para: Analista");
                                        System.out.println("Digite 4 para: Tecnico");
                                        int funcUsuario = U.LerInt(input);

                                        if (funcUsuario == 1)
                                        {
                                            project.setDesenvolvedor(usuario);
                                            usuario.setFunc("Devp");
                                        }
                                        else if (funcUsuario == 2)
                                        {
                                            project.setTestador(usuario);
                                            usuario.setFunc("Test");
                                        }
                                        else if (funcUsuario == 3)
                                        {
                                            project.setAnalista(usuario);
                                            usuario.setFunc("Anlt");
                                        }
                                        else if (funcUsuario == 4)
                                        {
                                            if (project.getIdTecnico() == 0)
                                            {
                                                project.setIdTecnico(usuario.getId());
                                                usuario.setFunc("Tecn");
                                            }
                                        }
                                        else
                                        {
                                            //erro
                                        }
                                        project.setProjetistas(usuario);
                                        usuario.setDiaPag(LocalDateTime.now());
                                    }
                                }
                            }
                            else if (num == 2)
                            {
                                System.out.println("Qual sera a quantidade de usuarios removidos?");
                                int quant = U.LerInt(input);

                                for (int i = 0; i < quant; i++)
                                {
                                    System.out.println("Digite RG do usuario que deseja remover: ");
                                    int checkIdU = U.LerInt(input);
                                    Usuario usuario = U.BuscarUsuario(project.getProjetistas(), checkIdU);

                                    if (usuario != null)
                                    {
                                        if (usuario.getFunc().equals("Devp"))
                                        {
                                            project.getDesenvolvedores().remove(usuario);
                                            project.getProjetistas().remove(usuario);
                                            usuario.setFunc(null);
                                        }
                                        else if (usuario.getFunc().equals("Test"))
                                        {
                                            project.getTestadores().remove(usuario);
                                            project.getProjetistas().remove(usuario);
                                            usuario.setFunc(null);
                                        }
                                        else if (usuario.getFunc().equals("Anlt"))
                                        {
                                            project.getAnalistas().remove(usuario);
                                            project.getProjetistas().remove(usuario);
                                            usuario.setFunc(null);
                                        }
                                        else if (usuario.getFunc().equals("Tecn"))
                                        {
                                            project.setIdTecnico(0);
                                            project.getProjetistas().remove(usuario);
                                            usuario.setFunc(null);
                                        }
                                        project.getProjetistas().remove(usuario);
                                    }
                                }
                            }
                        }
                        else if (cmdProjeto == 4)
                        {
                            System.out.println ("Deseja 1 = Adicionar ou 2 = Remover ?");
                            int num = U.LerInt(input);

                            if (num == 1)
                            {
                                System.out.println("Qual sera a quantidade de atividades adicionadas?");
                                int quant = U.LerInt(input);

                                for (int i = 0; i < quant; i++)
                                {
                                    System.out.println("Crie a atividade a ser adicionada: ");

                                    System.out.println("Digite o ID da atividade: ");
                                    int idAtividade = U.LerInt(input);

                                    System.out.println("Digite a descricao da atividade: ");
                                    String descAtividade = input.nextLine();

                                    System.out.println("Digite o nome do responsavel pela atividade: ");
                                    int checkIdU = U.LerInt(input);
                                    Usuario responsavel = U.BuscarUsuario(project.getProjetistas(), checkIdU);

                                    System.out.println ("Digite a data de inicio no formato: HH:mm dd/MM/yyyy");
                                    LocalDateTime inicio = LocalDateTime.parse(input.nextLine(), format);

                                    System.out.println ("Digite a data de termino no formato: HH:mm dd/MM/yyyy");
                                    LocalDateTime termino = LocalDateTime.parse(input.nextLine(), format);

                                    if (idAtividade > 0 && descAtividade != null && inicio != null && termino != null)
                                    {
                                        
                                        Atividade atividade = new Atividade(idAtividade, descAtividade, responsavel.getId(), responsavel, inicio, termino);
                                        project.setAtividades(atividade);
                                    }
                                }
                            }
                            else if (num == 2)
                            {

                                System.out.println("Qual sera a quantidade de atividades removidas?");
                                int quant = U.LerInt(input);

                                for (int i = 0; i < quant; i++)
                                {
                                    System.out.println("Digite o ID da atividade a ser removida: ");
                                    int checkIdA = U.LerInt(input);

                                    Atividade atividade = U.BuscarAtividade(project.getAtividades(), checkIdA);
                                    if (atividade != null)
                                    {
                                        project.getAtividades().remove(atividade);
                                    }
                                }
                            }
                        }
                        else if (cmdProjeto == 5)
                        {
                            System.out.println("Valor atual da bolsa: " +project.getBolsaDesenvolvedor());
                            System.out.println("Digite o novo valor, -1 para manter");
                            float bolsa = U.LerFloat(input);

                            if (bolsa > -1)
                            {
                                project.setBolsaDesenvolvedor(bolsa);
                            }
                            else if (bolsa < -1)
                            {
                                //erro
                            }
                        }
                        else if (cmdProjeto == 6)
                        {
                            System.out.println("Valor atual da bolsa: " +project.getBolsaTestador());
                            System.out.println("Digite o novo valor, -1 para manter");
                            float bolsa = U.LerFloat(input);

                            if (bolsa > -1)
                            {
                                project.setBolsaTestador(bolsa);
                            }
                            else if (bolsa < -1)
                            {
                                //erro
                            }
                        }
                        else if (cmdProjeto == 7)
                        {
                            System.out.println("Valor atual da bolsa: " +project.getBolsaAnalista());
                            System.out.println("Digite o novo valor, -1 para manter");
                            float bolsa = U.LerFloat(input);

                            if (bolsa > -1)
                            {
                                project.setBolsaAnalista(bolsa);
                            }
                            else if (bolsa < -1)
                            {
                                //erro
                            } 
                        }
                        else if (cmdProjeto == 8)
                        {
                            System.out.println("Prazo atual da bolsa: ");
                            U.MostrarDataHora(project.getTempoBolsaDesenvolvedor());
                            System.out.println("Gostaria de alterar? 1 para S, 0 para N");
                            
                            int decisao = U.LerInt(input);
                            if (decisao == 1)
                            {
                                LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
                                project.setTempoBolsaDesenvolvedor(tempoBolsa);
                            }
                        }
                        else if (cmdProjeto == 9)
                        {
                            System.out.println("Prazo atual da bolsa: ");
                            U.MostrarDataHora(project.getTempoBolsaTestador());
                            System.out.println("Gostaria de alterar? 1 para S, 0 para N");
                            
                            int decisao = U.LerInt(input);
                            if (decisao == 1)
                            {
                                LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
                                project.setTempoBolsaTestador(tempoBolsa);
                            }
                        }
                        else if (cmdProjeto == 10)
                        {
                            System.out.println("Prazo atual da bolsa: ");
                            U.MostrarDataHora(project.getTempoBolsaAnalista());
                            System.out.println("Gostaria de alterar? 1 para S, 0 para N");
                            
                            int decisao = U.LerInt(input);
                            if (decisao == 1)
                            {
                                LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
                                project.setTempoBolsaAnalista(tempoBolsa);
                            }
                        }
                        
                        m.MenuProjeto();
                    }
                }

                else
                {
                    System.out.println("Erro: Projeto fora do Sistema");
                }
            }

            else if (cmd == 4)
            {
                System.out.println("Digite o ID do projeto onde a atividade esta localizada: ");
                int checkIdP = U.LerInt(input);
                Projeto project = U.BuscarProjeto(projetos, checkIdP);

                if (project != null)
                {
                    System.out.println("Digite o ID da atividade que deseja editar: ");
                    int checkIdA = U.LerInt(input);
                    Atividade atividade = U.BuscarAtividade(project.getAtividades(), checkIdA);

                    if (atividade != null)
                    {
                        m.MenuAtividade();

                        int cmdAtividade = -1;

                        while (cmdAtividade != 0)
                        {
                            cmdAtividade = U.LerInt(input);
                            if (cmdAtividade == 1)
                            {
                                atividade.EditarAtividade(project, atividade, input);
                            }
                            else if (cmdAtividade == 2)
                            {
                                System.out.print("Responsavel atual pela ativiadade: ");
                                System.out.println(U.BuscarUsuario(atividade.getUsuarios(), atividade.getIdResponsavel()).getNome());
                                
                                System.out.println("Gostaria de alterar? 1 para sim");
                                int decisao = U.LerInt(input);

                                if (decisao == 1)
                                {
                                    System.out.println("Digite o RG do novo responsavel");
                                    int checkIdU = U.LerInt(input);
                                    Usuario responsavel = U.BuscarUsuario(atividade.getUsuarios(), checkIdU);
                                    atividade.setIdResponsavel(responsavel.getId());
                                    atividade.setUsuarios(responsavel);
                                }
                            }
                            else if (cmdAtividade == 3)
                            {
                                System.out.println("Qual sera a quantidade de usuarios adicionados? 0 para nenhum");
                                int quant = U.LerInt(input);

                                for (int i = 0; i < quant; i++)
                                {
                                    System.out.println("Digite o RG do usuario que deseja adicionar: ");
                                    int checkIdU = U.LerInt(input);
                                    Usuario usuario = U.BuscarUsuario(project.getProjetistas(), checkIdU);

                                    if (usuario != null)
                                    {
                                        atividade.setUsuarios(usuario);
                                    }
                                }
                            }
                            else if (cmdAtividade == 4)
                            {
                                System.out.println("Qual sera a quantidade de usuarios removidos? 0 para nenhum");
                                int quant = U.LerInt(input);

                                for (int i = 0; i < quant; i++)
                                {
                                    System.out.println("Digite o RG do usuario que deseja remover: ");
                                    int checkIdU = U.LerInt(input);
                                    Usuario usuario = U.BuscarUsuario(atividade.getUsuarios(), checkIdU);

                                    if (usuario != null)
                                    {
                                        atividade.getUsuarios().remove(usuario);
                                    }
                                }
                            }

                            else if (cmdAtividade == 5)
                            {
                                System.out.println("Qual sera a quantidade de tarefas adicionadas? 0 para nenhuma: ");
                                int quant = U.LerInt(input);

                                for (int i = 0; i < quant; i++)
                                {
                                    System.out.println("Digite as infos sobre a tarefa que deseja adicionar: ");
                                    
                                    System.out.println("Digite a descricao da tarefa: ");
                                    String descTarefa = input.nextLine();

                                    System.out.println("Digite o nome do profissional que realizara a tarefa: ");
                                    int profTarefa = U.LerInt(input);

                                    Tarefa tarefa = new Tarefa(descTarefa, profTarefa);
                                    atividade.setTarefas(tarefa);
                                }
                            }
                            else if (cmdAtividade == 6)
                            {
                                System.out.println("Qual sera a quantidade de tarefas removidas? 0 para nenhuma");
                                int quant = U.LerInt(input);

                                for (int i = 0; i < quant; i++)
                                {
                                    System.out.println("Digite o RG do respons??vel pela tarefa que deseja remover: ");
                                    int respTarefa = U.LerInt(input);

                                    for (Tarefa item : atividade.getTarefas())
                                    {
                                        if (item.getProfissional() == respTarefa)
                                        {
                                            System.out.println("Descricao da atividade: "+item.getDesc());
                                            System.out.println("Gostaria de remove-la? 1 para sim");

                                            int dec = U.LerInt(input);
                                            if (dec == 1)
                                            {
                                                atividade.getTarefas().remove(item);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            
                            m.MenuPrincipal();
                        }
                    }
                }
            }

            else if (cmd == 5)
            {
                System.out.println("Digite seu RG: ");
                int checkIdU = U.LerInt(input);
                Usuario usuario = U.BuscarUsuario(usuarios, checkIdU);

                if (usuario != null)
                {
                    System.out.println("O que deseja editar? ");

                    System.out.println("Digite 0  para sair: ");
                    System.out.println("Digite 1 para alterar a senha: ");
                    System.out.println("Digite 2  para se associar a um projeto: ");
                    System.out.println("Digite 3  para se associar a uma atividade: ");
                    System.out.println("Digite 4 para alterar o status de uma tarefa: ");

                    int cmdUsuario = -1;

                    while (cmdUsuario != 0)
                    {
                        cmdUsuario = U.LerInt(input);

                        if (cmdUsuario == 1)
                        {
                            System.out.println("Sua senha atual ?? "+usuario.getSenha());
                            System.out.println("Gostaria de mudar? 1 para sim");

                            int decisao = U.LerInt(input);

                            if (decisao == 1)
                            {
                                System.out.println("Digite a nova senha: ");
                                String novaSenha = input.nextLine();

                                // tratamento de erro senha, vazia
                                usuario.setSenha(novaSenha);
                            }
                        }
                        else if (cmdUsuario == 2)
                        {
                            System.out.println("Digite o ID do projeto ao qual gostaria de associar-se: ");
                            int checkIdP = U.LerInt(input);
                            Projeto project = U.BuscarProjeto(projetos, checkIdP);

                            if (project != null)
                            {
                                usuario.setProjeto(project.getId());
                                project.setProjetistas(usuario);
                                usuario.setDiaPag(LocalDateTime.now());
                            }
                        }
                        else if (cmdUsuario == 3)
                        {
                            System.out.println("Digite o ID do projeto em que a atividade esta localizada: ");
                            int checkIdP = U.LerInt(input);
                            Projeto project = U.BuscarProjeto(projetos, checkIdP);

                            if (project != null)
                            {
                                System.out.println("Digite o ID da atividade a qual gostaria de associar-se: ");
                                int checkIdA = U.LerInt(input);
                                Atividade atividade = U.BuscarAtividade(project.getAtividades(), checkIdA);

                                if (atividade != null)
                                {
                                    usuario.setAtividade(atividade.getId());
                                }
                            }
                        }

                        else if (cmdUsuario == 4)
                        {
                            System.out.println("Tarefas atribuidas: ");

                            Tarefa item = null;
                            for (int i = 0; i <= usuario.getTarefas().size(); i++)
                            {
                                item = usuario.getTarefas().get(i);
                                System.out.println((i+1)+":"+item.getDesc());
                            }

                            int indTarefa = 0;

                            while (indTarefa != -1)
                            {
                                System.out.println("Digite o indice da tarefa que deseja mudar o status: ");
                                System.out.println("-1 para sair");

                                indTarefa = U.LerInt(input) - 1;
                                if (indTarefa >= 0 && indTarefa < usuario.getTarefas().size())
                                {
                                    item = usuario.getTarefas().get(indTarefa);
                                }
                                else
                                {
                                    item = null;
                                }

                                if (item != null)
                                {
                                    System.out.println("Tarefa selecionada: "+item.getDesc());

                                    System.out.println("Digite 0 para escolher de novo");
                                    System.out.println("Digite 1 para marcar como Iniciada");
                                    System.out.println("Digite 2 para marcar como Finalizada");

                                    int decisao = U.LerInt(input);

                                    if (decisao == 1)
                                    {
                                        item.setDesc("Iniciada");
                                        System.out.println("Status Atualizado");
                                    }
                                    else if (decisao == 2)
                                    {
                                        System.out.println("Status Atualizado");
                                        item.setDesc("Finalizada");
                                    }
                                }
                                else
                                {
                                    System.out.println("Indice invalido");
                                }
                            }
                        }
                    }
                }
            }
            
            else if (cmd == 6)
            {
                System.out.println("Somente Coordenadores podem alterar o Status de um projeto: ");
                System.out.println("Digite seu RG: ");
                int checkIdU = U.LerInt(input);
                Usuario coordenador = U.BuscarUsuario(usuarios, checkIdU);

                if (coordenador != null)
                {
                    if (coordenador.getCoord())
                    {
                        Projeto project = U.BuscarProjeto(projetos, coordenador.getProjeto());

                        if (project != null)
                        {
                            System.out.println("Seu projeto ?? o : "+project.getDesc());
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
                                        System.out.println("Status alterado para 'Conclu??do'");
                                        project.setStatus("Conclu??do");
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
            }

            else if (cmd == 7)
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
                    Usuario usuario = U.BuscarUsuario(usuarios, checkIdU);

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
                    Projeto project = U.BuscarProjeto(projetos, checkIdP);

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
                    Projeto project = U.BuscarProjeto(projetos, checkIdP);

                    if (project != null)
                    {
                        System.out.println("Dados do projeto encontrado: ");
                        U.DadosProj(project);
                    }
               }
            }

            else if (cmd == 8)
            {
                System.out.println("Somente Coordenadores podem checar os relatorios");
                System.out.println("Digite seu RG: ");
                int checkIdU = U.LerInt(input);
                Usuario coordenador = U.BuscarUsuario(usuarios, checkIdU);

                if (coordenador != null)
                {
                    if (coordenador.getCoord())
                    {
                        System.out.println("Digite 1 para: Relatorio de Projeto");
                        System.out.println("Digite 2 para: Relatorio de Atividade");

                        int decisao = U.LerInt(input);
                        Projeto project = U.BuscarProjeto(projetos, coordenador.getProjeto());
                        
                        if (decisao == 1)
                        {
                            U.RelatorioProj(project);
                        }
                        else if (decisao == 2)
                        {
                            System.out.println("Digite o id da atividade desejada: ");
                            int checkIdA = U.LerInt(input);
                            U.RelatorioAtiv(U.BuscarAtividade(project.getAtividades(), checkIdA));
                        }
                    }
                }
            }
            
            else if (cmd == 9)
            {
                System.out.println("Somente Coordenadores podem fazer o pagamento");
                System.out.println("Digite seu RG: ");
                int checkIdU = U.LerInt(input);
                Usuario coordenador = U.BuscarUsuario(usuarios, checkIdU);

                if (coordenador != null)
                {
                    if (coordenador.getCoord())
                    {
                        Projeto project = U.BuscarProjeto(projetos, coordenador.getProjeto());
                        ArrayList <Usuario> pagar = new ArrayList<Usuario>();
                        LocalDateTime dtAtual = LocalDateTime.now();

                        System.out.println("Lista dos bolsistas que podem receber a bolsa: ");
                        System.out.println("Desenvolvedores: ");
                        
                        for (Usuario item : project.getDesenvolvedores())
                        {
                            LocalDateTime dtBolsista = item.getDiaPag();
                            long dias = Duration.between(dtBolsista, dtAtual).toDays();

                            if (dias >= 30)
                            {
                                System.out.println("Nome: "+item.getNome());
                                System.out.println("Nome: "+item.getId());
                                pagar.add(item);
                            }
                        }

                        System.out.println("Testadores: ");
                        for (Usuario item : project.getTestadores())
                        {
                            LocalDateTime dtBolsista = item.getDiaPag();
                            long dias = Duration.between(dtBolsista, dtAtual).toDays();

                            if (dias >= 30)
                            {
                                System.out.println("Nome: "+item.getNome());
                                System.out.println("Nome: "+item.getId());
                                pagar.add(item);
                            }
                        }

                        System.out.println("Analistas: ");
                        for (Usuario item : project.getAnalistas())
                        {
                            LocalDateTime dtBolsista = item.getDiaPag();
                            long dias = Duration.between(dtBolsista, dtAtual).toDays();

                            if (dias >= 30)
                            {
                                System.out.println("Nome: "+item.getNome());
                                System.out.println("Nome: "+item.getId());
                                pagar.add(item);
                            }
                        }

                        System.out.println("Gostaria de fazer o pagamento?");
                        System.out.println("0 para: nenhum");
                        System.out.println("1 para: todos");
                        System.out.println("2 para: alguns");

                        int cmdPag = U.LerInt(input);

                        if (cmdPag == 1)
                        {
                            for (Usuario item : pagar)
                            {
                                item.setDiaPag(LocalDateTime.now());
                            }

                            System.out.println("Pagamento Realizado a todos");
                        }
                        else if (cmdPag == 2)
                        {
                            for (Usuario item : pagar)
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

            else if (cmd == 10)
            {
                System.out.println("Somente Coordenadores podem adicionar intercambistas em projetos");
                System.out.println("Digite seu RG: ");
                int checkIdU = U.LerInt(input);
                Usuario coordenador = U.BuscarUsuario(usuarios, checkIdU);
                if (coordenador != null)
                {
                    if (coordenador.getCoord())
                    {
                        Projeto project = U.BuscarProjeto(projetos, coordenador.getProjeto());

                        System.out.println("Digite o RG do usuario que deseja adicionar:");
                        Usuario intercamb = U.BuscarUsuario(usuarios, U.LerInt(input));

                        if (intercamb != null)
                        {
                            if (intercamb.getProjInterCam() == 0)
                            {
                                Projeto projInterCam = U.BuscarProjeto(projetos, intercamb.getProjeto());
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
            m.MenuPrincipal();
            cmd = U.LerInt(input);
        }

        input.close();
    }

    private boolean ChecarStatusDoProjeto (Projeto projeto)
    {
        boolean falha = false;

        if (projeto.getIdCoordenador() == 0)
        {
            System.out.println("Falta designar Coordenador: ");
            falha = true;
        }
        if (projeto.getProjetistas().isEmpty())
        {
            System.out.println("Falta designar Projetistas: ");
            falha = true;
        }
        if (projeto.getDesenvolvedores().isEmpty())
        {
            System.out.println("Falta designar pelo menos 1 Desenvolvedor: ");
            falha = true;
        }if (projeto.getTestadores().isEmpty())
        {
            System.out.println("Falta designar pelo menos 1 Testador: ");
            falha = true;
        }
        if (projeto.getAnalistas().isEmpty())
        {
            System.out.println("Falta designar pelo menos 1 Analista: ");
            falha = true;
        }
        if (projeto.getIdTecnico() == 0)
        {
            System.out.println("Falta designar 1 Tecnico: ");
            falha = true;
        }
        if (projeto.getAtividades().isEmpty())
        {
            System.out.println("Falta designar Atividades: ");
            falha = true;
        }
        if (projeto.getBolsaDesenvolvedor() == 0)
        {
            System.out.println("Falta designar o valor da Bolsa do Desenvolvedor: ");
            falha = true;
        }
        if (projeto.getBolsaAnalista() == 0)
        {
            System.out.println("Falta designar o valor da Bolsa do Analista: ");
            falha = true;
        }
        if (projeto.getBolsaTestador() == 0)
        {
            System.out.println("Falta designar o valor da Bolsa do Testador: ");
            falha = true;
        }
        if (projeto.getTempoBolsaDesenvolvedor() == null)
        {
            System.out.println("Falta designar o tempo da Bolsa do Desenvolvedor: ");
            falha = true;
        }
        if (projeto.getTempoBolsaAnalista() == null)
        {
            System.out.println("Falta designar o tempo da Bolsa do Analista: ");
            falha = true;
        }
        if (projeto.getTempoBolsaTestador() == null)
        {
            System.out.println("Falta designar o tempo da Bolsa do Testador: ");
            falha = true;
        }

        if (!falha)
        {
            projeto.setStatus("Iniciado");
            return true;
        }
        return false;
        
    }

    public void MenuPrincipal()
    {
        System.out.println ("Digite 1 para: Criar um projeto");
        System.out.println ("Digite 2 para: Cadastrar um usuario");
        System.out.println ("Digite 3 para: Editar um projeto");
        System.out.println ("Digite 4 para: Editar uma atividade");
        System.out.println ("Digite 5 para: Editar um usuario");
        System.out.println ("Digite 6 para: Alterar o status de um projeto");
        System.out.println ("Digite 7 para: Consultar");
        System.out.println ("Digite 8 para: Relatorio");
        System.out.println ("Digite 9 para: Fazer o pagamento de bolsa");
        System.out.println ("Digite 10 para: Realizar Intercambio");
    } 

    public void MenuProjeto()
    {
        System.out.println("Digite qual info deseja editar: ");

        System.out.println("Digite 0 para sair: ");
        System.out.println("Digite 1 para tudo: ");
        System.out.println("Digite 2 para coordenador: ");
        System.out.println("Digite 3 para adicionar ou remover usu??rios: ");
        System.out.println("Digite 4 para adicionar ou remover atividades: ");
        System.out.println("Digite 5 para adicionar ou editar o valor da Bolsa-Desenvolvedor: ");
        System.out.println("Digite 6 para adicionar ou editar o valor da Bolsa-Testador: ");
        System.out.println("Digite 7 para adicionar ou editar o valor da Bolsa-Analista: ");
        System.out.println("Digite 8 para adicionar ou editar o prazo da Bolsa-Desenvolvedor: ");
        System.out.println("Digite 9 para adicionar ou editar o prazo da Bolsa-Testador: ");
        System.out.println("Digite 10 para adicionar ou editar o prazo da Bolsa-Analista: ");
    }

    public void MenuAtividade()
    {
        System.out.println("Digite qual info deseja editar: ");

        System.out.println("Digite 0 para sair: ");
        System.out.println("Digite 1 para tudo: ");
        System.out.println("Digite 2 para responsavel: ");
        System.out.println("Digite 3 para adicionar ou remover usuarios da atividade: ");
        System.out.println("Digite 4 para adicionar ou remover tarefas da atividade: ");
    }
}