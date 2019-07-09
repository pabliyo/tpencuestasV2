package tpencuestas3

class Pregunta {

    String enunciado
    Integer orden

    static belongsTo = [encuesta: Encuesta]
    static hasMany = [opciones: Opcion]


    static constraints = {
       orden (min:1, max:5)
    }

    static mapping = {
        opciones cascade: "all-delete-orphan", sort: "id"
    }


}
