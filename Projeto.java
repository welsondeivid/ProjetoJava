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

    public void Editar (ArrayList<Usuario> usuarios, Usuario coord) throws Exception
    {
        System.out.println("Somente Docentes podem coordenar um projeto");
        DesignarCoordenador(usuarios);

        AdicionarUsuarios(usuarios);

        if (this.projetistas.size() > 0)
        {
            RemoverUsuarios(this.projetistas);
        }
        else
        {
            System.out.println("Erro: Lista de usuarios do projeto vazia");
        }

        AdicionarAtividades(coord);

        if (this.atividades.size() > 0)
        {
            RemoverAtividades();  
        }
        else
        {
            System.out.println("Erro: Lista de atividades do projeto vazia");
        }
                             

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

        Usuario usuario = U.BuscarUsuario(usuarios, checkIdU);

        if (usuario instanceof Docente)
        {
            Docente doce = (Docente)usuario;
            doce.setCoord(true);
            this.setIdCoordenador(usuario.getId());
            usuario.setProjeto(this.getId());
            System.out.println("Coordenador alterado");
        }
        else
        {
            System.out.println("Coordenador precisa ser Docente");
        }
    }

    public void AdicionarUsuarios(ArrayList<Usuario> usuarios) throws Exception
    {
        System.out.println("\nQual sera a quantidade de usuarios adicionados? 0 para nenhum\n");
        int quant = U.LerInt();

        for (int i = 0; i < quant; i++)
        {
            try {
            
                System.out.println("Digite o RG do usuario que deseja adicionar: ");
                ListarUsers(usuarios);
                int checkIdU = U.LerInt();
                Usuario usuario = U.BuscarUsuario(usuarios, checkIdU);
                
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

                usuario.IngressarProjeto(this);
                System.out.println("Usuario adicionado com sucesso\n");

            } catch (Exception e) {

                System.out.println("Falha ao adicionar usuario: "+e.getMessage());
                System.out.println("\nManter o numero de adicoes? 1 para sim");

                int decisao = input.nextInt();

                if (decisao == 1)   i--;
            }
            
        }
    }

    public void RemoverUsuarios(ArrayList<Usuario> usuarios) throws Exception
    {
        System.out.println("Qual sera a quantidade de usuarios removidos?");
        int quant = U.LerInt();

        for (int i = 0; i < quant; i++)
        {
            try {

                System.out.println("Digite RG do usuario que deseja remover: ");
                ListarUsers(usuarios);
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

                Atividade ativ = null;

                try {
                    ativ = U.BuscarAtividade(this.getAtividades(), usuario.getId());
                } catch (Exception e) {
                    System.out.println("Usuario sem atividade para remover\n");
                }
                usuario.SairProjeto(this, ativ);

            } catch (Exception e) {

                System.out.println("Falha ao remover usuario: "+e.getMessage());
                System.out.println("\nManter o numero de remocoes? 1 para sim");

                int decisao = input.nextInt();

                if (decisao == 1)   i--;
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
    
    public void AdicionarAtividades(Usuario coord) throws Exception
    {
        System.out.println("Qual sera a quantidade de atividades adicionadas?");
        int quant = U.LerInt();

        for (int i = 0; i < quant; i++)
        {
            try {

                System.out.println("Crie a atividade a ser adicionada: ");

                System.out.println("Digite o ID da atividade: ");
                int idAtividade = U.LerInt();

                try {
                    U.BuscarAtividade(this.getAtividades(), idAtividade);
                    System.out.println("Falha ao adicionar atividade: ID de atividade ja consta no sistema\n");
                    return;
                } catch (Exception e) {
                   System.out.println("ID disponivel");
                }

                System.out.println("Digite a descricao da atividade: ");
                String descAtividade = input.nextLine();

                System.out.println("Digite o RG do responsavel pela atividade: ");
                ListarUsers(this.getProjetistas());
                int checkIdU = U.LerInt();
                Usuario responsavel = U.BuscarUsuario(this.getProjetistas(), checkIdU);

                LocalDateTime inicio = null, termino = null;
                try
                {
                    inicio = U.DefinirDataHora();
                    termino = U.DefinirDataHora();
                }
                catch (Exception e)
                {
                    throw new RuntimeException(e.getMessage());
                }

                if (idAtividade > 0 && descAtividade != null && inicio != null && termino != null)
                {
                    Atividade atividade = new Atividade(idAtividade, descAtividade, responsavel.getId(), responsavel, inicio, termino);
                    this.setAtividades(atividade);
                    responsavel.setAtividade(idAtividade);
                    atividade.setUsuarios(coord);
                    coord.setAtividade(this.getId());
                }
            } catch (Exception e) {

                System.out.println("Falha ao adicionar atividade: "+e.getMessage());
                System.out.println("\nManter o numero de adicoes? 1 para sim");

                int decisao = input.nextInt();

                if (decisao == 1)   i--;
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
            try {

                System.out.println("Digite o ID da atividade a ser removida: ");
                ListarAtivs(atividades);
                int checkIdA = U.LerInt();

                Atividade atividade = U.BuscarAtividade(this.getAtividades(), checkIdA);

                for (Usuario item : atividade.getUsuarios())
                {
                    item.setAtividade(0);
                    item.getTarefas().clear();
                }
                atividade.getUsuarios().clear();
                this.getAtividades().remove(atividade);
                atividade.getTarefas().clear();
                atividade = null;

            } catch (Exception e) {

                System.out.println("Falha ao remover atividade: "+e.getMessage());
                System.out.println("\nManter o numero de adicoes? 1 para sim");

                int decisao = input.nextInt();

                if (decisao == 1)   i--;
            }
        }
    }

    public void DefinirBolsa(String tipoBolsa)
    {
        System.out.println("Digite o novo valor para a bolsa, -1 para manter");

        float bolsa = -1;
        System.out.println("Valores atuais das Bolsas: \n");

        System.out.println("Bolsa-Desenvolvedor: "+this.getBolsaDesenvolvedor());
        System.out.println("Bolsa-Desenvolvedor: "+this.getBolsaTestador());
        System.out.println("Bolsa-Desenvolvedor: "+this.getBolsaAnalista());

        boolean check = false;

        while (!check)
        {
            bolsa = U.LerFloat();

            if (bolsa == -1)
            {
                System.out.println("Valores Mantidos\n");
                check = true;
            }
            else if (bolsa > -1)
            {
                if (tipoBolsa.equals("Desenvolvedor"))
                {
                    this.setBolsaDesenvolvedor(bolsa);
                }
                else if (tipoBolsa.equals("Testador"))
                {
                    this.setBolsaTestador(bolsa);
                }
                else if (tipoBolsa.equals("Analista"))
                {
                    this.setBolsaAnalista(bolsa);
                }
                check = true;
                System.out.println("Valor alterado com sucesso\n");
            }
            
            else
            {
                System.out.println("Valor digitado menor que 0\n");
            }
        }
    }
    
    public void DefinirPrazoBolsa(String tipoBolsa) throws Exception
    {
        System.out.println("Defina um novo prazo para as bolsas, 1 para mudar");
        System.out.println("Prazos atuais das bolsas: \n");

        System.out.print("Bolsa-Desenvolvedor: "+U.MostrarDataHora(this.getTempoBolsaDesenvolvedor()));
        System.out.print("Bolsa-Testador: "+U.MostrarDataHora(this.getTempoBolsaTestador()));
        System.out.print("Bolsa-Analista: "+U.MostrarDataHora(this.getTempoBolsaAnalista()));

        int decisao = U.LerInt();

        if (decisao == 1)
        {
            boolean check = false;
            while (!check)
            {
                try {
                    if (tipoBolsa.equals("Desenvolvedor"))
                    {
                        this.setTempoBolsaDesenvolvedor(U.DefinirDataHora());
                    }
                    else if (tipoBolsa.equals("Testador"))
                    {
                        this.setTempoBolsaTestador(U.DefinirDataHora());
                    }
                    else if (tipoBolsa.equals("Analista"))
                    {
                        this.setTempoBolsaAnalista(U.DefinirDataHora());
                    }
                    check = true;
                    System.out.println("Prazo alterado com sucesso\n");

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void AlterarStatus() throws Exception
    {
        System.out.println("Seu projeto é o : "+this.getDesc());
        System.out.println("Status atual: "+this.getStatus());
        System.out.println("Gostaria de alterar? 1 para sim");

        int checkProj = U.LerInt();

        if (checkProj == 1)
        {
            System.out.println("Escolha para qual Status gostaria de alterar: ");
            menu.MenuStatusProjeto();

            int decisao = U.LerInt();
            if (decisao == 1)
            {
                boolean check = this.ChecarStatus();

                if (check)
                {
                    System.out.println("Status alterado com sucesso");
                    this.setStatus("Em andamento");
                }
                else
                {
                    System.out.println("Adicione as infos que faltam");
                }
            }
            else if (decisao == 2)
            {
                if (this.status.equals("Em andamento"))
                {
                    ArrayList<Atividade> ativs = new ArrayList<Atividade>();

                    for (Atividade item : this.getAtividades())
                    {
                        if (!item.getStatus().equals("Concluida"))  ativs.add(item);
                    }
                    if (ativs.isEmpty()) 
                    {
                        System.out.println("Status alterado para 'Concluído'");
                        this.setStatus("Concluído");
                    }
                    else
                    {
                        System.out.println("Alguma(s) atividades ainda não foram concluídas");
                        for (Atividade item : ativs)
                        {
                            System.out.println(item.getId()+": "+item.getDesc());
                        }
                    }
                }
                else
                {
                    System.out.println("Marque o projeto como iniciado");
                }
            }
            else
            {
                throw new RuntimeException ("Erro: Valor invalido");
            }
        }
    }
    
    public boolean ChecarStatus()
    {
        boolean falha = false;

        if (this.getIdCoordenador() == 0)
        {
            System.out.println("Falta designar Coordenador: ");
            falha = true;
        }
        if (this.getProjetistas().isEmpty())
        {
            System.out.println("Falta designar Projetistas: ");
            falha = true;
        }
        if (this.getDesenvolvedores().isEmpty())
        {
            System.out.println("Falta designar pelo menos 1 Desenvolvedor: ");
            falha = true;
        }if (this.getTestadores().isEmpty())
        {
            System.out.println("Falta designar pelo menos 1 Testador: ");
            falha = true;
        }
        if (this.getAnalistas().isEmpty())
        {
            System.out.println("Falta designar pelo menos 1 Analista: ");
            falha = true;
        }
        if (this.getIdTecnico() == 0)
        {
            System.out.println("Falta designar 1 Tecnico: ");
            falha = true;
        }
        if (this.getAtividades().isEmpty())
        {
            System.out.println("Falta designar Atividades: ");
            falha = true;
        }
        if (this.getBolsaDesenvolvedor() == 0)
        {
            System.out.println("Falta designar o valor da Bolsa do Desenvolvedor: ");
            falha = true;
        }
        if (this.getBolsaAnalista() == 0)
        {
            System.out.println("Falta designar o valor da Bolsa do Analista: ");
            falha = true;
        }
        if (this.getBolsaTestador() == 0)
        {
            System.out.println("Falta designar o valor da Bolsa do Testador: ");
            falha = true;
        }
        if (this.getTempoBolsaDesenvolvedor() == null)
        {
            System.out.println("Falta designar o tempo da Bolsa do Desenvolvedor: ");
            falha = true;
        }
        if (this.getTempoBolsaAnalista() == null)
        {
            System.out.println("Falta designar o tempo da Bolsa do Analista: ");
            falha = true;
        }
        if (this.getTempoBolsaTestador() == null)
        {
            System.out.println("Falta designar o tempo da Bolsa do Testador: ");
            falha = true;
        }

        if (!falha)
        {
            this.setStatus("Iniciado");
            return true;
        }
        return false; 
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
                System.out.println("Nome: "+item.getNome()+"ID: "+item.getId());
                System.out.println("Email: "+item.getEmail()+"\n");
            }
        }
    }

    @Override
    public void ListarDiscentes(ArrayList<Usuario> users)
    {
        
    }
}