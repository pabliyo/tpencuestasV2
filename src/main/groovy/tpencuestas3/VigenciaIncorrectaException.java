package tpencuestas3;

public class VigenciaIncorrectaException extends Exception{

    public VigenciaIncorrectaException(){
        super("Vigencia incorrecta, la fecha de inicio debera ser mayor a hoy al igual que la fecha de fin");
    }

}