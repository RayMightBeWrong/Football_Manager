 

import Model.*;
import View.*;
import Controller.*;
import java.io.*;


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
        
        new FootballManager().run();
    }
    
    /**
     * COnstrutor para objetos da classe FootballManager
     */
    public FootballManager()
    {
        this.model = new FMModel();
        try
        {
        this.model = FMModel.carregarEstado("estado.dat");
        }
        catch (Exception fnfe)
        {
            System.out.println("Erro ao carregar");
        }
        //Ler estado de um ficheiro;
        this.controller = new FMController(this.model);
        
        this.view = new FMView(this.controller);
        
        this.model.addObserver(this.controller);
        
        this.controller.addObserver(this.view);
    }
    
    private void run() {
        try{
            this.model = Parser.parse();
        }
        catch(LinhaIncorretaException lie){
            System.out.println("Erro: " + lie.getMessage());
        }
        catch(NumeroExistenteException nee){
            System.out.println("Erro: " + nee.getMessage());
        }
        catch(JogadorExistenteException jee){
            System.out.println("Erro: " + jee.getMessage());
        }
        catch(AtributoInvalidoException aie){
            System.out.println("Erro: " + aie.getMessage());
        }
        catch(EquipaNaoExistenteException enee){
            System.out.println("Erro: " + enee.getMessage());
        }

        System.out.println(this.model.getListJogador().size());

        this.view.run();
        try
        {
            this.model.escreverFicheiroTexto("output.txt");
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            System.out.println("Ficheiro errado!");
        }
        try
        {
        this.model.guardarEstado("estado.dat");
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
        }
        catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
}
