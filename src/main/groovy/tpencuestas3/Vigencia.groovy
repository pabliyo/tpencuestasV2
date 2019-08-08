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

    boolean fechaCorrecta(){
        def fechaElegida = new Date()
        def dias = 30
        fechaElegida.setDate(fechaInicio.getDate()+dias)
        (fechaFin <= fechaElegida)
    }

    String toString() {
        fechaInicio.toString() + " - " + fechaFin.toString()
    }
}
