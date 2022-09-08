import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

public class Atividade {
    
    private int id = -1;
    private String desc = null;
    private String responsavel = null;
    private LocalDateTime inicio = null;
    private LocalDateTime termino = null;

    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();

    public Atividade(int id, String desc, String responsavel, LocalDateTime inicio, LocalDateTime termino)
    {
        this.setId(id);
        this.setDesc(desc);
        this.setResponsavel(responsavel);
        this.setInicio(inicio);
        this.setTermino(termino);
    }

    public void EditarAtividade(Projeto project, Atividade atividade, Scanner input)
    {
        System.out.println("Responsavel atual pela ativiadade: " +atividade.getResponsavel());
        System.out.println("Gostaria de alterar? 1 para sim");
        int decisao = input.nextInt();
        input.nextLine();

        if (decisao == 1)
        {
            String responsavel = input.nextLine();
            atividade.setResponsavel(responsavel);
        }

        System.out.println("Qual sera a quantidade de usuarios adicionados? 0 para nenhum");
        int quant = input.nextInt();
        input.nextLine();

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o CPF do usuario que deseja adicionar: ");
            int idUser = input.nextInt();
            input.nextLine();
            for (Usuario item : project.getProjetistas())
            {
                if (item.getId() == idUser)
                {
                    atividade.setUsuarios(item);
                    break;
                }
            }
        }

        System.out.println("Qual sera a quantidade de usuarios removidos? 0 para nenhum");
        quant = input.nextInt();
        input.nextLine();

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o CPF do usuario que deseja remover: ");
            int idUser = input.nextInt();
            input.nextLine();
            for (Usuario item : atividade.getUsuarios())
            {
                if (item.getId() == idUser)
                {
                    atividade.getUsuarios().remove(item);
                    break;
                }
            }
        }

        System.out.println("Qual sera a quantidade de tarefas adicionadas? 0 para nenhuma: ");
        quant = input.nextInt();
        input.nextLine();

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite as infos sobre a tarefa que deseja adicionar: ");
            
            System.out.println("Digite a descricao da tarefa: ");
            String descTarefa = input.nextLine();

            System.out.println("Digite o nome do profissional que realizara a tarefa: ");
            String profTarefa = input.nextLine();

            Tarefa tarefa = new Tarefa(descTarefa, profTarefa);
            atividade.setTarefas(tarefa);
        }

        System.out.println("Qual sera a quantidade de tarefas removidas? 0 para nenhuma");
        quant = input.nextInt();
        input.nextLine();

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o CPF do responsável pela tarefa que deseja remover: ");
            String profTarefa = input.nextLine();

            for (Tarefa item : atividade.getTarefas())
            {
                if (item.getProfissional().equals(profTarefa))
                {
                    System.out.println("Descricao da atividade: "+item.getDesc());
                    System.out.println("Gostaria de remove-la? 1 para sim");

                    int dec = input.nextInt();
                    if (dec == 1)
                    {
                        atividade.getTarefas().remove(item);
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

    public String getResponsavel() {
        return this.responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
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
}