public class Menu {

    public void MenuPrincipal()
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
        System.out.println("Digite 1 para tudo: ");
        System.out.println("Digite 2 para responsavel: ");
        System.out.println("Digite 3 para adicionar ou remover usuarios da atividade: ");
        System.out.println("Digite 4 para adicionar ou remover tarefas da atividade: ");
    }

    public void MenuEditarUsuario()
    {
        System.out.println("O que deseja editar? ");

        System.out.println("Digite 0  para sair: ");
        System.out.println("Digite 1 para alterar a senha: ");
        System.out.println("Digite 2  para ingressar em um projeto: ");
        System.out.println("Digite 3  para ingressar em uma atividade: ");
        System.out.println("Digite 4 para alterar o status de uma tarefa: ");
    }
    
    public void MenuUsuarioDocente()
    {
        System.out.println ("Digite 0  para: Sair");
        System.out.println ("Digite 1  para: Criar um projeto");
        System.out.println ("Digite 2  para: Editar um projeto");
        System.out.println ("Digite 3  para: Editar uma atividade");
        System.out.println ("Digite 4  para: Editar um usuario");
        System.out.println ("Digite 5  para: Alterar o status de um projeto");
        System.out.println ("Digite 6  para: Consultar");
        System.out.println ("Digite 7  para: Relatorio");
        System.out.println ("Digite 8  para: Fazer o pagamento de bolsa");
        System.out.println ("Digite 9  para: Realizar Intercambio");
    }

    public void MenuUsuarioDiscente()
    {
        System.out.println ("Digite 0  para: Sair");
        System.out.println ("Digite 1  para: Editar um usuario");
        System.out.println ("Digite 2  para: Consultar");
    }
}
