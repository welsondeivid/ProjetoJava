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

        System.out.println("Digite 0 para: Sair");
        System.out.println("Digite 1 para: Tudo");
        System.out.println("Digite 2 para: Coordenador");
        System.out.println("Digite 3 para: Adicionar ou remover usuários");
        System.out.println("Digite 4 para: Remover intercambistas");
        System.out.println("Digite 5 para: Adicionar ou remover atividades");
        System.out.println("Digite 6 para: Adicionar ou editar o valor da Bolsa-Desenvolvedor");
        System.out.println("Digite 7 para: Adicionar ou editar o valor da Bolsa-Testador");
        System.out.println("Digite 8 para: Adicionar ou editar o valor da Bolsa-Analista");
        System.out.println("Digite 9 para: Adicionar ou editar o prazo da Bolsa-Desenvolvedor");
        System.out.println("Digite 10 para: Adicionar ou editar o prazo da Bolsa-Testador");
        System.out.println("Digite 11 para: Adicionar ou editar o prazo da Bolsa-Analista");
        
    }

    public void MenuAtividade()
    {
        System.out.println("Escolha qual info deseja editar: ");

        System.out.println("Digite 0 para: Sair");
        System.out.println("Digite 1 para: Tudo");
        System.out.println("Digite 2 para: Responsavel");
        System.out.println("Digite 3 para: Adicionar ou remover usuarios da atividade");
        System.out.println("Digite 4 para: Adicionar ou remover tarefas da atividade");
    }

    public void MenuEditarUsuario(boolean liberado)
    {
        System.out.println("O que deseja editar? ");

        System.out.println("Digite 0  para: Sair");
        System.out.println("Digite 1  para: Alterar a senha");
        System.out.println("Digite 2  para: Alterar o status de uma tarefa");
        
        if (liberado)
        {
            System.out.println("Digite 3  para: Ingressar em um projeto");
            System.out.println("Digite 4  para: Ingressar em uma atividade");
        }
    }
    
    public void MenuEditarTarefa()
    {
        System.out.println("Digite 0  para: Escolher de novo");
        System.out.println("Digite 1  para: Marcar como Iniciada");
        System.out.println("Digite 2  para: Marcar como Finalizada");
    }

    public void MenuUsuarioDocente()
    {
        System.out.println("Digite 0  para: Sair");
        System.out.println("Digite 1  para: Criar um projeto");
        System.out.println("Digite 2  para: Editar um projeto");
        System.out.println("Digite 3  para: Editar uma atividade");
        System.out.println("Digite 4  para: Editar um usuario");
        System.out.println("Digite 5  para: Alterar o status de um projeto");
        System.out.println("Digite 6  para: Consultar");
        System.out.println("Digite 7  para: Relatorio");
        System.out.println("Digite 8  para: Fazer o pagamento de bolsa");
        System.out.println("Digite 9  para: Realizar Intercambio");
    }

    public void MenuUsuarioDiscente()
    {
        System.out.println("Digite 0  para: Sair");
        System.out.println("Digite 1  para: Editar um usuario");
        System.out.println("Digite 2  para: Consultar");
        System.out.println("Digite 3  para: Relatorio");
    }

    public void MenuAddRemove()
    {
        System.out.println("Digite 1  para: Adicionar");
        System.out.println("Digite 2  para: Remover");
    }
    
    public void MenuStatusProjeto()
    {
        System.out.println("Digite 1 para 'Iniciado': ");
        System.out.println("Digite 2 para 'Concluido': ");
    }

    public void MenuConsulta()
    {
        System.out.println("Digite 1 para consultar um usuario");
        System.out.println("Digite 2 para consultar uma atividade");
        System.out.println("Digite 3 para consultar um projeto");
    }

    public void MenuRelatorio()
    {
        System.out.println("Digite 1 para: Relatorio de Projeto");
        System.out.println("Digite 2 para: Relatorios das Atividades");
    }
    
    public void MenuPagamento()
    {
        System.out.println("0 para: nenhum");
        System.out.println("1 para: todos");
        System.out.println("2 para: alguns");
    }
    
    public void MenuFuncUsuario()
    {
        System.out.println("Digite 1  para: Desenvolvedor");
        System.out.println("Digite 2  para: Testador");
        System.out.println("Digite 3  para: Analista");
        System.out.println("Digite 4  para: Tecnico");
    }

    public void MenuTipoUser()
    {
        System.out.println("Digite 1 para: Graduando");
        System.out.println("Digite 2 para: Mestrando");
        System.out.println("Digite 3 para: Doutorando");
        System.out.println("Digite 4 para: Professor");
        System.out.println("Digite 5 para: Pesquisador");
    }
}
