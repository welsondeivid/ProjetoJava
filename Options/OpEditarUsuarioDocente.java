public class OpEditarUsuarioDocente implements ComandosDocente{

    @Override
    public void Option(Login log, Manager m, Docente user) throws Exception {
        
        log.OpEditarUsuarioDocente(m.getProjetos(), user);
    }
}
