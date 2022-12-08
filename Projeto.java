import java.time.LocalDateTime;
import java.util.ArrayList;

public class Projeto extends VarGlobais implements Lista{

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

    public Projeto (int id, String desc, LocalDateTime inicio, LocalDateTime termino, String status, Usuario Coord)
    {
        this.setId(id);
        this.setDesc(desc);
        this.setInicio(inicio);
        this.setTermino(termino);
        this.setStatus(status);
        this.setIdCoordenador(Coord.getId());
        this.setProjetistas(Coord);
    }

    public void EditarProjeto (ArrayList<Usuario> usuarios) throws Exception
    {
        System.out.println("Somente Docentes podem coordenar um projeto");
        DesignarCoordenador(usuarios);

        AdicionarUsuarios(usuarios);

        RemoverUsuarios(this.projetistas);

        AdicionarAtividades();

        RemoverAtividades();                       

        DefinirBolsa("Desenvolvedor");

        DefinirBolsa("Testador");

        DefinirBolsa("Analista");

        DefinirPrazoBolsa("Desenvolvedor");

        DefinirPrazoBolsa("Testador");

        DefinirPrazoBolsa("Analista");
    }

    public void DesignarCoordenador(ArrayList<Usuario> usuarios) throws Exception
    {
        System.out.println("Digite o RG do novo coordenador do projeto: ");
        ListarDocentes(usuarios);

        int checkIdU = U.LerInt();
        try {
            
            Usuario usuario = U.BuscarUsuario(usuarios, checkIdU);
            if (usuario instanceof Docente)
            {
                Docente doce = (Docente)usuario;
                doce.setCoord(true);
                this.setIdCoordenador(usuario.getId());
                usuario.setProjeto(this.getId());
            }
            else
            {
                System.out.println("Usuario precisa ser Docente");
            }
        } catch (Exception e) {
            DesignarCoordenador(usuarios);
        }
    }

