import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

public class Atividade {
    
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

    public void EditarAtividade(Projeto project, Atividade atividade, Scanner input)
    {
        DefinirResponsavel(atividade, input);

        AdicionarUsuarios(project, atividade, input);

        RemoverUsuarios(atividade, input);

        AdicionarTarefas(atividade, input);

        RemoverTarefas(atividade, input);
    }

    public void DefinirResponsavel(Atividade atividade, Scanner input)
    {
        Usuario respAtual = U.BuscarUsuario(atividade.getUsuarios(), atividade.getIdResponsavel());

        System.out.print("Responsavel atual pela atividade: ");
        System.out.println(respAtual.getNome());

        System.out.println("Gostaria de alterar? 1 para sim");
        int decisao = U.LerInt(input);

        if (decisao == 1)
        {
            System.out.println("Digite o RG do novo Responsavel");
            int checkIdU = U.LerInt(input);
            Usuario idResponsavel = U.BuscarUsuario(atividade.getUsuarios(), checkIdU);
            atividade.setIdResponsavel(idResponsavel.getId());
            atividade.setUsuarios(idResponsavel);
        }
    }
    
    public void AdicionarUsuarios(Projeto project, Atividade atividade, Scanner input)
    {
        System.out.println("Qual sera a quantidade de usuarios adicionados? 0 para nenhum");
        int quant = U.LerInt(input);

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o RG do usuario que deseja adicionar: ");
            int checkIdU = U.LerInt(input);
            Usuario usuario = U.BuscarUsuario(project.getProjetistas(), checkIdU);

            if (usuario != null)
            {
                atividade.setUsuarios(usuario);
            }
        }
    }
    
    public void RemoverUsuarios(Atividade atividade, Scanner input)
    {
        System.out.println("Qual sera a quantidade de usuarios removidos? 0 para nenhum");
        int quant = U.LerInt(input);

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o RG do usuario que deseja remover: ");
            int checkIdU = U.LerInt(input);
            Usuario usuario = U.BuscarUsuario(atividade.getUsuarios(), checkIdU);

            if (usuario != null)
            {
                usuario.setAtividade(0);
                usuario.getTarefas().clear();
                atividade.getUsuarios().remove(usuario);
            }
        }
    }

    public void AdicionarTarefas(Atividade atividade, Scanner input)
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
            Usuario user = U.BuscarUsuario(atividade.getUsuarios(), respTarefa);

            if (user != null)
            {
                Tarefa tarefa = new Tarefa(descTarefa, respTarefa);
                user.setTarefas(tarefa);
                atividade.setTarefas(tarefa);
            }
        }
    }
    
    public void RemoverTarefas(Atividade atividade, Scanner input)
    {
        System.out.println("Qual sera a quantidade de tarefas removidas? 0 para nenhuma");
        int quant = U.LerInt(input);

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o RG do responsável pela tarefa que deseja remover: ");
            int respTarefa = U.LerInt(input);
            Usuario responsavel = U.BuscarUsuario(atividade.getUsuarios(), respTarefa);

            for (Tarefa item : atividade.getTarefas())
            {
                if (item.getProfissional() == respTarefa)
                {
                    System.out.println("Descricao da atividade: "+item.getDesc());
                    System.out.println("Gostaria de remove-la? 1 para sim");

                    int dec = U.LerInt(input);
                    if (dec == 1)
                    {
                        atividade.getTarefas().remove(item);
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
}