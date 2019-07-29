package tpencuestas3

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode

@GrailsCompileStatic
@EqualsAndHashCode(includes = 'username')
class Usuario implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String email
    boolean cuentaPremium

    static hasMany = [encuestas: Encuesta]

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

    int cantidadEncuestas() {
        encuestas.size()
    }

    String toString() {
        username
    }
}