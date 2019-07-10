package tpencuestas3

class Encuesta {

    String titulo
    String descripcion
    Vigencia vigencia

    static hasMany = [preguntas: Pregunta]

    static constraints = {
        vigencia(nullable:true)

        preguntas maxSize: 5 ,minSize: 5
    }

    static mapping = {
        preguntas cascade: "all-delete-orphan", sort: "id"
    }

    String toString(){
        titulo
    }
}
