package tpencuestas3

import org.hibernate.mapping.List

class Respuesta {

    Date fechaVoto
    Usuario votante
    Encuesta encuesta
    Map<Pregunta,Opcion> respuestas


    static constraints = {
        fechaVoto (nullable:true)
        votante (nullable:true)
    }
}
