import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Utilidades{
	    
    Scanner input = new Scanner(System.in);

    public int LerInt()
    {
        int num = -1;
        try 
        {
            num = input.nextInt();
        } 
        catch (Exception e)
        {
            throw new RuntimeException("Digite um valor inteiro\n");      
        }
        finally
        {
            input.nextLine();
        }        
        return num;
    }
    
    public float LerFloat()
    {
        float num = -1.0f;
        try
        {
            num = input.nextFloat();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Digite um valor real\n");
        }
        finally
        {
            input.nextLine();
        }
        return num;
    }   

    public void Continue()
    {
        System.out.println("Pressione Enter para continuar");
        input.nextLine();
    }

    public <T extends Busca> T Buscar(ArrayList<T> objs, int checkId)
    {
        for (T item : objs)
        {
            if (checkId == item.getId())
            {
                return item;
            }
        }

        throw new RuntimeException("Falha na busca: Fora do sistema");
    }

    public <T extends Busca> void Listar (ArrayList<T> objs)
    {
        for (T item : objs)
        {
            System.out.println("Nome: "+item.getNome());
            System.out.println("ID: "+item.getId());
        }
    }
    
    public <T extends Busca> void Consultar (ArrayList<T> objs) throws Exception
    {
        Listar(objs);
        int escolha = LerInt();

        T obj = Buscar(objs, escolha);

        Dados(obj);
    }
    
    public <T extends Busca> void Dados (T obj)
    {
        System.out.println(obj);
    }

    public void RelatorioAtiv (Atividade ativ)
    {
        System.out.println(ativ);

        ativ.UsersRelatorio(ativ.getUsuarios());

        ArrayList<Tarefa> tasks = new ArrayList<Tarefa>();
        ativ.TasksRelatorio(ativ.getTarefas(), tasks);

        System.out.println("Status atual da atividade: "+ativ.getStatus());

        Continue();

        ativ.AlterarStatus(tasks);
    }

    public void RelatorioProj(Projeto proj, GerenciadorBolsa gerBolsa)
    {
        System.out.println(proj);

        proj.ListarTiposUsers("Desenvolvedores", proj.getDesenvolvedores());

        proj.ListarTiposUsers("Testadores", proj.getTestadores());

        proj.ListarTiposUsers("Analistas", proj.getAnalistas());

        proj.ListarTiposUsers("Intercambistas", proj.getIntercambistas());

        System.out.println("Tecnico do Projeto: ");
        String tecnico = "Sem Tecnico";
        if (proj.getIdTecnico() != 0)   tecnico = Buscar(proj.getProjetistas(), proj.getIdTecnico()).getNome();
        System.out.println(tecnico+"\n");  

        System.out.println("Lista de Atividades: ");
        if (!proj.getAtividades().isEmpty())    for (Atividade item : proj.getAtividades()) Dados(item);
        else    System.out.println("Sem atividades no momento");

        System.out.println("\n"+gerBolsa);
    }
    
    public String MostrarDataHora(LocalDateTime tempo)
    {
        if (tempo != null)
        {
            DateTimeFormatter dataForm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        DateTimeFormatter horaForm = DateTimeFormatter.ofPattern("HH:mm");

            return  "Data: "+dataForm.format(tempo)+
                    "Hora: "+horaForm.format(tempo)+"\n";
        }
        else
        {
            return "Falta Definir\n";
        }
    }

    public LocalDateTime DefinirDataHora() throws Exception
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern ("HH:mm dd/MM/yyyy");
        System.out.println ("Digite a data do novo prazo no formato: HH:mm dd/MM/yyyy");

        try {
            LocalDateTime tempoBolsa = LocalDateTime.parse(input.nextLine(), format);
            return tempoBolsa;
        } catch (Exception e) {
            throw new RuntimeException ("Formato de Data/Hora invalido\n");
        }
    }

    public ArrayList<Discente> ChecarPagamento(ArrayList<Usuario> users, String func)
    {
        ArrayList <Discente> pagar = new ArrayList<Discente>();
        System.out.println(func);
        LocalDateTime dtAtual = LocalDateTime.now();

        for (Usuario item : users)
        {
            if (item instanceof Discente)
            {
                Discente permitido = (Discente)item;
                LocalDateTime dtBolsista = permitido.getDiaPag();
                long dias = Duration.between(dtBolsista, dtAtual).toDays();

                if (dias >= 30)
                {
                    System.out.println("Nome: "+item.getNome());
                    System.out.println("Nome: "+item.getId());
                    pagar.add(permitido);
                }
            }
        }
        return pagar;
    }
    
    public String ChecarTipoUsuario (int tipo)
    {
        if (tipo == 1 || tipo == 2 || tipo == 3)
        {
            return "Disc";
        }
        else if (tipo == 4 || tipo == 5)
        {
            return "Doce";
        }

        throw new RuntimeException("Erro: Valor Invalido\nTente Novamente: ");
    }

    public Usuario EscolherUser(ArrayList<Usuario> users, String tipo) throws Exception
    {
        System.out.println("Digite o ID "+tipo+" :");
        int checkIdU = LerInt();
        Usuario user = Buscar(users, checkIdU);

        return user;
    }

    public <T extends Busca> int EscolherQuant(String tipo, ArrayList<T> objs) throws Exception
    {
        System.out.println("Qual sera a quantidade de "+tipo+"? 0 para nenhum");
        try
        {
            Listar(objs);
        }
        catch (Exception e)
        {
            System.out.println("Lista vazia");
        }

        int quant = LerInt();
        return quant;
    }
}
