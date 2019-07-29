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
}
