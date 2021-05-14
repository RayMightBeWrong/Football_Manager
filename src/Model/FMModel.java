package src.Model;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.*;
 
/**
 * Escreva a descri��o da classe FootballManager aqui.
 * 
 * @author (seu nome) 
 * @version (n�mero de vers�o ou data)
 */
public class FMModel extends Observable
{
    private Map<String,Jogador> jogadores ;
    private Map<String,Equipa> equipas;
    private String valueFromModel;

    /**
     * COnstrutor para objetos da classe FMModel
     */
    public FMModel()
    {
        this.jogadores = new HashMap<>();
        this.equipas = new HashMap<>();
    }

    public FMModel(Map<String,Jogador> jog,Map<String,Equipa> equipas) {
        this.jogadores = jog.values().stream().collect(Collectors.toMap(j->j.getNome(),j->j.clone()));
        this.equipas = equipas.values().stream().collect(Collectors.toMap(e->e.getName(),e->e.clone()));
    }

    public List<Jogador> getListJogador() {
       return this.jogadores.values().stream().map(Jogador::clone).collect(Collectors.toList());
    }

    public List<Equipa> getListEquipa() {
        return this.equipas.values().stream().map(Equipa::clone).collect(Collectors.toList());
     }

     public void addJogador (Jogador j) throws JogadorExistenteException{
        if (!(this.jogadores.containsKey(j.getNome()))) {
            this.jogadores.putIfAbsent(j.getNome(),j.clone());
            valueFromModel = "Sucesso na criacao";
            this.setChanged();
            this.notifyObservers(valueFromModel);
        }
        else throw new JogadorExistenteException("Jogador já existente!");
     }

     public void addJogadorFromText (List<String> args) {
        int posicao = Integer.parseInt(args.get(0));
        String nome = args.get(1);
        int altura = Integer.parseInt(args.get(2));
        List<Integer> atributos = new ArrayList<>();
        for (int i = 3;i< args.size();i++)
            atributos.add(Integer.parseInt(args.get(i)));
        switch(posicao) {
        case 1: try {
                this.addJogador(new GuardaRedes(atributos.get(0),atributos.get(1),atributos.get(2),atributos.get(3),atributos.get(4),atributos.get(5),atributos.get(6),atributos.get(7),atributos.get(8),altura,nome,1,new ArrayList<>()));
            } catch (JogadorExistenteException | AtributoInvalidoException e) {
                this.valueFromModel = e.getMessage();
            }
            break;
        case 2: try {
                this.addJogador(new Defesa(atributos.get(0),atributos.get(1),atributos.get(2),atributos.get(3),atributos.get(4),atributos.get(5),atributos.get(6),atributos.get(7),atributos.get(8),altura,nome,4,new ArrayList<>()));
            } catch (JogadorExistenteException | AtributoInvalidoException e) {
                this.valueFromModel = e.getMessage();
            }
            break;
        case 3: try {
                this.addJogador(new Lateral(atributos.get(0),atributos.get(1),atributos.get(2),atributos.get(3),atributos.get(4),atributos.get(5),atributos.get(6),atributos.get(7),atributos.get(8),altura,nome,2,new ArrayList<>()));
            } catch (JogadorExistenteException | AtributoInvalidoException e) {
                this.valueFromModel = e.getMessage();
            }
            break;
        case 4: try {
                this.addJogador(new Medio(atributos.get(0),atributos.get(1),atributos.get(2),atributos.get(3),atributos.get(4),atributos.get(5),atributos.get(6),atributos.get(7),atributos.get(8),altura,nome,8,new ArrayList<>()));
            } catch (JogadorExistenteException | AtributoInvalidoException e) {
                this.valueFromModel = e.getMessage();
            }
            break;
        case 5: try {
                this.addJogador(new Avancado(atributos.get(0),atributos.get(1),atributos.get(2),atributos.get(3),atributos.get(4),atributos.get(5),atributos.get(6),atributos.get(7),altura,nome,9,new ArrayList<>()));
            } catch (JogadorExistenteException | AtributoInvalidoException e) {
                this.valueFromModel = e.getMessage();
            }
            break;
    }
         this.setChanged();
         this.notifyObservers(valueFromModel);
     }

     public void addEquipa (String nome) {
        if (!(this.equipas.containsKey(nome))) {
            Equipa e = new Equipa(nome);
            this.equipas.putIfAbsent(nome,e.clone());
            valueFromModel = "Equipa " + nome + " adicionada"; 
        }
        else valueFromModel = "Erro na criacao da equipa";
        this.setChanged();
        this.notifyObservers(valueFromModel);
     }

     public void removerEquipa (String nome) {
        if ((this.equipas.containsKey(nome))) {
            this.equipas.remove(nome);
            valueFromModel = "Equipa " + nome + " removida"; 
        }
        else valueFromModel = "Erro na remocao da equipa";
        this.setChanged();
        this.notifyObservers(valueFromModel);
     }

     public Jogador getJogador(String nome) throws JogadorNaoExistenteException {
         if (this.jogadores.containsKey(nome)) {
            valueFromModel = this.jogadores.get(nome).toString();
            this.setChanged();
            this.notifyObservers(valueFromModel);
            return this.jogadores.get(nome);
         }
        else throw new JogadorNaoExistenteException("Jogador nao existe!");
     }

     public void removerJogador(String nome) throws JogadorNaoExistenteException {
        if (this.jogadores.containsKey(nome)) {
            this.jogadores.remove(nome);
            valueFromModel = "Jogador "+nome+ " removido." ;
            this.setChanged();
            this.notifyObservers(valueFromModel);
         }
        else throw new JogadorNaoExistenteException("Jogador nao existe!");

     }

     public double calculaHabilidadeJogador(String nome)  throws JogadorNaoExistenteException {
        if (this.jogadores.containsKey(nome)) {
            Jogador j = this.jogadores.get(nome);
            valueFromModel = "Habilidade do " + nome + " : " + j.habilidadeJogador();
            this.setChanged();
            this.notifyObservers(valueFromModel);
            return j.habilidadeJogador();
         }
        else throw new JogadorNaoExistenteException("Jogador nao existe!");
     }

     public void listarEquipas() {
        valueFromModel ="";
        for (Equipa e: this.equipas.values())
                valueFromModel += e.toString(); 
        this.setChanged();
        this.notifyObservers(valueFromModel);
     }

    public void addJogadorEquipa(List<String> args){
        String nomeJ = args.get(0);
        String nomeE = args.get(1);
        if (this.jogadores.containsKey(nomeJ)) {
            Jogador j = this.jogadores.get(nomeJ);
            if (this.equipas.containsKey(nomeE)) {
                try
                {
                    this.equipas.get(nomeE).addPlayer(j);
                    valueFromModel = "Sucesso ao adicionar o jogador a equipa";
                }
                catch (NumeroExistenteException | JogadorExistenteException e)
                {
                    valueFromModel = e.getMessage();
                }
            }
            else valueFromModel = "Equipa inexistente";
        }
        else valueFromModel = "Jogador nao existe";
        this.setChanged();
        this.notifyObservers(valueFromModel);
}

}
