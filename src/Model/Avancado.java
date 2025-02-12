package Model;

import java.math.BigDecimal;
import java.util.List;
import java.math.RoundingMode;
import java.util.Random;

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
    
    public Avancado (int velocidade, int resistencia, int destreza, int impulsao,int jogodecabeca, int passe
                    , int remate,int comp,int altura, String nome, int numCamisola, List<String> historialEquipas) throws AtributoInvalidoException{
        super(velocidade,resistencia,destreza,impulsao,jogodecabeca,passe,remate,altura,nome,numCamisola, historialEquipas);
        setCompustura(comp);
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
    
    public void setCompustura(int comp) throws AtributoInvalidoException {
        if (compustura >= 0 && compustura <= 100) this.compustura = comp;
        else throw new AtributoInvalidoException("Atributo compustura inserido invalido!");
    }
    
    public String toString (){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(this.getCompustura()).append(";");
        for (String e: this.getHistorialEquipas()){
            sb.append(e).append(";");
        }
        return sb.toString();
    }

    public String simpleToString () {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(":");
        sb.append(super.simpleToString());
        return sb.toString();
    }
    
    public Avancado clone () {
        return new Avancado(this);
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avancado)) return false;
        Avancado jogador = (Avancado) o;
        return this.getVelocidade() == jogador.getVelocidade() && this.getResistencia() == jogador.getResistencia() && this.getDestreza() == jogador.getDestreza() && this.getImpulsao() == jogador.getImpulsao() 
                && this.getJogodecabeca() == jogador.getJogodecabeca() && this.getRemate() == jogador.getRemate() && this.getPasse() == jogador.getPasse() && this.getCompustura() == jogador.getCompustura()
                && this.getAltura() == jogador.getAltura() && this.getHistorialEquipas().equals(jogador.getHistorialEquipas());
    }
    
    /**
     * Funçao que determina a habilidade de um Avancado;
     */
    
    public float habilidadeJogador (){
        float soma = this.getVelocidade()*0.1f;
        soma += this.getResistencia()*0.1f;
        soma += this.getDestreza()*0.1f;
        soma += this.getImpulsao()*0.1f;
        soma += this.getJogodecabeca()*0.1f;
        soma += this.getPasse()*0.1f;
        soma += this.getRemate()*0.25f;
        soma += this.getCompustura() * 0.1f;
        if (this.getAltura() >= 180) soma += 5;
        return BigDecimal.valueOf(soma).setScale(2,RoundingMode.HALF_EVEN).floatValue();
    }

    public float calculaHabilidadeGuardaRedes(){
        return 10.0f;
    }

    public float calculaHabilidadeDefesa() {
        float soma = this.getVelocidade()*0.15f;
        soma += this.getResistencia()*0.05f;
        soma += this.getDestreza()*0.05f;
        soma += this.getImpulsao()*0.15f;
        soma += this.getJogodecabeca()*0.15f;
        soma += this.getPasse()*0.05f;
        if (this.getAltura() >= 180) soma += 5;
        return BigDecimal.valueOf(soma).setScale(2,RoundingMode.HALF_EVEN).floatValue();
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
        return this.habilidadeJogador();
    }

    public static Avancado parse(String input) throws AtributoInvalidoException{
        String[] campos = input.split(",");
        Random rand = new Random();
        return new Avancado(Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[7]),
                rand.nextInt(70) + 30,
                rand.nextInt(45) + 160,
                campos[0],
                Integer.parseInt(campos[1]),
                null);
    }
}
