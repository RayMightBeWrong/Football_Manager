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

    public FMModel(Map <String,Jogador> jog,Map<String,Equipa> equipas,List <Jogo> jogos) {
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
         for (Jogador j : this.jogadores.values())
                sb.append(j.toString());
        valueFromModel = sb.toString();
        this.setChanged();
        this.notifyObservers(valueFromModel);
     }

     public double calculaHabilidadeJogador(String nome)  throws JogadorNaoExistenteException {
        if (this.jogadores.containsKey(nome)) {
            Jogador j = this.jogadores.get(nome);
            valueFromModel = "Habilidade do " + nome + " : " + j.habilidadeJogador();
            this.setChanged();
            this.notifyObservers(valueFromModel);
            return j.habilidadeJogador();
         }
        else throw new JogadorNaoExistenteException("Jogador nao existe!");
     }

     public void listarEquipa(String nome) {
        if (this.equipas.containsKey(nome))
            valueFromModel = this.equipas.get(nome).toString();
        else valueFromModel = "Equipa nao existente!";
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
                    j.setNumCamisola(numero);
                    this.equipas.get(nomeE).addPlayer(j);
                    this.jogadores.remove(nomeJ);
                    valueFromModel = "Sucesso ao adicionar o jogador a equipa";
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
    
    public void comecarJogo(String eq1,String eq2) {
        Equipa e1 = this.equipas.get(eq1);
        Equipa e2 = this.equipas.get(eq2);
        Jogo j = new Jogo(e1, e2,0,0,0,"Bom tempo",LocalDateTime.now());
        j.iniciarJogo();
    }

    public void escreverFicheiroTexto (String nomeF) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(nomeF);
        for (Equipa e:this.equipas.values())
            pw.println(e.toString());
        pw.println("Equipa Sem equipa:");
        for (Jogador j: this.jogadores.values())
            pw.println(j.toString());
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
