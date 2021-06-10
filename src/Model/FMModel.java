package Model;

import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;
import java.io.FileInputStream;
import java.time.*;
import javafx.util.Pair;
 
/**
 * Escreva a descri��o da classe FootballManager aqui.
 * 
 * @author (seu nome) 
 * @version (n�mero de vers�o ou data)
 */
public class FMModel extends Observable implements Serializable 
{
    private Map<String,Jogador> jogadores ;
    private Map<String,Equipa> equipas;
    private List<Jogo> jogos;
    private String valueFromModel;

    /**
     * COnstrutor para objetos da classe FMModel
     */
    public FMModel()
    {
        this.jogadores = new TreeMap<>();
        this.equipas = new HashMap<>();
        this.jogos = new ArrayList<>();
    }

    public FMModel(Map <String,Jogador> jog,Map<String,Equipa> equipas,List<Jogo> jogos) {
        this.jogadores = jog.values().stream().collect(Collectors.toMap(j->j.getNome(),j->j.clone()));
        this.equipas = equipas.values().stream().collect(Collectors.toMap(e->e.getName(),e->e.clone()));
        this.jogos = jogos.stream().map(Jogo::clone).collect(Collectors.toList());
    }

    public List<Jogador> getListJogador() {
        return this.jogadores.values().stream().map(Jogador::clone).collect(Collectors.toList());
    }

    public List<Equipa> getListEquipa() {
        return this.equipas.values().stream().map(Equipa::clone).collect(Collectors.toList());
     }

     public List <Jogo> getListJogos() {
         return this.jogos.stream().map(Jogo::clone).collect(Collectors.toList());
     }

     public void addJogador (Jogador j) throws JogadorExistenteException {
        if (!this.jogadores.containsKey(j.getNome())) {
            this.jogadores.put(j.getNome(),j.clone());
            this.valueFromModel = "Sucesso ao criar o jogador " + j.getNome();
        }
        else throw new JogadorExistenteException ("Jogador ja existente");
     }

     public void addJogadorFromText (List<String> args) {
        int posicao = Integer.parseInt(args.get(0));
        String nome = args.get(1);
        int altura = Integer.parseInt(args.get(2));
        List<Integer> atributos = new ArrayList<>();
        for (int i = 3;i< args.size();i++)
            atributos.add(Integer.parseInt(args.get(i)));
        switch(posicao) {
        case 1: try {
                this.addJogador(new GuardaRedes(atributos.get(0),atributos.get(1),atributos.get(2),atributos.get(3),atributos.get(4),atributos.get(5),atributos.get(6),atributos.get(7),atributos.get(8),altura,nome,1,new ArrayList<>()));
            } catch (JogadorExistenteException | AtributoInvalidoException e) {
                this.valueFromModel = e.getMessage();
            }
            break;
        case 2: try {
                this.addJogador(new Defesa(atributos.get(0),atributos.get(1),atributos.get(2),atributos.get(3),atributos.get(4),atributos.get(5),atributos.get(6),atributos.get(7),atributos.get(8),altura,nome,4,new ArrayList<>()));
            } catch (JogadorExistenteException | AtributoInvalidoException e) {
                this.valueFromModel = e.getMessage();
            }
            break;
        case 3: try {
                this.addJogador(new Lateral(atributos.get(0),atributos.get(1),atributos.get(2),atributos.get(3),atributos.get(4),atributos.get(5),atributos.get(6),atributos.get(7),atributos.get(8),altura,nome,2,new ArrayList<>()));
            } catch (JogadorExistenteException | AtributoInvalidoException e) {
                this.valueFromModel = e.getMessage();
            }
            break;
        case 4: try {
                this.addJogador(new Medio(atributos.get(0),atributos.get(1),atributos.get(2),atributos.get(3),atributos.get(4),atributos.get(5),atributos.get(6),atributos.get(7),atributos.get(8),altura,nome,8,new ArrayList<>()));
            } catch (JogadorExistenteException | AtributoInvalidoException e) {
                this.valueFromModel = e.getMessage();
            }
            break;
        case 5: try {
                this.addJogador(new Avancado(atributos.get(0),atributos.get(1),atributos.get(2),atributos.get(3),atributos.get(4),atributos.get(5),atributos.get(6),atributos.get(7),altura,nome,9,new ArrayList<>()));
            } catch (JogadorExistenteException | AtributoInvalidoException e) {
                this.valueFromModel = e.getMessage();
            }
            break;
        default: this.valueFromModel = "Posição não é válida!";
    }
         this.setChanged();
         this.notifyObservers(valueFromModel);
     }

