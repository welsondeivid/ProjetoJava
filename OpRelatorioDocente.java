public class OpRelatorioDocente implements ComandosDocente{

    @Override
    public void Option(Login log, Manager m, Docente user) throws Exception {
        log.OpRelatorioDocente(m, user);
    }
}