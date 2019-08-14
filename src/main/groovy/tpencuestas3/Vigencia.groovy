package tpencuestas3

import grails.validation.Validateable

class Vigencia implements Validateable {

    Date fechaInicio
    Date fechaFin

    static constraints = {

    }

    boolean vigenciaCorrecta(){
        def ahora = new Date()
        def resta = 1
        ahora.setDate(ahora.getDate()-resta)
        fechaFin>fechaInicio && fechaFin>ahora && fechaInicio>=ahora
    }

    boolean estaVigente() {
        def fechaHoy = new Date()
        (fechaInicio <= fechaHoy && fechaHoy < fechaFin)
    }

    boolean vigenciaPremium(){
        def fechaElegida = new Date()
        def dias = 30
        fechaElegida.setDate(fechaInicio.getDate()+dias)
        (fechaFin <= fechaElegida)
    }

    String toString() {
        fechaInicio.toString() + " - " + fechaFin.toString()
    }
}
