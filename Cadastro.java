import java.util.ArrayList;

public class Cadastro extends VarGlobais {
    
    public Usuario Cadastrar(ArrayList<Usuario> users) throws Exception
    {
        System.out.println("Digite seu nome: ");
        String nomeUser = input.nextLine();
        
        erro.CheckErros(nomeUser, "nome");
        
        System.out.println("Digite seu RG, usaremos como seu id: ");
        int idUser = U.LerInt();
        
        try {

            U.BuscarUsuario(users, idUser);
            System.out.println("\nID ja consta no sistema\nFalha no cadastro\n");
            return null;

        } catch (Exception e) {
            
            System.out.println("ID valido");
        }

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
}
