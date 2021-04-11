package src;


public class Interface {
    public static void main(String[] args) {
        Jogador avancado = new Avancado(16,15,12,13,16,15,20,"Sefo",10);
        Jogador medio = new Medio(13,16,17,12,10,18,12,"GOD TARA",8);
        Jogador defesa = new Defesa (14,12,14,15,17,12,7,"LUCAO",4);
        Jogador guarda = new GuardaRedes (13,8,15,16,2,11,12,15,"LEITE",1);
        Jogador lateral = new Lateral (14,2,12,12,12,13,16,"NUNO TAVARES",2);
        //System.out.println(j.toString());
        Equipa t = new Equipa();
        t.setName("Porto");
        t.createTeam();
        t.setPlayer(avancado);
        t.setPlayer(medio);
        t.setPlayer(defesa);
        t.setPlayer(guarda);
        t.setPlayer(lateral);
        System.out.println(t.toString());


    }
}
