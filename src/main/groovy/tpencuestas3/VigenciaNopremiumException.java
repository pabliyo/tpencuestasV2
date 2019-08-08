package tpencuestas3;

public class VigenciaNopremiumException extends Exception{

    public VigenciaNopremiumException(){
        super("El Usuario no es premium, solo puede tener una VIGENCIA de hasta 30 para la encuesta");
    }

}
