 



import java.math.BigDecimal;
import java.util.List;

/**
 * Write a description of class Defesa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Defesa extends Jogador
{
    // instance variables - replace the example below with your own
    private int posicionamento;
    private int desarme;
    
    /**
     * Construtor vazia da classe Defesa; 
     */
    public Defesa()
    {
       super();
       this.posicionamento = 0;
       this.posicionamento = 0;
    }
    
    /**
     * Constructor parametrizado da classe Defesa;
     */
    
    public Defesa (int velocidade, int resistencia, int destreza, int impulsao,int jogodecabeca, int passe, int remate,int desarme,int posicionamento, String nome, int numCamisola, List<String> historialEquipas){
        super(velocidade,resistencia,destreza,impulsao,jogodecabeca,passe,remate,nome,numCamisola, historialEquipas);
        this.posicionamento = posicionamento;
        this.desarme = desarme;
    }
    
    /**
     * Construtor copia da classe Defesa;
     */
    
    public Defesa (Defesa copia){
        super(copia);
        this.posicionamento = copia.getPosicionamento();
        this.desarme = copia.getDesarme();
    }
    
    public int getPosicionamento() {
        return this.posicionamento;
    }
    
    public int getDesarme() {
        return this.desarme;
    }
    
    public void setPosicionamento(int pos) {
        this.posicionamento = pos;
    }
    
    public void setDesarme(int des) {
        this.desarme= des;
    }
    
    public String toString (){
        return "Nome: " +this.getNome() + "; Posicao = Defesa; Habilidade =" + this.habilidadeJogador() +
                ";Atributos : Vel=" + this.getVelocidade() +
                ", Res=" + this.getResistencia() +
                ", Dest=" + this.getDestreza() +
                ", Imp=" + this.getImpulsao() +
                ", Jdc=" + this.getJogodecabeca() +
                ", Rem=" + this.getRemate() +
                ", Pas=" + this.getPasse() +
                ", Pos=" + this.getPosicionamento() +
                ", Des=" + this.getDesarme() +
                ", Numero=" + this.getNumCamisola() +
                ", Historial de Equipas" + this.getHistorialEquipas() +
                ";\n";
    }
    
    public Defesa clone () {
        return new Defesa(this);
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Defesa)) return false;
        Defesa jogador = (Defesa) o;
        return this.getVelocidade() == jogador.getVelocidade() && this.getResistencia() == jogador.getResistencia() && this.getDestreza() == jogador.getDestreza() && this.getImpulsao() == jogador.getImpulsao() && this.getJogodecabeca() == jogador.getJogodecabeca() && this.getRemate() == jogador.getRemate() && this.getPasse() == jogador.getPasse() && this.getDesarme() == jogador.getDesarme() && this.getPosicionamento() == jogador.getPosicionamento() && this.getHistorialEquipas().equals(jogador.getHistorialEquipas());
    }
    
    /**
     * Funçao que determina a habilidade de um Defesa;
     */
    
    public float habilidadeJogador (){
        float soma = this.getVelocidade()*0.10f;
        soma += this.getResistencia()*0.05f;
        soma += this.getDestreza()*0.05f;
        soma += this.getImpulsao()*0.15f;
        soma += this.getJogodecabeca()*0.15f;
        soma += this.getPasse()*0.05f;
        soma += this.getRemate()*0.01f;
        soma += this.getDesarme()*0.22f;
        soma += this.getPosicionamento() * 0.22f;
        return BigDecimal.valueOf(soma).setScale(2,BigDecimal.ROUND_HALF_DOWN).floatValue();
    }
}
