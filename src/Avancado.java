 



import java.math.BigDecimal;
import java.util.List;

/**
 * Write a description of class Avancado here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Avancado extends Jogador
{
    // instance variables - replace the example below with your own
    private int compustura;
    /**
     * Construtor vazia da classe Avancado; 
     */
    public Avancado()
    {
       super();
       this.compustura = 0;
    }
    
    /**
     * Constructor parametrizado da classe Avancado;
     */
    
    public Avancado (int velocidade, int resistencia, int destreza, int impulsao,int jogodecabeca, int passe, int remate,int comp, String nome, int numCamisola, List<String> historialEquipas){
        super(velocidade,resistencia,destreza,impulsao,jogodecabeca,passe,remate,nome,numCamisola, historialEquipas);
        this.compustura = comp;
    }
    
    /**
     * Construtor copia da classe Avancado;
     */
    
    public Avancado (Avancado copia){
        super(copia);
        this.compustura = copia.getCompustura();
    }
    
    public int getCompustura(){
        return this.compustura;
    }
    
    public void setCompustura(int comp){
        this.compustura = comp;
    }
    
    public String toString (){
        return "Nome: " +this.getNome() + ", Posicao = Avancado; Habilidade =" + this.habilidadeJogador() +
                ",Atributos : Vel=" + this.getVelocidade() +
                ", Res=" + this.getResistencia() +
                ", Dest=" + this.getDestreza() +
                ", Imp=" + this.getImpulsao() +
                ", Jdc=" + this.getJogodecabeca() +
                ", Rem=" + this.getRemate() +
                ", Pas=" + this.getPasse() +
                ", Comp=" + this.getCompustura() +
                ", Numero=" + this.getNumCamisola() +
                ", Historial de Equipas" + this.getHistorialEquipas() +
                ";\n";
    }
    
    public Avancado clone () {
        return new Avancado(this);
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avancado)) return false;
        Avancado jogador = (Avancado) o;
        return this.getVelocidade() == jogador.getVelocidade() && this.getResistencia() == jogador.getResistencia() && this.getDestreza() == jogador.getDestreza() && this.getImpulsao() == jogador.getImpulsao() && this.getJogodecabeca() == jogador.getJogodecabeca() && this.getRemate() == jogador.getRemate() && this.getPasse() == jogador.getPasse() && this.getCompustura() == jogador.getCompustura() && this.getHistorialEquipas().equals(jogador.getHistorialEquipas());
    }
    
    /**
     * Funçao que determina a habilidade de um Avancado;
     */
    
    public float habilidadeJogador (){
        float soma = this.getVelocidade()*0.1f;
        soma += this.getResistencia()*0.1f;
        soma += this.getDestreza()*0.1f;
        soma += this.getImpulsao()*0.1f;
        soma += this.getJogodecabeca()*0.15f;
        soma += this.getPasse()*0.1f;
        soma += this.getRemate()*0.25f;
        soma += this.getCompustura() * 0.1f;
        return BigDecimal.valueOf(soma).setScale(2,BigDecimal.ROUND_HALF_DOWN).floatValue();
    }
}
