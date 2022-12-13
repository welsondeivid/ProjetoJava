public class OpEditarAtividadeDocente implements ComandosDocente{

    @Override
    public void Option(Login log, Manager m, Docente user) throws Exception {
        
        log.OpEditarAtividade(m.getProjetos(), user);
    }
}
