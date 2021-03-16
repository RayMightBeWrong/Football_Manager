import java.util.ArrayList;

public class Equipa {
    private ArrayList<Jogador> equipa;
    private String name;
    private int numJogadores;

    /*
    Construtores de equipa
     */
    public Equipa() {
        this.equipa = new ArrayList<Jogador>();
    }

    public void createTeam() {
        this.equipa = new ArrayList<Jogador>();
        Jogador j;
        for(int i = 0; i < this.numJogadores; i++) this.equipa.add(i,new Jogador());
    }

    /*
    Getter methods
     */
    public Jogador getJogador(int i) {
        return this.equipa.get(i);
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
        this.equipa.add(j);
    }

    public void setEquipa(ArrayList<Jogador> equipa) {
        this.equipa = equipa;
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
        return "Equipa{\n" +
                "equipa=\n" + equipa +
                "\n}";
    }
}
