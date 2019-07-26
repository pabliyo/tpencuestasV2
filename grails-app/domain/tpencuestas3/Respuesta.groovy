package tpencuestas3

class Respuesta {

    Date fechaVoto
    Usuario votante
    Encuesta encuesta
    Map<Pregunta,Opcion> respuestas

    static constraints = {
        fechaVoto (nullable:true)
        votante (nullable:true)
    }

    static List<Respuesta> crearListaDeRespuestas(Encuesta encuesta){
        def respuestas = new ArrayList<Respuesta>()
        encuesta.getPreguntas().size().times {
            respuestas.add(new Respuesta())
            //todo inicializar correctamente Respuesta
        }
        respuestas
    }
}
