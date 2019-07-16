package tpencuestas3

class Opcion {

    Long orden
    String descripcion

    static belongsTo = [pregunta: Pregunta]

    static constraints = {
       // orden(min:1, max:4)
        pregunta nullable:true
    }

    String toString(){
        descripcion
    }

}
