package tpencuestas3

class Encuesta {

    String titulo
    String descripcion
    Vigencia vigencia

    static belongsTo = [usuario: Usuario]
    static hasMany = [preguntas: Pregunta]

    static constraints = {
        usuario(nullable:true)
        titulo()
        descripcion()
        vigencia(nullable:true)
        preguntas maxSize:5, nullable:true
    }

    static mapping = {
        preguntas cascade: "all-delete-orphan", sort: "id"
    }

    String toString(){
        titulo
    }
}