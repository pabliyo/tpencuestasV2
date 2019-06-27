package tpencuestas3

class Opcion {

    Integer valor
    String descripcion

    static belongsTo = [pregunta: Pregunta]

    static constraints = {
    }

}
