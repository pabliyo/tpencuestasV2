package tpencuestas3;

public class NoRespondioException extends Exception{

    public NoRespondioException(){
        super("Debe elegir 1 opcion para CADA pregunta de la encuesta");
    }
}
