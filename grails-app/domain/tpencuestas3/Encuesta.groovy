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
        vigencia nullable:true
    }

    static mapping = {
        preguntas cascade: "all-delete-orphan", sort: "id"
    }

    boolean puedeCrearEncuesta(Usuario usuario) {
        if (usuario.esPremium()) {
            true
        } else {
            !usuario.esPremium() && usuario.cantidadEncuestas() < 3
        }
    }

    int cantidadPreguntas() {
        preguntas.size()
    }

    boolean puedeAgregarPreguntas(Usuario usuario) {
        if (usuario.esPremium()) {
            true
        } else {
            if ((!usuario.esPremium()) && (cantidadPreguntas()) < 5) {
                true
            } else {
                false
            }
        }
    }

    boolean vigenciaCorrecta(Vigencia vigencia){
        //if(vigencia.fechaInicio)

    }

    String toString() {
        titulo
    }

}