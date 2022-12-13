public class OpIntercambiar implements ComandosDocente{

    @Override
    public void Option(Login log, Manager m, Docente user) throws Exception {
        
        log.OpIntercambiar(m, user);
    }
    
}