     public void addEquipa (String nome) {
        if (!(this.equipas.containsKey(nome))) {
            Equipa e = new Equipa(nome);
            this.equipas.putIfAbsent(nome,e.clone());
            valueFromModel = "Equipa " + nome + " adicionada"; 
        }
        else valueFromModel = "Erro na criacao da equipa";
        this.setChanged();
        this.notifyObservers(valueFromModel);
     }

     public void removerEquipa (String nome) {
        if ((this.equipas.containsKey(nome))) {
            this.equipas.remove(nome);
            valueFromModel = "Equipa " + nome + " removida"; 
        }
        else valueFromModel = "Erro na remocao da equipa";
        this.setChanged();
        this.notifyObservers(valueFromModel);
     }

     public Jogador getJogador(String nome) throws JogadorNaoExistenteException {
         if (this.jogadores.containsKey(nome)) {
            valueFromModel = this.jogadores.get(nome).toString();
            this.setChanged();
            this.notifyObservers(valueFromModel);
            return this.jogadores.get(nome);
         }
        else throw new JogadorNaoExistenteException("Jogador nao existe!");
     }

     public void removerJogador(String nome) throws JogadorNaoExistenteException {
        if (this.jogadores.containsKey(nome)) {
            this.jogadores.remove(nome);
            valueFromModel = "Jogador "+nome+ " removido." ;
            this.setChanged();
            this.notifyObservers(valueFromModel);
         }
        else throw new JogadorNaoExistenteException("Jogador nao existe!");

     }

     public void listarJogadoresFree () {
         StringBuilder sb = new StringBuilder();
         for (Jogador j : this.jogadores.values()){
            sb.append("\n");
            sb.append(j.toString());  
        }
        valueFromModel = sb.toString();
        this.setChanged();
        this.notifyObservers(valueFromModel);
     }

     public void listarEquipa(String nome) {
        if (this.equipas.containsKey(nome))
            valueFromModel = this.equipas.get(nome).toString();
        else valueFromModel = "Equipa nao existente!";
        this.setChanged();
        this.notifyObservers(valueFromModel);
     }

     public void calculaHabilidadeJogador (String nome) {
         if (this.jogadores.containsKey(nome)) {
             Jogador j = this.jogadores.get(nome);
             valueFromModel = "A habilidade do jogador " + nome + " e " + j.habilidadeJogador();
         }
         else valueFromModel = "Jogador inexistente!";
         this.setChanged();
        this.notifyObservers(valueFromModel);
     }

