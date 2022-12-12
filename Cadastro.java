import java.util.ArrayList;
import java.time.LocalDateTime;

public class Cadastro extends VarGlobais {

    public Usuario CadastrarUsuario(ArrayList<Usuario> users) throws Exception
    {
        String nomeUser = CadastrarNomeUsuario();

        int idUser = CadastrarIdUsuario(users);

        if (idUser == -1)   return null;

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

    private int CadastrarIdUsuario(ArrayList<Usuario> users) throws Exception
    {
        System.out.println("Digite seu RG, usaremos como seu id: ");
        int id = U.LerInt();
        
        try {

            U.Buscar(users, id);
            System.out.println("\nID ja consta no sistema\nFalha no cadastro\n");
            return -1;

        } catch (Exception e) {
            
            System.out.println("ID valido");
            return id;
        }
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
        String descTarefa = CadastrarDescTarefa();

        int respTarefa = CadastrarIdRespTarefa(ativ.getUsuarios());

        Usuario user = U.Buscar(ativ.getUsuarios(), respTarefa);
        
        Tarefa tarefa = new Tarefa(descTarefa, respTarefa);

        user.setTarefas(tarefa);

        return tarefa;
    }

    private String CadastrarDescTarefa() throws Exception
    {
        System.out.println("Digite a descricao da tarefa: ");
        String desc = input.nextLine();

        erro.CheckErros(desc, "vazio");

        return desc;
    }

    private int CadastrarIdRespTarefa (ArrayList<Usuario> users)
    {
        System.out.println("Digite o RG do profissional que realizara a tarefa: ");
        U.Listar(users);

        int resp = U.LerInt();

        return resp;
    }

    
    public Atividade CadastrarAtividade(Projeto proj, Usuario coord) throws Exception
    {
        int idAtividade = CadastrarIdAtividade(proj);
        if (idAtividade == 0)   throw new RuntimeException("ID invalido");

        String descAtividade = CadastrarDescAtividade();

        Usuario responsavel = CadastrarRespAtividade(proj.getProjetistas());

        if (responsavel == null) throw new RuntimeException("ID invalido");

        LocalDateTime inicio = CadastrarPeriodo("inicial");

        LocalDateTime termino = CadastrarPeriodo("final");

        Atividade atividade = new Atividade(idAtividade, descAtividade, responsavel.getId(), responsavel, inicio, termino);
            
        responsavel.setAtividade(idAtividade);
        atividade.setUsuarios(coord);
        coord.setAtividade(proj.getId());
        return atividade;
    }

    private int CadastrarIdAtividade(Projeto proj)
    {
        System.out.println("Digite o ID da atividade: ");
        int id = U.LerInt();

        try {
            U.Buscar(proj.getAtividades(), id);
            System.out.println("Falha ao adicionar atividade: ID de atividade ja consta no sistema\n");
            return 0;

        } catch (Exception e) {
            System.out.println("ID disponivel");
        }

        if (id < 0) return 0;

        return id;
    }

    private String CadastrarDescAtividade() throws Exception
    {
        System.out.println("Digite a descricao da atividade: ");
        String desc = input.nextLine();

        erro.CheckErros(desc, "vazio");

        return desc;
    }

    private Usuario CadastrarRespAtividade(ArrayList<Usuario> users)
    {
        System.out.println("Digite o RG do responsavel pela atividade: ");
        U.Listar(users);
        int checkIdU = U.LerInt();

        Usuario user = U.Buscar(users, checkIdU);

        return user;
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
