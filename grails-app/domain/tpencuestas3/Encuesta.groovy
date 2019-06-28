package tpencuestas3

class Encuesta {

    String titulo
    String descripcion
    Vigencia vigencia
    byte[] imagen

    static hasMany = [preguntas: Pregunta]


    static constraints = {
        vigencia(nullable:true)
        imagen(nullable:true, maxSize:1000000)
    }


    static mapping = {
        preguntas cascade: "all-delete-orphan", sort: "id"
    }
}