    public void addJogadorEquipa(List<String> args){
        String nomeJ = args.get(0);
        String nomeE = args.get(1);
        int numero = Integer.parseInt(args.get(2));
        if (this.jogadores.containsKey(nomeJ)) {
            Jogador j = this.jogadores.get(nomeJ);
            if (this.equipas.containsKey(nomeE)) {
                try
                {
                    List<String> l = j.getHistorialEquipas();
                    if (l.size() > 0) {
                        this.equipas.get(l.get(l.size()-1)).removePlayer(j);
                        j.setNumCamisola(numero);
                        this.equipas.get(nomeE).addPlayer(j);
                        valueFromModel = "Sucesso ao adicionar o jogador a equipa";
                    }
                }
                catch (NumeroExistenteException | JogadorExistenteException | AtributoInvalidoException e)
                {
                    valueFromModel = e.getMessage();
                }
            }
            else valueFromModel = "Equipa inexistente";
        }
        else valueFromModel = "Jogador nao existe";
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    public void listarEquipas() {
        StringBuilder sb =  new StringBuilder();
        for (Equipa e: this.equipas.values())
            sb.append(e.getName()).append ("\n");
        valueFromModel = sb.toString();
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    public void calcularHabilidadeEquipa(String nome) {
        if (this.equipas.containsKey(nome)) {
            float he = this.equipas.get(nome).calculaHabilidadeEquipa();
            valueFromModel = "Habilidade da equipa " + nome + " e " + he;
        }
        else valueFromModel = "Equipa nao existe!";
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    public void addJogadorTitular(List<String> args) {
        int id = Integer.parseInt(args.get(0));
        int eq = Integer.parseInt(args.get(1));
        int num = Integer.parseInt(args.get(2));
        int pos = Integer.parseInt (args.get(3));
        Jogo jogo = this.jogos.get(id-1);
        Equipa e;
        if (eq == 0) e = jogo.getVisitado();
        else e = jogo.getVisitante();
        try {
            e.addTitular(num, pos);
            if (eq == 0) jogo.setVisitado(e);
            else jogo.setVisitante(e);
            valueFromModel = "Jogador adicionado ao titulares!";
        } catch (JogadorExistenteException | TamanhoEquipaException | JogadorNaoExistenteException e1) {
            valueFromModel = e1.getMessage();
        }
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    public void listarTitularesEquipa(int id,int eq) {
        StringBuilder sb = new StringBuilder();
        sb.append("Titulares : \n");
        Jogo jogo = this.jogos.get(id-1);
        Equipa e;
        if (eq == 0) e = jogo.getVisitado();
        else e = jogo.getVisitante();
        for (Map.Entry<Integer,List<Jogador>> s : e.getTatica().getTatica().entrySet()) {
                int i = s.getKey();
                if (i == 0) sb.append("Guarda-Redes: [ | ");
                if (i == 1) sb.append("Defesas: [ | ");
                if (i == 2) sb.append("Medios: [ | ");
                if (i == 3) sb.append("Avancados: [ | ");
                for (Jogador j: s.getValue())
                    sb.append(j.getNumCamisola() + " - " + j.getNome() + " | ");
                sb.append("];\n");
        }
        if (e.getTatica().getTitulares().values().size() == 0) valueFromModel = "Ainda nao foi escolhido nenhum titular";
        else valueFromModel = sb.toString();
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    public void listarNaoTitulares (int id,int eq) throws EquipaNaoExistenteException {
        StringBuilder sb = new StringBuilder();
        sb.append("Jogadores que podem ser escolhidos para titular!\n");
        Jogo jogo = this.jogos.get(id-1);
        Equipa e;
        if (eq == 0) e = jogo.getVisitado();
        else e = jogo.getVisitante();
        List<Jogador> l = e.getTatica().getTitulares().values().stream().collect(Collectors.toList());
        for (Jogador j: e.getJogadores())
            if (l.contains(j));
            else{ 
                sb.append(j.toString());
                sb.append("\n");
            }
        valueFromModel = sb.toString();
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    public void removerTitular(int id,int eq,int num){
        Jogo jogo = this.jogos.get(id-1);
        Equipa e ;
            if (eq == 0) e = jogo.getVisitado();
            else e = jogo.getVisitante();
            try {
                e.removeTitular(num);
                if (eq == 0) jogo.setVisitado(e);
                else jogo.setVisitante(e);
                valueFromModel = "Jogador removido com sucesso";
            } catch (JogadorNaoTitularException e1) {
                valueFromModel = e1.getMessage();
            }
            this.setChanged();
            this.notifyObservers(valueFromModel);
    }

    public void adicionarSubstituicao (int id,int eq,int ent,int saida) {
        Jogo jogo = this.jogos.get(id-1);
        jogo.adicionaSubstituicao(eq,ent,saida);
        valueFromModel = "Substituicao adicionada com sucesso";
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }
    
    public void addJogo (String e1,String e2,LocalDate data) throws EquipaNaoExistenteException{
        if (this.equipas.containsKey(e1) && this.equipas.containsKey(e2)) {
            Equipa eq1 = this.equipas.get(e1);
            Equipa eq2 = this.equipas.get(e2);
            if (LocalDate.now().compareTo(data) > 0) valueFromModel = "Data do jogo anterior a data atual!";
            else {
                Jogo j = new Jogo(eq1,eq2,0,0,0,data);
                this.jogos.add(j);
                valueFromModel = "Jogo criado com sucesso\n";
            }
            this.setChanged();
            this.notifyObservers(valueFromModel);
        }
        else throw new EquipaNaoExistenteException("Equipa nao existe");
    }

    public void listarJogos () {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Jogo j: this.jogos) {
            sb.append("ID:").append(i).append(", Visitado -> ").append(j.getVisitado().getName()).append(", Visitante -> ").append(j.getVisitante().getName())
                .append("; Minuto : ").append(j.getMinutos()).append("; Resultado : ").append(j.getGolosVisitado()).append(" - ").append(j.getGolosVisitante()).append("\n");
            i++;
        }
        if (this.jogos.size() < 1) valueFromModel = "Ainda nao se adicionaram os jogos";
        else valueFromModel = sb.toString();
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    public void listarJogosSimulaveis() {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Jogo j: this.jogos) {
            if (j.getMinutos() < 90) 
                sb.append("ID:").append(i).append(", Visitado -> ").append(j.getVisitado().getName()).append(", Visitante -> ").append(j.getVisitante().getName())
                    .append("; Minuto : ").append(j.getMinutos()).append("; Resultado : ").append(j.getGolosVisitado()).append(" - ").append(j.getGolosVisitante()).append("\n");
            i++;
        }
        if (this.jogos.stream().filter(j->j.getMinutos() < 90).count() < 1) valueFromModel = "Nao ha jogos possiveis de simular!";
        else valueFromModel = sb.toString();
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    public void imprimirEquipa(int id, int equipa){
        StringBuilder sb = new StringBuilder();
        Jogo j = this.jogos.get(id - 1);
        Equipa teamToPrint;
        if(equipa == 0)
            teamToPrint = j.getVisitado();
        else
            teamToPrint = j.getVisitante();

        sb.append("\n============= ");
        sb.append(teamToPrint.getName());
        sb.append(" =============");
        valueFromModel = sb.toString();
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    public void simulaJogo(int id) {
        StringBuilder sb = new StringBuilder();
        try {
        Jogo jogo = this.jogos.get(id-1);
        if (jogo.getMinutos() == Jogo.FIMJOGO) valueFromModel = "Jogo ja realizado. Resultado final: " + jogo.getGolosVisitado() + " - " + jogo.getGolosVisitante() + "\n";
        else {
        if (jogo.getVisitado().getTatica().getTitulares().values().size() == 11 &&  jogo.getVisitante().getTatica().getTitulares().values().size() == 11 ) {
        int posse = new Random().nextInt(2); // Quem começa com a bola
        sb.append("O jogo vai comecar!\n");
        Pair <Integer,Integer> subc = new Pair(0,0),subf = new Pair(0,0);
        for (;jogo.getMinutos() < Jogo.INTERVALO;jogo.setMinutos(jogo.getMinutos() + 1)){
            if (posse == 0) {
                float ataque = jogo.getVisitado().calculaHabilidadeAtaque();
                float defesa = jogo.getVisitante().calculaHabilidadeDefesa();
                int resultado = jogo.simulaAtaque (ataque,defesa);
                switch (resultado) {
                    case 0 : sb.append("Minuto ").append(jogo.getMinutos()).append("- A equipa de casa mantem a bola!\n");
                                break;
                    case 1: jogo.setGoloVisitado(jogo.getGolosVisitado() + 1);
                                sb.append("Minuto ").append(jogo.getMinutos()).append("- A equipa de casa marcou golo! Placard :").append(jogo.getGolosVisitado()).append(" - ").append(jogo.getGolosVisitante()).append("\n");
                                posse++;
                                break;
                    case -1:sb.append("Minuto ").append(jogo.getMinutos()).append("- A equipa visitante recupera a bola!\n");
                                posse++;
                                break;
                }
            }
            else {
                float ataque = jogo.getVisitante().calculaHabilidadeAtaque();
                float defesa = jogo.getVisitado().calculaHabilidadeDefesa();
                int resultado = jogo.simulaAtaque (ataque,defesa);
                switch (resultado) {
                    case 0 : sb.append("Minuto ").append(jogo.getMinutos()).append("- A equipa de fora mantem a bola!\n");
                                break;
                    case 1:  jogo.setGoloVisitante(jogo.getGolosVisitante() + 1);
                                sb.append("Minuto ").append(jogo.getMinutos()).append("- A equipa visitante marcou golo! Placard :").append(jogo.getGolosVisitado()).append(" - ").append(jogo.getGolosVisitante()).append("\n");
                                posse--;
                                break;
                    case -1:sb.append("Minuto ").append(jogo.getMinutos()).append("- A equipa de casa recupera a bola!\n");
                                posse--;
                                break;
                }
            }
        }
        sb.append("Intervalo! Placard :").append(jogo.getGolosVisitado()).append(" - ").append(jogo.getGolosVisitante()).append("\n");
        try
        {
            if (jogo.getSubsCasa().size() > 0 ) { subc = jogo.getSubsCasa().get(0); jogo.aplicaSubsCasa(0); sb.append("Substituicao na equipa da casa : Entra o numero " + subc.getKey()).append(" e sai o numero ").append(subc.getValue()).append("\n");}
            if (jogo.getSubsFora().size() > 0 ) { subf = jogo.getSubsFora().get(0);jogo.aplicaSubsFora(0);sb.append("Substituicao na equipa de fora : Entra o numero " + subf.getKey()).append(" e sai o numero ").append(subf.getValue()).append("\n");}
        }
        catch (Exception e1)
        {
            sb.append(e1.getMessage());
        }
        for (;jogo.getMinutos() < Jogo.FIMJOGO;jogo.setMinutos(jogo.getMinutos() + 1)){
            if (jogo.getMinutos() == 60) {
                try
                {
                    if (jogo.getSubsCasa().size() > 1 ) { subc = jogo.getSubsCasa().get(1); jogo.aplicaSubsCasa(1);sb.append("Substituicao na equipa da casa : Entra o numero " + subc.getKey()).append(" e sai o numero ").append(subc.getValue()).append("\n");}
                    if (jogo.getSubsFora().size() > 1 ) {subf = jogo.getSubsFora().get(1);jogo.aplicaSubsFora(1); sb.append("Substituicao na equipa de fora : Entra o numero " + subf.getKey()).append(" e sai o numero ").append(subf.getValue()).append("\n");}  
                }
                catch (Exception e2)
                {
                    sb.append(e2.getMessage());
                }
            }
            if (jogo.getMinutos() == 75) {
                try
                {
                    if (jogo.getSubsCasa().size() > 2 ) {subc = jogo.getSubsCasa().get(2);jogo.aplicaSubsCasa(2);sb.append("Substituicao na equipa da casa : Entra o numero " + subc.getKey()).append(" e sai o numero ").append(subc.getValue()).append("\n");}
                    if (jogo.getSubsFora().size() > 2 ) {subf = jogo.getSubsFora().get(2);jogo.aplicaSubsFora(2);sb.append("Substituicao na equipa de fora : Entra o numero " + subf.getKey()).append(" e sai o numero ").append(subf.getValue()).append("\n");}
                }
                catch (Exception e3)
                {
                    sb.append(e3.getMessage());
                } 
            }
            if (posse == 0) {
                float ataque = jogo.getVisitado().calculaHabilidadeAtaque();
                float defesa = jogo.getVisitante().calculaHabilidadeDefesa();
                int resultado = jogo.simulaAtaque (ataque,defesa);
                switch (resultado) {
                    case 0 : sb.append("Minuto ").append(jogo.getMinutos()).append("- A equipa de casa mantem a bola!\n");
                                break;
                    case 1: jogo.setGoloVisitado(jogo.getGolosVisitado() + 1);
                                sb.append("Minuto ").append(jogo.getMinutos()).append("- A equipa de casa marcou golo! Placard :").append(jogo.getGolosVisitado()).append(" - ").append(jogo.getGolosVisitante()).append("\n");
                                posse++;
                                break;
                    case -1:sb.append("Minuto ").append(jogo.getMinutos()).append("- A equipa visitante recupera a bola!\n");
                                posse++;
                                break;
                }
            }
            else {
                float ataque = jogo.getVisitante().calculaHabilidadeAtaque();
                float defesa = jogo.getVisitado().calculaHabilidadeDefesa();
                int resultado = jogo.simulaAtaque (ataque,defesa);
                switch (resultado) {
                    case 0 : sb.append("Minuto ").append(jogo.getMinutos()).append("- A equipa de fora mantem a bola!\n");
                                break;
                    case 1: jogo.setGoloVisitante(jogo.getGolosVisitante() + 1);
                                sb.append("Minuto ").append(jogo.getMinutos()).append("- A equipa visitante marcou golo! Placard :").append(jogo.getGolosVisitado()).append(" - ").append(jogo.getGolosVisitante()).append("\n");
                                posse--;
                                break;
                    case -1:sb.append("Minuto ").append(jogo.getMinutos()).append("- A equipa de casa recupera a bola!\n");
                                posse--;
                                break;
                }
            }
        }
        sb.append("Acabou o jogo!! Resultado final :").append(jogo.getGolosVisitado()).append(" - ").append(jogo.getGolosVisitante()).append("\n");
        valueFromModel = sb.toString();
        }
        else valueFromModel = "Uma das equipas nao tem os 11 titulares ou nao definiu as substituicoes";
    }
    }
    catch (IndexOutOfBoundsException ie) {
        valueFromModel = ie.getMessage();
    }
    this.setChanged();
    this.notifyObservers(valueFromModel);
    }

    public void escreverFicheiroTexto (String nomeF) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(nomeF);
        for (Equipa e:this.equipas.values())
            pw.println(e.toString());
        pw.flush();
        pw.close();
    }

    public void guardarEstado (String nomeF) throws FileNotFoundException,IOException {
        FileOutputStream fos = new FileOutputStream(nomeF);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public static FMModel carregarEstado (String nomeF) throws FileNotFoundException,ClassNotFoundException,IOException {
        FileInputStream fis = new FileInputStream(nomeF);
        ObjectInputStream ois = new ObjectInputStream(fis);

        FMModel novo = (FMModel) ois.readObject();

        ois.close();
        return novo;
    }

}
