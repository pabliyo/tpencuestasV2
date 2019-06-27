package tpencuestas3

class Pregunta {

    String enunciado
    Integer orden

    static hasMany = [opciones: Opcion]
    static belongsTo = [encuesta: Encuesta]


    static constraints = {
    }

    static mapping = {
        opciones cascade: "all-delete-orphan", sort: "id"
    }


}
