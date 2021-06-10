package View;
import java.util.*;
import Controller.*;
import Model.AtributoInvalidoException;

public class FMView implements Observer{
    private Menu menuInicial, menuJogadores,menuEquipas,menuJogos,menuTatica,menuFicheiros;
    private String valueToPrint;
    private FMController controller;
    
    public void update (Observable ob,Object value) {
        this.valueToPrint = (String) value;
    }
    
    public FMView(FMController cont)
    {
        String[] opcoes1 = {"Gestao de jogadores","Gestao de equipas","Gestao de jogos","Ler/Guardar Ficheiros"};
        String[] opcoes2 = {"Criar jogador","Listar jogadores","Trocar jogador de equipa","Calcular Habilidade Jogador"};
        String[] opcoes3 = {"Criar Equipa","Listar jogadores de uma equipa","Listar Equipas","Calcular habilidade de uma equipa"};
        String[] opcoes4 = {"Criar jogo","Listar jogos","Simular jogo","Menu Tatica Equipa"};
        String[] opcoes5 = {"Listar titulares","Adicionar titular","Remover titular","Adicionar substituicao"};
        String[] opcoes6 = {"Carregar estado","Guardar estado"};
        this.menuInicial = new Menu(opcoes1);
        this.menuJogadores = new Menu(opcoes2);
        this.menuEquipas = new Menu(opcoes3);
        this.menuJogos = new Menu(opcoes4);
        this.menuTatica = new Menu(opcoes5);
        this.menuFicheiros = new Menu(opcoes6);
        this.controller = cont;
    }

    public void run() {
        do {
            this.menuInicial.executaMenu();
                switch (this.menuInicial.getOpcao()){
                    case 1: this.runGestaoJogadores();
                                break;
                    case 2: this.runGestaoEquipa();
                                break;
                    case 3: this.runGestaoJogo();
                                break;
                    case 4: this.runGestaoFicheiros();
                                break;
                }
            System.out.println();
        }
        while (this.menuInicial.getOpcao() != 0);
    }

    private void runGestaoJogadores(){
        System.out.println();
        do {
            this.menuJogadores.executaMenu();
            System.out.println();
                switch (this.menuJogadores.getOpcao()){
                    case 1: criarJogador();
                                break;
                    case 2: listarJogadores();
                                break;
                    case 3: moverJogadorEquipa();
                                break;
                    case 4:calcularHabilidadeJogador();
                                break;
                }
        }
        while (this.menuJogadores.getOpcao() != 0);
    }

    private void runGestaoEquipa() {
        System.out.println();
        do {
            this.menuEquipas.executaMenu();
                switch (this.menuEquipas.getOpcao()){
                    case 1: adicionarEquipa();
                                break;
                    case 2: listarEquipa();
                                break;
                    case 3: listarEquipas();
                                break;
                    case 4: calcularHabilidadeEquipa();
                            break;
                }
        }
        while (this.menuEquipas.getOpcao() != 0);
    }

    private void runGestaoJogo() {
        System.out.println();
        do {
            this.menuJogos.executaMenu();
                switch (this.menuJogos.getOpcao()){
                    case 1: criarJogo();
                                break;
                    case 2: listarJogos();
                                break;
                    case 3: simulaJogo();
                                break;
                    case 4: runGestaoTatica();
                                break;
                }
        }
        while (this.menuJogos.getOpcao() != 0);
    }

    private void runGestaoFicheiros(){
        System.out.println();
        do {
            this.menuFicheiros.executaMenu();
                switch (this.menuFicheiros.getOpcao()){
                    case 1: carregarEstado();
                                break;
                    case 2: guardarEstado();
                                break;
                }
        }
        while (this.menuJogos.getOpcao() != 0);
    }

    
    private void runGestaoTatica() {
        List <String> args = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o ID do jogo que pretende alterar: ");
        try{
            String jogo = Integer.toString(sc.nextInt());
            args.add(jogo);
            System.out.print("Editar a equipa de casa(0) ou a equipa visitante (1): ");
            int equipaI = sc.nextInt();
            String equipa = Integer.toString(equipaI);
            while(equipaI != 0 && equipaI != 1){
                System.out.println("\nResponda so com 0 ou 1!");
                System.out.print("Editar a equipa de casa(0) ou a equipa visitante (1): ");
                equipaI = sc.nextInt();
                equipa = Integer.toString(equipaI);
            }
            args.add(equipa);
            this.controller.setComando(16);
            this.controller.processaComando(args);
            System.out.println(valueToPrint);
            do {
                this.menuTatica.executaMenu();
                switch (this.menuTatica.getOpcao()){
                    case 1: listarTitulares(jogo,equipa);
                    break;
                    case 2: addJogadorTitular(jogo,equipa);
                    break;
                    case 3: removerTitular(jogo,equipa);
                    break;
                    case 4: adicionarSubstituicao(jogo,equipa);
                }
            }
            while (this.menuTatica.getOpcao() != 0);
        }
        catch(InputMismatchException ime){
            System.out.println("Nao introduziu um ID (numero inteiro)!");
        }
        sc.close();
    }
    
    
    
