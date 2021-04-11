package src;


/**
 * Write a description of class Avancado here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuardaRedes extends Jogador
{
    // instance variables - replace the example below with your own
    private int elasticidade;
    
    /**
     * Construtor vazia da classe GuardaRedes; 
     */
    public GuardaRedes()
    {
       super();
       this.elasticidade = 0;
    }
    
    /**
     * Constructor parametrizado da classe GuardaRedes;
     */
    
    public GuardaRedes (int velocidade, int resistencia, int destreza, int impulsao,int jogodecabeca, int passe, int remate,int elasticidade, String nome, int numCamisola){
        super(velocidade,resistencia,destreza,impulsao,jogodecabeca,passe,remate,nome,numCamisola);
        this.elasticidade = elasticidade;
    }
    
    /**
     * Construtor copia da classe GuardaRedes;
     */
    
    public GuardaRedes (GuardaRedes copia){
        super(copia);
        this.elasticidade = copia.getElasticidade();
    }
    
    public int getElasticidade() {
        return this.elasticidade;
    }
    
    public void setElasticidade(int elasticidade) {
        this.elasticidade = elasticidade;
    }
    
    public String toString (){
        return this.getNome()+"{\n" +
                "Posicao = Guarda-Redes,Habilidade =" + this.habilidadeJogador() +
                ",\n velocidade=" + this.getVelocidade() +
                ",\n resistencia=" + this.getResistencia() +
                ",\n destreza=" + this.getDestreza() +
                ",\n impulsao=" + this.getImpulsao() +
                ",\n jogodecabeca=" + this.getJogodecabeca() +
                ",\n remate=" + this.getRemate() +
                ",\n passe=" + this.getPasse() +
                ",\n elasticidade= " +this.getElasticidade() +
                ",\n numCamisola=" + this.getNumCamisola() +
                "\n}";
    }
    
    public GuardaRedes clone () {
        return new GuardaRedes(this);
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GuardaRedes)) return false;
        GuardaRedes jogador = (GuardaRedes) o;
        return this.getVelocidade() == jogador.getVelocidade() && this.getResistencia() == jogador.getResistencia() && this.getDestreza() == jogador.getDestreza() && this.getImpulsao() == jogador.getImpulsao() && this.getJogodecabeca() == jogador.getJogodecabeca() && this.getRemate() == jogador.getRemate() && this.getPasse() == jogador.getPasse() && this.getElasticidade()== jogador.getElasticidade();
    }
    
    /**
     * Funçao que determina a habilidade de um GuardaRedes;
     */
    
    public float habilidadeJogador (){
        float soma = this.getVelocidade()*0.1f;
        soma += this.getResistencia()*0.01f;
        soma += this.getDestreza()*0.3f;
        soma += this.getImpulsao()*0.25f;
        soma += this.getJogodecabeca()*0.01f;
        soma += this.getPasse()*0.1f;
        soma += this.getRemate()*0.01f;
        soma += this.getElasticidade()*0.31f;
        return soma;
    }
}
