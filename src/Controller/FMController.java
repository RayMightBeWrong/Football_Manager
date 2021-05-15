package Controller;

import java.util.*;
import Model.*;

/**
 * Escreva a descrição da classe FMController aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class FMController extends Observable implements Observer
{
   private int comando;
   private String valueFromModel;
   private FMModel model;
    
   public void update (Observable ob,Object value) {
       this.valueFromModel = (String) value; 
   }
    
   public FMController() {
       comando = 0;
   }

   public FMController(FMModel model){
       this.comando = 0;
       this.model = model;
   }

   public int getComando() {
        return this.comando;
   }

   public void setComando(int comando){
       this.comando = comando;
   }

   public void processaComando(List<String> args) {
     switch (this.getComando()) {
         case 1 : adicionaJogador(args);break;
         case 2: pesquisarJogador(args.get(0));break;
         case 3: removerJogador(args.get(0));break;
         case 4: calculaHabilidadeJogador(args.get(0));break;
         case 5: adicionarEquipa(args.get(0));break;
         case 6: listarEquipas();break;
         case 7: removerEquipa(args.get(0));break;
         case 8: adicionarJogadorEquipa(args);break;
     }
   }

   private void adicionaJogador (List<String> args) {
        this.model.addJogadorFromText(args);
        this.setChanged();
        this.notifyObservers(valueFromModel);
}

private void pesquisarJogador(String nome){
        try {
            this.model.getJogador(nome);
        }
        catch (JogadorNaoExistenteException e) {
            valueFromModel = e.getMessage();
        }
        this.setChanged();
        this.notifyObservers(valueFromModel);
}

private void removerJogador(String nome) {
    try{
        this.model.removerJogador(nome);
    }
    catch (JogadorNaoExistenteException e) {
        valueFromModel = e.getMessage();
    }
    this.setChanged();
    this.notifyObservers(valueFromModel);
}

private void calculaHabilidadeJogador(String nome) {
    try {
        this.model.calculaHabilidadeJogador(nome);
    }
    catch (JogadorNaoExistenteException e) {
        valueFromModel = e.getMessage();
    }
    this.setChanged();
    this.notifyObservers(valueFromModel);
}

private void adicionarEquipa (String nome) {
    this.model.addEquipa(nome);
    this.setChanged();
    this.notifyObservers(valueFromModel);
}

private void listarEquipas() {
    this.model.listarEquipas();
    this.setChanged();
    this.notifyObservers(valueFromModel);
}

private void removerEquipa (String nome) {
    this.model.removerEquipa(nome);
    this.setChanged();
    this.notifyObservers(valueFromModel);
}

private void adicionarJogadorEquipa(List<String> args) {
    this.model.addJogadorEquipa(args);
    this.setChanged();
    this.notifyObservers(valueFromModel);
}

}
