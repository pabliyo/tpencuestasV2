package tpencuestas3

class Encuesta {

    String titulo
    String descripcion
    Integer cantPreguntas
    Vigencia vigencia

    static hasMany = [preguntas: Pregunta]


    static constraints = {
        vigencia(nullable:true)
        cantPreguntas(min:5, max:5, unique:true)
    }


    static mapping = {
        preguntas cascade: "all-delete-orphan", sort: "id"
    }
}
