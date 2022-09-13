import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Projeto {

    Utilidades U = new Utilidades();

    private int id = -1;
    private String desc = null;
    private String status = null;
    private LocalDateTime inicio = null;
    private LocalDateTime termino = null;

    private int idCoordenador = 0;
    private ArrayList<Usuario> desenvolvedores = new ArrayList<Usuario>();
    private ArrayList<Usuario> testadores = new ArrayList<Usuario>();
    private ArrayList<Usuario> analistas = new ArrayList<Usuario>();
    private ArrayList<Usuario> intercambistas = new ArrayList<Usuario>();

    private int idTecnico = 0;
    private ArrayList <Usuario> projetistas = new ArrayList<Usuario>();
    private ArrayList <Atividade> atividades = new ArrayList<Atividade>();

    private float bolsaDesenvolvedor = 0;
    private float bolsaTestador = 0;
    private float bolsaAnalista = 0;
    private LocalDateTime tempoBolsaDesenvolvedor = null;
    private LocalDateTime tempoBolsaTestador = null;
    private LocalDateTime tempoBolsaAnalista = null;

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
        System.out.println("Somente Pesquisadores ou Professores podem coordenar um projeto");
        System.out.println("Digite o RG do novo coordenador do projeto: ");
        int checkIdU = U.LerInt(input);
        Usuario usuario = U.BuscarUsuario(usuarios, checkIdU);

        if (usuario != null)
        {
            if (usuario.getTipo().equals("Prof") || usuario.getTipo().equals("Pesq"))
            {
                project.setIdCoordenador(usuario.getId());
                usuario.setCoord(true);
                usuario.setProjeto(project.getId());
            }
            else
            {
                System.out.println("Usuario precisa ser professor ou pesquisador");
                return;
            }
        }

        System.out.println("Qual sera a quantidade de usuarios adicionados? 0 para nenhum");
        int quant = U.LerInt(input);

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o RG do usuario que deseja adicionar: ");
            checkIdU = U.LerInt(input);
            usuario = U.BuscarUsuario(usuarios, checkIdU);

            if (usuario != null)
            {
                System.out.println("Designe a funcao dele no projeto: ");
                System.out.println("Digite 1 para: Desenvolvedor");
                System.out.println("Digite 2 para: Testador");
                System.out.println("Digite 3 para: Analista");
                System.out.println("Digite 4 para: Tecnico");
                int funcUsuario = U.LerInt(input);

                if (funcUsuario == 1)
                {
                    project.setDesenvolvedor(usuario);
                    usuario.setFunc("Devp");
                }
                else if (funcUsuario == 2)
                {
                    project.setTestador(usuario);
                    usuario.setFunc("Test");
                }
                else if (funcUsuario == 3)
                {
                    project.setAnalista(usuario);
                    usuario.setFunc("Anlt");
                }
                else if (funcUsuario == 4)
                {
                    if (project.getIdTecnico() == 0)
                    {
                        project.setIdTecnico(usuario.getId());
                        usuario.setFunc("Tecn");
                    }
                }
                else
                {
                    //erro
                }
                project.setProjetistas(usuario);
                usuario.setDiaPag(LocalDateTime.now());
            }
        }

        System.out.println("Qual sera a quantidade de usuarios removidos?");
        quant = U.LerInt(input);

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite RG do usuario que deseja remover: ");
            checkIdU = U.LerInt(input);
            usuario = U.BuscarUsuario(project.getProjetistas(), checkIdU);

            if (usuario != null)
            {
                if (usuario.getFunc().equals("Devp"))
                {
                    project.getDesenvolvedores().remove(usuario);
                    usuario.setFunc(null);
                }
                else if (usuario.getFunc().equals("Test"))
                {
                    project.getTestadores().remove(usuario);
                    usuario.setFunc(null);
                }
                else if (usuario.getFunc().equals("Anlt"))
                {
                    project.getAnalistas().remove(usuario);
                    usuario.setFunc(null);
                }
                else if (usuario.getFunc().equals("Tecn"))
                {
                    project.setIdTecnico(0);
                    usuario.setFunc(null);
                }
                project.getProjetistas().remove(usuario);
            }
        }
        System.out.println("Qual sera a quantidade de atividades adicionadas?");
        quant = U.LerInt(input);

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Crie a atividade a ser adicionada: ");

            System.out.println("Digite o ID da atividade: ");
            int idAtividade = U.LerInt(input);

            System.out.println("Digite a descricao da atividade: ");
            String descAtividade = input.nextLine();

            System.out.println("Digite o RG do responsavel pela atividade: ");
            checkIdU = U.LerInt(input);
            Usuario responsavel = U.BuscarUsuario(project.getProjetistas(), checkIdU);

            System.out.println ("Digite a data de inicio no formato: HH:mm dd/MM/yyyy");
            LocalDateTime inicio = LocalDateTime.parse(input.nextLine(), format);

            System.out.println ("Digite a data de termino no formato: HH:mm dd/MM/yyyy");
            LocalDateTime termino = LocalDateTime.parse(input.nextLine(), format);

            if (idAtividade > 0 && descAtividade != null && inicio != null && termino != null)
            {
                Atividade atividade = new Atividade(idAtividade, descAtividade, responsavel.getId(), responsavel, inicio, termino);
                project.setAtividades(atividade);
            }
        }

        System.out.println("Qual sera a quantidade de atividades removidas?");
        quant = U.LerInt(input);

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o ID da atividade a ser removida: ");
            int checkIdA = U.LerInt(input);

            Atividade atividade = U.BuscarAtividade(project.getAtividades(), checkIdA);
            if (atividade != null)
            {
                project.getAtividades().remove(atividade);
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

        System.out.println("Prazo atual da bolsa: ");
        U.MostrarDataHora(project.getTempoBolsaDesenvolvedor());
        System.out.println("Gostaria de alterar? 1 para S, 0 para N");
        int decisao = U.LerInt(input);

        if (decisao == 1)
        {
            System.out.println ("Digite a data do prazo no formato: HH:mm dd/MM/yyyy");
            LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
            project.setTempoBolsaDesenvolvedor(tempoBolsa);
        }

        System.out.println("Prazo atual da bolsa: ");
        U.MostrarDataHora(project.getTempoBolsaTestador());
        System.out.println("Gostaria de alterar? 1 para S, 0 para N");
        decisao = U.LerInt(input);

        if (decisao == 1)
        {
            System.out.println ("Digite a data do prazo no formato: HH:mm dd/MM/yyyy");
            LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
            project.setTempoBolsaTestador(tempoBolsa);
        }

        System.out.println("Prazo atual da bolsa: ");
        U.MostrarDataHora(project.getTempoBolsaAnalista());
        System.out.println("Gostaria de alterar? 1 para S, 0 para N");
        decisao = U.LerInt(input);

        if (decisao == 1)
        {
            System.out.println ("Digite a data do prazo no formato: HH:mm dd/MM/yyyy");
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

    public int getIdCoordenador() {
        return this.idCoordenador;
    }

    public void setIdCoordenador(int coordenador) {
        this.idCoordenador = coordenador;
    }

    public ArrayList<Usuario> getDesenvolvedores() {
        return this.desenvolvedores;
    }

    public void setDesenvolvedor(Usuario desenvolvedor) {
        desenvolvedores.add(desenvolvedor);
    }

    public ArrayList<Usuario> getTestadores() {
        return this.testadores;
    }

    public void setTestador(Usuario testador) {
        testadores.add(testador);
    }

    public ArrayList<Usuario> getAnalistas() {
        return this.analistas;
    }

    public void setAnalista(Usuario analista) {
        analistas.add(analista);
    }

    public int getIdTecnico()
    {
        return this.idTecnico;
    }
    public void setIdTecnico (int idTecnico)
    {
        this.idTecnico = idTecnico;
    }

    public ArrayList<Usuario> getIntercambistas() {
        return this.intercambistas;
    }

    public void setIntercambista(Usuario intercambista) {
        intercambistas.add(intercambista);
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