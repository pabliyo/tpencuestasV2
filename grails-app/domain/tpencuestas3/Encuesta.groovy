package tpencuestas3

class Encuesta {

    String titulo
    String descripcion
    Vigencia vigencia

    static belongsTo = [usuario: Usuario]
    static hasMany = [preguntas: Pregunta]

    static embedded = ['vigencia']

    static constraints = {
        /*Re implentear constraints
        vigencia.fechaInicio min: (new Date())
        vigencia.fechaFin min: (new Date())*/
        usuario nullable:true , editable:false //whether it can be edited from scaffolding views
        vigencia nullable:true
        preguntas maxSize:5, nullable:true
    }

    static mapping = {
        preguntas cascade: "all-delete-orphan", sort: "id"
    }

    String toString(){
        titulo
    }
}