package tpencuestas3

class Respuesta {

    Date fechaVoto
    Usuario votante
    Encuesta encuesta
    def respuestas = [:] //mapa <k,v> inicializado vacio

    static constraints = {
        fechaVoto nullable: true
        respuestas nullable: true
    }

    protected void agregarRespuesta(Pregunta pregunta, Opcion opcion){
        respuestas.put(pregunta, opcion)
    }

    protected void agregarFechaVotacion(){
        fechaVoto = new Date()
    }

}