    private void criarJogador(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=================================");
        System.out.println("1 - GuardaRedes");
        System.out.println("2 - Defesa");
        System.out.println("3 - Lateral");
        System.out.println("4 - Medio");
        System.out.println("5 - Avancado");
        System.out.print("Escolha a posicao do jogador: ");
        int posicao = -1;
        try{
            posicao = sc.nextInt();
            if(posicao > 0 && posicao < 6){
                List<String> args = lerStatJogador(posicao);
                this.controller.setComando(1);
                this.controller.processaComando(args);
                System.out.println(valueToPrint);
            }
            else
                System.out.println("Posicao invalida!");
        }
        catch (InputMismatchException ime){
            System.out.println("Nao introduziu um numero");
        }
        sc.close();
    }
    
    
    private void listarJogadores() {
        List <String> args = new ArrayList<>();
        this.controller.setComando(2);
        this.controller.processaComando(args);
        System.out.println(valueToPrint);
    }
    
    
    private void moverJogadorEquipa() {
        List <String> args = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome do jogador: ");
        args.add(sc.nextLine());
        System.out.print("Digite o nome da equipa: ");
        args.add(sc.nextLine());
        try{
            System.out.print("Digite o numero do jogador na equipa: ");
            args.add(sc.nextLine());
            this.controller.setComando(3);
            this.controller.processaComando(args);
            System.out.println(valueToPrint);
        }
        catch (NumberFormatException nfe){
            System.out.println("Nao introduziu um numero!");
        }
        sc.close();
    }
    
    private void calcularHabilidadeJogador () {
        List <String> args = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome do jogador: ");
        args.add(sc.nextLine());
        this.controller.setComando(4);
        this.controller.processaComando(args);
        System.out.println(valueToPrint);
    }

    private void adicionarEquipa() {
        List <String> args = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome da equipa: ");
        args.add(sc.nextLine());
        this.controller.setComando(5);
        this.controller.processaComando(args);
        System.out.println(valueToPrint);
        sc.close();
    }
    
    private void listarEquipa() {
        List <String> args = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome da equipa: ");
        args.add(sc.nextLine());
        System.out.println("");
        this.controller.setComando(6);
        this.controller.processaComando(args);
        System.out.println(valueToPrint);
        sc.close();
    }

    private void listarEquipas() {
        System.out.println("");
        this.controller.setComando(7);
        this.controller.processaComando(new ArrayList<>());
        System.out.println(valueToPrint);
    }
    
    private void calcularHabilidadeEquipa () {
        List <String> args = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome da equipa: ");
        args.add(sc.nextLine());
        this.controller.setComando(8);
        this.controller.processaComando(args);
        System.out.println(valueToPrint);
        sc.close();
    }
    
    private void criarJogo() {
        List <String> args = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.print("Digite o nome da equipa de casa: ");
        args.add(sc.nextLine());
        System.out.print("Digite o nome da equipa de fora: ");
        args.add(sc.nextLine());
        System.out.print("Digite a data do jogo (AAAA-MM-DD): ");
        args.add(sc.nextLine());
        this.controller.setComando(9);
        this.controller.processaComando(args);
        System.out.println(valueToPrint);
        sc.close();
    }
    
    private void listarJogos() {
        System.out.println("");
        this.controller.setComando(10);
        this.controller.processaComando(new ArrayList<>());
        System.out.println(valueToPrint);
    }
    
    private void simulaJogo() {
        List <String> args = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        this.controller.setComando(17);
        this.controller.processaComando(args);
        System.out.println(valueToPrint);
        System.out.print("Digite o ID do jogo para simular: ");
        try{
            args.add(Integer.toString(sc.nextInt()));
            this.controller.setComando(11);
            this.controller.processaComando(args);
            System.out.println(valueToPrint);
        }
        catch (InputMismatchException ime){
            System.out.println("Nao introduziu um ID (numero inteiro)!");
        }
        sc.close();
    }

