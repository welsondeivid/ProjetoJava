import java.util.ArrayList;
import java.time.LocalDateTime;

public class Cadastro extends VarGlobais {

    public Usuario CadastrarUsuario(ArrayList<Usuario> users) throws Exception
    {
        String nomeUser = CadastrarNomeUsuario();

        int idUser = CadastrarId(users, "usuario");
        if (idUser == 0)   return null;

        String emailUser = CadastrarEmailUsuario();

        String senhaUser = CadastrarSenhaUsuario();

        int tipoUser = CadastrarTipoUsuario();

        Usuario usuario = null;
                
        if (U.ChecarTipoUsuario(tipoUser).equals("Disc"))
        {
            usuario = new Discente(nomeUser, emailUser, senhaUser, tipoUser, idUser);
        }
        else if (U.ChecarTipoUsuario(tipoUser).equals("Doce"))
        {
            usuario = new Docente(nomeUser, emailUser, senhaUser, tipoUser, idUser);
        }

        System.out.println("Cadastro Realizado com sucesso\n");

        return usuario;
    }

    private String CadastrarNomeUsuario() throws Exception
    {
        System.out.println("Digite seu nome: ");
        String nome = input.nextLine();
        
        erro.CheckErros(nome, "nome");

        return nome;
    }

    private String CadastrarEmailUsuario() throws Exception
    {
        System.out.println("Digite seu email: ");
        String email = input.nextLine();

        erro.CheckErros(email, "vazio");
        email += "@icproj.com";

        System.out.println("Email Cadastrado: "+email);

        return email;
    }

    private String CadastrarSenhaUsuario() throws Exception
    {
        System.out.println("Digite sua senha: ");
        String senha = input.nextLine();

        erro.CheckErros(senha, "senha");

        return senha;
    }

    private int CadastrarTipoUsuario()
    {
        System.out.println("Digite o seu tipo de usuario: ");
        menu.MenuTipoUser();

        int tipo = U.LerInt();

        return tipo;
    }


    public Tarefa CadastrarTarefa (Atividade ativ) throws Exception
    {
        String descTarefa = CadastrarDesc("da atividade");

        int idTarefa = CadastrarIdTarefa(ativ.getUsuarios());
        if (idTarefa == 0) throw new RuntimeException("ID invalido");

        Usuario user = U.Buscar(ativ.getUsuarios(), idTarefa);
        
        Tarefa tarefa = new Tarefa(descTarefa, idTarefa);

        user.setTarefas(tarefa);

        return tarefa;
    }
    
    private int CadastrarIdTarefa(ArrayList<Usuario> users)
    {
        System.out.println("Digite o ID do profissional que realizara a tarefa: ");
        int id = U.LerInt();

        if (id < 0) return 0;

        try {
            U.Listar(users);
            return id;
        } catch (Exception e) {
            throw new RuntimeException("ID invalido");
        }
    }
    
    
    public Atividade CadastrarAtividade(Projeto proj, Usuario coord) throws Exception
    {
        int idAtividade = CadastrarId(proj.getAtividades(), "atividade");

        if (idAtividade == 0)   throw new RuntimeException("ID invalido");

        String descAtividade = CadastrarDesc("da atividade");

        Usuario responsavel = CadastrarRespAtividade(proj.getProjetistas());

        if (responsavel == null) throw new RuntimeException("ID invalido");

        LocalDateTime inicio = CadastrarPeriodo("inicial");
        LocalDateTime termino = CadastrarPeriodo("final");
        erro.CheckTempo(inicio, termino);

        Atividade atividade = new Atividade(idAtividade, descAtividade, responsavel.getId(), responsavel, inicio, termino);
            
        responsavel.setAtividade(idAtividade);
        atividade.setUsuarios(coord);
        coord.setAtividade(proj.getId());
        
        return atividade;
    }

    private Usuario CadastrarRespAtividade(ArrayList<Usuario> users)
    {
        System.out.println("Digite o RG do responsavel pela atividade: ");
        U.Listar(users);
        int checkIdU = U.LerInt();

        Usuario user = U.Buscar(users, checkIdU);

        return user;
    }

    
    public Projeto CadastrarProjeto(ArrayList<Projeto> projs, ArrayList<Usuario> users) throws Exception
    {
        int idProject = CadastrarId(projs, "projeto");
        if (idProject == 0) throw new RuntimeException("ID invalido");

        String descProj = CadastrarDesc("do projeto");

        LocalDateTime inicio = CadastrarPeriodo("inicial");
        LocalDateTime termino = CadastrarPeriodo("final");
        erro.CheckTempo(inicio, termino);

        Usuario userCoord = U.EscolherUser(users, "do coordenador");

        if (userCoord instanceof Docente)
        {
            Docente user = (Docente)userCoord;

            Projeto project = new Projeto(idProject, descProj, inicio, termino, "Em processo de criacao", user);

            user.Coordenar(project);
            
            System.out.println("Projeto criado com sucesso");
            U.Continue();
            return project;
        }
        else
        {
            System.out.println("Discente nao pode ser Coordenador");
            return null;
        }
    }

    private <T extends Busca> int CadastrarId(ArrayList<T> objs, String tipo)
    {
        System.out.println("Digite o ID: ");
        int id = U.LerInt();
        if (id < 0) return 0;

        try {
            U.Buscar(objs, id);
            System.out.println("Falha ao criar "+tipo+": ID ja consta no sistema");
            return 0;
        } catch (Exception e) {
            System.out.println("ID disponivel\n");
        }

        return id;
    }

    private String CadastrarDesc(String tipo) throws Exception
    {
        System.out.println("Digite a descricao "+tipo+": ");
        String desc = input.nextLine();

        erro.CheckErros(desc, "vazio");

        return desc;
    }

    private LocalDateTime CadastrarPeriodo(String tipo)
    {
        LocalDateTime tempo = null;
        try
        {
            System.out.println("Define a data "+tipo+": ");
            tempo = U.DefinirDataHora();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

        return tempo;
    }
}
