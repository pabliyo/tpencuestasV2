package santander

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes = 'username')
class Usuario implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    //String email

    //Variables de manejo de cuenta usado por spring security core
    boolean enabled = true
    boolean accountExpired = false
    private boolean accountLocked = false
    private boolean passwordExpired = false

    static hasMany = [meetUps: MeetUp]
    public static final int limiteEncuestasSiNoPremium = 3;

    static constraints = {
        username blank: false, unique: true
        password blank: false, password: true
        //email blank: false, unique: true, email: true
    }

    static mapping = {
        username column: '`username`'
    }


    Set<Rol> getAuthorities() {//usado por spring security core
        (UsuarioRol.findAllByUsuario(this) as List<UsuarioRol>)*.rol as Set<Rol>
    }

    int cantidadMeetUps() {
        this.meetUps.size()
    }

    String toString() {
        username
    }
}
