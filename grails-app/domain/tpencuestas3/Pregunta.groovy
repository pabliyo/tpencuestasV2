package tpencuestas3

class Pregunta {

    String enunciado
    Integer orden

    static belongsTo = [encuesta: Encuesta]
    static hasMany = [opciones: Opcion]

    static constraints = {
       orden (min:1, max:5, unique:true)
    }

    static mapping = {

    }

    String toString(){
        enunciado
    }

}
