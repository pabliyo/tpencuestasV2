package tpencuestas3;

public class EdicionEncuestaVotadaException extends Exception {

    public EdicionEncuestaVotadaException() { super("Esta encuesta ya recibio votaciones, NO se puede modificar"); }

}