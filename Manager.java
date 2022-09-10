import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Check as funcs do projeto polir e criar classe com utilidades

public class Manager {

    public static void main (String[] args) {
        
        Utilidades U = new Utilidades();
        Manager m = new Manager();

        ArrayList<Projeto> projetos = new ArrayList<Projeto>();
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        DateTimeFormatter format = DateTimeFormatter.ofPattern ("HH:mm dd/MM/yyyy");
        Scanner input = new Scanner(System.in);

        int cmd = -1;

        System.out.println ("Digite 1 para: Criar um projeto");
        System.out.println ("Digite 2 para: Cadastrar um usuario");
        System.out.println ("Digite 3 para: Editar um projeto");
        System.out.println ("Digite 4 para: Editar uma atividade");
        System.out.println ("Digite 5 para: Editar um usuario");
        System.out.println ("Digite 6 para: Alterar o status de um projeto");
        System.out.println ("Digite 10 para: Printar");

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

                if (idProject > 0 && descProject != null && inicio != null && termino != null)
                {
                    Projeto project = new Projeto(idProject, descProject, inicio, termino, "Em processo de criacao");
                    projetos.add(project);
                }
            }

            else if (cmd == 2)
            {
                System.out.println("Digite seu nome: ");
                String nomeUser = input.nextLine();
                
                System.out.println("Digite seu cpf, usaremos como seu id: ");
                int idUser = U.LerInt(input);

                System.out.println("Digite seu email: "); //Falta Formatar
                String emailUser = input.nextLine();

                System.out.println("Digite sua senha: "); //Crie uma confirmação
                String senhaUser = input.nextLine();

                System.out.println("Digite o seu tipo de usuario: "); // Especifique os tipos permitidos
                String tipoUser = input.nextLine();

                Usuario usuario = new Usuario(nomeUser, emailUser, senhaUser, tipoUser, idUser);
                usuarios.add(usuario);
            }

