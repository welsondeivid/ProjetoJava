import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class VarGlobais {
    
    Erros erro = new Erros();

    Utilidades U = new Utilidades();

    Menu menu = new Menu();

    DateTimeFormatter format = DateTimeFormatter.ofPattern ("HH:mm dd/MM/yyyy");
    
    Scanner input = new Scanner(System.in);
    
    GerenciadorBolsa gerBolsa =  new GerenciadorBolsa();
    
}
