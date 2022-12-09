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
                    int id = 0;
                    String senha = null;
                    System.out.println("\t\tLOGIN NO SISTEMA");

                    System.out.print("Digite seu ID: ");
                    id = U.LerInt();

                    Usuario loginUser = U.BuscarUsuario(m.getUsuarios(), id);

                    System.out.print("Digite sua senha: ");
                    senha = input.nextLine();

                    if (senha.equals(loginUser.getSenha()))
                    {
                        System.out.println();
                        Login log = new Login();
                        System.out.println("Login realizado com Sucesso\n");
                        
                        log.LoginOn(loginUser, m);
                    }
                    else
                    {
                        throw new RuntimeException ("Erro: Senha incorreta");
                    }
                }
                else if (cmd == 2)
                {
                    Cadastro cadastro = new Cadastro();
                    Usuario usuario = null;

                    while (usuario == null)
                    {
                        try
                        {
                            usuario = cadastro.Cadastrar(m.getUsuarios());
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

                    System.out.print("Seu nome: ");
                    String nome = input.nextLine();

                    System.out.print("Seu Id: ");
                    int id = U.LerInt();

                    Usuario user = U.BuscarUsuario(m.getUsuarios(), id);

                    System.out.print("Seu email: ");
                    String email = input.nextLine();

                    if (!email.contains("@icproj")) email += "@icproj.com";

                    if (nome.equals(user.getNome()) && email.equals(user.getEmail()))
                    {
                        System.out.println("Digite a nova senha: ");
                        {
                            String novaSenha = input.nextLine();
                            erro.CheckErros(novaSenha, "senha");

                            if (!novaSenha.equals(user.getSenha()))
                            {
                                user.setSenha(novaSenha);
                                System.out.println("Senha alterada com sucesso");
                            }
                            else
                            {
                                throw new RuntimeException ("Erro: Senha igual a anterior");
                            }
                        }
                    }
                }
                else
                {
                    if (cmd != 0)   throw new RuntimeException ("Erro: Valor invalido");
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage()+"\n");
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