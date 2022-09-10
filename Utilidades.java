import java.util.Scanner;
import java.util.ArrayList;

public class Utilidades {
    
    public int LerInt(Scanner input)
    {
        int num = input.nextInt();
        input.nextLine();
        return num;
    }

    public Usuario BuscarUsuario(ArrayList<Usuario> users, Scanner input)
    {
        Usuario user = null;
        int checkId = LerInt(input);

        for (Usuario item: users)
        {
            if (checkId == item.getId())    user = item;
        }

        return user;
    }

    public Projeto BuscarProjeto(ArrayList<Projeto> projs, Scanner input)
    {
        Projeto proj = null;
        int checkId = LerInt(input);

        for (Projeto item : projs)
        {
            if (checkId == item.getId())    proj = item;
        }

        return proj;
    }

    public Atividade BuscarAtividade(ArrayList<Atividade> ativs, Scanner input)
    {
        Atividade ativ = null;
        int checkId = LerInt(input);

        for (Atividade item : ativs)
        {
            if (checkId == item.getId())    ativ = item;
        }

        return ativ;
    }
}
