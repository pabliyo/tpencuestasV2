package tpencuestas3;

public class NoPremiumException extends Exception{

    public NoPremiumException(){
        super("El Usuario no es premium, no puede realizar la accion solicitada");
    }

}

