import java.time.LocalDateTime;

// continue daqui, metodo de pagamento
public class GerenciadorBolsa{
    
    Utilidades U = new Utilidades();
    
    private float valorDevp = 0;
    private float valorTest = 0;
    private float valorAnlt = 0;
    
    private LocalDateTime prazoDevp = null;
    private LocalDateTime prazoTest = null;
    private LocalDateTime prazoAnlt = null;

    public void DefinirBolsa (String tipoBolsa) throws Exception
    {
        System.out.println("Digite o novo valor para a bolsa, -1 para manter");

        float bolsa = -1;
        System.out.println("Valores atuais das Bolsas: \n");

        System.out.println("Bolsa-Desenvolvedor: "+this.getValorDevp());
        System.out.println("Bolsa-Testador: "+this.getValorTest());
        System.out.println("Bolsa-Analista: "+this.getValorAnlt());

        boolean check = false;

        while (!check)
        {
            bolsa = U.LerFloat();

            if (bolsa == -1)
            {
                System.out.println("Valores Mantidos\n");
                check = true;
            }
            else if (bolsa > -1)
            {
                if (tipoBolsa.equals("Desenvolvedor"))
                {
                    this.setValorDevp(bolsa);
                }
                else if (tipoBolsa.equals("Testador"))
                {
                    this.setValorTest(bolsa);
                }
                else if (tipoBolsa.equals("Analista"))
                {
                    this.setValorAnlt(bolsa);
                }
                check = true;
                System.out.println("Valor alterado com sucesso\n");
            }
            
            else
            {
                System.out.println("Valor digitado menor que 0\n");
            }
        }
    }

    public void DefinirPrazoBolsa(String tipoBolsa) throws Exception
    {
        System.out.println("Defina um novo prazo para as bolsas, 1 para mudar");
        System.out.println("Prazos atuais das bolsas: \n");

        System.out.print("Bolsa-Desenvolvedor: "+U.MostrarDataHora(this.getPrazoDevp()));
        System.out.print("Bolsa-Testador: "+U.MostrarDataHora(this.getPrazoTest()));
        System.out.print("Bolsa-Analista: "+U.MostrarDataHora(this.getPrazoAnlt()));

        int decisao = U.LerInt();

        if (decisao == 1)
        {
            boolean check = false;
            while (!check)
            {
                try {
                    if (tipoBolsa.equals("Desenvolvedor"))
                    {
                        this.setPrazoDevp(U.DefinirDataHora());
                    }
                    else if (tipoBolsa.equals("Testador"))
                    {
                        this.setPrazoTest(U.DefinirDataHora());
                    }
                    else if (tipoBolsa.equals("Analista"))
                    {
                        this.setPrazoAnlt(U.DefinirDataHora());
                    }
                    check = true;
                    System.out.println("Prazo alterado com sucesso\n");

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public String ChecarBolsas()
    {
        String falha = null;
        if (this.getValorDevp() == 0)
        {
            falha += "Falta designar o valor da Bolsa do Desenvolvedor\n";
        }
        else if (this.getValorAnlt() == 0)
        {
            falha += "Falta designar o valor da Bolsa do Analista\n";
        }
        else if (this.getValorTest() == 0)
        {
            falha += "Falta designar o valor da Bolsa do Testador\n";
        }
        else if (this.getPrazoDevp() == null)
        {
            falha += "Falta designar o tempo da Bolsa do Desenvolvedor\n";
        }
        else if (this.getPrazoAnlt() == null)
        {
            falha += "Falta designar o tempo da Bolsa do Analista\n";
        }
        else if (this.getPrazoTest() == null)
        {
            falha += "Falta designar o tempo da Bolsa do Testador\n";
        }

        return falha;
    }
    
    private float getValorDevp() {
        return this.valorDevp;
    }

    private void setValorDevp(float valorDevp) {
        this.valorDevp = valorDevp;
    }

    private float getValorTest() {
        return this.valorTest;
    }

    private void setValorTest(float valorTest) {
        this.valorTest = valorTest;
    }

    private float getValorAnlt() {
        return this.valorAnlt;
    }

    private void setValorAnlt(float valorAnlt) {
        this.valorAnlt = valorAnlt;
    }

    private LocalDateTime getPrazoDevp() {
        return this.prazoDevp;
    }

    private void setPrazoDevp(LocalDateTime prazoDevp) {
        this.prazoDevp = prazoDevp;
    }

    private LocalDateTime getPrazoTest() {
        return this.prazoTest;
    }

    private void setPrazoTest(LocalDateTime prazoTest) {
        this.prazoTest = prazoTest;
    }

    private LocalDateTime getPrazoAnlt() {
        return this.prazoAnlt;
    }

    private void setPrazoAnlt(LocalDateTime prazoAnlt) {
        this.prazoAnlt = prazoAnlt;
    }
    
    @Override
    public String toString()
    {
        String prazoDevp = U.MostrarDataHora(this.getPrazoDevp());
        String prazoTest = U.MostrarDataHora(this.getPrazoTest());
        String prazoAnlt = U.MostrarDataHora(this.getPrazoAnlt());
        

        return  "Valor da Bolsa-Desenvolvedor: "+this.getValorDevp()+"\n"+
                "Valor da Bolsa-Testador: "+this.getValorTest()+"\n"+
                "Valor da Bolsa-Analista: "+this.getValorAnlt()+"\n"+
                "Prazo da Bolsa-Desenvolvedor: "+prazoDevp+"\n"+
                "Prazo da Bolsa-Testador: "+prazoTest+"\n"+
                "Prazo da Bolsa-Analista: "+prazoAnlt+"\n";
    } 
}
