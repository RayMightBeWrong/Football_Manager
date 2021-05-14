package src;

import src.Model.*;
import src.View.*;
import src.Controller.*;


/**
 * Escreva a descrição da classe FootballManager aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class FootballManager
{
    FMModel model;
    FMController controller;
    FMView view;
    
    public static void main (String[] args){
        try {
            String s = "Cristiano Jose Goncalves Neiva Pereira,2,52,55,85,25,63,66,69";
            Avancado j = Avancado.parse(s);
            System.out.println(j.toString());
        }
        catch (AtributoInvalidoException e){
            e.printStackTrace();
        }
        //new FootballManager().run();
    }
    
    /**
     * COnstrutor para objetos da classe FootballManager
     */
    public FootballManager()
    {
        this.model = new FMModel();
        //Ler estado de um ficheiro;
        
        this.controller = new FMController(this.model);
        
        this.view= new FMView(this.controller);
        
        this.model.addObserver(this.controller);
        
        this.controller.addObserver(this.view);
    }
    
    private void run() {
        this.view.run();
    }
}
