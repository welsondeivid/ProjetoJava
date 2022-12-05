import java.util.ArrayList;
import java.util.Scanner;

public class Manager {

    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    ArrayList<Projeto> projetos = new ArrayList<Projeto>();

    public static void main (String[] args) {
        
        Erros erro = new Erros();
        Utilidades U = new Utilidades();
        Manager m = new Manager();
        Menu menu = new Menu();
        
        String[] tipos = {"Grad", "Mest", "Dout", "Prof", "Pesq"};
	    
        Scanner input = new Scanner(System.in);

        int cmd = -1;
        
        while (cmd != 0)
        {
            try
            {
                menu.MenuPrincipal();
                cmd = U.LerInt(input);

                if (cmd == 1)
                {
                    int id = 0;
                    String senha = null;
                    System.out.println("\t\tLOGIN NO SISTEMA");

                    System.out.print("Digite seu ID: ");
                    id = U.LerInt(input);

                    System.out.print("Digite sua senha: ");
                    senha = input.nextLine();

                    try {

                        Usuario loginUser = U.BuscarUsuario(m.getUsuarios(), id);

                        if (senha.equals(loginUser.getSenha()))
                        {
                            System.out.println();
                            Login log = new Login();
                            System.out.println("Login realizado com Sucesso\n");
                            
                            log.LoginOn(input, loginUser, m);
                        }
                        else
                        {
                            throw new RuntimeException ("Senha incorreta");
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("Id fora do sistema");
                    }
                }
                else if (cmd == 2)
                {
                    System.out.println("Digite seu nome: ");
                    String nomeUser = input.nextLine();
                    
                    erro.CheckErros(nomeUser, "nome");
                    
                    System.out.println("Digite seu RG, usaremos como seu id: ");
                    int idUser = U.LerInt(input);
                    
                    try {

                        U.BuscarUsuario(m.getUsuarios(), idUser);
                        System.out.println("ID ja consta no sistema\nFalha no cadastro");
                        continue;

                    } catch (Exception e) {
                        
                       System.out.println("ID valido");
                    }

                    System.out.println("Digite seu email: "); //Falta Formatar
                    String emailUser = input.nextLine();
                    erro.CheckErros(nomeUser, "nome");

                    System.out.println("Digite sua senha: "); //Crie uma confirmação
                    String senhaUser = input.nextLine();
                    erro.CheckErros(nomeUser, "senha");

                    System.out.println("Digite o seu tipo de usuario: ");
                    for (int i = 0; i < tipos.length; i++)
                    {
                        System.out.println(tipos[i]);
                    }
                    String tipoUser = input.nextLine();

                    Usuario usuario = null;
                    
                    if (U.ChecarTipoUsuario(tipoUser).equals("Disc"))
                    {
                        usuario = new Discente(nomeUser, emailUser, senhaUser, tipoUser, idUser);
                    }
                    else if (U.ChecarTipoUsuario(tipoUser).equals("Doce"))
                    {
                        usuario = new Docente(nomeUser, emailUser, senhaUser, tipoUser, idUser);
                    }
                    
                    m.getUsuarios().add(usuario);
                }

                else if (cmd == 3)
                {
                    System.out.println("Para recuperar a senha preencha os campos abaixo");

                    System.out.print("Seu nome: ");
                    String nome = input.nextLine();

                    System.out.print("Seu Id: ");
                    int id = U.LerInt(input);

                    System.out.print("Seu email: ");
                    String email = input.nextLine();

                    Usuario user = U.BuscarUsuario(m.getUsuarios(), id);

                    if (nome.equals(user.getNome()) && email.equals(user.getEmail()))
                    {
                        System.out.println("Digite a nova senha: ");
                        {
                            String novaSenha = input.nextLine();

                            if (!novaSenha.equals(user.getSenha()))
                            {
                                user.setSenha(novaSenha);
                                System.out.println("Senha alterada com sucesso");
                            }
                            else
                            {
                                throw new RuntimeException ("Erro, senha igual a anterior");
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        
        input.close();
    }

    
    public boolean ChecarStatusDoProjeto (Projeto projeto)
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

    public ArrayList<Usuario> getUsuarios ()
    {
        return usuarios;
    }
    public ArrayList<Projeto> getProjetos()
    {
        return projetos;
    }
}