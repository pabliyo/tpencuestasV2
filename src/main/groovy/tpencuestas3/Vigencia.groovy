package tpencuestas3

import grails.validation.Validateable

class Vigencia implements Validateable {

    Date fechaInicio
    Date fechaFin

    static constraints = {

    }

    boolean estaVigente() {
        def fechaHoy = new Date()
        (fechaInicio <= fechaHoy && fechaHoy < fechaFin)
    }

    String toString() {
        fechaInicio.toString() + " - " + fechaFin.toString()
    }
}
