public class OpEditarAtividadeDiscente implements ComandosDiscente{

    @Override
    public void Option(Login log, Manager m, Discente user) throws Exception {
        
        log.OpEditarAtividade(m.getProjetos(), user);
    }
    
}
