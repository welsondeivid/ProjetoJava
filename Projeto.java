import java.time.LocalDateTime;
import java.util.ArrayList;

public class Projeto {

    private int id = -1;
    private String desc = null;
    public String status = null;
    private LocalDateTime inicio = null;
    private LocalDateTime termino = null;

    private String coordenador = null;
    private ArrayList <String> profissionais = new ArrayList<String>();
    private ArrayList <Atividade> atividades = new ArrayList<Atividade>();

    private String bolsaDesenvolvedor = null;
    private String bolsaTestador = null;
    private String bolsaAnalista = null;
    private String tempoBolsaDesenvolvedor = null;
    private String tempoBolsaTestador = null;
    private String tempoBolsaAnalista = null;

    public int setID(int id)
    {
        this.id = id;
        return this.id;
    }
    public String setDesc(String desc)
    {
        this.desc = desc;
        return this.desc;
    }
    public LocalDateTime setInicio(LocalDateTime inicio)
    {
        this.inicio = inicio;
        return this.inicio;
    }
    public LocalDateTime setTermino(LocalDateTime termino)
    {
        this.termino = termino;
        return this.termino;
    }
    public String setCoordenador(String coordenador)
    {
        this.coordenador = coordenador;
        return this.coordenador;
    }
    public void setProfissionais(String profissional)
    {
        this.profissionais.add(profissional);
    }
    public void setAtividades(Atividade atividade)
    {
        this.atividades.add(atividade);
    }
    public String setBolsaDesenvolvedor(String bolsaDesenvolvedor)
    {
        this.bolsaDesenvolvedor = bolsaDesenvolvedor;
        return this.bolsaDesenvolvedor;
    }
    public String setBolsaTestador(String bolsaTestador)
    {
        this.bolsaTestador = bolsaTestador;
        return this.bolsaTestador;
    }
    public String setBolsaAnalista(String bolsaAnalista)
    {
        this.bolsaAnalista = bolsaAnalista;
        return this.bolsaAnalista;
    }
    public String setTempoBolsaDesenvolvedor(String tempoBolsaDesenvolvedor)
    {
        this.tempoBolsaDesenvolvedor = tempoBolsaDesenvolvedor;
        return this.tempoBolsaDesenvolvedor;
    }
    public String setTempoBolsaTestador(String tempoBolsaTestador)
    {
        this.tempoBolsaTestador = tempoBolsaTestador;
        return this.tempoBolsaTestador;
    }
    public String setTempoBolsaAnalista(String tempobolsaAnalista)
    {
        this.tempoBolsaAnalista = tempobolsaAnalista;
        return this.tempoBolsaAnalista;
    }
}