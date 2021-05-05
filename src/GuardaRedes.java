 




import java.math.BigDecimal;
import java.util.List;

/**
 * Write a description of class Avancado here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuardaRedes extends Jogador
{
    // instance variables - replace the example below with your own
    private int elasticidade;
    private int reflexos;
    
    /**
     * Construtor vazia da classe GuardaRedes; 
     */
    public GuardaRedes()
    {
       super();
       this.elasticidade = 0;
       this.reflexos = 0;
    }
    
    /**
     * Constructor parametrizado da classe GuardaRedes;
     */
    
    public GuardaRedes (int velocidade, int resistencia, int destreza, int impulsao, int jogodecabeca, int passe, int remate, int elasticidade, int reflexos, String nome, int numCamisola, List<String> historialEquipas){
        super(velocidade,resistencia,destreza,impulsao,jogodecabeca,passe,remate,nome,numCamisola,historialEquipas);
        this.elasticidade = elasticidade;
        this.reflexos = reflexos;
    }
    
    /**
     * Construtor copia da classe GuardaRedes;
     */
    
    public GuardaRedes (GuardaRedes copia){
        super(copia);
        this.elasticidade = copia.getElasticidade();
        this.reflexos = copia.getReflexos();
    }
    
    public int getReflexos() {
        return this.reflexos;
    }
    
    public int getElasticidade() {
        return this.elasticidade;
    }
    
    public void setElasticidade(int elasticidade) {
        this.elasticidade = elasticidade;
    }
    
    public void setReflexos(int reflexos) {
        this.reflexos = reflexos;
    }
    
    public String toString (){
        return "Nome: "+this.getNome() + ", Posicao = Guarda-Redes; Habilidade =" + this.habilidadeJogador() +
                ";Atributos : Vel=" + this.getVelocidade() +
                ", Res=" + this.getResistencia() +
                ", Dest=" + this.getDestreza() +
                ", Imp=" + this.getImpulsao() +
                ", Jdc=" + this.getJogodecabeca() +
                ", Rem=" + this.getRemate() +
                ", Pas=" + this.getPasse() +
                ", Elas= " +this.getElasticidade() +
                ", Refl= " + this.getReflexos() +
                ", Numero=" + this.getNumCamisola() +
                ", Historial de Equipas" + this.getHistorialEquipas() +
                ";\n";
    }
    
    public GuardaRedes clone () {
        return new GuardaRedes(this);
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GuardaRedes)) return false;
        GuardaRedes jogador = (GuardaRedes) o;
        return this.getVelocidade() == jogador.getVelocidade() && this.getResistencia() == jogador.getResistencia() && this.getDestreza() == jogador.getDestreza() && this.getImpulsao() == jogador.getImpulsao() && this.getJogodecabeca() == jogador.getJogodecabeca() && this.getRemate() == jogador.getRemate() && this.getPasse() == jogador.getPasse() && this.getElasticidade()== jogador.getElasticidade() && this.getReflexos() == jogador.getReflexos() && this.getHistorialEquipas().equals(jogador.getHistorialEquipas());
    }
    
    /**
     * Funçao que determina a habilidade de um GuardaRedes;
     */
    
    public float habilidadeJogador (){
        float soma = this.getVelocidade()*0.1f;
        soma += this.getResistencia()*0.01f;
        soma += this.getDestreza()*0.16f;
        soma += this.getImpulsao()*0.25f;
        soma += this.getJogodecabeca()*0.01f;
        soma += this.getPasse()*0.05f;
        soma += this.getRemate()*0.01f;
        soma += this.getElasticidade()*0.25f;
        soma += this.getReflexos() * 0.25f;
        return BigDecimal.valueOf(soma).setScale(2,BigDecimal.ROUND_HALF_DOWN).floatValue();
    }
}
