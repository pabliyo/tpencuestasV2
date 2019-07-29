package tpencuestas3

class Encuesta {

    String titulo
    String descripcion
    Vigencia vigencia

    static belongsTo = [usuario: Usuario]
    static hasMany = [preguntas: Pregunta]
    static embedded = ['vigencia']

    static constraints = {
        usuario editable: false //whether it can be edited from scaffolding views
        preguntas nullable: true
        vigencia nullable: true
    }

    static mapping = {
        preguntas cascade: "all-delete-orphan", sort: "id"
    }

    boolean puedeCrearEncuesta(Usuario usuario) {
        usuario.esPremium() || usuario.cantidadEncuestas() < 3
    }

    int cantidadPreguntas() {
        preguntas.size()
    }

    boolean puedeAgregarPreguntas(Usuario usuario) {
        usuario.esPremium() || cantidadPreguntas() < 5
    }

    String toString() {
        titulo
    }

}