public class Erros {
    
    public void CheckErros(String test, String tipo) throws Exception
    {
        if (tipo.equals("email") ||tipo.equals("senha"))
        {
            CheckEmail(test);
        }
        else if (tipo.equals("nome"))
        {
            CheckNome(test);
        }
    }

    private void CheckEmail(String test) throws Exception
    {
        if (test.isEmpty())
        {
            throw new RuntimeException("Entrada Invalida: Vazia");
        }
    }

    private void CheckNome(String test) throws Exception
    {
        if (test.isEmpty())
        {
            throw new RuntimeException("Entrada Invalida: Vazia");
        }
        for (int i = 0; i < test.length(); i++)
        {
            char a = test.charAt(i);
            if (Character.isDigit(a))
            {
                throw new RuntimeException("Entrada Invalida: Caractere invalido '"+a+"'");
            }
        }
    }
}
