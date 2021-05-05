 


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tatica {
    private int[] formacao;
    private List<Jogador> titulares;
    private List<Jogador> suplentes;
    private boolean add;

    public Tatica(){
        this.formacao = new int[3];
        this.formacao[0] = 4;
        this.formacao[1] = 3;
        this.formacao[2] = 3;
        this.titulares = new ArrayList<Jogador>();
        this.suplentes = new ArrayList<Jogador>();
    }

    public Tatica(int d, int m, int a, List<Jogador> titulares, List<Jogador> suplentes){
        this.formacao = new int[3];
        this.formacao[0] = d;
        this.formacao[1] = m;
        this.formacao[2] = a;
        this.titulares = titulares.stream().map(Jogador::clone).collect(Collectors.toList());
        this.suplentes = suplentes.stream().map(Jogador::clone).collect(Collectors.toList());
    }

    public Tatica(Tatica t){
        this.formacao = t.getFormacao();
        this.titulares = t.getTitulares();
        this.suplentes = t.getSuplentes();
    }

    public int[] getFormacao() {
        return this.formacao.clone();
    }

    public List<Jogador> getTitulares() {
        return this.titulares.stream().map(Jogador::clone).collect(Collectors.toList());
    }

    public List<Jogador> getSuplentes() {
        return this.suplentes.stream().map(Jogador::clone).collect(Collectors.toList());
    }

    public void setFormacao(int[] formacao) {
        this.formacao = formacao.clone();
    }

    public void setTitulares(List<Jogador> titulares) {
        this.titulares = titulares.stream().map(Jogador::clone).collect(Collectors.toList());
    }

    public void setSuplentes(List<Jogador> suplentes) {
        this.suplentes = suplentes.stream().map(Jogador::clone).collect(Collectors.toList());
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Formacao: ");
        sb.append(Arrays.toString(formacao));
        sb.append("\n");
        sb.append("Titulares: [\n");
        for(Jogador j : this.titulares) {
            sb.append(j.toString());
        }
        sb.append("]\n");
        sb.append("Suplentes: [\n");
        for(Jogador j : this.suplentes) {
            sb.append(j.toString());
        }
        sb.append("]\n");
        return sb.toString();
    }

    public Tatica clone(){
        return new Tatica(this);
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if ((o == null) || this.getClass() != o.getClass()) return false;

        Tatica t = (Tatica) o;
        return this.formacao.equals(t.formacao) && this.titulares.equals(t.titulares) && this.suplentes.equals(t.suplentes);
    }

    public void adicionaTitular(Jogador j) {
        this.titulares.add(j.clone());
    }

    public void adicionaSuplente (Jogador j) {
        this.suplentes.add(j.clone());
    }

    public void removeJogadorTatica(Jogador j) {
        if (this.titulares.contains(j)) this.titulares.remove(j);
        if (this.suplentes.contains(j)) this.suplentes.remove(j);
    }
}
