import java.util.Scanner;

public class Erros {
    
    public void CheckErros(String test, String tipo) throws Exception
    {
        if (tipo.equals("vazio"))
        {
            CheckVazio(test);
        }
        else if (tipo.equals("nome"))
        {
            CheckNome(test);
        }
    }

    public int TratarEscolha(Scanner input, Exception e, String tipo) throws Exception
    {
        System.out.println("Falha ao "+tipo+" :"+e.getMessage());
        System.out.println("\nManter o numero de adicoes? 1 para sim");

        int decisao = input.nextInt();

        return decisao;
    }
    
    private void CheckVazio(String test) throws Exception
    {
        if (test.isEmpty())
        {
            throw new RuntimeException("Entrada Invalida: Vazia");
        }
    }

    private void CheckNome(String test) throws Exception
    {
        CheckVazio(test);
        
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
