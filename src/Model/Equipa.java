package Model;


 
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Equipa implements Serializable{
    private Map<Integer,Jogador> equipa;
    private String name;
    private int numJogadores;
    private Tatica tatica;

    /*
    Construtores de equipa
     */
    public Equipa() {
        this.equipa = new HashMap<Integer,Jogador>();
        this.name = "";
        this.numJogadores = 0;
        this.tatica = new Tatica();
    }

    public Equipa (String name) {
        this.equipa = new HashMap<Integer,Jogador>();
        this.name = name;
        this.numJogadores = 0;
        this.tatica = new Tatica();
    }

    public Equipa(Map<Integer, Jogador> equipa, String name, int numJogadores, Tatica tatica) {
        this.equipa = equipa.values().stream().collect(Collectors.toMap(j->j.getNumCamisola(),j->j.clone()));
        this.name = name;
        this.numJogadores = numJogadores;
        this.tatica = tatica;
    }

    public Equipa(Equipa e) {
        this.equipa = e.getJogadores().stream().collect(Collectors.toMap(j->j.getNumCamisola(),j->j.clone()));
        this.name = e.getName();
        this.numJogadores = e.getNumJogadores();
        this.tatica = e.getTatica();
    }

    public List<Jogador> getJogadores () {
        return this.equipa.values().stream().collect(Collectors.toList());
    }
    
    /*
    Getter methods
     */
    public Jogador getJogador(int num) {
        return this.equipa.get(num).clone();
    }

    public int getNumJogadores() {
        return this.numJogadores;
    }

    public String getName() {
        return this.name;
    }

    public Tatica getTatica(){
        return this.tatica.clone();
    }

    /*
    Setter methods
     */

    public void addPlayer(Jogador j) throws NumeroExistenteException, JogadorExistenteException{
        if (this.equipa.containsKey(j.getNumCamisola()))
        throw new NumeroExistenteException("Numero ja ocupado!");
        else if (this.equipa.containsValue(j)){
            throw new JogadorExistenteException ("Jogador ja presente na equipa!");
        }
        else {
            j.addEquipatoHistorial(this.getName());
            this.equipa.putIfAbsent(j.getNumCamisola(),j.clone());
            this.numJogadores++;
        }
    }

    public void setEquipa(Map<Integer,Jogador> equipa) {
        this.equipa = equipa.values().stream().collect(Collectors.toMap(j->j.getNumCamisola(),j->j.clone()));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumJogadores(int numJogadores) {
        this.numJogadores = numJogadores;
    }

    public void setTatica(Tatica tatica){
        this.tatica = tatica.clone();
    }

    /*
    useful methods
    */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Equipa:").append(this.getName()).append("\n");
        for (Jogador j:this.equipa.values()) {
            sb.append(j.toString()).append("\n");
        }
        return sb.toString();
    }
    
    public Equipa clone() {
        return new Equipa(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipa )) return false;
        Equipa equipa= (Equipa ) o;
        return this.getTatica().equals(equipa.getTatica()) && this.getNumJogadores() == equipa.getNumJogadores() && this.getName().equals(equipa.getName()) && this.getJogadores().equals(equipa.getJogadores());
    }

    public void addTitular (Jogador j) {
        if (this.equipa.containsKey(j.getNumCamisola()))
            this.tatica.adicionaTitular(j);
    }

    public void removePlayer(Jogador j){
        if(this.equipa.containsKey(j.getNumCamisola())) {
            this.equipa.remove(j.getNumCamisola());
            this.numJogadores--;
        }
        this.tatica.removeJogadorTatica(j);
    }

    public void addSuplente(Jogador  j) {
        if (this.equipa.containsKey(j.getNumCamisola()))
            this.tatica.adicionaSuplente(j);
    }

    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }
}
