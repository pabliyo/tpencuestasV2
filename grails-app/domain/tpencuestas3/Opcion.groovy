package tpencuestas3

class Opcion {

    int orden
    String descripcion

    static belongsTo = [pregunta: Pregunta]

    static constraints = {
        pregunta nullable: true
        orden nullable: true
        descripcion()
    }

    String toString() {
        descripcion
    }

    int contarRepuestas(List<Respuesta> respuestas) {
        int acum = 0;
        respuestas.each {
            acum += it.respuestas.count(this)
        }
        acum
    }

}
