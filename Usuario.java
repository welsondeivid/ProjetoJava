import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Usuario
{
    Utilidades U = new Utilidades();
    Menu menu = new Menu();

    private int id = -1;
    private String nome = null;
    private String email = null;
    private String senha = null;
    private String tipo = null;

    private int projeto = 0;
    private LocalDateTime diaIngresso = null;
    private String func = null;
    private int atividade = 0;
    private ArrayList <Tarefa> tarefas = new ArrayList<Tarefa>();

    public Usuario(String nome, String email, String senha, String tipo, int id)
    {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        setTipo(tipo);
        setId(id);
    }

    public void AlterarSenha (Scanner input) throws Exception
    {
        System.out.println("Sua senha atual é "+this.senha);
        System.out.println("Gostaria de mudar? 1 para sim");

        int decisao = U.LerInt(input);

        if (decisao == 1)
        {
            System.out.println("Digite a nova senha: ");
            String novaSenha = input.nextLine();

            // tratamento de erro senha, vazia
            this.senha = novaSenha;
        }
    }
    
    public abstract void IngressarProjeto(Projeto project);

    public void IngressarAtividade(Projeto project, Scanner input)
    {
        if (project != null)
        {
            System.out.println("Digite o ID da atividade a qual gostaria de associar-se: ");
            int checkIdA = U.LerInt(input);
            Atividade atividade = U.BuscarAtividade(project.getAtividades(), checkIdA);

            if (atividade != null)
            {
                this.setAtividade(atividade.getId());
            }
        }
    }

    public void EditarTarefas(Scanner input)
    {
        System.out.println("Tarefas atribuidas: ");

        Tarefa item = null;
        for (int i = 0; i <= this.getTarefas().size(); i++)
        {
            item = this.getTarefas().get(i);
            System.out.println((i+1)+":"+item.getDesc());
        }

        int indTarefa = 0;

        while (indTarefa != -1)
        {
            System.out.println("Digite o indice da tarefa que deseja mudar o status: ");
            System.out.println("-1 para sair");

            indTarefa = U.LerInt(input) - 1;
            if (indTarefa >= 0 && indTarefa < this.getTarefas().size())
            {
                item = this.getTarefas().get(indTarefa);
            }
            else
            {
                item = null;
            }

            if (item != null)
            {
                System.out.println("Tarefa selecionada: "+item.getDesc());

                menu.MenuEditarTarefa();

                int decisao = U.LerInt(input);

                if (decisao == 1)
                {
                    item.setDesc("Iniciada");
                    System.out.println("Status Atualizado");
                }
                else if (decisao == 2)
                {
                    System.out.println("Status Atualizado");
                    item.setDesc("Finalizada");
                }
            }
            else
            {
                System.out.println("Indice invalido");
            }
        }
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFunc() {
        return this.func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public int getProjeto() {
        return this.projeto;
    }

    public void setProjeto(int projeto) {
        this.projeto = projeto;
    }

    public LocalDateTime getDiaIngresso() {
        return this.diaIngresso;
    }

    public void setDiaIngresso(LocalDateTime diaIngresso) {
        this.diaIngresso = diaIngresso;
    }

    public int getAtividade() {
        return this.atividade;
    }

    public void setAtividade(int atividade) {
        this.atividade = atividade;
    }

    public ArrayList<Tarefa> getTarefas() {
        return this.tarefas;
    }

    public void setTarefas(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    @Override
    public String toString()
    {
        String funcao = "";
        if (this.getFunc() != null)
        {
            funcao = "Funcao no Projeto: ";
            if (this.getFunc().equals("Devp"))          funcao.concat("Desenvolvedor");
            
            else if (this.getFunc().equals("Test"))     funcao.concat("Testador");

            else if (this.getFunc().equals("Anlt"))     funcao.concat("Analista");

            else if (this.getFunc().equals("Tecn"))     funcao.concat("Tecnico");
        }

        String dataHora = null, taskLists = "Ainda nao designadas";
        if (this.getProjeto() != 0) // se for code smells, iniciar strings vazias e juntar em 1 return
        {
            dataHora = U.MostrarDataHora(this.getDiaIngresso());

            if (this.getTarefas() != null) //try
            {
                for (Tarefa item : this.getTarefas())
                {
                    taskLists.concat(item.getDesc()+"\n");
                }
            }
            return  "Nome: "+this.getNome()+"\n"+
                    "Email: "+this.getEmail()+"\n"+
                    "Projeto Associado: "+this.getProjeto()+"\n"+
                    "Dia de ingresso: "+dataHora+"\n"+
                    "Atividade associada: "+this.getAtividade()+"\n"+
                    "Lista de Tarefas: "+"\n"+taskLists+
                    funcao+"\n";
        }
        return  "Nome: "+this.getNome()+"\n"+
                "Email: "+this.getEmail()+"\n"+
                "Sem projeto associado no momento";
    }
}