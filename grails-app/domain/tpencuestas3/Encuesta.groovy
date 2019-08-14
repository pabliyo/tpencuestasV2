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
        preguntas nullable: true
        vigencia nullable: true
    }

    static mapping = {
        preguntas cascade: "all-delete-orphan", sort: "id"
    }

    boolean puedeCrearEncuesta(Usuario usuario) {
        usuario.esPremium() || usuario.cantidadEncuestas() < limiteEncuestasSiNoPremium
    }

    boolean vigenciaValida(){
        vigencia.vigenciaCorrecta()
    }

    boolean vigenciaPremium(){
        vigencia.vigenciaPremium()
    }

    void setUsuario(Usuario usuario){
        this.usuario=usuario
    }

    int cantidadPreguntas() {
        preguntas.size()
    }

    boolean puedeAgregarPreguntas(Usuario usuario) {
        usuario.esPremium() || cantidadPreguntas() < limitePreguntasSiNoPremium
    }

    boolean tieneVotaciones() {
        List<Respuesta> lista = Respuesta.findAllWhere(encuesta: this)
        !lista.isEmpty()
    }

    int cantidadVotaciones(List<Respuesta> respuestas) {
        int acum = 0;
        respuestas.each {
            if(it.encuesta==this)
                acum+=1
        }
        acum
    }

    String toString() {
        titulo
    }

}