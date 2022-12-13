public class OpEditarUsuarioDiscente implements ComandosDiscente{

    @Override
    public void Option(Login log, Manager m, Discente user) throws Exception 
    {
        log.OpEditarUsuarioDiscente(user);
    }
    
    
}
