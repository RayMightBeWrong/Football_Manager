package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
         case 1: adicionaJogador(args);break;
         case 2: listarJogadoresFree();break;
         case 3: moverJogadorEquipa(args);break;
         case 4: calcularHabilidadeJogador(args.get(0));break;
         case 5: adicionarEquipa(args.get(0));break;
         case 6: listarEquipa(args.get(0));break;
         case 7: listarEquipas();break;
         case 8: calcularHabilidadeEquipa(args.get(0));break;
         case 9: criarJogo(args);break;
         case 10: listarJogos();break;
         case 11: simulaJogo(args);break;
         case 12: adicionarJogadorTitular(args);break;
         case 13: listarTitularesEquipa(args);break;
         case 14: removerTitular(args);break;
         case 15: listarNaoTitulares (args);break;
         case 16: imprimirEquipa(args);break;
         case 17: listarJogosSimulaveis();break;
         case 18: adicionarSubstituicao(args);break;
         case 19: carregarFicheiroTexto(args);break;
     }
   }


private void adicionaJogador (List<String> args) {
       this.model.addJogadorFromText(args);
       this.setChanged();
       this.notifyObservers(valueFromModel);
    }

    
    private void listarJogadoresFree() {
        this.model.listarJogadoresFree();
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    private void calcularHabilidadeJogador(String nome) {
        this.model.calculaHabilidadeJogador(nome);
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    private void moverJogadorEquipa(List<String> args) {
        this.model.addJogadorEquipa(args);
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    private void adicionarEquipa (String nome) {
        this.model.addEquipa(nome);
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }
    
    private void listarEquipa(String nome) {
        this.model.listarEquipa(nome);
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }
   
    private void listarEquipas() {
        this.model.listarEquipas();
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    private void calcularHabilidadeEquipa(String nome) {
        this.model.calcularHabilidadeEquipa(nome);
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }
    
    private void adicionarJogadorTitular (List<String> args) {
        this.model.addJogadorTitular(args);
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }
    
    private void listarTitularesEquipa(List<String> args) {
        int id = Integer.parseInt(args.get(0));
        int eq = Integer.parseInt(args.get(1));
        this.model.listarTitularesEquipa(id,eq);
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }
    
    private void listarNaoTitulares(List<String> args) {
        try {
            int id = Integer.parseInt(args.get(0));
            int eq = Integer.parseInt(args.get(1));
            this.model.listarNaoTitulares(id,eq);
     } catch (EquipaNaoExistenteException e) {
         valueFromModel = e.getMessage();
     }
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }
   
    private void removerTitular(List<String> args) {
            int id = Integer.parseInt(args.get(0));
            int eq = Integer.parseInt(args.get(1));
            int num = Integer.parseInt(args.get(2));
            this.model.removerTitular(id,eq,num);
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    
    private void criarJogo(List<String> args) {
        String e1 = args.get(0);
        String e2 = args.get(1);
        try{
            LocalDate data = LocalDate.parse(args.get(2));
            this.model.addJogo(e1,e2,data);
        }
        catch (DateTimeParseException e) {
            valueFromModel = "Data inserida incorretamente";
        }
        catch (EquipaNaoExistenteException ex) {
            valueFromModel = ex.getMessage();
        }
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    private void listarJogos() {
        this.model.listarJogos();
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    private void simulaJogo(List <String> args) {
        int id = Integer.parseInt(args.get(0));
        this.model.simulaJogo(id);
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    private void imprimirEquipa(List<String> args){
        int id = Integer.parseInt(args.get(0));
        int equipa = Integer.parseInt(args.get(1));
        this.model.imprimirEquipa(id, equipa);
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    private void listarJogosSimulaveis() {
        this.model.listarJogosSimulaveis();
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    private void adicionarSubstituicao (List <String> args) {
        int id = Integer.parseInt(args.get(0));
        int equipa = Integer.parseInt(args.get(1));
        int entrada = Integer.parseInt(args.get(2));
        int saida = Integer.parseInt(args.get(3));
        this.model.adicionarSubstituicao(id,equipa,entrada,saida);        
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }

    private void carregarFicheiroTexto(List<String> args){
        String fich = args.get(0);
        try{
            this.model = Parser.parse(fich);
        }
        catch(Exception e){
            valueFromModel = e.getMessage();
        }
        this.setChanged();
        this.notifyObservers(valueFromModel);
    }
}
