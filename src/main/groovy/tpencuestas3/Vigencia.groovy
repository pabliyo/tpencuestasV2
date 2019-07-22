package tpencuestas3


class Vigencia {

	//ver de cambiar a LocalDateTime
    Date fechaInicio
    Date fechaFin

    boolean estaVigente() {
        def fechaHoy = new Date()
        (fechaInicio <= fechaHoy && fechaHoy <= fechaFin)
    }

    String toString() {
        fechaInicio.toString() + " - " + fechaFin.toString()
    }
}
