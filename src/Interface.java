 

import java.util.ArrayList;
import java.util.List;

public class Interface {
    public static void main(String[] args) {
        List<String> equipas = new ArrayList<>();
        Jogador avancado = new Avancado(16,15,12,13,16,15,20,20,"Sefo",10, equipas);
        Jogador medio = new Medio(13,16,17,12,10,18,12,12,18,"GOD TARA",8, equipas);
        Jogador defesa = new Defesa (14,12,14,15,17,12,7,15,16,"LUCAO",4, equipas);
        Jogador guarda = new GuardaRedes (13,8,15,16,2,11,12,15,17,"LEITE",1, equipas);
        Jogador lateral = new Lateral (14,2,12,12,12,13,16,18,12,"NUNO TAVARES",2, equipas);
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
