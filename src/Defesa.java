package src;

import java.math.BigDecimal;

/**
 * Write a description of class Avancado here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Defesa extends Jogador
{
    // instance variables - replace the example below with your own
    
    /**
     * Construtor vazia da classe Defesa; 
     */
    public Defesa()
    {
       super(); 
    }
    
    /**
     * Constructor parametrizado da classe Defesa;
     */
    
    public Defesa (int velocidade, int resistencia, int destreza, int impulsao,int jogodecabeca, int passe, int remate, String nome, int numCamisola){
        super(velocidade,resistencia,destreza,impulsao,jogodecabeca,passe,remate,nome,numCamisola);
    }
    
    /**
     * Construtor copia da classe Defesa;
     */
    
    public Defesa (Defesa copia){
        super(copia);
    }
    
    public String toString (){
        return this.getNome() + ",Posicao = Defesa,Habilidade =" + this.habilidadeJogador() +
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
    
    public Defesa clone () {
        return new Defesa(this);
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Defesa)) return false;
        Defesa jogador = (Defesa) o;
        return this.getVelocidade() == jogador.getVelocidade() && this.getResistencia() == jogador.getResistencia() && this.getDestreza() == jogador.getDestreza() && this.getImpulsao() == jogador.getImpulsao() && this.getJogodecabeca() == jogador.getJogodecabeca() && this.getRemate() == jogador.getRemate() && this.getPasse() == jogador.getPasse();
    }
    
    /**
     * Funçao que determina a habilidade de um Defesa;
     */
    
    public float habilidadeJogador (){
        float soma = this.getVelocidade()*0.15f;
        soma += this.getResistencia()*0.15f;
        soma += this.getDestreza()*0.15f;
        soma += this.getImpulsao()*0.2f;
        soma += this.getJogodecabeca()*0.2f;
        soma += this.getPasse()*0.1f;
        soma += this.getRemate()*0.05f;
        return BigDecimal.valueOf(soma).setScale(2,BigDecimal.ROUND_HALF_DOWN).floatValue();
    }
}
