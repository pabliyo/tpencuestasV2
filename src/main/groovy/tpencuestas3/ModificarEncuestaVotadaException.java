package tpencuestas3;

public class ModificarEncuestaVotadaException extends Exception {

        public ModificarEncuestaVotadaException() { super("Esta encuesta ya recibio votaciones, NO se puede modificar"); }

}
