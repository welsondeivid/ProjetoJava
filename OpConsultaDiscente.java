public class OpConsultaDiscente implements ComandosDiscente {

    @Override
    public void Option(Login log, Manager m, Discente user) throws Exception {
        log.OpConsulta(m.getProjetos(), m.getUsuarios());
    }
}
