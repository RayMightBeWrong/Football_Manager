package Model;

public class AtributoInvalidoException extends Exception
{
    /**
     * COnstrutor para objetos da classe AtributoInvalidoException
     */
    public AtributoInvalidoException()
    {
        super();
    }

    public AtributoInvalidoException(String msg) {
         super(msg);   
    }
}
