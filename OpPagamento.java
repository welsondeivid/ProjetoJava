public class OpPagamento implements ComandosDocente{

    @Override
    public void Option(Login log, Manager m, Docente user) throws Exception {
        
        log.OpPagamento(m, user);
    }
    
}
