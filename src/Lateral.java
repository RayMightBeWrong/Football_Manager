package src;


/**
 * Write a description of class Lateral here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lateral extends Jogador
{
    // instance variables - replace the example below with your own
    
    /**
     * Construtor vazia da classe Lateral; 
     */
    public Lateral()
    {
       super(); 
    }
    
    /**
     * Constructor parametrizado da classe Lateral;
     */
    
    public Lateral (int velocidade, int resistencia, int destreza, int impulsao,int jogodecabeca, int passe, int remate, String nome, int numCamisola){
        super(velocidade,resistencia,destreza,impulsao,jogodecabeca,passe,remate,nome,numCamisola);
    }
    
    /**
     * Construtor copia da classe Lateral;
     */
    
    public Lateral (Lateral copia){
        super(copia);
    }
    
    public String toString (){
        return this.getNome()+"{\n" +
                "Posicao = Lateral,Habilidade =" + this.habilidadeJogador() +
                ",\n velocidade=" + this.getVelocidade() +
                ",\n resistencia=" + this.getResistencia() +
                ",\n destreza=" + this.getDestreza() +
                ",\n impulsao=" + this.getImpulsao() +
                ",\n jogodecabeca=" + this.getJogodecabeca() +
                ",\n remate=" + this.getRemate() +
                ",\n passe=" + this.getPasse() +
                ",\n numCamisola=" + this.getNumCamisola() +
                "\n}";
    }
    
    public Lateral clone () {
        return new Lateral(this);
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lateral)) return false;
        Lateral jogador = (Lateral) o;
        return this.getVelocidade() == jogador.getVelocidade() && this.getResistencia() == jogador.getResistencia() && this.getDestreza() == jogador.getDestreza() && this.getImpulsao() == jogador.getImpulsao() && this.getJogodecabeca() == jogador.getJogodecabeca() && this.getRemate() == jogador.getRemate() && this.getPasse() == jogador.getPasse();
    }
    
    /**
     * Funçao que determina a habilidade de um Lateral;
     */
    
    public float habilidadeJogador (){
        float soma = this.getVelocidade()*0.2f;
        soma += this.getResistencia()*0.25f;
        soma += this.getDestreza()*0.2f;
        soma += this.getImpulsao()*0.05f;
        soma += this.getJogodecabeca()*0.05f;
        soma += this.getPasse()*0.2f;
        soma += this.getRemate()*0.05f;
        return soma;
    }
}
