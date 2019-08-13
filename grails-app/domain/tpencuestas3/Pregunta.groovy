package tpencuestas3

class Pregunta {

    String enunciado
    int orden
    public static final int limiteOpcionesSiNoPremium = 3;

    static belongsTo = [encuesta: Encuesta]
    static hasMany = [opciones: Opcion]

    static constraints = {
        orden nullable:true
        opciones nullable: true
        encuesta nullable: true
    }

    static mapping = {
        opciones cascade: "all-delete-orphan", sort: "id"
    }

    int cantidadOpciones() {
        opciones.size()
    }

    boolean puedeAgregarOpciones(Usuario usuario) {
        usuario.esPremium() || cantidadOpciones() < limiteOpcionesSiNoPremium
    }

    String toString() {
        enunciado
    }

}
