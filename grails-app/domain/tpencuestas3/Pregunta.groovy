package tpencuestas3

class Pregunta {

    String enunciado
    int orden
    boolean respondio=false //atributo para validacion

    static belongsTo = [encuesta: Encuesta]
    static hasMany = [opciones: Opcion]

    static constraints = {
        orden (min:1)
        opciones nullable:true
        encuesta nullable:true, editable:false
    }

    static mapping = {
        opciones cascade: "all-delete-orphan", sort: "id"

    }

    int cantidadOpciones(){
        opciones.size()
    }

    boolean puedeAgregarOpciones(Usuario usuario){
        if (usuario.esPremium()) {
            true
        }else{
            if ((!usuario.esPremium())&&(cantidadOpciones()<3)) {
                true
            } else {
                false
            }
        }
    }

    String toString(){
        enunciado
    }

}
