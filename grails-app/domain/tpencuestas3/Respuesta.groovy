package tpencuestas3

class Respuesta {

    Date fechaVoto
    Usuario votante
    Encuesta encuesta
    List<Opcion> respuestas

    static constraints = {
        fechaVoto nullable: true
        respuestas nullable: true
    }

    protected void initCollection() {
        respuestas = []
    }

    protected void agregarRespuesta(Opcion opcion) {
        respuestas.add(opcion)
    }

    protected void agregarFechaVotacion() {
        fechaVoto = new Date()
    }

}
