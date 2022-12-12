import java.time.LocalDateTime;
import java.util.ArrayList;

public class Projeto extends VarGlobais implements Busca{
    
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

    public Projeto (int id, String desc, LocalDateTime inicio, LocalDateTime termino, String status, Usuario Coord)
    {
        this.setId(id);
        this.setNome(desc);
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
                             
        gerBolsa.DefinirBolsa("Desenvolvedor");

        gerBolsa.DefinirBolsa("Testador");

        gerBolsa.DefinirBolsa("Analista");

        gerBolsa.DefinirPrazoBolsa("Desenvolvedor");

        gerBolsa.DefinirPrazoBolsa("Testador");

        gerBolsa.DefinirPrazoBolsa("Analista");
    }

    public void DesignarCoordenador(ArrayList<Usuario> usuarios) throws Exception
    {
        Usuario usuario = U.EscolherUser(usuarios, "do novo coordenador do projeto");

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
        U.Continue();
    }

    public void AdicionarUsuarios(ArrayList<Usuario> usuarios) throws Exception
    {
        int quant = U.EscolherQuant("usuarios adicionados", usuarios);;

        for (int i = 0; i < quant; i++)
        {
            try {
            
                Usuario usuario = U.EscolherUser(usuarios, "do usuario que deseja adicionar");
                
                System.out.println("Designe a funcao dele no projeto: ");
                menu.MenuFuncUsuario();
                
                DefinirFuncUser(usuario);

                usuario.IngressarProjeto(this);
                System.out.println("Usuario adicionado com sucesso\n");
                U.Continue();

            } catch (Exception e) {

                if (erro.TratarEscolha(input, e, "adicionar usuario") == 1)   i--;
            }
            
        }
    }

    public void RemoverUsuarios(ArrayList<Usuario> usuarios) throws Exception
    {
        int quant = U.EscolherQuant("usuarios removidos", this.getProjetistas());

        for (int i = 0; i < quant; i++)
        {
            try {

                Usuario usuario = U.EscolherUser(this.getProjetistas(), "do usuario que deseja remover");

                RemoverFuncUser(usuario);

                Atividade ativ = null;

                try {
                    ativ = U.Buscar(this.getAtividades(), usuario.getId());
                } catch (Exception e) {
                    System.out.println("Usuario sem atividade para remover\n");
                }

                usuario.SairProjeto(this, ativ);
                System.out.println("Usuario removido com sucesso");
                U.Continue();

            } catch (Exception e) {

                if (erro.TratarEscolha(input, e, "remover usuario") == 1)   i--;
            }
            
        }
    }

    public void RemoverIntercambista() throws Exception
    {
        int quant = U.EscolherQuant("usuarios removidos", this.getIntercambistas());

        for (int i = 0; i < quant; i++)
        {
            Usuario user = U.EscolherUser(this.getIntercambistas(), "do usuario que deseja remover");

            Discente intercamb = (Discente)user;
            Atividade ativ = U.Buscar(this.getAtividades(), intercamb.getAtivInterCam());
            
            this.getIntercambistas().remove(intercamb);
            ativ.getUsuarios().remove(intercamb);
            intercamb.setProjInterCam(0);
            intercamb.setAtivInterCam(0);

            System.out.println("Usuario removido com sucesso");
            U.Continue();
        }
    }
    
    public void AdicionarAtividades(Usuario coord) throws Exception
    {
        int quant = U.EscolherQuant("atividades adicionadas", null);

        for (int i = 0; i < quant; i++)
        {
            try {

                Cadastro cadastro = new Cadastro();
                Atividade atividade = null;

                System.out.println("Crie a atividade a ser adicionada: ");

                atividade = cadastro.CadastrarAtividade(this, coord);

                this.setAtividades(atividade);
                
            } catch (Exception e) {

                if (erro.TratarEscolha(input, e, "adicionar atividade") == 1)   i--;
            }
        }
    }

    public void RemoverAtividades() throws Exception
    {
        System.out.println("Qual sera a quantidade de atividades removidas?");
        U.Listar(this.getAtividades());
        int quant = U.LerInt();

        for (int i = 0; i < quant; i++)
        {
            try {

                System.out.println("Digite o ID da atividade a ser removida: ");
                U.Listar(atividades);
                int checkIdA = U.LerInt();

                Atividade atividade = U.Buscar(this.getAtividades(), checkIdA);

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

    public void AlterarStatus() throws Exception
    {
        System.out.println("Seu projeto é o : "+this.getNome());
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
                            System.out.println(item.getId()+": "+item.getNome());
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

        String checkBolsas = gerBolsa.ChecarBolsas();
        if(checkBolsas != null)
        {
            System.out.println(checkBolsas);
            falha = true;
        }

        if (!falha)
        {
            this.setStatus("Iniciado");
            return true;
        }
        return false; 
    }

    public void ListarTiposUsers(String tipo, ArrayList<Usuario> users)
    {
        System.out.println("Lista de "+tipo+": ");
        if (!users.isEmpty()) U.Listar(users);
        else                                      System.out.println("Sem "+tipo+"\n");
    }

    public void DefinirFuncUser(Usuario usuario)
    {
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
    }
    
    public void RemoverFuncUser(Usuario usuario)
    {
        if (usuario.getFunc().equals("Devp"))       this.getDesenvolvedores().remove(usuario);

        else if (usuario.getFunc().equals("Test"))  this.getTestadores().remove(usuario);
        
        else if (usuario.getFunc().equals("Anlt"))  this.getAnalistas().remove(usuario);

        else if (usuario.getFunc().equals("Tecn"))  this.setIdTecnico(0);

        usuario.setFunc(null);
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

    @Override
    public String toString()
    {
        String coordNome = U.Buscar(this.getProjetistas(), this.getIdCoordenador()).getNome();

        return  "Projeto descrito: \n"+this.getNome()+"\n"+
                "Status do Projeto: "+this.getStatus()+"\n"+
                "Inicio do Projeto: "+this.getInicio()+"\n"+
                "Termino do Projeto: "+this.getTermino()+"\n"+
                "Coordenador do Projeto: "+coordNome+"\n";
    }
}