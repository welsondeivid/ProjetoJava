import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

public class Atividade implements Lista{
    
    Utilidades U = new Utilidades();

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
        this.setDesc(desc);
        this.setIdResponsavel(idResponsavel);
        this.setUsuarios(user);
        this.setInicio(inicio);
        this.setTermino(termino);
        this.setStatus("Iniciada");
    }

    public void EditarAtividade(Projeto project, Scanner input)
    {
        DefinirResponsavel(input);

        AdicionarUsuarios(project, input);

        RemoverUsuarios(input);

        AdicionarTarefas(input);

        RemoverTarefas(input);
    }

    public void DefinirResponsavel(Scanner input)
    {
        Usuario respAtual = U.BuscarUsuario(this.getUsuarios(), this.getIdResponsavel());

        System.out.print("Responsavel atual pela atividade: ");
        System.out.println(respAtual.getNome());

        System.out.println("Gostaria de alterar? 1 para sim");
        int decisao = U.LerInt(input);

        if (decisao == 1)
        {
            System.out.println("Digite o RG do novo Responsavel");
            ListarUsers(this.getUsuarios());
            int checkIdU = U.LerInt(input);
            Usuario idResponsavel = U.BuscarUsuario(this.getUsuarios(), checkIdU);
            this.setIdResponsavel(idResponsavel.getId());
            this.setUsuarios(idResponsavel);
        }
    }
    
    public void AdicionarUsuarios(Projeto project,Scanner input) //Continue Daqui
    {
        System.out.println("Qual sera a quantidade de usuarios adicionados? 0 para nenhum");
        ListarUsers(project.getProjetistas());
        int quant = U.LerInt(input);

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o RG do usuario que deseja adicionar: ");
            int checkIdU = U.LerInt(input);
            Usuario usuario = U.BuscarUsuario(project.getProjetistas(), checkIdU);

            if (usuario.getAtividade() == 0)
            {
                this.setUsuarios(usuario);
                usuario.setAtividade(this.getId());
            }
            else
            {
                System.out.println("Usuario ja esta alocado em uma atividade\ntente outro usuario\n");
                i--;
            }
        }
    }
    
    public void RemoverUsuarios(Scanner input)
    {
        System.out.println("Qual sera a quantidade de usuarios removidos? 0 para nenhum");
        ListarUsers(this.getUsuarios());
        int quant = U.LerInt(input);

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o RG do usuario que deseja remover: ");
            int checkIdU = U.LerInt(input);
            Usuario usuario = U.BuscarUsuario(this.getUsuarios(), checkIdU);

            usuario.setAtividade(0);
            usuario.getTarefas().clear();
            this.getUsuarios().remove(usuario);
        }
    }

    public void AdicionarTarefas(Scanner input)
    {
        System.out.println("Qual sera a quantidade de tarefas adicionadas? 0 para nenhuma: ");
        int quant = U.LerInt(input);

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite as infos sobre a tarefa que deseja adicionar: ");
            
            System.out.println("Digite a descricao da tarefa: ");
            String descTarefa = input.nextLine();

            System.out.println("Digite o RG do profissional que realizara a tarefa: ");
            int respTarefa = U.LerInt(input);
            Usuario user = U.BuscarUsuario(this.getUsuarios(), respTarefa);

            if (user != null)
            {
                Tarefa tarefa = new Tarefa(descTarefa, respTarefa);
                user.setTarefas(tarefa);
                this.setTarefas(tarefa);
            }
        }
    }
    
    public void RemoverTarefas(Scanner input)
    {
        System.out.println("Qual sera a quantidade de tarefas removidas? 0 para nenhuma");
        int quant = U.LerInt(input);

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o RG do responsável pela tarefa que deseja remover: ");
            ListarTasks(this.getTarefas());
            
            int respTarefa = U.LerInt(input);
            Usuario responsavel = U.BuscarUsuario(this.getUsuarios(), respTarefa);

            for (Tarefa item : this.getTarefas())
            {
                if (item.getProfissional() == respTarefa)
                {
                    System.out.println("Descricao da atividade: "+item.getDesc());
                    System.out.println("Gostaria de remove-la? 1 para sim");

                    int dec = U.LerInt(input);
                    if (dec == 1)
                    {
                        this.getTarefas().remove(item);
                        responsavel.getTarefas().remove(item);
                        item = null;
                        break;
                    }
                }
            }
        }
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
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
        String respNome = U.BuscarUsuario(this.getUsuarios(), this.getIdResponsavel()).getNome();
        
        return  "Atividade descrita: "+this.getDesc()+"\n"+
                "Responsavel pela Atividade: "+respNome+"\n"+
                "Inicio da Atividade: "+this.getInicio()+"\n"+
                "Termino da Atividade: "+this.getTermino()+"\n";
    }

    @Override
    public void ListarProjs(ArrayList<Projeto> projs) {
        
    }

    @Override
    public void ListarAtivs(ArrayList<Atividade> ativs) {
        
    }

    @Override
    public void ListarTasks(ArrayList<Tarefa> tasks)
    {
        System.out.println("        Lista de tarefas disponiveis");
        for (Tarefa item : tasks)
        {
            System.out.println("Descricao: "+item.getDesc());
            System.out.println("Status da tarefa: "+item.getStatus());
            System.out.println("ID do responsavel: "+item.getProfissional()+"\n");
        }
    }

    @Override
    public void ListarUsers(ArrayList<Usuario> users)
    {
        System.out.println("        Lista de usuarios disponiveis");
        for (Usuario item : users)
        {
            System.out.println("Nome: "+item.getNome());
            System.out.println("ID: "+item.getId());
        }
    }

    @Override
    public void ListarDocentes(ArrayList<Usuario> users) {
        
        
    }

    @Override
    public void ListarDiscentes(ArrayList<Usuario> users) {
        
        
    }
}