package tpencuestas3

import spock.lang.Specification

class HistoriasDeUsuarioSpec extends Specification {

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

    void "Se quiere crear una encuesta por encima del límite y no es usuario premium"() {
        when: "un usuario que ya creó 3 encuestas quiere crear una 4a"

        then: "no se le permitirá crear la misma"

    }

    void "se quiere agregar una pregunta a una encuesta por encima del límite y no es usuario premium"() {
        given: "encuesta ya tiene 5 preguntas"
        def encuesta = new Encuesta()
        encuesta.preguntas = new ArrayList<Pregunta>()
        encuesta.limitePreguntasSiNoPremium.times {encuesta.add(new Pregunta(orden: it+1))}
        def usuario = new Usuario(cuentaPremium : false)

        when: "quiera crea una nueva"
        boolean resultado = encuesta.puedeAgregarPreguntas(usuario)

        then: "no se le permitirá crear la misma"
        !resultado

    }

    void "se quiere agregar una opcion a una pregunta por encima del límite y no es usuario premium"() {
        given: "la pregunta ya tiene 3 opciones"
        def pregunta = new Pregunta()
        pregunta.opciones = new ArrayList<Opcion>()
        Pregunta.limiteOpcionesSiNoPremium.times {pregunta.opciones.add(new Opcion())}
        def usuario = new Usuario(cuentaPremium : false)

        when: "quiera crea una nueva"
        boolean resultado = pregunta.puedeAgregarOpciones(usuario)

        then: "no se le permitirá crear la misma"
        !resultado
    }

    void "El usuario premium no tiene restricciones"() {//agregar lo mismo para preg , enc , opc
        given: "la pregunta ya tiene 3 opciones"
        def pregunta = new Pregunta()
        pregunta.opciones = new ArrayList<Opcion>()
        Pregunta.limiteOpcionesSiNoPremium.times {pregunta.opciones.add(new Opcion())}
        def usuario = new Usuario(cuentaPremium : true)

        when: "quiera crea una nueva"
        boolean resultado = pregunta.puedeAgregarOpciones(usuario)

        then: "debe poder hacerlo"
        resultado
    }

    void "Modificar una encuesta"() {
        given: "no entró en vigencia"

        when: "seleccione una encuesta creada"

        then: "debe poder modificarla"

    }

    void "Responder una encuesta"() {
        given: "un usuario quiere responder una encuesta y está vigente"

        when: "selecciona responder encuesta y elige la encuesta a responder"

        then: "se carga la misma para ser respondida"

    }

    void "Guardar las respuestas a una encuesta"() {
        given:
        "un usuario quiere dar por terminado el ingreso de datos " +
                "y respondió todas las preguntas obligatorias"

        when: "quiere terminar"

        then: "se guardan los datos ingresados."

    }

    void "Saber cuántas personas respondieron a una encuesta"() {
        when: "quiere conocer cantidad de encuestados"

        then: "se le muestra un informe con la cantidad de encuestados y las opciones elegidas"

    }
}
