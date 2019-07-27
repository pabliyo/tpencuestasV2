package tpencuestas3

import org.hibernate.mapping.Map

class Respuesta {

    Date fechaVoto
    Usuario votante
    Encuesta encuesta
    def respuestas = [:] //mapa <k,v> inicializado vacio

    static constraints = {
        fechaVoto (nullable:true)
        respuestas (nullable:true)
    }



}
