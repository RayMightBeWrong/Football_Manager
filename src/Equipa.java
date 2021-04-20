package src;

import java.util.*;
import java.util.stream.Collectors;

public class Equipa {
    private Map<Integer,Jogador> equipa;
    private String name;
    private int numJogadores = 0;

    /*
    Construtores de equipa
     */
    public Equipa() {
        this.equipa = new HashMap<Integer,Jogador>();
    }

    public void createTeam() {
        this.equipa = new HashMap<Integer,Jogador>();
    }

    /*
    Getter methods
     */
    public Jogador getJogador(int num) {
        return this.equipa.get(num);
    }

    public int getNumJogadores() {
        return this.numJogadores;
    }

    public String getName() {
        return this.name;
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
}