    public void AdicionarUsuarios(ArrayList<Usuario> usuarios) throws Exception
    {
        System.out.println("Qual sera a quantidade de usuarios adicionados? 0 para nenhum");
        ListarUsers(usuarios);
        int quant = U.LerInt();

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o RG do usuario que deseja adicionar: ");
            int checkIdU = U.LerInt();
            Usuario usuario = U.BuscarUsuario(usuarios, checkIdU);
            Docente doce = null;

            if (usuario instanceof Docente)
            {
                doce = (Docente)usuario;
            }
            if (!doce.getCoord())
            {
                System.out.println("Designe a funcao dele no projeto: ");
                menu.MenuFuncUsuario();
                
                int funcUsuario = U.LerInt();

                if (funcUsuario == 1)
                {
                    this.setDesenvolvedor(usuario);
                    usuario.setFunc("Devp");
                }
                else if (funcUsuario == 2)
                {
                    this.setTestador(usuario);
                    usuario.setFunc("Test");
                }
                else if (funcUsuario == 3)
                {
                    this.setAnalista(usuario);
                    usuario.setFunc("Anlt");
                }
                else if (funcUsuario == 4)
                {
                    if (this.getIdTecnico() == 0)
                    {
                        this.setIdTecnico(usuario.getId());
                        usuario.setFunc("Tecn");
                    }
                }
                
                this.setProjetistas(usuario);
                usuario.setDiaIngresso(LocalDateTime.now());
                usuario.setProjeto(this.getId());

                if (usuario instanceof Discente)
                {
                    Discente disc = (Discente)usuario;
                    disc.setDiaPag(LocalDateTime.now());
                }
            }
        }
    }

    public void RemoverUsuarios(ArrayList<Usuario> usuarios) throws Exception
    {
        System.out.println("Qual sera a quantidade de usuarios removidos?");
        ListarUsers(usuarios);
        int quant = U.LerInt();

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite RG do usuario que deseja remover: ");
            int checkIdU = U.LerInt();
            Usuario usuario = U.BuscarUsuario(this.getProjetistas(), checkIdU);

            if (usuario.getFunc().equals("Devp"))
            {
                this.getDesenvolvedores().remove(usuario);
                usuario.setFunc(null);
            }
            else if (usuario.getFunc().equals("Test"))
            {
                this.getTestadores().remove(usuario);
                usuario.setFunc(null);
            }
            else if (usuario.getFunc().equals("Anlt"))
            {
                this.getAnalistas().remove(usuario);
                usuario.setFunc(null);
            }
            else if (usuario.getFunc().equals("Tecn"))
            {
                this.setIdTecnico(0);
                usuario.setFunc(null);
            }

            this.getProjetistas().remove(usuario);
            usuario.setProjeto(0);

            Atividade ativ = U.BuscarAtividade(this.getAtividades(), usuario.getId());
            ativ.getUsuarios().remove(usuario);
            usuario.setAtividade(0);
            
            usuario.getTarefas().clear();

            if (usuario instanceof Discente)
            {
                Discente disc = (Discente)usuario;
                disc.setDiaPag(null);
            }
        }
    }

    public void RemoverIntercambista() throws Exception
    {
        System.out.println("Lista de Intercambistas atuais: ");
        ListarUsers(this.getIntercambistas());

        System.out.println("Qual sera a quantidade de usuarios removidos?");

        int quant = U.LerInt();

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite RG do usuario que deseja remover: ");
            int checkIdU = U.LerInt();
            Usuario user = U.BuscarUsuario(this.getIntercambistas(), checkIdU);

            Discente intercamb = (Discente)user;
            Atividade ativ = U.BuscarAtividade(this.getAtividades(), intercamb.getAtivInterCam());
            
            this.getIntercambistas().remove(intercamb);
            ativ.getUsuarios().remove(intercamb);
            intercamb.setProjInterCam(0);
            intercamb.setAtivInterCam(0);
        }
    }
    
    public void AdicionarAtividades() throws Exception
    {
        System.out.println("Qual sera a quantidade de atividades adicionadas?");
        int quant = U.LerInt();

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Crie a atividade a ser adicionada: ");

            System.out.println("Digite o ID da atividade: ");
            int idAtividade = U.LerInt();

            if (U.BuscarAtividade(this.getAtividades(), idAtividade) != null)
            {
                System.out.println("ID de atividade ja consta no sistema");
                System.out.println("Falha ao criar atividade");
                return;
            }

            System.out.println("Digite a descricao da atividade: ");
            String descAtividade = input.nextLine();

            System.out.println("Digite o RG do responsavel pela atividade: ");
            int checkIdU = U.LerInt();
            Usuario responsavel = U.BuscarUsuario(this.getProjetistas(), checkIdU);

            System.out.println ("Digite a data de inicio no formato: HH:mm dd/MM/yyyy");
            LocalDateTime inicio = LocalDateTime.parse(input.nextLine(), format);

            System.out.println ("Digite a data de termino no formato: HH:mm dd/MM/yyyy");
            LocalDateTime termino = LocalDateTime.parse(input.nextLine(), format);

            if (idAtividade > 0 && descAtividade != null && inicio != null && termino != null)
            {
                Atividade atividade = new Atividade(idAtividade, descAtividade, responsavel.getId(), responsavel, inicio, termino);
                this.setAtividades(atividade);
            }
        }
    }

    public void RemoverAtividades() throws Exception
    {
        System.out.println("Qual sera a quantidade de atividades removidas?");
        ListarAtivs(this.getAtividades());
        int quant = U.LerInt();

        for (int i = 0; i < quant; i++)
        {
            System.out.println("Digite o ID da atividade a ser removida: ");
            int checkIdA = U.LerInt();

            Atividade atividade = U.BuscarAtividade(this.getAtividades(), checkIdA);
            if (atividade != null)
            {
                for (Usuario item : atividade.getUsuarios())
                {
                    item.setAtividade(0);
                    item.getTarefas().clear();
                }
                atividade.getUsuarios().clear();
                this.getAtividades().remove(atividade);
                atividade.getTarefas().clear();
                atividade = null;
            }
        }
    }

    public void DefinirBolsa(String tipoBolsa) throws Exception
    {
        System.out.println("Digite o novo valor para a bolsa, -1 para manter");

        float bolsa = -1;
        System.out.print("Valor atual da Bolsa-"+tipoBolsa+": ");

        if (tipoBolsa.equals("Desenvolvedor"))
        {
            System.out.println(this.getBolsaDesenvolvedor());
            
            bolsa = U.LerFloat();

            if (bolsa > -1)
            {
                this.setBolsaDesenvolvedor(bolsa);
            }
        }
        else if (tipoBolsa.equals("Testador"))
        {
            System.out.println(+this.getBolsaTestador());

            bolsa = U.LerFloat();

            if (bolsa > -1)
            {
                this.setBolsaTestador(bolsa);
            }
        }
        else if (tipoBolsa.equals("Analista"))
        {
            System.out.println(this.getBolsaAnalista());
            
            bolsa = U.LerFloat();

            if (bolsa > -1)
            {
                this.setBolsaAnalista(bolsa);
            }
        }
    
        if (bolsa < -1)
        {
            //erro
        }
    }
    
    public void DefinirPrazoBolsa(String tipoBolsa) throws Exception
    {
        System.out.println("Defina um novo prazo para as bolsas, 1 para mudar");
        System.out.println("Prazo atual da bolsa: ");

        int decisao = U.LerInt();

        if (tipoBolsa.equals("Desenvolvedor"))
        {
            U.MostrarDataHora(this.getTempoBolsaDesenvolvedor());

            if (decisao == 1)
            {
                System.out.println ("Digite a data do prazo no formato: HH:mm dd/MM/yyyy");
                LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
                this.setTempoBolsaDesenvolvedor(tempoBolsa);
            }
        }
        else if (tipoBolsa.equals("Testador"))
        {
            U.MostrarDataHora(this.getTempoBolsaTestador());

            if (decisao == 1)
            {
                System.out.println ("Digite a data do prazo no formato: HH:mm dd/MM/yyyy");
                LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
                this.setTempoBolsaTestador(tempoBolsa);
            }
        }
        else if (tipoBolsa.equals("Analista"))
        {
            U.MostrarDataHora(this.getTempoBolsaAnalista());

            if (decisao == 1)
            {
                System.out.println ("Digite a data do prazo no formato: HH:mm dd/MM/yyyy");
                LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
                this.setTempoBolsaAnalista(tempoBolsa);
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

    public void setIdCoordenador(int idCoordenador) {
        this.idCoordenador = idCoordenador;
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

    @Override
    public String toString()
    {
        String coordNome = U.BuscarUsuario(this.getProjetistas(), this.getIdCoordenador()).getNome();

        return  "Projeto descrito: \n"+this.getDesc()+"\n"+
                "Status do Projeto: "+this.getStatus()+"\n"+
                "Inicio do Projeto: "+this.getInicio()+"\n"+
                "Termino do Projeto: "+this.getTermino()+"\n"+
                "Coordenador do Projeto: "+coordNome+"\n";
    }

    @Override
    public void ListarProjs(ArrayList<Projeto> projs)
    {
        
    }

    @Override
    public void ListarAtivs(ArrayList<Atividade> ativs)
    {
        System.out.println("        Lista de atividades disponiveis");
        for (Atividade item : ativs)
        {
            System.out.println("Descricao: "+item.getDesc());
            System.out.println("ID da atividade: "+item.getId());
        }
    }

    @Override
    public void ListarTasks(ArrayList<Tarefa> tasks)
    {
        
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
    public void ListarDocentes(ArrayList<Usuario> users)
    {
        System.out.println("        Lista de Docentes disponiveis");
        for (Usuario item : users)
        {
            if (item instanceof Docente)
            {
                System.out.println("Nome: "+item.getNome());
                System.out.println("ID: "+item.getId());
                System.out.println("Email: "+item.getEmail());
            }
        }
    }

    @Override
    public void ListarDiscentes(ArrayList<Usuario> users)
    {
        
    }
}