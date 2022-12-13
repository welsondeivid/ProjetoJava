public class OpEditarProjetoDocente implements ComandosDocente{

    @Override
    public void Option(Login log, Manager m, Docente user) throws Exception {
        log.OpEditarProjeto(m, user);
    }
    
}
