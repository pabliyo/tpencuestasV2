package tpencuestas3


class Vigencia {

	//ver de cambiar a LocalDateTime
    Date fechaInicio
    Date fechaFin

    boolean estaVigente() {
        fechaHoy = new Date()
        (fechaInicio <= fechaHoy && fechaHoy <= fechaFin)
    }

    String toString() {
        fechaInicio.toString() + " - " + fechaFin.toString()
    }
}