            else if (cmd == 3)
            {
                System.out.println("Digite o ID do projeto que deseja editar: ");

                Projeto project = U.BuscarProjeto(projetos, input);

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
                                System.out.println("Digite o CPF do novo coordenador do projeto: ");
                                
                                Usuario usuario = U.BuscarUsuario(usuarios, input);
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
                                    System.out.println("Digite o CPF do usuario que deseja adicionar: ");
                                    Usuario usuario = U.BuscarUsuario(usuarios, input);

                                    if (usuario != null)
                                    {
                                        project.setProjetistas(usuario);
                                    }
                                }
                            }
                            else if (num == 2)
                            {
                                System.out.println("Qual sera a quantidade de usuarios removidos?");
                                int quant = U.LerInt(input);

                                for (int i = 0; i < quant; i++)
                                {
                                    System.out.println("Digite CPF do usuario que deseja remover: ");
                                    
                                    Usuario usuario = U.BuscarUsuario(project.getProjetistas(), input);

                                    if (usuario != null)
                                    {
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
                                    String responsavel = input.nextLine();

                                    System.out.println ("Digite a data de inicio no formato: HH:mm dd/MM/yyyy");
                                    LocalDateTime inicio = LocalDateTime.parse(input.nextLine(), format);

                                    System.out.println ("Digite a data de termino no formato: HH:mm dd/MM/yyyy");
                                    LocalDateTime termino = LocalDateTime.parse(input.nextLine(), format);

                                    if (idAtividade > 0 && descAtividade != null && inicio != null && termino != null)
                                    {
                                        Atividade atividade = new Atividade(idAtividade, descAtividade, responsavel, inicio, termino);
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
                                    int idAtividade = U.LerInt(input);

                                    for (Atividade item : project.getAtividades())
                                    {
                                        if (item.getId() == idAtividade)
                                        {
                                            project.getAtividades().remove(item);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        else if (cmdProjeto == 5)
                        {
                            System.out.println("Valor atual da bolsa: " +project.getBolsaDesenvolvedor());
                            System.out.println("Digite o novo valor, -1 para manter");
                            float bolsa = input.nextFloat();

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
                            float bolsa = input.nextFloat();

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
                            float bolsa = input.nextFloat();

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
                            System.out.println("Prazo atual da bolsa: " +project.getTempoBolsaDesenvolvedor());
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
                            System.out.println("Prazo atual da bolsa: " +project.getTempoBolsaTestador());
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
                            System.out.println("Prazo atual da bolsa: " +project.getTempoBolsaAnalista());
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
                Projeto project = U.BuscarProjeto(projetos, input);

                if (project != null)
                {
                    System.out.println("Digite o ID da atividade que deseja editar: ");

                    Atividade atividade = U.BuscarAtividade(project.getAtividades(), input);

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
                                System.out.println("Responsavel atual pela ativiadade: " +atividade.getResponsavel());
                                System.out.println("Gostaria de alterar? 1 para sim");
                                int decisao = U.LerInt(input);

                                if (decisao == 1)
                                {
                                    String responsavel = input.nextLine();
                                    atividade.setResponsavel(responsavel);
                                }
                            }
                            else if (cmdAtividade == 3)
                            {
                                System.out.println("Qual sera a quantidade de usuarios adicionados? 0 para nenhum");
                                int quant = U.LerInt(input);

                                for (int i = 0; i < quant; i++)
                                {
                                    System.out.println("Digite o CPF do usuario que deseja adicionar: ");
                                    
                                    Usuario usuario = U.BuscarUsuario(project.getProjetistas(), input);

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
                                    System.out.println("Digite o CPF do usuario que deseja remover: ");
                                    
                                    Usuario usuario = U.BuscarUsuario(atividade.getUsuarios(), input);

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
                                    String profTarefa = input.nextLine();

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
                                    System.out.println("Digite o CPF do responsável pela tarefa que deseja remover: ");
                                    String profTarefa = input.nextLine();

                                    for (Tarefa item : atividade.getTarefas())
                                    {
                                        if (item.getProfissional().equals(profTarefa))
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
                System.out.println("Digite seu CPF: ");
                Usuario usuario = U.BuscarUsuario(usuarios, input);

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
                            System.out.println("Sua senha atual é "+usuario.getSenha());
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

                            Projeto project = U.BuscarProjeto(projetos, input);

                            if (project != null)
                            {
                                usuario.setProjeto(project.getId());
                            }
                        }
                        else if (cmdUsuario == 3)
                        {
                            System.out.println("Digite o ID do projeto em que a atividade esta localizada: ");

                            Projeto project = U.BuscarProjeto(projetos, input);

                            if (project != null)
                            {
                                System.out.println("Digite o ID da atividade a qual gostaria de associar-se: ");

                                Atividade atividade = U.BuscarAtividade(project.getAtividades(), input);

                                if (atividade != null)
                                {
                                    usuario.setAtividade(atividade.getId());
                                }
                            }
                        }

                        else if (cmdUsuario == 4)
                        {
                            System.out.println("Atividades atribuidas: ");

                            /*for (Tarefa item : usuario.getTarefas())
                            {
                                System.out.println(item.getDesc());
                            }*/

                            Tarefa item = null;
                            for (int i = 1; i <= usuario.getTarefas().size(); i++)
                            {
                                item = usuario.getTarefas().get(i);
                                System.out.println(i+":"+item.getDesc());
                            }

                            System.out.println("Digite o indice da tarefa que deseja mudar o status: ");
                            int indTarefa = U.LerInt(input) - 1;

                            if (indTarefa > 0 && indTarefa < usuario.getTarefas().size())
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
                                }
                                else if (decisao == 2)
                                {
                                    item.setDesc("Finalizada");
                                }
                            }
                        }
                    }
                }
            }
            
            else if (cmd == 6)
            {
                System.out.println("Somente Coordenadores podem alterar o Status de um projeto: ");
                System.out.println("Digite seu CPF: ");
                
                Usuario coordenador = U.BuscarUsuario(usuarios, input);

                if (coordenador.getCoord())
                {
                    Projeto project = U.BuscarProjeto(projetos, input);

                    if (project != null)
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
                                System.out.println("Status alterado para 'Concluído'");
                                //Tratar, talvez uma lista
                            }
                        }    
                    }
                }
            }

            /*else if (cmd == )
            {
                System.out.println("Digite o ID do projeto que deseja remover: ");
                Projeto project = null;
                int idProject = U.LerInt(input);

                for (Projeto item : projetos)
                {
                    if (idProject == item.getId())
                    {
                        project = item;
                    }
                }

                if (project != null)
                {
                    projetos.remove(project);
                    System.out.println("Projeto removido com sucesso");
                }
                else
                {
                    System.out.println("Erro: Projeto fora do sistema");
                }
            }*/

            else if (cmd == 10)
            {
                for (int i = 0; i < usuarios.size(); i++)
                {
                    System.out.println(usuarios.get(i).getId());
                    System.out.println(usuarios.get(i).getNome());
                }
            }

            System.out.println("Digite o proximo comando: ");
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

    } 

    public void MenuProjeto()
    {
        System.out.println("Digite qual info deseja editar: ");

        System.out.println("Digite 0 para sair: ");
        System.out.println("Digite 1 para tudo: ");
        System.out.println("Digite 2 para coordenador: ");
        System.out.println("Digite 3 para adicionar ou remover usuários: ");
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