package tpencuestas3

class Opcion {

    int orden
    String descripcion

    static belongsTo = [pregunta: Pregunta]

    static constraints = {
        pregunta nullable:true, editable:false
        orden min:1
        descripcion()
    }


    String toString(){
        descripcion
    }

}
