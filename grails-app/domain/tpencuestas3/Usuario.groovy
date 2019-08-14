package tpencuestas3

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes = 'username')
class Usuario implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String email
    Boolean cuentaPremium

    //Variables de manejo de cuenta usado por spring security core
    boolean enabled = true
    boolean accountExpired = false
    private boolean accountLocked = false
    private boolean passwordExpired = false

    static hasMany = [encuestas: Encuesta]
    public static final int limiteEncuestasSiNoPremium = 3;

    static constraints = {
        username blank: false, unique: true
        password blank: false, password: true
        email blank: false, unique: true, email: true
        encuestas nullable: true
    }

    static mapping = {
        username column: '`username`'
        encuestas cascade: "all-delete-orphan", sort: "id"
    }

    boolean esPremium() {
        cuentaPremium
    }

    boolean puedeCrearEncuesta() {
        cuentaPremium || cantidadEncuestas() < limiteEncuestasSiNoPremium
    }


    boolean vigenciaDelUsuario(Encuesta encuesta) {
        if (encuesta.vigenciaValida()) {
            if (vigenciaPremium(encuesta)) {
                true
            } else {
                throw new VigenciaNopremiumException()
            }
        }else {
            throw new VigenciaIncorrectaException()
        }
    }

    boolean vigenciaPremium(Encuesta encuesta){
        if(esPremium()) {
            true
        } else {
            encuesta.vigenciaPremium()
        }
    }

    Set<Rol> getAuthorities() {//usado por spring security core
        (UsuarioRol.findAllByUsuario(this) as List<UsuarioRol>)*.rol as Set<Rol>
    }

    int cantidadEncuestas() {
        encuestas.size()
    }

    String toString() {
        username
    }
}