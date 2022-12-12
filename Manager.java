import java.util.ArrayList;
import java.util.Scanner;

public class Manager {

    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    ArrayList<Projeto> projetos = new ArrayList<Projeto>();

    public static void main (String[] args) {
        
        Utilidades U = new Utilidades();
        Manager m = new Manager();
        Menu menu = new Menu();
	    
        Scanner input = new Scanner(System.in);

        int cmd = -1;
        
        System.out.println("-----Gerenciador de Projetos-----\n");

        //teste, apague essas linhas
        Usuario testeU = new Docente("Welson", "Welson@icproj.com", "456", 5, 123);
        m.getUsuarios().add(testeU);
        Usuario testeU2 = new Discente("Deivid", "Deivid@icproj.com", "456", 1, 789);
        m.getUsuarios().add(testeU2);
        Usuario testeU3 = new Discente("Gustavo", "Gustavo@icproj.com", "456", 2, 147);
        m.getUsuarios().add(testeU3);
        Projeto testeP = new Projeto(1, "abc", null, null, null, testeU);
        m.getProjetos().add(testeP);
        testeU.setProjeto(testeP.getId());
        Atividade testeA = new Atividade(1, "atv1", testeU2.getId(), testeU2, null, null);
        testeP.setAtividades(testeA);
        testeP.setProjetistas(testeU2);
        testeP.setProjetistas(testeU3);
        testeU2.setProjeto(testeP.getId());
        testeU2.setAtividade(testeA.getId());
        testeA.setUsuarios(testeU);
        testeU.setAtividade(testeA.getId());
        //

        while (cmd != 0)
        {
            try
            {
                menu.MenuPrincipal();
                cmd = U.LerInt();

                if (cmd == 1)
                {
                    m.Logar(U, input, m);
                }
                else if (cmd == 2)
                {
                    Cadastro cadastro = new Cadastro();
                    Usuario usuario = null;

                    while (usuario == null)
                    {
                        try
                        {
                            usuario = cadastro.CadastrarUsuario(m.getUsuarios());
                        }
                        catch (Exception e)
                        {
                            System.out.println("Falha no cadastro\n"+e.getMessage()+"\n");
                        }
                    }
                    
                    m.getUsuarios().add(usuario);
                }

                else if (cmd == 3)
                {
                    System.out.println("Para recuperar a senha preencha os campos abaixo");

                    System.out.print("Seu Id: ");
                    int id = U.LerInt();

                    Usuario user = U.Buscar(m.getUsuarios(), id);

                    String novaSenha = m.RecuperarSenha(U, input, m, user);

                    if (novaSenha != null)
                    {
                        user.setSenha(novaSenha);
                        System.out.println("Senha alterada com sucesso");
                    }
                }
                else
                {
                    if (cmd != 0)   throw new RuntimeException ("Erro: Valor invalido");
                }
                U.Continue();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage()+"\n");
            }
        }
        
        input.close();
    }

    public void Logar(Utilidades U, Scanner input, Manager m) throws Exception
    {
        int id = 0;
        String senha = null;
        System.out.println("\t\tLOGIN NO SISTEMA");

        System.out.print("Digite seu ID: ");
        id = U.LerInt();

        Usuario loginUser = U.Buscar(m.getUsuarios(), id);

        System.out.print("Digite sua senha: ");
        senha = input.nextLine();

        if (senha.equals(loginUser.getSenha()))
        {
            Login log = new Login();
            System.out.println("\nLogin realizado com Sucesso\n");
            U.Continue();
            log.LoginOn(loginUser, m);
        }
        else
        {
            throw new RuntimeException ("Erro: Senha incorreta");
        }
    }
   
    public String RecuperarSenha(Utilidades U, Scanner input, Manager m, Usuario user) throws Exception
    {
        Erros erro = new Erros();
        
        System.out.print("Seu nome: ");
        String nome = input.nextLine();

        System.out.print("Seu email: ");
        String email = input.nextLine();

        if (!email.contains("@icproj")) email += "@icproj.com";

        if (nome.equals(user.getNome()) && email.equals(user.getEmail()))
        {
            System.out.println("Digite a nova senha: ");
            {
                String novaSenha = input.nextLine();
                erro.CheckErros(novaSenha, "vazio");

                if (!novaSenha.equals(user.getSenha()))
                {
                    return novaSenha;
                }
                else
                {
                    throw new RuntimeException ("Erro: Senha igual a anterior");
                }
            }
        }

        return null;
    }
    
    public ArrayList<Usuario> getUsuarios ()
    {
        return usuarios;
    }
    
    public ArrayList<Projeto> getProjetos()
    {
        return projetos;
    }
}