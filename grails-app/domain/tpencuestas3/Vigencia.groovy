package tpencuestas3

class Vigencia {

    Date fechaInicio
    Date fechaFin

    static constraints = {
        fechaInicio min: (new Date())
        fechaFin min: (new Date())
    }

    boolean estaVigente() {
        fechaHoy = new Date()
        (fechaInicio <= fechaHoy && fechaHoy <= fechaFin)
    }

    String toString() {
        fechaInicio.toString() + " - " + fechaFin.toString()
    }
}
