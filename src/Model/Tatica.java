package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class Tatica implements Serializable{
    private int[] formacao;
    private Map<Integer,List<Jogador>> tatica;
    private Map<Integer,Jogador> titulares;
    private boolean add;

    public Tatica(){
        this.formacao = new int[4];
        this.formacao[0] = 1;
        this.formacao[1] = 4;
        this.formacao[2] = 4;
        this.formacao[3] = 3;
        this.tatica = new HashMap<Integer, List<Jogador>>();
        this.titulares = new HashMap<Integer, Jogador>();
        this.add = true;
    }

    public Tatica(int d, int m, int a, Map<Integer,List<Jogador>> tatica, Map<Integer,Jogador> titulares){
        this.formacao = new int[4];
        this.formacao[0] = 1;
        this.formacao[1] = d;
        this.formacao[2] = m;
        this.formacao[3] = a;
        this.tatica = tatica.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        this.titulares = titulares.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (this.titulares.values().size() < 11) this.add = true;
        else this.add = false;
    }

    public Tatica(Tatica t){
        this.formacao = t.getFormacao();
        this.tatica = t.getTatica();
        this.titulares = t.getTitulares();
        this.add = t.getAdd();
    }
    
    public boolean getAdd() {
        return this.add;
    }
    
    public int[] getFormacao() {
        return this.formacao.clone();
    }

    public Map<Integer, List<Jogador>> getTatica() {
        return this.tatica.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Jogador> getTitulares() {
        return this.titulares.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setFormacao(int[] formacao) {
        this.formacao = formacao.clone();
    }

    public void setTatica(Map<Integer, List<Jogador>> tatica) {
        this.tatica = tatica.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setTitulares(Map<Integer, Jogador> titulares) {
        this.titulares = titulares.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Formacao: ");
        sb.append(Arrays.toString(formacao));
        sb.append("\n");
        sb.append("Titulares: [\n");
        for(List<Jogador> l: this.tatica.values())
            for (Jogador j : l)
            sb.append(j.toString());
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
        return this.formacao.equals(t.formacao) && this.titulares.equals(t.titulares) && this.tatica.equals(t.tatica);
    }

    public void adicionaTitular(Jogador j,int posicao) throws JogadorExistenteException, TamanhoEquipaException{
        if (this.add) {
            if (this.titulares.containsKey(j.getNumCamisola()))
                throw new JogadorExistenteException("Jogador já está a titular!");
            else {
                if (this.tatica.containsKey(posicao));
                else this.tatica.put(posicao,new ArrayList<>());
                if (this.tatica.get(posicao).size() < this.formacao[posicao]) {
                    this.titulares.put(j.getNumCamisola(),j.clone());
                    this.tatica.get(posicao).add(j.clone());
                }
            else throw new TamanhoEquipaException("A formacao tatica nao esta a ser respeitada!");
            }
            if (this.titulares.values().size() >= 11) this.add = false;
        }
        else throw new TamanhoEquipaException("A equipa ja tem 11 titulares!");
}

    public void substituicao(int in, int out) throws JogadorNaoTitularException, JogadorNaoSuplenteException{
    }

    public void removeTitular(int num) throws JogadorNaoTitularException{
        if (this.titulares.containsKey(num)) {
            this.titulares.remove(num);
            for (List<Jogador> l: this.tatica.values()) {
                Iterator<Jogador> it = l.iterator(); 
                while (it.hasNext()) {
                        Jogador j = it.next();
                        if (j.getNumCamisola() == num) it.remove();
                }
            }
        }
        else throw new JogadorNaoTitularException("Jogador não existe nos titulares!");
    }

}
