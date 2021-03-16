import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Jogador {
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jododecabeca;
    private int remate;
    private int passe;
    private String posicao;
    private String nome;
    private int numCamisola;
    /*
    Construtor de jogador
     */
    public Jogador() {
        this.velocidade    = 0;
        this.resistencia   = 0;
        this.destreza      = 0;
        this.impulsao      = 0;
        this.jododecabeca  = 0;
        this.passe         = 0;
        this.remate        = 0;
        this.posicao       = null;
        this.numCamisola   = 0;
        this.nome          = null;
    }

    /*
    Construtor de jogador parametrizado
     */
    public Jogador(int velocidade, int resistencia, int destreza, int impulsao,int jododecabeca, int passe, int remate, String posicao, String nome, int numCamisola) {
        this.velocidade    = velocidade;
        this.resistencia   = resistencia;
        this.destreza      = destreza;
        this.impulsao      = impulsao;
        this.jododecabeca  = jododecabeca;
        this.passe         =  passe;
        this.remate        = remate;
        this.posicao       = posicao;
        this.nome          = nome;
        this.numCamisola   = numCamisola;
    }

    /*
    Construtor de copia
     */
    public Jogador(Jogador j) {
        this.posicao      = j.getPosicao();
        this.remate       = j.getRemate();
        this.passe        = j.getPasse();
        this.jododecabeca = j.getJododecabeca();
        this.impulsao     = j.getImpulsao();
        this.destreza     = j.getDestreza();
        this.velocidade   = j.getVelocidade();
        this.resistencia  = j.getResistencia();
        this.numCamisola  = j.getNumCamisola();
        this.nome         = j.getNome();
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

    public int getJododecabeca() {
        return this.jododecabeca;
    }

    public int getPasse() {
        return this.passe;
    }

    public int getRemate() {
        return this.remate;
    }

    public String getPosicao() {
        return this.posicao;
    }

    public int getNumCamisola() {
        return this.numCamisola;
    }

    public String getNome() {
        return this.nome;
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

    public void setJododecabeca(int jododecabeca) {
        this.jododecabeca = jododecabeca;
    }

    public void setPasse(int passe) {
        this.passe = passe;
    }

    public void setRemate(int remate) {
        this.remate = remate;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumCamisola(int numCamisola) {
        this.numCamisola = numCamisola;
    }

    /*
    Useful methods
    */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jogador)) return false;
        Jogador jogador = (Jogador) o;
        return velocidade == jogador.velocidade && resistencia == jogador.resistencia && destreza == jogador.destreza && impulsao == jogador.impulsao && jododecabeca == jogador.jododecabeca && remate == jogador.remate && passe == jogador.passe && Objects.equals(posicao, jogador.posicao);
    }

    public Jogador clone() {
        return new Jogador(this);
    }

    @Override
    public String toString() {
        return "Jogador{\n" +
                " velocidade=" + this.velocidade +
                ",\n resistencia=" + this.resistencia +
                ",\n destreza=" + this.destreza +
                ",\n impulsao=" + this.impulsao +
                ",\n jododecabeca=" + this.jododecabeca +
                ",\n remate=" + this.remate +
                ",\n passe=" + this.passe +
                ",\n posicao='" + this.posicao + '\'' +
                ",\n nome='" + this.nome + '\'' +
                ",\n numCamisola=" + this.numCamisola +
                "\n}";
    }
}
