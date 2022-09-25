import java.util.ArrayList;
import java.util.Scanner;

//Polimento: Printar as listas para opcao
//Login e recuperacao de senha
//tratar os erros nos else's

public class Manager {

    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    ArrayList<Projeto> projetos = new ArrayList<Projeto>();

    public static void main (String[] args) {
        
        Utilidades U = new Utilidades();
        Manager m = new Manager();
        
        String[] tipos = {"Grad", "Mest", "Dout", "Prof", "Pesq"};
	    
        Scanner input = new Scanner(System.in);

        m.MenuPrincipal();

        int cmd = -1;

        cmd = U.LerInt(input);

        while (cmd != 0)
        {
            if (cmd == 1)
            {
                int id = 0;
                String senha = null;
                System.out.println("\t\tLOGIN NO SISTEMA");

                System.out.print("Digite seu ID: ");
                id = U.LerInt(input);

                System.out.print("Digite sua senha: ");
                senha = input.nextLine();

                Usuario loginUser = U.BuscarUsuario(m.getUsuarios(), id);

                if (loginUser != null)
                {
                    if (senha.equals(loginUser.getSenha()))
                    {
                        System.out.println();
                        Login log = new Login();
                        log.LoginOn(input, loginUser);
                    }
                    else
                    {
                        System.out.println("Senha incorreta");
                    }
                }
                else
                {
                    System.out.println("Id fora do sistema");
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

                System.out.println("Digite sua senha: "); //Crie uma confirmação
                String senhaUser = input.nextLine();

                System.out.println("Digite o seu tipo de usuario: "); // Especifique os tipos permitidos
                for (int i = 0; i < tipos.length; i++)
                {
                    System.out.println(tipos[i]);
                }
                String tipoUser = input.nextLine();

                Usuario usuario = new Usuario(nomeUser, emailUser, senhaUser, tipoUser, idUser);
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

                if (user != null)
                {
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
                                System.out.println("Erro, senha igual a anterior");
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("Campos diferentes de qualquer usuario no sitema");
                    System.out.println("Tente de novo");
                }
            }

            m.MenuPrincipal();
            cmd = U.LerInt(input);
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

    private void MenuPrincipal()
    {
        System.out.println("Digite 0 para: Sair");
        System.out.println("Digite 1 para: Login");
        System.out.println("Digite 2 para: Cadastrar");
        System.out.println("Digite 3 para: Recuperar senha");
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
        System.out.println("Digite 1 para tudo: "); //Coord
        System.out.println("Digite 2 para responsavel: "); //Coord
        System.out.println("Digite 3 para adicionar ou remover usuarios da atividade: "); //Resp
        System.out.println("Digite 4 para adicionar ou remover tarefas da atividade: "); //Resp
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