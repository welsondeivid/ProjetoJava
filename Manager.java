//import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Manager {

    //private ArrayList<Projeto> projetos = new ArrayList<Projeto>();
    public static void main (String[] args) {
        
        Manager m = new Manager();
        Scanner input = new Scanner(System.in);

        int cmd = -1;

        System.out.println ("Digite 1 para: Criar um projeto");
        
        cmd = input.nextInt();

        while (cmd != 0)
        {
            if (cmd == 1)
            {
                Projeto project = new Projeto();
                
                System.out.println("Digite o ID do projeto: ");
                project.setID(input.nextInt());
                input.nextLine();

                System.out.println("Digite a descricao do projeto: ");
                project.setDesc(input.nextLine());

                DateTimeFormatter format = DateTimeFormatter.ofPattern ("HH:mm dd/MM/yyyy");

                System.out.println ("Digite a data de inicio no formato: HH:mm dd/MM/yyyy");
                LocalDateTime inicio = LocalDateTime.parse(input.nextLine(), format);
                project.setInicio(inicio);

                System.out.println ("Digite a data de termino no formato: HH:mm dd/MM/yyyy");
                LocalDateTime termino = LocalDateTime.parse(input.nextLine(), format);
                project.setInicio(termino);

                System.out.println("Digite o nome do coordenador do projeto: ");
                project.setCoordenador(input.nextLine());

                System.out.println ("Quantos profissionais estarao no projeto? ");
                int num = input.nextInt();
                input.nextLine();
                for (int i = 0; i < num; i++)
                {
                    System.out.println("Digite o nome de um profissional do projeto: ");
                    project.setProfissionais(input.nextLine());
                }

                System.out.println ("Quantas atividades estarao no projeto? ");
                num = input.nextInt();
                input.nextLine();
                for (int i = 0; i < num; i++)
                {
                    System.out.println("Digite as infos sobre a atividade: ");

                    Atividade atividade = m.CriarAtividade(input, format);
                    project.setAtividades(atividade);
                }

                System.out.println("Digite o valor da bolsa para o(s) Desenvolvedor(es) no formato: ");
                project.setBolsaDesenvolvedor(input.nextLine());

                System.out.println("Digite o valor da bolsa para o(s) Testador(es) no formato: ");
                project.setBolsaTestador(input.nextLine());

                System.out.println("Digite o valor da bolsa para o(s) Analista(s) no formato: ");
                project.setBolsaAnalista(input.nextLine());

                System.out.println("Digite por quanto tempo a bolsa valera para o(s) Desenvolvedor(es) no formato: ");
                project.setTempoBolsaDesenvolvedor(input.nextLine());

                System.out.println("Digite por quanto tempo a bolsa valera para o(s) Desenvolvedor(es) no formato: ");
                project.setTempoBolsaTestador(input.nextLine());

                System.out.println("Digite por quanto tempo a bolsa valera para o(s) Desenvolvedor(es) no formato: ");
                project.setTempoBolsaAnalista(input.nextLine());
            }

            //else if (cmd == 2)

            System.out.println("Digite o proximo comando: ");
            cmd = input.nextInt();
        }

        input.close();
    }

    public Atividade CriarAtividade(Scanner input, DateTimeFormatter format)
    {
        //Scanner input = new Scanner(System.in);
        Atividade atividade = new Atividade();

        System.out.println("Digite o ID da atividade: ");
        atividade.setID(input.nextInt());
        input.nextLine();

        System.out.println("Digite a descricao da atividade: ");
        atividade.setDesc(input.nextLine());

        //DateTimeFormatter format = DateTimeFormatter.ofPattern ("HH:mm dd/MM/yyyy");

        System.out.println ("Digite a data de inicio no formato: HH:mm dd/MM/yyyy");
        LocalDateTime inicio = LocalDateTime.parse(input.nextLine(), format);
        atividade.setInicio(inicio);

        System.out.println ("Digite a data de termino no formato: HH:mm dd/MM/yyyy");
        LocalDateTime termino = LocalDateTime.parse(input.nextLine(), format);
        atividade.setInicio(termino);

        System.out.println("Digite o nome do responsavel pela atividade: ");
        atividade.setResponsavel(input.nextLine());

        System.out.println ("Quantos profissionais estarao na atividade? ");
        int num = input.nextInt();
        input.nextLine();
        for (int i = 0; i < num; i++)
        {
            System.out.println("Digite o nome de um profissional da atividade: ");
            atividade.setProfissionais(input.nextLine());
        }

        System.out.println ("Quantas tarefas estarao na atividade? ");
        num = input.nextInt();
        input.nextLine();
        for (int i = 0; i < num; i++)
        {
            System.out.println("Digite o nome das tarefas: ");
            atividade.setTarefas(input.nextLine());
        }

        //input.close();
        return atividade;
    }
}