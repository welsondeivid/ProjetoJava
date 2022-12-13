# ProjetoJava

## Code Smells

### Padrões simples

- `Primitive obsession:`

    - Problema: na classe [Projeto.java](/Projeto), atributos referentes à bolsa eram tratados com floats e strings;

    - Solução: classe GerenciadorBolsa criada com esses atributos e alguns metodos auxiliares;

- `Duplicate Code:`

    - Problema: [Utilidades.java](/Utilidades)funções Buscar, Listar, Consultar e Dados, cada uma tinha mais de 1 opção com o mesmo comportamento com parâmetros diferentes;
    
    - Solução: aplicando generics com a interface Busca e convergi cada uma em uma unica função;

- `Large Class:` 

    - Problema: trechos de códigos menores dentro de funções que se repetiam;
    
    - Solução: funções criadas para auxiliá-las e tornar o código mais legível;

- `Long Parameter List:`

    - Problema: variáveis que várias classes usam e eram sempre passadas como parâmetros;

    - Solução: classe VarGlobais criada para definir essas variaveis, quem precisa herda dela;

- `Long Method:`

    - Problema: métodos com muitos trechos grandes que poderiam ser funções a parte;

    - Solução: funções menores criadas para facilitar a leitura e definir melhor o escopo de cada método, Classe Cadastro inteiramente refatorada, Alguns métodos da [Atividade.java](/Atividade), [Projeto](/Projeto), [Manager.java](/Manager), [Erro.java](/Erro), [GerenciadorBolsa.java](/GerenciadorBolsa), etc;


### Padrão Complexo

- `Switch Statements:`

    - Problema: [Login.java](/Login.java) com vários ifs para as opções do usuário Docente e Discente;

    - Solução: Criada as Interfaces ComandosDiscente e ComandosDocentes, junto com classes que as implementam para salvar em 2 vetores funções e percorrê-los chamando-as.

