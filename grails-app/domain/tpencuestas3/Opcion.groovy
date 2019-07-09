package tpencuestas3

class Opcion {

    Integer orden
    String descripcion

    static belongsTo = [pregunta: Pregunta]

    static constraints = {
        orden(min:1, max:4, unique: true)
    }

}
