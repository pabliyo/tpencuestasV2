package tpencuestas3

class Pregunta {

    String enunciado
    Long orden

    static belongsTo = [encuesta: Encuesta]
    static hasMany = [opciones: Opcion]

    static constraints = {
      // orden (min:1, max:5, unique:true)
        opciones nullable:true
        encuesta nullable:true, editable:false
    }

    static mapping = {
        opciones cascade: "all-delete-orphan", sort: "id"

    }

    String toString(){
        enunciado
    }

}
