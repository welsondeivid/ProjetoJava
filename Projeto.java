import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Projeto {

    private int id = -1;
    private String desc = null;
    public String status = null;
    private LocalDateTime inicio = null;
    private LocalDateTime termino = null;

    private String coordenador = null;
    private ArrayList <Usuario> projetistas = new ArrayList<Usuario>();
    private ArrayList <Atividade> atividades = new ArrayList<Atividade>();

    private float bolsaDesenvolvedor = 0;
    private float bolsaTestador = 0;
    private float bolsaAnalista = 0;
    private LocalDateTime tempoBolsaDesenvolvedor = null;
    private LocalDateTime tempoBolsaTestador = null;
    private LocalDateTime tempoBolsaAnalista = null;

    //Scanner input = new Scanner(System.in);

    public Projeto (int id, String desc, LocalDateTime inicio, LocalDateTime termino, String status)
    {
        this.setId(id);
        this.setDesc(desc);
        this.setInicio(inicio);
        this.setTermino(termino);
        this.setStatus(status);
    }

    public void EditarProjeto (ArrayList<Usuario> usuarios, Projeto project, Scanner input, DateTimeFormatter format)
    {
        System.out.println("Digite o nome do coordenador do projeto: ");
        project.setCoordenador(input.nextLine());

        System.out.println("Qual sera a quantidade de usuarios adicionados? 0 para nenhum");
        int quant = input.nextInt();
        input.nextLine();

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o CPF do usuario que deseja adicionar: ");
            int idUser = input.nextInt();
            input.nextLine();

            for (Usuario item : usuarios)
            {
                if (item.getId() == idUser)
                {
                    project.setProjetistas(item);
                    break;
                }
            }
        }

        System.out.println("Qual sera a quantidade de usuarios removidos?");
        quant = input.nextInt();
        input.nextLine();

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite CPF do usuario que deseja remover: ");
            int idUser = input.nextInt();
            input.nextLine();

            for (Usuario item: project.getProjetistas())
            {
                if (item.getId() == idUser)
                {
                    project.getProjetistas().remove(item);
                    break;
                }
            }
        }
        System.out.println("Qual sera a quantidade de atividades adicionadas?");
        quant = input.nextInt();
        input.nextLine();

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Crie a atividade a ser adicionada: ");

            System.out.println("Digite o ID da atividade: ");
            int idAtividade = input.nextInt();
            input.nextLine();

            System.out.println("Digite a descricao da atividade: ");
            String descAtividade = input.nextLine();

            System.out.println("Digite o nome do responsavel pela atividade: ");
            String responsavel = input.nextLine();

            System.out.println ("Digite a data de inicio no formato: HH:mm dd/MM/yyyy");
            LocalDateTime inicio = LocalDateTime.parse(input.nextLine(), format);

            System.out.println ("Digite a data de termino no formato: HH:mm dd/MM/yyyy");
            LocalDateTime termino = LocalDateTime.parse(input.nextLine(), format);

            if (idAtividade > 0 && descAtividade != null && inicio != null && termino != null)
            {
                Atividade atividade = new Atividade(idAtividade, descAtividade, responsavel, inicio, termino);
                project.setAtividades(atividade);
            }
        }

        System.out.println("Qual sera a quantidade de atividades removidas?");
        quant = input.nextInt();
        input.nextLine();

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o ID da atividade a ser removida: ");
            int idAtividade = input.nextInt();
            input.nextLine();

            for (Atividade item : project.getAtividades())
            {
                if (item.getId() == idAtividade)
                {
                    project.getAtividades().remove(item);
                    break;
                }
            }
        }                         

        System.out.println("Valor atual da Bolsa-Desenvolvedor: " +project.getBolsaDesenvolvedor());
        System.out.println("Digite o novo valor, -1 para manter");
        float bolsa = input.nextFloat();

        if (bolsa > -1)
        {
            project.setBolsaDesenvolvedor(bolsa);
        }
        else if (bolsa < -1)
        {
            //erro
        }

        System.out.println("Valor atual da Bolsa-Testador: " +project.getBolsaTestador());
        System.out.println("Digite o novo valor, -1 para manter");
        bolsa = input.nextFloat();

        if (bolsa > -1)
        {
            project.setBolsaTestador(bolsa);
        }
        else if (bolsa < -1)
        {
            //erro
        }

        System.out.println("Valor atual da bolsa: " +project.getBolsaAnalista());
        System.out.println("Digite o novo valor, -1 para manter");
        bolsa = input.nextFloat();

        if (bolsa > -1)
        {
            project.setBolsaAnalista(bolsa);
        }
        else if (bolsa < -1)
        {
            //erro
        }

        System.out.println("Prazo atual da bolsa: " +project.getTempoBolsaDesenvolvedor());
        System.out.println("Gostaria de alterar? 1 para S, 0 para N");
        int decisao = input.nextInt();
        input.nextLine();

        if (decisao == 1)
        {
            LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
            project.setTempoBolsaDesenvolvedor(tempoBolsa);
        }

        System.out.println("Prazo atual da bolsa: " +project.getTempoBolsaTestador());
        System.out.println("Gostaria de alterar? 1 para S, 0 para N");
        decisao = input.nextInt();
        input.nextLine();

        if (decisao == 1)
        {
            LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
            project.setTempoBolsaTestador(tempoBolsa);
        }

        System.out.println("Prazo atual da bolsa: " +project.getTempoBolsaAnalista());
        System.out.println("Gostaria de alterar? 1 para S, 0 para N");
        decisao = input.nextInt();
        input.nextLine();

        if (decisao == 1)
        {
            LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
            project.setTempoBolsaAnalista(tempoBolsa);
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCoordenador() {
        return this.coordenador;
    }

    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }

    public ArrayList<Usuario> getProjetistas() {
        return this.projetistas;
    }

    public void setProjetistas(Usuario projestista) {
        projetistas.add(projestista);
    }

    public ArrayList<Atividade> getAtividades() {
        return this.atividades;
    }

    public void setAtividades(Atividade atividade) {
        atividades.add(atividade);
    }

    public float getBolsaDesenvolvedor() {
        return this.bolsaDesenvolvedor;
    }

    public void setBolsaDesenvolvedor(float bolsaDesenvolvedor) {
        this.bolsaDesenvolvedor = bolsaDesenvolvedor;
    }

    public float getBolsaTestador() {
        return this.bolsaTestador;
    }

    public void setBolsaTestador(float bolsaTestador) {
        this.bolsaTestador = bolsaTestador;
    }

    public float getBolsaAnalista() {
        return this.bolsaAnalista;
    }

    public void setBolsaAnalista(float bolsaAnalista) {
        this.bolsaAnalista = bolsaAnalista;
    }

    public LocalDateTime getTempoBolsaDesenvolvedor() {
        return this.tempoBolsaDesenvolvedor;
    }

    public void setTempoBolsaDesenvolvedor(LocalDateTime tempoBolsaDesenvolvedor) {
        this.tempoBolsaDesenvolvedor = tempoBolsaDesenvolvedor;
    }

    public LocalDateTime getTempoBolsaTestador() {
        return this.tempoBolsaTestador;
    }

    public void setTempoBolsaTestador(LocalDateTime tempoBolsaTestador) {
        this.tempoBolsaTestador = tempoBolsaTestador;
    }

    public LocalDateTime getTempoBolsaAnalista() {
        return this.tempoBolsaAnalista;
    }

    public void setTempoBolsaAnalista(LocalDateTime tempoBolsaAnalista) {
        this.tempoBolsaAnalista = tempoBolsaAnalista;
    }
}