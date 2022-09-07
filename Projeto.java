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

    public void EditarProjeto (Projeto project, Scanner input, DateTimeFormatter format)
    {
        System.out.println("Digite o nome do coordenador do projeto: ");
        project.setCoordenador(input.nextLine());

        /*System.out.println ("Quantos profissionais estarao no projeto? ");
        int num = input.nextInt();
        input.nextLine();
        for (int i = 0; i < num; i++)
        {
            System.out.println("Digite o nome de um profissional do projeto: ");
            project.setProfissionais(input.nextLine());
        }*/

        System.out.println("Digite o valor da bolsa para o(s) Desenvolvedor(es) no formato: ");
        project.setBolsaDesenvolvedor(input.nextFloat());

        System.out.println("Digite o valor da bolsa para o(s) Testador(es) no formato: ");
        project.setBolsaTestador(input.nextFloat());

        System.out.println("Digite o valor da bolsa para o(s) Analista(s) no formato: ");
        project.setBolsaAnalista(input.nextFloat());

        System.out.println("Digite o prazo da bolsa para o(s) Desenvolvedor(es) no formato: ");
        //project.setTempoBolsaDesenvolvedor(input.nextLine());

        System.out.println("Digite o prazo da bolsa para o(s) Testador(es) no formato: ");
        //project.setTempoBolsaTestador(input.nextLine());

        System.out.println("Digite o prazo da bolsa para o(s) Analista(s) no formato: ");
        //project.setTempoBolsaAnalista(input.nextLine());
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