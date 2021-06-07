package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Escreva a descri��o da classe Jogo aqui.
 * 
 * @author (seu nome) 
 * @version (n�mero de vers�o ou data)
 */
public class Jogo
{
    public final static int INTERVALO = 45;
    public final static int FIMJOGO = 90;

    // vari�veis de inst�ncia - substitua o exemplo abaixo pelo seu pr�prio
    private Equipa visitado;
    private Equipa visitante;
    private int golosVisitado;
    private int golosVisitante;
    private int minutos;
    private String meteorologia;
    private LocalDate dataJogo;
    
    /**
     * COnstrutor para objetos da classe Jogo
     */
    public Jogo()
    {
        this.visitado = new Equipa();
        this.visitante = new Equipa();
        this.golosVisitado = 0;
        this.golosVisitante = 0;
        this.minutos = 0;
        this.meteorologia = "";
        this.dataJogo = LocalDate.now();
    }

    public Jogo(Equipa visitado, Equipa visitante, int goloVisitado,int goloVisitante,int minutos,String meto,LocalDate data) {
        this.visitado = visitado.clone();
        this.visitante = visitante.clone();
        this.golosVisitado = goloVisitado;
        this.golosVisitante = goloVisitante;
        this.minutos = 0;
        this.meteorologia = meto;
        this.dataJogo = data;
    }

    public Jogo(Jogo j) {
        this.visitado = j.getVisitado();
        this.visitante = j.getVisitante();
        this.golosVisitado = j.getGolosVisitado();
        this.golosVisitante = j.getGolosVisitante();
        this.minutos = j.getMinutos();
        this.meteorologia = j.getMeteorologia();
        this.dataJogo = j.getData();
    }
    
    public Equipa getVisitado() {
        return this.visitado.clone();
    }

    public Equipa getVisitante() {
        return this.visitante.clone();
    }

    public int getGolosVisitado() {
        return this.golosVisitado;
    }

    public int getGolosVisitante() {
        return this.golosVisitante;
    }

    public int getMinutos() {
        return this.minutos;
    }

    public String getMeteorologia() {
        return this.meteorologia;
    }

    public LocalDate getData() {
        return this.dataJogo;
    }

    public void setVisitado(Equipa e) {
        this.visitado = e.clone();
    }

    public void setVisitante(Equipa e) {
        this.visitante = e.clone();
    }

    public void setGoloVisitado(int golo) {
        this.golosVisitado = golo;
    }

    public void setGoloVisitante (int golo){
        this.golosVisitante =golo;
    }

    public void setMinutos (int min) {
        this.minutos = min;
    }

    public void setMeteorologia (String meto) {
        this.meteorologia = meto;
    }

    public void setData (LocalDate data) {
        this.dataJogo = data;
    }

    public Jogo clone() {
        return new Jogo(this);
    }
    
    public int simulaAtaque (float ataque, float defesa) {
        float diferenca = ataque - defesa;
        Random rand = new Random();
        double probabilidade = rand.nextDouble();
        int resultado = 0;
        if (diferenca > 0) {
            if (probabilidade < diferenca/(20*100)) resultado = 1;
            else if (probabilidade < (ataque/defesa)/5) resultado = 0;
            else resultado = -1;
        }
        else {
            if (probabilidade < (defesa/ataque)/2) resultado = -1;
            else if (probabilidade <  (1- ((defesa/ataque)/2))/100) resultado = 1;
            else resultado = 0;
        }
        return resultado;
    }

    public String toString () {
        StringBuilder sb = new StringBuilder();
        sb.append(this.dataJogo.toString());
        sb.append("Equipa da casa:").append(this.visitado.getName()).append("\n");
        sb.append("Equipa de fora:").append(this.visitante.getName()).append("\n");
        sb.append("Placard:").append(this.golosVisitado).append(" - ").append(this.golosVisitante).append("\n");
        sb.append("Minuto:").append(this.getMinutos()).append("\n");
        return sb.toString();
    }

    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Jogo)) return false;
        Jogo jogo = (Jogo) o;
        return this.getGolosVisitado() == jogo.getGolosVisitado() && this.getGolosVisitante() == jogo.getGolosVisitante() && this.getMinutos() == jogo.getMinutos()
                    && this.getMeteorologia().equals(jogo.getMeteorologia()) && this.getVisitado().equals(jogo.getVisitado()) && this.getVisitante().equals(jogo.getVisitante())
                    && this.getData().equals(jogo.getData());
    }

    
    
}
