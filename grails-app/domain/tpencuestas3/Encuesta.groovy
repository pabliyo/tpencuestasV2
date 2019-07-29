package tpencuestas3

class Encuesta {

    String titulo
    String descripcion
    Vigencia vigencia
    public static final int limiteEncuestasSiNoPremium = 3;
    public static final int limitePreguntasSiNoPremium = 5;

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
        usuario.esPremium() || usuario.cantidadEncuestas() < limiteEncuestasSiNoPremium
    }

    int cantidadPreguntas() {
        preguntas.size()
    }

    boolean puedeAgregarPreguntas(Usuario usuario) {
        usuario.esPremium() || cantidadPreguntas() < limitePreguntasSiNoPremium
    }

    String toString() {
        titulo
    }

}