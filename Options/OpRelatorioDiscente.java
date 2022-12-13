public class OpRelatorioDiscente implements ComandosDiscente{

    @Override
    public void Option(Login log, Manager m, Discente user) throws Exception {
        log.OpRelatorioDiscente(m.getProjetos(), user);
    }
}
