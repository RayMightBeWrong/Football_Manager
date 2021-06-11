package Model;

import java.math.BigDecimal;
import java.util.List;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Write a description of class Lateral here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lateral extends Jogador
{
    // instance variables - replace the example below with your own
    private int cruzamentos;
    private int posicionamento;
    /**
     * Construtor vazia da classe Lateral; 
     */
    public Lateral()
    {
       super();
       this.cruzamentos = 0;
       this.posicionamento = 0;
    }
    
    /**
     * Constructor parametrizado da classe Lateral;
     */
    
    public Lateral (int velocidade, int resistencia, int destreza, int impulsao, int jogodecabeca, int passe
        , int remate, int cruzamentos, int posicionamento,int altura, String nome, int numCamisola, List<String> historialEquipas) throws AtributoInvalidoException {
        super(velocidade,resistencia,destreza,impulsao,jogodecabeca,passe,remate,altura,nome,numCamisola,historialEquipas);
        setPosicionamento(posicionamento);
        setCruzamentos(cruzamentos);
    }
    
    /**
     * Construtor copia da classe Lateral;
     */
    
    public Lateral (Lateral copia){
        super(copia);
        this.cruzamentos = copia.getCruzamentos();
        this.posicionamento = copia.getPosicionamento();
    }
    
    public int getPosicionamento() {
        return this.posicionamento;
    }
    
    public int getCruzamentos() {
        return this.cruzamentos;
    }
    
    public void setPosicionamento(int pos) throws AtributoInvalidoException {
        if (pos >= 0 && pos <= 100) this.posicionamento = pos;
        else throw new AtributoInvalidoException("Atributo posicionamento invalido!");
    }
    
    public void setCruzamentos(int cru) throws AtributoInvalidoException {
        if (cru >= 0 && cru <= 100) this.cruzamentos= cru;
        else throw new AtributoInvalidoException("Atributo cruzamentos invalido!");
    }
    
    public String toString (){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(this.getCruzamentos()).append(";");
        sb.append(this.getPosicionamento()).append(";");
        for (String e: this.getHistorialEquipas()){
            sb.append(e).append(";");
        }
        return sb.toString();
    }
    
    public String simpleToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(":");
        sb.append(super.simpleToString());
        sb.append(",").append(this.getCruzamentos());
        return sb.toString();
    }

    public Lateral clone () {
        return new Lateral(this);
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lateral)) return false;
        Lateral jogador = (Lateral) o;
        return this.getVelocidade() == jogador.getVelocidade() && this.getResistencia() == jogador.getResistencia() && this.getDestreza() == jogador.getDestreza() && this.getImpulsao() == jogador.getImpulsao() 
                && this.getJogodecabeca() == jogador.getJogodecabeca() && this.getRemate() == jogador.getRemate() && this.getPasse() == jogador.getPasse() && this.getCruzamentos() == jogador.getCruzamentos() 
                && this.getPosicionamento() == jogador.getPosicionamento() && this.getAltura() == jogador.getAltura()&& this.getHistorialEquipas().equals(jogador.getHistorialEquipas());
    }
    
    /**
     * Funçao que determina a habilidade de um Lateral;
     */
    
    public float habilidadeJogador (){
        float soma = this.getVelocidade()*0.15f;
        soma += this.getResistencia()*0.15f;
        soma += this.getDestreza()*0.15f;
        soma += this.getImpulsao()*0.05f;
        soma += this.getJogodecabeca()*0.05f;
        soma += this.getPasse()*0.1f;
        soma += this.getRemate()*0.05f;
        soma += this.getCruzamentos() * 0.2f;
        soma += this.getPosicionamento()* 0.1f;
        return BigDecimal.valueOf(soma).setScale(2,RoundingMode.HALF_EVEN).floatValue();
    }


    public float calculaHabilidadeGuardaRedes(){
        return 10.0f;
    }

    public float calculaHabilidadeDefesa() {
        return this.habilidadeJogador();
    }
    
    public float calculaHabilidadeMedio() {
        float soma = this.getVelocidade() * 0.1f;
        soma += this.getResistencia() * 0.15f;
        soma += this.getDestreza() * 0.10f;
        soma += this.getImpulsao() * 0.1f;
        soma += this.getJogodecabeca() * 0.05f;
        soma += this.getPasse() * 0.2f;
        soma += this.getRemate() * 0.1f;
        return BigDecimal.valueOf(soma).setScale(2,RoundingMode.HALF_EVEN).floatValue();
    }

    public float calculaHabilidadeAvancado() {
        float soma = this.getVelocidade()*0.1f;
        soma += this.getResistencia()*0.05f;
        soma += this.getDestreza()*0.1f;
        soma += this.getImpulsao()*0.1f;
        soma += this.getJogodecabeca()*0.1f;
        soma += this.getPasse()*0.05f;
        soma += this.getRemate()*0.2f;
        if (this.getAltura() >= 180) soma += 5;
        return BigDecimal.valueOf(soma).setScale(2,RoundingMode.HALF_EVEN).floatValue();
    }
    
    public static Lateral parse(String input) throws AtributoInvalidoException{
        String[] campos = input.split(",");
        Random rand = new Random();
        return new Lateral(Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[9]),
                rand.nextInt(50) + 50,
                rand.nextInt(32) + 158,
                campos[0],
                Integer.parseInt(campos[1]),
                null);
    }
}
