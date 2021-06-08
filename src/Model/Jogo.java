package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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
    private LocalDate dataJogo;
    Map<Integer, Integer> subsCasa = new HashMap<>();
    Map<Integer, Integer> subsFora = new HashMap<>();
    
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
        this.dataJogo = LocalDate.now();
        this.subsCasa = new HashMap<>();
        this.subsFora = new HashMap<>();
    }

    public Jogo(Equipa visitado, Equipa visitante, int goloVisitado, int goloVisitante, int minutos, LocalDate data){
        this.visitado = visitado.clone();
        this.visitante = visitante.clone();
        this.golosVisitado = goloVisitado;
        this.golosVisitante = goloVisitante;
        this.minutos = 0;
        this.dataJogo = data;
        this.subsCasa = new HashMap<>();
        this.subsFora = new HashMap<>();
    }

    public Jogo(Equipa visitado, Equipa visitante, int goloVisitado,int goloVisitante,int minutos,LocalDate data, Map<Integer, Integer> subsCasa, Map<Integer, Integer> subsFora) {
        this.visitado = visitado.clone();
        this.visitante = visitante.clone();
        this.golosVisitado = goloVisitado;
        this.golosVisitante = goloVisitante;
        this.minutos = 0;
        this.dataJogo = data;
        this.subsCasa = new HashMap<>(subsCasa);
        this.subsFora = new HashMap<>(subsFora);
    }

    public Jogo(Jogo j) {
        this.visitado = j.getVisitado();
        this.visitante = j.getVisitante();
        this.golosVisitado = j.getGolosVisitado();
        this.golosVisitante = j.getGolosVisitante();
        this.minutos = j.getMinutos();
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

    public LocalDate getData() {
        return this.dataJogo;
    }

    public Map<Integer, Integer> getSubsCasa(){
        return this.subsCasa;
    }

    public Map<Integer, Integer> getSubsFora(){
        return this.subsFora;
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

    public void setData (LocalDate data) {
        this.dataJogo = data;
    }

    public void setSubsCasa(Map<Integer, Integer> subsCasa){
        this.subsCasa = new HashMap<>(subsCasa);
    }

    public void setSubsFora(Map<Integer, Integer> subsFora){
        this.subsFora = new HashMap<>(subsFora);
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

    public static Jogo parse(String input, Map<String, Equipa> equipas) throws EquipaNaoExistenteException{
        String[] campos = input.split(",");
        Equipa casa = equipas.get(campos[0]);
        Equipa fora = equipas.get(campos[1]);
        if (casa == null)
            throw new EquipaNaoExistenteException(campos[0] + " não consta na lista de equipas.");
        if(fora == null)
            throw new EquipaNaoExistenteException(campos[0] + " não consta na lista de equipas.");
        String[] data = campos[4].split("-");
        List<Integer> jc = new ArrayList<>();
        List<Integer> jf = new ArrayList<>();
        Map<Integer, Integer> subsC = new HashMap<>();
        Map<Integer, Integer> subsF = new HashMap<>();
        for (int i = 5; i < 16; i++){
            jc.add(Integer.parseInt(campos[i]));
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            subsC.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        for (int i = 19; i < 30; i++){
            jf.add(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            subsF.put(Integer.parseInt(sub[0]), Integer.parseInt(sub[1]));
        }
        //public Jogo(Equipa visitado, Equipa visitante, int goloVisitado,int goloVisitante,int minutos,LocalDate data, subs1, subs2)
        return new Jogo(casa, fora, Integer.parseInt(campos[2]), Integer.parseInt(campos[3]), FIMJOGO,
                        LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])), subsC, subsF);
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
                    && this.getVisitado().equals(jogo.getVisitado()) && this.getVisitante().equals(jogo.getVisitante())
                    && this.getData().equals(jogo.getData()) && this.getSubsCasa().equals(jogo.getSubsCasa()) && this.getSubsFora().equals(jogo.getSubsFora());
    }

    
    
}
