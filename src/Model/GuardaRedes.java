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
    
    public GuardaRedes (int velocidade, int resistencia, int destreza, int impulsao, int jogodecabeca, int passe
            , int remate, int elasticidade, int reflexos,int altura, String nome, int numCamisola, List<String> historialEquipas) throws AtributoInvalidoException{
        super(velocidade,resistencia,destreza,impulsao,jogodecabeca,passe,remate,altura,nome,numCamisola,historialEquipas);
        setElasticidade(elasticidade);
        setReflexos(reflexos);
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
    
    public void setElasticidade(int elasticidade) throws AtributoInvalidoException{
        if (elasticidade >= 0 && elasticidade <= 100) this.elasticidade = elasticidade;
        else throw new AtributoInvalidoException("Atributo elasticidade inserido invalido!");
    }
    
    public void setReflexos(int reflexos) throws AtributoInvalidoException {
        if (reflexos >= 0 && reflexos <= 100) this.reflexos = reflexos;
        else throw new AtributoInvalidoException("Atributo reflexos inserido invalido!");
    }
    
    public String toString (){
        StringBuilder sb = new StringBuilder();
        sb.append("Guarda-Redes").append(":");
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
        sb.append(this.getElasticidade()).append(";");
        sb.append(this.getReflexos()).append(";");
        for (String e: this.getHistorialEquipas()){
            sb.append(e).append(";");
        }
        return sb.toString();
    }
    
    public GuardaRedes clone () {
        return new GuardaRedes(this);
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GuardaRedes)) return false;
        GuardaRedes jogador = (GuardaRedes) o;
        return this.getVelocidade() == jogador.getVelocidade() && this.getResistencia() == jogador.getResistencia() && this.getDestreza() == jogador.getDestreza() && this.getImpulsao() == jogador.getImpulsao() 
                    && this.getJogodecabeca() == jogador.getJogodecabeca() && this.getRemate() == jogador.getRemate() && this.getPasse() == jogador.getPasse() && this.getElasticidade()== jogador.getElasticidade()
                     && this.getReflexos() == jogador.getReflexos() && this.getAltura() == jogador.getAltura() && this.getHistorialEquipas().equals(jogador.getHistorialEquipas());
    }
    
    /**
     * Funçao que determina a habilidade de um GuardaRedes;
     */
    
    public float habilidadeJogador (){
        float soma = this.getVelocidade()*0.1f;
        soma += this.getResistencia()*0.01f;
        soma += this.getDestreza()*0.16f;
        soma += this.getImpulsao()*0.2f;
        soma += this.getJogodecabeca()*0.01f;
        soma += this.getPasse()*0.05f;
        soma += this.getRemate()*0.01f;
        soma += this.getElasticidade()*0.25f;
        soma += this.getReflexos() * 0.25f;
        if (this.getAltura() >= 185) soma += 5;
        return BigDecimal.valueOf(soma).setScale(2,RoundingMode.HALF_EVEN).floatValue();
    }

    public float calculaHabilidadeGuardaRedes(){
        return this.habilidadeJogador();
    }

    public float calculaHabilidadeDefesa() {
        return 10.0f;
    }
    
    public float calculaHabilidadeMedio() {
        return 10.0f;
    }

    public float calculaHabilidadeAvancado() {
        return 10.0f;
    }
    

    public static GuardaRedes parse(String input) throws AtributoInvalidoException{
        String[] campos = input.split(",");
        Random rand = new Random();
        return new GuardaRedes(Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[9]),
                rand.nextInt(50) + 50,
                rand.nextInt(35) + 175,
                campos[0],
                Integer.parseInt(campos[1]),
                null);
    }
}
