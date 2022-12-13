public class OpConsultaDocente implements ComandosDocente {

    @Override
    public void Option(Login log, Manager m, Docente user) throws Exception {
        log.OpConsulta(m.getProjetos(), m.getUsuarios());
    }
}