    private void addJogadorTitular(String jogo,String nome) {
        List <String> args = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        args.add(jogo);
        args.add(nome);
        this.controller.setComando(13);
        this.controller.processaComando(args);
        System.out.println(valueToPrint);
        this.controller.setComando(15);
        this.controller.processaComando(args);
        System.out.println(valueToPrint);
        System.out.print("Digite o numero do jogador na equipa:");
        args.add(sc.nextLine());
        System.out.print("Escolha a posicao(0-GR,1-DEF,2-MED,3-AVA):");
        args.add(sc.nextLine());
        this.controller.setComando(12);
        this.controller.processaComando(args);
        System.out.println(valueToPrint);
        sc.close();
    }
    
    private void listarTitulares(String jogo, String nome) {
        List <String> args = new ArrayList<>();
        args.add(jogo);
        args.add(nome);
        this.controller.setComando(13);
        this.controller.processaComando(args);
        System.out.println(valueToPrint);
    }

    private void removerTitular(String jogo,String nome) {
        List <String> args = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        args.add(jogo);
        args.add(nome);
        System.out.print("Digite o numero do jogador a remover!");
        args.add(sc.nextLine());
        this.controller.setComando(14);
        this.controller.processaComando(args);
        System.out.println(valueToPrint);
        sc.close();
    }

    private void adicionarSubstituicao(String jogo, String nome) {
        List <String> args = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        args.add(jogo);
        args.add(nome);
        try {
            this.controller.setComando(15);
            this.controller.processaComando(args);
            System.out.println(valueToPrint);
            System.out.print("Digite o numero do jogador para entrar: ");
            args.add(sc.nextLine());
            this.controller.setComando(13);
            this.controller.processaComando(args);
            System.out.println(valueToPrint);
            System.out.print("Digite o numero do jogador para sair: ");
            args.add(sc.nextLine());
            this.controller.setComando(18);
            this.controller.processaComando(args);
            System.out.println(valueToPrint);
        }
        catch (NumberFormatException e) {
            System.out.println("Tem de ser introduzido um numero!");
        }
        sc.close();
    }

    private List<String> lerStatJogador(int posicao) {
        List<String> args = new ArrayList<>();
        args.add(Integer.toString(posicao));
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o nome do Jogador: ");
        args.add(sc.nextLine());
        System.out.print("Insira a altura do jogador (em cm): ");
        args.add(sc.next());
        System.out.print("Insira a velocidade do jogador: ");
        args.add(sc.next());
        System.out.print("Insira a resistencia do jogador: ");
        args.add(sc.next());
        System.out.print("Insira a destreza do jogador: ");
        args.add(sc.next());
        System.out.print("Insira a impulsao do jogador: ");
        args.add(sc.next());
        System.out.print("Insira o jogo de cabeca do jogador: ");
        args.add(sc.next());
        System.out.print("Insira o remate do jogador: ");
        args.add(sc.next());
        System.out.print("Insira o passe do jogador: ");
        args.add(sc.next());
        switch (posicao) {
                case 1: System.out.print("Insira a elasticidade do jogador: ");
                            args.add(sc.next());
                            System.out.print("Insira os reflexos do jogador: ");
                            args.add(sc.next());
                            break;
                case 2: System.out.print("Insira o desarme do jogador: ");
                            args.add(sc.next());
                            System.out.print("Insira o posicionamento do jogador: ");
                            args.add(sc.next());
                            break;
                            case 3: System.out.print("Insira os cruzamentos do jogador: ");
                            args.add(sc.next());
                            System.out.print("Insira o posicionamento do jogador: ");
                            args.add(sc.next());
                            break;
                case 4:System.out.print("Insira a recuperacao de bola do jogador: ");
                            args.add(sc.next());
                            System.out.print("Insira a criatividade do jogador: ");
                             args.add(sc.next());
                            break;
                case 5:System.out.print("Insira a compustura do jogador: ");
                            args.add(sc.next());
                            break;
        }
        sc.close();
        return args;
    }

    private void carregarEstado(){
        List <String> args = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.print("Digite o nome do ficheiro a ler: ");
        args.add(sc.nextLine());
        this.controller.setComando(19);
        this.controller.processaComando(args);
        System.out.println(valueToPrint);
        sc.close();
    }

    private void guardarEstado(){
        List <String> args = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.print("Digite o nome do ficheiro a guardar: ");
        args.add(sc.nextLine());
        this.controller.setComando(20);
        this.controller.processaComando(args);
        System.out.println(valueToPrint);
        sc.close();
    }

}
