import java.time.LocalDateTime;
import java.util.ArrayList;

public class Atividade extends VarGlobais implements Busca{
    
    private int id = -1;
    private String desc = null;
    private int idResponsavel = 0;
    private LocalDateTime inicio = null;
    private LocalDateTime termino = null;

    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();

    private String status = null;

    public Atividade(int id, String desc, int idResponsavel, Usuario user, LocalDateTime inicio, LocalDateTime termino)
    {
        this.setId(id);
        this.setNome(desc);
        this.setIdResponsavel(idResponsavel);
        this.setUsuarios(user);
        this.setInicio(inicio);
        this.setTermino(termino);
        this.setStatus("Iniciada");
    }

    public void EditarAtividade(Projeto project) throws Exception
    {
        DefinirResponsavel();

        AdicionarUsuarios(project);

        if (this.getUsuarios().size() > 0)   RemoverUsuarios();

        AdicionarTarefas();

        if (this.getTarefas().size() > 0)  RemoverTarefas();
    }

    public void DefinirResponsavel()
    {
        Usuario respAtual = U.Buscar(this.getUsuarios(), this.getIdResponsavel());

        System.out.print("Responsavel atual pela atividade: ");
        System.out.println(respAtual.getNome());

        System.out.println("Gostaria de alterar? 1 para sim");
        int decisao = U.LerInt();

        U.Listar(this.getUsuarios());

        while (decisao == 1)
        {
            try {

                Usuario idResponsavel = U.EscolherUser(this.getUsuarios(), "do novo Responsavel");

                this.setIdResponsavel(idResponsavel.getId());
                System.out.println("Responsavel alterado com sucesso");
                U.Continue();
                decisao = 0;

            } catch (Exception e) {
                System.out.println("Falha ao editar responsavel: "+e.getMessage());
            }
        }
    }
    
    public void AdicionarUsuarios(Projeto project) throws Exception
    {
        int quant = U.EscolherQuant("usuarios adicionados", project.getProjetistas());

        for (int i = 0; i < quant; i++)
        {
            try {

                Usuario usuario = U.EscolherUser(project.getProjetistas(), "do usuario que deseja adicionar");

                if (usuario.getAtividade() == 0)
                {
                    this.setUsuarios(usuario);
                    usuario.setAtividade(this.getId());
                    System.out.println("Usuario adicionado com sucesso\n");
                    U.Continue();
                }
                else
                {
                    System.out.println("Usuario ja esta alocado em uma atividade\nTente outro usuario\n");
                    i--;
                }
            } catch (Exception e) {
                
                if (erro.TratarEscolha(input, e, "adicionar usuario") == 1)   i--;
            }
        }
    }
    
    public void RemoverUsuarios() throws Exception
    {
        int quant = U.EscolherQuant("usuarios removidos", this.getUsuarios());

        for (int i = 0; i < quant; i++)
        {
            try {
                
                Usuario usuario = U.EscolherUser(this.getUsuarios(), "do usuario que deseja remover");

                usuario.setAtividade(0);
                usuario.getTarefas().clear();
                this.getUsuarios().remove(usuario);
                System.out.println("Usuario removido com sucesso\n");
                U.Continue();

            } catch (Exception e) {

                if (erro.TratarEscolha(input, e, "remover usuario") == 1)   i--;
            }
        }
    }

    public void AdicionarTarefas() throws Exception
    {
        int quant = U.EscolherQuant("tarefas adicionadas", null);

        for (int i = 0; i < quant; i++)
        {
            try {

                this.setTarefas(CriarTarefa());

                System.out.println("Tarefa adicionada com sucesso");
                U.Continue();
            
            } catch (Exception e) {

                if (erro.TratarEscolha(input, e, "adicionar tarefa") == 1)   i--;
            }
        }
    }
    
    public void RemoverTarefas() throws Exception
    {
        int quant = U.EscolherQuant("tarefas removidas", this.getTarefas());

        for (int i = 0; i < quant; i++)
        {
            try {

                Usuario responsavel = U.EscolherUser(this.getUsuarios(), "da tarefa que deseja remover");

                for (Tarefa item : responsavel.getTarefas())
                {
                    System.out.println("Descricao da tarefa: "+item.getNome());
                    System.out.println("Gostaria de remove-la? 1 para sim");

                    int dec = U.LerInt();

                    if (dec == 1)
                    {
                        this.getTarefas().remove(item);
                        responsavel.getTarefas().remove(item);
                        item = null;
                        U.Continue();
                        break;
                    }
                }
            } catch (Exception e) {
                
                if (erro.TratarEscolha(input, e, "remover tarefa") == 1)   i--;
            }
        }
    }
    
    public void UsersRelatorio(ArrayList<Usuario> users)
    {
        System.out.println("Lista de usuarios: ");
        
        if (users.isEmpty())
        {
            for(Usuario item : users)
            {
                System.out.println("Nome: "+item.getNome());
                System.out.println("Func: "+item.getFunc());
            }
        }
        else    System.out.println("Sem usuario no momento");
    }

    public ArrayList<Tarefa> TasksRelatorio(ArrayList<Tarefa> tasks, ArrayList<Tarefa> newTasks)
    {
        System.out.println("Lista de tarefas: ");

        for(Tarefa item : tasks)
        {
            if (!item.getStatus().equals("Finalizada"))
            {
                newTasks.add(item);
            }

            System.out.println(item);
        }

        return newTasks;
    }

    public void AlterarStatus(ArrayList<Tarefa> tasks)
    {
        if (tasks.isEmpty())
        {
            System.out.println("Todas as tarefas Finalizadas, alterando Status para Concluida");
            U.Continue();
            this.setStatus("Concluida");
        }
        else
        {
            System.out.println("Tarefas restantes para concluir a atividade: ");
            for (Tarefa item : tasks)
            {
                System.out.println(item);
            }
            U.Continue();
        }
    }

    private Tarefa CriarTarefa() throws Exception
    {
        System.out.println("Digite as infos sobre a tarefa que deseja adicionar: ");

        Cadastro cadastro = new Cadastro();
        Tarefa tarefa = null;
        
        tarefa = cadastro.CadastrarTarefa(this);

        return tarefa;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return this.desc;
    }

    public void setNome(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getInicio() {
        return this.inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getTermino() {
        return this.termino;
    }

    public void setTermino(LocalDateTime termino) {
        this.termino = termino;
    }

    public int getIdResponsavel() {
        return this.idResponsavel;
    }

    public void setIdResponsavel(int idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public ArrayList<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(Usuario usuario) {
        usuarios.add(usuario);
    }

    public ArrayList<Tarefa> getTarefas() {
        return this.tarefas;
    }

    private void setTarefas(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public String getStatus()
    {
        return this.status;
    }

    private void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        String respNome = U.Buscar(this.getUsuarios(), this.getIdResponsavel()).getNome();
        
        return  "Atividade descrita: "+this.getNome()+"\n"+
                "Responsavel pela Atividade: "+respNome+"\n"+
                "Inicio da Atividade: "+this.getInicio()+"\n"+
                "Termino da Atividade: "+this.getTermino()+"\n";
    }
}