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

                System.out.println("Digite o RG do novo Responsavel");
                int checkIdU = U.LerInt();
                
                Usuario idResponsavel = U.Buscar(this.getUsuarios(), checkIdU);
                this.setIdResponsavel(idResponsavel.getId());
                System.out.println("Responsavel aletrado com sucesso");
                decisao = 0;

            } catch (Exception e) {
                System.out.println("Falha ao editar responsavel: "+e.getMessage());
            }
        }
    }
    
    public void AdicionarUsuarios(Projeto project) throws Exception
    {
        System.out.println("Qual sera a quantidade de usuarios adicionados? 0 para nenhum");
        U.Listar(project.getProjetistas());
        int quant = U.LerInt();

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o RG do usuario que deseja adicionar: ");
            int checkIdU = U.LerInt();

            try {
                 
                Usuario usuario = U.Buscar(project.getProjetistas(), checkIdU);
                if (usuario.getAtividade() == 0)
                {
                    this.setUsuarios(usuario);
                    usuario.setAtividade(this.getId());
                    System.out.println("Usuario adicionado com sucesso\n");
                }
                else
                {
                    System.out.println("Usuario ja esta alocado em uma atividade\nTente outro usuario\n");
                    i--;
                }
            } catch (Exception e) {
                
                System.out.println("Falha ao adicionar usuario: "+e.getMessage());
                System.out.println("\nManter o numero de adicoes? 1 para sim");

                int decisao = input.nextInt();

                if (decisao == 1)   i--;
            }
        }
    }
    
    public void RemoverUsuarios() throws Exception
    {
        System.out.println("Qual sera a quantidade de usuarios removidos? 0 para nenhum");
        U.Listar(this.getUsuarios());
        int quant = U.LerInt();

        for (int i = 0; i < quant; i++)
        {
            try {
                System.out.println("Digite o RG do usuario que deseja remover: ");
                int checkIdU = U.LerInt();

                Usuario usuario = U.Buscar(this.getUsuarios(), checkIdU);
                usuario.setAtividade(0);
                usuario.getTarefas().clear();
                this.getUsuarios().remove(usuario);
                System.out.println("Usuario removido com sucesso\n");

            } catch (Exception e) {
                System.out.println("Falha ao remover usuario: "+e.getMessage());
                System.out.println("\nManter o numero de remocoes? 1 para sim");

                int decisao = input.nextInt();

                if (decisao == 1)   i--;
            }
        }
    }

    public void AdicionarTarefas() throws Exception
    {
        System.out.println("Qual sera a quantidade de tarefas adicionadas? 0 para nenhuma: ");
        int quant = U.LerInt();

        for (int i = 0; i < quant; i++)
        {
            try {
                
                System.out.println("Digite as infos sobre a tarefa que deseja adicionar: ");
                
                System.out.println("Digite a descricao da tarefa: ");
                String descTarefa = input.nextLine();
                erro.CheckErros(descTarefa, "tarefa");

                System.out.println("Digite o RG do profissional que realizara a tarefa: ");
                U.Listar(this.getUsuarios());
                int respTarefa = U.LerInt();

                Usuario user = U.Buscar(this.getUsuarios(), respTarefa);
                
                Tarefa tarefa = new Tarefa(descTarefa, respTarefa);
                user.setTarefas(tarefa);
                this.setTarefas(tarefa);

                System.out.println("Tarefa adicionada com sucesso");
            
            } catch (Exception e) {
                System.out.println("Falha ao adicionar tarefa: "+e.getMessage());
                System.out.println("\nManter o numero de adicoes? 1 para sim");

                int decisao = input.nextInt();

                if (decisao == 1)   i--;
            }
        }
    }
    
    public void RemoverTarefas() throws Exception
    {
        System.out.println("Qual sera a quantidade de tarefas removidas? 0 para nenhuma");
        U.Listar(this.getTarefas());
        int quant = U.LerInt();

        for (int i = 0; i < quant; i++)
        {
            try {

                System.out.println("Digite o RG do responsável pela tarefa que deseja remover: ");
                int respTarefa = U.LerInt();
                Usuario responsavel = U.Buscar(this.getUsuarios(), respTarefa);

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
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Falha ao remover tarefa: "+e.getMessage());
                System.out.println("\nManter o numero de adicoes? 1 para sim");

                int decisao = input.nextInt();

                if (decisao == 1)   i--;
            }
        }
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

    public void setTarefas(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status)
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