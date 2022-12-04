import java.util.ArrayList;

public interface Lista {
    public void ListarProjs (ArrayList<Projeto> projs);

    public void ListarAtivs (ArrayList<Atividade> ativs);

    public void ListarTasks (ArrayList<Tarefa> tasks);

    public void ListarUsers (ArrayList<Usuario> users);

    public void ListarDocentes (ArrayList<Usuario> users);

    public void ListarDiscentes (ArrayList<Usuario> users);
}
