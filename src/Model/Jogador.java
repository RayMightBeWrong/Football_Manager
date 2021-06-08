package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Jogador implements Serializable ,Comparable<Jogador>{
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogodecabeca;
    private int remate;
    private int passe;
    private String nome;
    private int numCamisola;
    private int altura;
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
        this.altura        = 0;
        this.numCamisola   = 0;
        this.nome          = "";
        this.historialEquipas = new ArrayList<String>();
    }

    /*
    Construtor de jogador parametrizado
     */
    public Jogador(int velocidade, int resistencia, int destreza, int impulsao,int jogodecabeca, int passe, int remate, int altura,String nome, int numCamisola, List<String> historialEquipas) throws AtributoInvalidoException{
        setVelocidade(velocidade);
        setResistencia(resistencia);
        setDestreza(destreza);
        setImpulsao(impulsao);
        setJogodecabeca(jogodecabeca);
        setPasse(passe);
        setRemate(remate);
        setAltura(altura);
        setNome(nome);
        setNumCamisola(numCamisola);
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
        this.altura = j.getAltura();
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

    public int getAltura(){
        return this.altura;
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

    public void setResistencia(int resistencia) throws AtributoInvalidoException {
        if (resistencia >= 0 && resistencia <= 100) this.resistencia = resistencia;
        else throw new AtributoInvalidoException("Atributo resistencia inserido invalido!");
    }

    public void setDestreza(int destreza) throws AtributoInvalidoException{
        if (destreza >= 0 && destreza <= 100) this.destreza = destreza;
        else throw new AtributoInvalidoException("Atributo destreza inserido invalido!");
    }

    public void setImpulsao(int impulsao) throws AtributoInvalidoException{
        if (impulsao >= 0 && impulsao <= 100) this.impulsao = impulsao;
        else throw new AtributoInvalidoException("Atributo impulsao inserido invalido!");
    }

    public void setVelocidade(int velocidade) throws AtributoInvalidoException {
        if (velocidade >= 0 && velocidade <= 100) this.velocidade = velocidade;
        else throw new AtributoInvalidoException("Atributo velocidade inserido invalido!");
    }

    public void setJogodecabeca(int jogodecabeca) throws AtributoInvalidoException{
        if (jogodecabeca >= 0 && jogodecabeca <= 100) this.jogodecabeca = jogodecabeca;
        else throw new AtributoInvalidoException("Atributo jogo de cabeca inserido invalido!");
    }

    public void setPasse(int passe) throws AtributoInvalidoException{
        if (passe >= 0 && passe <= 100) this.passe = passe;
        else throw new AtributoInvalidoException("Atributo passe inserido invalido!");
    }

    public void setRemate(int remate) throws AtributoInvalidoException{
        if (remate >= 0 && remate <= 100) this.remate = remate;
        else throw new AtributoInvalidoException("Atributo remate inserido invalido!");
    }

    public void setAltura (int altura) throws AtributoInvalidoException{
        if (altura >= 130 && altura <= 240) this.altura = altura;
        else throw new AtributoInvalidoException("Atributo altura inserido invalido!");
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumCamisola(int numCamisola) throws AtributoInvalidoException {
        if (numCamisola >= 0 && numCamisola <= 100) this.numCamisola = numCamisola;
        else throw new AtributoInvalidoException("Atributo numero camisola inserido invalido!");
    }

    public void setHistorialEquipas(List<String> historialEquipas) {
        if(historialEquipas != null)
            this.historialEquipas = historialEquipas.stream().collect(Collectors.toList());
        else
            this.historialEquipas = new ArrayList<String>();
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

    abstract public float calculaHabilidadeGuardaRedes();

    abstract public float calculaHabilidadeDefesa();
    
    abstract public float calculaHabilidadeMedio();

    abstract public float calculaHabilidadeAvancado();

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(":");
        sb.append(this.getNome()).append(";");
        sb.append(this.getNumCamisola()).append(";");
        sb.append(this.getAltura()).append(";");
        sb.append(this.getVelocidade()).append(";");
        sb.append(this.getResistencia()).append(";");
        sb.append(this.getDestreza()).append(";");
        sb.append(this.getImpulsao()).append(";");
        sb.append(this.getJogodecabeca()).append(";");
        sb.append(this.getRemate()).append(";");
        sb.append(this.getPasse()).append(";");
        return sb.toString();
    }
    
    public int compareTo (Jogador j) {
        if (this.getNome().compareTo(j.getNome()) == 0)
            return (int) (this.habilidadeJogador() - j.habilidadeJogador());
        else return this.getNome().compareTo(j.getNome());
    }
}
