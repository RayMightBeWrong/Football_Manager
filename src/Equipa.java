//package src;

import java.util.*;
import java.util.stream.Collectors;

public class Equipa {
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

    public Equipa(Map<Integer, Jogador> equipa, String name, int numJogadores, Tatica tatica) {
        this.equipa = new HashMap<Integer,Jogador>();
        this.name = name;
        this.numJogadores = numJogadores;
        this.tatica = tatica;
    }

    public Equipa(Equipa e) {
        this.equipa = new HashMap<Integer,Jogador>();
        this.name = e.getName();
        this.numJogadores = e.getNumJogadores();
        this.tatica = e.getTatica();
    }

    public void createTeam() {
        this.equipa = new HashMap<Integer,Jogador>();
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

    public void setPlayer(Jogador j) {
        this.equipa.put(j.getNumCamisola(),j.clone());
        this.numJogadores++;
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
        sb.append("Jogadores:\n");
        for (Jogador j:this.equipa.values()) {
            sb.append(j.toString());
        }
        return sb.toString();
    }

    public void removePlayer(int numCamisola){
        if(this.equipa.containsKey(numCamisola)) {
            this.equipa.remove(numCamisola);
            this.numJogadores--;
        }
    }
}
