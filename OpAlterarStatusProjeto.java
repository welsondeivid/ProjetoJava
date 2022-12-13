public class OpAlterarStatusProjeto implements ComandosDocente{

    @Override
    public void Option(Login log, Manager m, Docente user) throws Exception {
        
        log.OpAlterarStatusProjeto(m, user);
    }
}
