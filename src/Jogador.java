 
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Jogador {
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogodecabeca;
    private int remate;
    private int passe;
    private String nome;
    private int numCamisola;
    private List<String> historialEquipas;
    /*
    Construtor de jogador
     */
    public Jogador() {
        this.velocidade    = 0;
        this.resistencia   = 0;
        this.destreza      = 0;
        this.impulsao      = 0;
        this.jogodecabeca  = 0;
        this.passe         = 0;
        this.remate        = 0;
        this.numCamisola   = 0;
        this.nome          = null;
        this.historialEquipas = new ArrayList<String>();
    }

    /*
    Construtor de jogador parametrizado
     */
    public Jogador(int velocidade, int resistencia, int destreza, int impulsao,int jododecabeca, int passe, int remate, String nome, int numCamisola, List<String> historialEquipas) {
        this.velocidade    = velocidade;
        this.resistencia   = resistencia;
        this.destreza      = destreza;
        this.impulsao      = impulsao;
        this.jogodecabeca  = jododecabeca;
        this.passe         =  passe;
        this.remate        = remate;
        this.nome          = nome;
        this.numCamisola   = numCamisola;
        setHistorialEquipas(historialEquipas);
    }

    /*
    Construtor de copia
     */
    public Jogador(Jogador j) {
        this.remate       = j.getRemate();
        this.passe        = j.getPasse();
        this.jogodecabeca = j.getJogodecabeca();
        this.impulsao     = j.getImpulsao();
        this.destreza     = j.getDestreza();
        this.velocidade   = j.getVelocidade();
        this.resistencia  = j.getResistencia();
        this.numCamisola  = j.getNumCamisola();
        this.nome         = j.getNome();
        this.historialEquipas = j.getHistorialEquipas();
    }

    /*
    Getter methods :)
     */
    public int getVelocidade() {
        return this.velocidade;
    }

    public int getResistencia() {
        return this.resistencia;
    }

    public int getDestreza() {
        return this.destreza;
    }

    public int getImpulsao() {
        return this.impulsao;
    }

    public int getJogodecabeca() {
        return this.jogodecabeca;
    }

    public int getPasse() {
        return this.passe;
    }

    public int getRemate() {
        return this.remate;
    }


    public int getNumCamisola() {
        return this.numCamisola;
    }

    public String getNome() {
        return this.nome;
    }

    public List<String> getHistorialEquipas() {
        List<String> r = new ArrayList<>();
        r = this.historialEquipas.stream().collect(Collectors.toList());
        return r;
    }

    /*
    Setter methods :)
     */

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public void setJogodecabeca(int jogodecabeca) {
        this.jogodecabeca = jogodecabeca;
    }

    public void setPasse(int passe) {
        this.passe = passe;
    }

    public void setRemate(int remate) {
        this.remate = remate;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumCamisola(int numCamisola) {
        this.numCamisola = numCamisola;
    }

    public void setHistorialEquipas(List<String> historialEquipas) {
        this.historialEquipas = historialEquipas.stream().collect(Collectors.toList());
    }

    public void addEquipatoHistorial(String equipa){
        this.historialEquipas.add(equipa);
    }

    abstract public float habilidadeJogador ();
    
    /*
    Useful methods
    */
    abstract public boolean equals(Object o);

    abstract public Jogador clone();

    abstract public String toString();
}
