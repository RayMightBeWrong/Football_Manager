//package src;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Write a description of class Avancado here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Medio extends Jogador {
    // instance variables - replace the example below with your own
    private int recBola;
    private int criatividade;

    /**
     * Construtor vazia da classe Avancado;
     */
    public Medio() {
        super();
        this.recBola = 0;
        this.criatividade = 0;
    }

    /**
     * Constructor parametrizado da classe Avancado;
     */

    public Medio(int velocidade, int resistencia, int destreza, int impulsao, int jogodecabeca, int passe, int remate, int rec, int cri, String nome, int numCamisola, List<String> historialEquipas) {
        super(velocidade, resistencia, destreza, impulsao, jogodecabeca, passe, remate, nome, numCamisola, historialEquipas);
        this.recBola = rec;
        this.criatividade = cri;
    }

    /**
     * Construtor copia da classe Avancado;
     */

    public Medio(Medio copia) {
        super(copia);
        this.recBola = copia.getRecBola();
        this.criatividade = copia.getCriatividade();
    }

    public int getRecBola() {
        return this.recBola;
    }

    public int getCriatividade() {
        return this.criatividade;
    }

    public void setRecBola(int rec) {
        this.recBola = rec;
    }

    public void setCriatividade(int cri) {
        this.criatividade = cri;
    }

    public String toString() {
        return "Nome: " + this.getNome() + "; Posicao = Medio; Habilidade =" + this.habilidadeJogador() +
                ",Atributos : Vel=" + this.getVelocidade() +
                ", Res=" + this.getResistencia() +
                ", Dest=" + this.getDestreza() +
                ", Imp=" + this.getImpulsao() +
                ", Jdc=" + this.getJogodecabeca() +
                ", Rem=" + this.getRemate() +
                ", Pas=" + this.getPasse() +
                ", Cri=" + this.getCriatividade() +
                ", Rec=" + this.getRecBola() +
                ", Numero=" + this.getNumCamisola() +
                ", Historial de Equipas" + this.getHistorialEquipas() +
                ";\n";
    }

    public Medio clone() {
        return new Medio(this);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medio)) return false;
        Medio jogador = (Medio) o;
        return this.getVelocidade() == jogador.getVelocidade() && this.getResistencia() == jogador.getResistencia() && this.getDestreza() == jogador.getDestreza() && this.getImpulsao() == jogador.getImpulsao() && this.getJogodecabeca() == jogador.getJogodecabeca() && this.getRemate() == jogador.getRemate() && this.getPasse() == jogador.getPasse() && this.getCriatividade() == jogador.getCriatividade() && this.getRecBola() == jogador.getRecBola() && this.getHistorialEquipas().equals(jogador.getHistorialEquipas());
    }

    /**
     * Funçao que determina a habilidade de um Avancado;
     */

    public float habilidadeJogador() {
        float soma = this.getVelocidade() * 0.1f;
        soma += this.getResistencia() * 0.15f;
        soma += this.getDestreza() * 0.10f;
        soma += this.getImpulsao() * 0.1f;
        soma += this.getJogodecabeca() * 0.05f;
        soma += this.getPasse() * 0.2f;
        soma += this.getRemate() * 0.1f;
        soma += this.getCriatividade() * 0.1f;
        soma += this.getRecBola() * 0.1f;
        return BigDecimal.valueOf(soma).setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue();
    }
}