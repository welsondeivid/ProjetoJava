import java.util.ArrayList;

public class Cadastro extends VarGlobais {
    
    public Usuario Cadastrar(ArrayList<Usuario> users) throws Exception
    {
        String nomeUser = CadastrarNome();

        int idUser = CadastrarId(users);

        if (idUser == -1)
        {
            return null;
        }

        //Continue daqui: Long Method

        System.out.println("Digite seu email: ");
        String emailUser = input.nextLine();
        erro.CheckErros(nomeUser, "email");
        emailUser += "@icproj.com";
        System.out.println("Email Cadastrado: "+emailUser);

        System.out.println("Digite sua senha: ");
        String senhaUser = input.nextLine();
        erro.CheckErros(nomeUser, "senha");

        System.out.println("Digite o seu tipo de usuario: ");
        menu.MenuTipoUser();

        Usuario usuario = null;

        int tipoUser = U.LerInt();
                
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

    private String CadastrarNome() throws Exception
    {
        System.out.println("Digite seu nome: ");
        String nome = input.nextLine();
        
        erro.CheckErros(nome, "nome");

        return nome;
    }

    private int CadastrarId(ArrayList<Usuario> users) throws Exception
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
}
