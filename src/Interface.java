 

import java.util.ArrayList;
import java.util.List;

public class Interface {
    public static void main(String[] args) {
        List<String> equipas = new ArrayList<>();
        Jogador avancado;
        Jogador medio;
        Jogador defesa;
        Jogador guarda;
        Jogador lateral;
        Equipa t1 = new Equipa();
        Equipa t2 = new Equipa();
        t2.setName("Benfica");
        t1.setName("Porto");
        try {
            avancado = new Avancado(86,75,92,53,76,65,100,100,185,"Sefo",10, equipas);
            t1.addPlayer(avancado);
            t1.removePlayer(avancado);
            t2.addPlayer(avancado);
            t2.addTitular(avancado);
        } catch (AtributoInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (NumeroExistenteException e) {
            System.out.println(e.getMessage());
        } catch (JogadorExistenteException e) {
            System.out.println(e.getMessage());
        }
        try {
            medio = new Medio(73,86,87,52,40,98,62,62,92,167,"GOD TARA",10, equipas);
            t1.addPlayer(medio);
            t1.addSuplente(medio);
        } catch (AtributoInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (NumeroExistenteException e) {
            System.out.println(e.getMessage());
        } catch (JogadorExistenteException e) {
            System.out.println(e.getMessage());
        }
        try {
            defesa = new Defesa (74,62,74,85,87,52,37,85,96,190,"LUCAO",4, equipas);
            t1.addPlayer(defesa);
        } catch (AtributoInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (NumeroExistenteException e) {
            System.out.println(e.getMessage());
        } catch (JogadorExistenteException e) {
            System.out.println(e.getMessage());
        }
        try {
            guarda = new GuardaRedes (67,54,85,87,23,78,87,84,90,180,"LEITE",1, equipas);
            t1.addPlayer(guarda);
        } catch (AtributoInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (NumeroExistenteException e) {
            System.out.println(e.getMessage());
        } catch (JogadorExistenteException e) {
            System.out.println(e.getMessage());
        }
        try {
            lateral = new Lateral (74,32,52,52,62,63,86,98,62,200,"NUNO TAVARES",2, equipas);
            t1.addPlayer(lateral);
        } catch (AtributoInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (NumeroExistenteException e) {
            System.out.println(e.getMessage());
        } catch (JogadorExistenteException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(t1.toString());
        System.out.println(t2.toString());
    }
}
