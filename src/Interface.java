 

import java.util.ArrayList;
import java.util.List;

public class Interface {
    public static void main(String[] args) {
        List<String> equipas = new ArrayList<>();
        Jogador avancado = new Avancado(86,75,92,53,76,65,100,100,185,"Sefo",10, equipas);
        Jogador medio = new Medio(73,86,87,52,40,98,62,62,92,167,"GOD TARA",8, equipas);
        Jogador defesa = new Defesa (74,62,74,85,87,52,37,85,96,190,"LUCAO",4, equipas);
        Jogador guarda = new GuardaRedes (67,54,85,87,23,78,87,84,90,180,"LEITE",1, equipas);
        Jogador lateral = new Lateral (74,32,52,52,62,63,86,98,62,200,"NUNO TAVARES",2, equipas);
        //System.out.println(j.toString());
        Equipa t1 = new Equipa();
        Equipa t2 = new Equipa();
        t2.setName("Benfica");
        t1.setName("Porto");
        t1.setPlayer(avancado);
        t1.setPlayer(medio);
        t1.setPlayer(defesa);
        t1.setPlayer(guarda);
        t1.setPlayer(lateral);
        t1.removePlayer(avancado);
        t2.setPlayer(avancado);
        t1.addTitular(avancado);
        t1.addSuplente(medio);
        System.out.println(t1.toString());
        System.out.println(t2.toString());
    }
}
