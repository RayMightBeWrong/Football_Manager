package Model;

 
import java.math.BigDecimal;
import java.util.List;
import java.math.RoundingMode;
import java.util.Random;

/* Write a description of class Avancado here.
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

    public Medio(int velocidade, int resistencia, int destreza, int impulsao, int jogodecabeca, int passe
            , int remate, int recBola, int criatividade,int altura, String nome, int numCamisola, List<String> historialEquipas)  throws AtributoInvalidoException{
        super(velocidade, resistencia, destreza, impulsao, jogodecabeca, passe, remate, altura,nome, numCamisola, historialEquipas);
        setRecBola(recBola);
        setCriatividade(criatividade);
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

    public void setRecBola(int rec) throws AtributoInvalidoException {
        if (rec >= 0 && rec <= 100) this.recBola = rec;
        else throw new AtributoInvalidoException("Atributo recuperacao de bola inserido invalido!");
    }

    public void setCriatividade(int cri) throws AtributoInvalidoException {
        if (cri >= 0 && cri <= 100) this.criatividade = cri;
        else throw new AtributoInvalidoException("Atributo criatividade inserido invalido!");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(this.getCriatividade()).append(";");
        sb.append(this.getRecBola()).append(";");
        for (String e: this.getHistorialEquipas()){
            sb.append(e).append(";");
        }
        return sb.toString();
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
        return BigDecimal.valueOf(soma).setScale(2,RoundingMode.HALF_EVEN).floatValue();
    }

     public static Medio parse(String input) throws AtributoInvalidoException{
         String[] campos = input.split(",");
         Random rand = new Random();
         return new Medio(Integer.parseInt(campos[2]),
                 Integer.parseInt(campos[3]),
                 Integer.parseInt(campos[4]),
                 Integer.parseInt(campos[5]),
                 Integer.parseInt(campos[6]),
                 Integer.parseInt(campos[8]),
                 Integer.parseInt(campos[7]),
                 Integer.parseInt(campos[9]),
                 rand.nextInt(60) + 40,
                 rand.nextInt(37) + 158,
                 campos[0],
                 Integer.parseInt(campos[1]),
                 null);
     }
}