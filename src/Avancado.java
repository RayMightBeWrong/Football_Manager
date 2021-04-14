package src;

import java.math.BigDecimal;

/**
 * Write a description of class Avancado here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Avancado extends Jogador
{
    // instance variables - replace the example below with your own
    
    /**
     * Construtor vazia da classe Avancado; 
     */
    public Avancado()
    {
       super(); 
    }
    
    /**
     * Constructor parametrizado da classe Avancado;
     */
    
    public Avancado (int velocidade, int resistencia, int destreza, int impulsao,int jogodecabeca, int passe, int remate, String nome, int numCamisola){
        super(velocidade,resistencia,destreza,impulsao,jogodecabeca,passe,remate,nome,numCamisola);
    }
    
    /**
     * Construtor copia da classe Avancado;
     */
    
    public Avancado (Avancado copia){
        super(copia);
    }
    
    public String toString (){
        return this.getNome() + ",Posicao = Avancado,Habilidade =" + this.habilidadeJogador() +
                ",\nAtributos : VL=" + this.getVelocidade() +
                ",RES=" + this.getResistencia() +
                ",DEST=" + this.getDestreza() +
                ",IMP=" + this.getImpulsao() +
                ",JDC=" + this.getJogodecabeca() +
                ",REM=" + this.getRemate() +
                ",PAS=" + this.getPasse() +
                ",NUM=" + this.getNumCamisola() +
                ",\n";
    }
    
    public Avancado clone () {
        return new Avancado(this);
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avancado)) return false;
        Avancado jogador = (Avancado) o;
        return this.getVelocidade() == jogador.getVelocidade() && this.getResistencia() == jogador.getResistencia() && this.getDestreza() == jogador.getDestreza() && this.getImpulsao() == jogador.getImpulsao() && this.getJogodecabeca() == jogador.getJogodecabeca() && this.getRemate() == jogador.getRemate() && this.getPasse() == jogador.getPasse();
    }
    
    /**
     * Funçao que determina a habilidade de um Avancado;
     */
    
    public float habilidadeJogador (){
        float soma = this.getVelocidade()*0.15f;
        soma += this.getResistencia()*0.1f;
        soma += this.getDestreza()*0.1f;
        soma += this.getImpulsao()*0.15f;
        soma += this.getJogodecabeca()*0.15f;
        soma += this.getPasse()*0.1f;
        soma += this.getRemate()*0.25f;
        return BigDecimal.valueOf(soma).setScale(2,BigDecimal.ROUND_HALF_DOWN).floatValue();
    }
}
