package tpencuestas3

import spock.lang.Specification

class HistoriasDeUsuario extends Specification {

    void "Loggear un usuario"() {
        given: "un usuario quiere ingresar a la aplicación"

        when: "deberá poner su usuario único y su contraseña."

        then: "puede ingresar a su cuenta "

    }

    void "Cambiar la contraseña"() {
        given: "un usuario necesita cambiar su contraseña"

        when: "ingresando deberá seleccionar \"cambiar su contraseña\""

        then: "debe poder tener una contraseña distinta"

    }

    void "Adquirir membresía premium"() {
        when: "se haya logueado y elija \"obtener membresía premium\""

        then: " debe poder elegir su nuevo plan"

    }

    void "Crear una encuesta"() {
        given: "un usuario entra para crear una encuesta"

        when: "él llena los datos de la misma"

        then: "debe tener una nueva encuesta vinculada con los datos correspondientes"

    }

    void "Crear una pregunta en una encuesta"() {
        given: "un usuario está creando una encuesta"

        when: "haya creado la encuesta e ingrese los datos para la pregunta"

        then: "debe tener la pregunta asignada a la encuesta creada"

    }

    void "Agregar una opción a una pregunta"() {
        given: "un usuario está creando una pregunta "

        when: "haya creado la pregunta e ingrese los datos para la opción"

        then: "debe poder asignar la opción a la pregunta"

    }

    void "Finalizar la creación de la encuesta"() {
        given:
        "un usuario quiere terminar de crear una encuesta" +
                "y por lo menos tiene una pregunta" +
                "y se indicó la fecha de inicio y finalización"

        when: "selecciona finalizar creación"

        then: "la encuesta debe quedar habilitada para ser respondida por otros usuarios"

    }

    void "Se quiere crear una encuesta por encima del límite sin cargo y no es usuario premium"() {
        given: "un usuario que ya creó 3 encuestas quiere crear una 4a"

        then: "no se le permitirá crear la misma, recordando la limitación de una cuenta no premium"

    }
}
