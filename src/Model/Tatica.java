package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class Tatica implements Serializable{
    private int[] formacao;
    private Map<Integer,List<Jogador>> titulares;
    private Map<Integer,Jogador> suplentes;
    private boolean add;

    public Tatica(){
        this.formacao = new int[4];
        this.formacao[0] = 1;
        this.formacao[1] = 4;
        this.formacao[2] = 4;
        this.formacao[3] = 3;
        this.titulares = new HashMap<Integer, List<Jogador>>();
        this.suplentes = new HashMap<Integer, Jogador>();
    }

    public Tatica(int d, int m, int a, Map<Integer,List<Jogador>> titulares, Map<Integer,Jogador> suplentes){
        this.formacao = new int[4];
        this.formacao[0] = 1;
        this.formacao[1] = d;
        this.formacao[2] = m;
        this.formacao[3] = a;
        this.titulares = titulares.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        this.suplentes = suplentes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Tatica(Tatica t){
        this.formacao = t.getFormacao();
        this.titulares = t.getTitulares();
        this.suplentes = t.getSuplentes();
    }

    public int[] getFormacao() {
        return this.formacao.clone();
    }

    public Map<Integer, List<Jogador>> getTitulares() {
        return this.titulares.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Jogador> getSuplentes() {
        return this.suplentes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setFormacao(int[] formacao) {
        this.formacao = formacao.clone();
    }

    public void setTitulares(Map<Integer, List<Jogador>> titulares) {
        this.titulares = titulares.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setSuplentes(Map<Integer, Jogador> suplentes) {
        this.suplentes = suplentes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Formacao: ");
        sb.append(Arrays.toString(formacao));
        sb.append("\n");
        sb.append("Titulares: [\n");
        for(List<Jogador> l: this.titulares.values())
            for (Jogador j : l)
            sb.append(j.toString());
        sb.append("]\n");
        sb.append("Suplentes: [\n");
        for(Jogador j : this.suplentes.values()) {
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

    public void adicionaTitular(Jogador j,int posicao) {
        if (this.titulares.containsKey(posicao));
        else {
            this.titulares.put(posicao,new ArrayList<Jogador>());
        }
        this.titulares.get(posicao).add(j.clone());
    }

    public void adicionaSuplente (Jogador j) {
        this.suplentes.putIfAbsent(j.getNumCamisola(), j.clone());
    }

    public void substituicao(int in, int out) throws JogadorNaoTitularException, JogadorNaoSuplenteException{
    }

    public void removeTitular(int j) throws JogadorNaoTitularException{
        if (this.titulares.containsKey(j)) this.titulares.remove(j);
        else throw new JogadorNaoTitularException("Jogador não existe nos titulares!");
    }

    public void removeSuplente(int j) throws JogadorNaoSuplenteException{
        if (this.suplentes.containsKey(j)) this.suplentes.remove(j);
        else throw new JogadorNaoSuplenteException("Jogador não existe nos suplentes!");
    }

    public void removeJogadorTatica(Jogador j) {
        if (this.titulares.containsKey(j.getNumCamisola())) this.titulares.remove(j.getNumCamisola());
        if (this.suplentes.containsKey(j.getNumCamisola())) this.suplentes.remove(j.getNumCamisola());
    }
}
