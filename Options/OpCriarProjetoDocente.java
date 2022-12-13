public class OpCriarProjetoDocente implements ComandosDocente{

    @Override
    public void Option(Login log, Manager m, Docente user) throws Exception {
        log.OpCriarProjeto(m);
    }
}
