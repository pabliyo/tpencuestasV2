package tpencuestas3

import grails.gorm.services.Service
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic
import org.hibernate.validator.constraints.Email

@GrailsCompileStatic
@EqualsAndHashCode(includes = 'username')
class Usuario implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String email
    boolean enabled = true
    boolean cuentaPremium

    //Variables de manejo de cuenta
    boolean accountExpired = false
    private boolean accountLocked = false
    private boolean passwordExpired = false

    static hasMany = [encuestas: Encuesta]

    Set<Rol> getAuthorities() {
        (UsuarioRol.findAllByUsuario(this) as List<UsuarioRol>)*.rol as Set<Rol>
    }

    static constraints = {
        username nullable: false, blank: false, unique: true
        password nullable: false, blank: false, password: true
        email nullable: false, blank: false, unique: true, email: true
        cuentaPremium nullable: false
        encuestas nullable: true
    }

    static mapping = {
        username column: '`username`'
        encuestas cascade: "all-delete-orphan", sort: "id"
    }

    boolean esPremium() {
        cuentaPremium
    }

    String toString(){
        username
    }
}