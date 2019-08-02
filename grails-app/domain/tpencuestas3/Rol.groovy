package tpencuestas3

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes = 'authority')
@ToString(includes = 'authority', includeNames = true, includePackage = false)
class Rol implements Serializable {

    private static final long serialVersionUID = 1
    String authority

    static final Rol adminRol = new Rol(authority: 'ROLE_ADMIN').save()
    static final Rol userRol = new Rol(authority: 'ROLE_USER').save()

    static constraints = {
        authority nullable: false, blank: false, unique: true
    }

    static mapping = {
        cache true
    }
}
