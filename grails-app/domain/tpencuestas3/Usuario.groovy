package tpencuestas3

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic
import org.hibernate.validator.constraints.Email

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class Usuario implements Serializable {

    private static final long serialVersionUID = 1


    String username
    String password
    String email
    boolean enabled = true
    boolean cuentaPremium
    boolean accountExpired
    private boolean accountLocked
    private boolean passwordExpired

    Set<Rol> getAuthorities() {
        (UsuarioRol.findAllByUsuario(this) as List<UsuarioRol>)*.rol as Set<Rol>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        cuentaPremium nullable: false
        email nullable:false, blank: false, unique:true, email:true
    }

    static mapping = {
	    username column: '`username`'
    }
}
