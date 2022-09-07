import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Manager {

    public static void main (String[] args) {
        
        Manager m = new Manager();

        ArrayList<Projeto> projetos = new ArrayList<Projeto>();
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        //ArrayList<Atividade> atividades = new ArrayList<Atividade>();

        DateTimeFormatter format = DateTimeFormatter.ofPattern ("HH:mm dd/MM/yyyy");
        Scanner input = new Scanner(System.in);

        int cmd = -1;

        System.out.println ("Digite 1 para: Criar um projeto");
        System.out.println ("Digite 2 para: Cadastrar um Usuario");
        System.out.println ("Digite 3 para: Editar um projeto");
        System.out.println ("Digite 10 para: Printar");

        cmd = input.nextInt();
        input.nextLine();

        while (cmd != 0)
        {
            if (cmd == 1)
            {   
                System.out.println("Digite o ID do projeto: ");
                int idProject = input.nextInt();
                //project.setId(input.nextInt());
                input.nextLine();

                System.out.println("Digite a descricao do projeto: ");
                String descProject = input.nextLine();
                //project.setDesc(input.nextLine());

                System.out.println ("Digite a data de inicio no formato: HH:mm dd/MM/yyyy");
                LocalDateTime inicio = LocalDateTime.parse(input.nextLine(), format);
                //project.setInicio(inicio);

                System.out.println ("Digite a data de termino no formato: HH:mm dd/MM/yyyy");
                LocalDateTime termino = LocalDateTime.parse(input.nextLine(), format);
                //project.setTermino(termino);

                //project.setStatus("Em processo de criação");

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
                int idUser = input.nextInt();
                input.nextLine();

                System.out.println("Digite seu email: "); //Falta Formatar
                String emailUser = input.nextLine();

                System.out.println("Dgite sua senha: "); //Crie uma confirmação
                String senhaUser = input.nextLine();

                System.out.println("Digite o seu tipo de Usuario: "); // Especifique os tipos permitidos
                String tipoUser = input.nextLine();

                Usuario usuario = new Usuario(nomeUser, emailUser, senhaUser, tipoUser, idUser);
                usuarios.add(usuario);
            }

            else if (cmd == 3)
            {
                Projeto project = null;
                System.out.println("Digite o ID do projeto que deseja editar: ");
                int idProject = input.nextInt();
                input.nextLine();

                for (Projeto item : projetos)
                {
                    if (idProject == item.getId())
                    {
                        project = item;
                    }
                }

                if (project != null)
                {
                    System.out.println("Digite qual info deseja editar: ");

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

                    int cmdProjeto = -1;

                    while (cmdProjeto != 0)
                    {
                        if (cmdProjeto == 1)
                        {
                            project.EditarProjeto(project, input, format);
                        }
                        else if (cmdProjeto == 2)
                        {
                            System.out.println("Digite o nome do coordenador do projeto: ");
                            project.setCoordenador(input.nextLine());
                        }
                        else if (cmdProjeto == 3)
                        {
                            System.out.println ("Deseja 1 = Adicionar ou 2 = Remover ?");
                            int num = input.nextInt();
                            input.nextLine();

                            if (num == 1)
                            {
                                System.out.println("Digite o CPF do usuario que deseja adicionar: ");
                                int idUser = input.nextInt();
                                input.nextLine();

                                for (Usuario item : usuarios)
                                {
                                    if (item.getId() == idUser)
                                    {
                                        project.setProjetistas(item);
                                    }
                                }
                            }
                            else if (num == 2)
                            {
                                System.out.println("Digite o ID do usuario que deseja remover: ");
                                int idUser = input.nextInt();
                                input.nextLine();

                                for (Usuario item: project.getProjetistas())
                                {
                                    if (item.getId() == idUser)
                                    {
                                        project.getProjetistas().remove(item);
                                    }
                                }
                            }
                        }
                        else if (cmdProjeto == 4)
                        {
                            System.out.println ("Deseja 1 = Adicionar ou 2 = Remover ?");
                            int num = input.nextInt();
                            input.nextLine();

                            if (num == 1)
                            {
                                System.out.println("Crie a atividade a ser adicionada: ");

                                System.out.println("Digite o ID da atividade: ");
                                int idAtividade = input.nextInt();
                                input.nextLine();

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
                            else if (num == 2)
                            {
                                System.out.println("Digite o ID da atividade a ser removida: ");
                                int idAtividade = input.nextInt();
                                input.nextLine();

                                for (Atividade item : project.getAtividades())
                                {
                                    if (item.getId() == idAtividade)
                                    {
                                        project.getAtividades().remove(item);
                                    }
                                }
                            }
                        }
                        else if (cmdProjeto == 5)
                        {
                            System.out.println("Valor atual da bolsa: " +project.getBolsaDesenvolvedor());
                            System.out.println("Digite o novo valor, -1 para manter");
                            float bolsa = input.nextFloat();

                            if (bolsa == -1)
                            {
                                continue;
                            }
                            else if (bolsa > 0)
                            {
                                project.setBolsaDesenvolvedor(bolsa);
                            }
                        }
                        else if (cmdProjeto == 6)
                        {
                            System.out.println("Valor atual da bolsa: " +project.getBolsaTestador());
                            System.out.println("Digite o novo valor, -1 para manter");
                            float bolsa = input.nextFloat();

                            if (bolsa == -1)
                            {
                                continue;
                            }
                            else if (bolsa > 0)
                            {
                                project.setBolsaTestador(bolsa);
                            }
                        }
                        else if (cmdProjeto == 7)
                        {
                            System.out.println("Valor atual da bolsa: " +project.getBolsaAnalista());
                            System.out.println("Digite o novo valor, -1 para manter");
                            float bolsa = input.nextFloat();

                            if (bolsa == -1)
                            {
                                continue;
                            }
                            else if (bolsa > 0)
                            {
                                project.setBolsaAnalista(bolsa);
                            } 
                        }
                        else if (cmdProjeto == 8)
                        {
                            System.out.println("Prazo atual da bolsa: " +project.getTempoBolsaDesenvolvedor());
                            System.out.println("Gostaria de manter? 1 para S, 0 para N");
                            int decisao = input.nextInt();
                            input.nextLine();

                            if (decisao == 1)
                            {
                                continue;
                            }
                            else if (decisao == 0)
                            {
                                LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
                                project.setTempoBolsaDesenvolvedor(tempoBolsa);
                            }
                        }
                        else if (cmdProjeto == 9)
                        {
                            System.out.println("Prazo atual da bolsa: " +project.getTempoBolsaTestador());
                            System.out.println("Gostaria de manter? 1 para S, 0 para N");
                            int decisao = input.nextInt();
                            input.nextLine();

                            if (decisao == 1)
                            {
                                continue;
                            }
                            else if (decisao == 0)
                            {
                                LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
                                project.setTempoBolsaTestador(tempoBolsa);
                            }
                        }
                        else if (cmdProjeto == 10)
                        {
                            System.out.println("Prazo atual da bolsa: " +project.getTempoBolsaAnalista());
                            System.out.println("Gostaria de manter? 1 para S, 0 para N");
                            int decisao = input.nextInt();
                            input.nextLine();

                            if (decisao == 1)
                            {
                                continue;
                            }
                            else if (decisao == 0)
                            {
                                LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
                                project.setTempoBolsaAnalista(tempoBolsa);
                            }
                        }
                    }
                }

                else
                {
                    System.out.println("Erro: Projeto fora do Sistema");
                }
            }

            else if (cmd == 4)
            {
                System.out.println("Digite o ID do projeto que deseja checar: ");

                Projeto project = null;
                int idProject = input.nextInt();

                for (Projeto item : projetos)
                {
                    if (idProject == item.getId())
                    {
                        project = item;
                    }
                }

                if (project != null)
                {
                    boolean check = m.ChecarStatusDoProjeto(project);

                    if (check)  System.out.println("Projeto iniciado com sucesso");
                    else    System.out.println("Adicione as infos faltantes e check novamente para iniciar o projeto");
                }
                else
                {
                    System.out.println("Erro: Projeto fora do sistema");
                }

            }

            else if (cmd == 6)
            {
                System.out.println("Digite o ID do projeto que deseja remover: ");
                Projeto project = null;
                int idProject = input.nextInt();

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
            }

            else if (cmd == 10)
            {
                for (int i = 0; i < usuarios.size(); i++)
                {
                    System.out.println(usuarios.get(i).getId());
                    System.out.println(usuarios.get(i).getNome());
                }
            }

            System.out.println("Digite o proximo comando: ");
            cmd = input.nextInt();
            input.nextLine();
        }

        input.close();
    }

    private boolean ChecarStatusDoProjeto (Projeto projeto)
    {
        if (projeto.getCoordenador() == null)
        {
            System.out.println("Falta designar Coordenador: ");
            return false;
        }
        if (projeto.getProjetistas().isEmpty())
        {
            System.out.println("Falta designar Projetistas: ");
            return false;
        }
        if (projeto.getAtividades().isEmpty())
        {
            System.out.println("Falta designar Atividades: ");
            return false;
        }
        if (projeto.getBolsaDesenvolvedor() == 0)
        {
            System.out.println("Falta designar o valor da Bolsa do Desenvolvedor: ");
            return false;
        }
        if (projeto.getBolsaAnalista() == 0)
        {
            System.out.println("Falta designar o valor da Bolsa do Analista: ");
            return false;
        }
        if (projeto.getBolsaTestador() == 0)
        {
            System.out.println("Falta designar o valor da Bolsa do Testador: ");
            return false;
        }
        if (projeto.getTempoBolsaDesenvolvedor() == null)
        {
            System.out.println("Falta designar o tempo da Bolsa do Desenvolvedor: ");
            return false;
        }
        if (projeto.getTempoBolsaAnalista() == null)
        {
            System.out.println("Falta designar o tempo da Bolsa do Analista: ");
            return false;
        }
        if (projeto.getTempoBolsaTestador() == null)
        {
            System.out.println("Falta designar o tempo da Bolsa do Testador: ");
            return false;
        }

        projeto.setStatus("Iniciado");
        return true;
    }

    private Atividade CriarAtividade(Scanner input, DateTimeFormatter format)
    {
        //Scanner input = new Scanner(System.in);

        System.out.println("Digite o ID da atividade: ");
        int idAtividade = input.nextInt();
        input.nextLine();

        System.out.println("Digite a descricao da atividade: ");
        String descAtividade = input.nextLine();

        System.out.println("Digite o nome do responsavel pela atividade: ");
        String responsavel = input.nextLine();

        System.out.println ("Digite a data de inicio no formato: HH:mm dd/MM/yyyy");
        LocalDateTime inicio = LocalDateTime.parse(input.nextLine(), format);

        System.out.println ("Digite a data de termino no formato: HH:mm dd/MM/yyyy");
        LocalDateTime termino = LocalDateTime.parse(input.nextLine(), format);

        Atividade atividade = new Atividade(idAtividade, descAtividade, responsavel, inicio, termino);

        /*System.out.println ("Quantos profissionais estarao na atividade? ");
        int num = input.nextInt();
        input.nextLine();
        for (int i = 0; i < num; i++)
        {
            System.out.println("Digite o nome de um profissional da atividade: ");
            atividade.setProfissionais(input.nextLine());
        }

        System.out.println ("Quantas tarefas estarao na atividade? ");
        num = input.nextInt();
        input.nextLine();
        for (int i = 0; i < num; i++)
        {
            System.out.println("Digite o nome das tarefas: ");
            atividade.setTarefas(input.nextLine());
        }*/

        //input.close();
        return atividade;
    }
}