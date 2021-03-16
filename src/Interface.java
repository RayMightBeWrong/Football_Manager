
public class Interface {
    public static void main(String[] args) {
        Jogador j = new Jogador(20,20,20,20,20,20,20,"Avançado","Ronaldo",7);
        //System.out.println(j.toString());
        Equipa t = new Equipa();
        t.setName("Porto");
        t.setNumJogadores(3);
        t.createTeam();
        t.setPlayer(j);
        System.out.println(t.toString());
        Jogador roni = new Jogador();


    }
}
