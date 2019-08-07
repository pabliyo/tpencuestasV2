package tpencuestas3

import grails.buildtestdata.BuildDataTest
import grails.buildtestdata.mixin.Build
import grails.plugin.springsecurity.SpringSecurityService
import spock.lang.*

@Build([Usuario, Encuesta, Pregunta, Opcion])
class HistoriasDeUsuarioSpec extends Specification implements BuildDataTest{

    SpringSecurityService springSecurityService


    void "Loggear un usuario"() {
        given: "un usuario quiere ingresar a la aplicación"
        def usuario = new Usuario(username:"usuario", password:"usuario")

        when: "deberá poner su usuario único y su contraseña."

        then: "puede ingresar a su cuenta "
        boolean resultado= springSecurityService.isLoggedIn()
    }

    void "Cambiar la contraseña"() {
        given: "un usuario necesita cambiar su contraseña"
        def usuario = new Usuario(username:"usuario", password:"oldPass")

        when: "ingresando deberá seleccionar \"cambiar su contraseña\""
        usuario.setPassword("newPass")
        boolean resultado = false
        if(usuario.getPassword() == "newPass")
            resultado=true

        then: "debe poder tener una contraseña distinta"
        resultado
    }

    void "Cambiar email" (){
        given:"dado un usuario que tiene un email definido"
        def usuario = new Usuario()
        usuario.setEmail("oldEmail@email.com")

        when:"elige el nuevo email"
        String nuevoEmail = "newEmail@email.com"
        usuario.setEmail(nuevoEmail)
        boolean resultado = false
        if(usuario.getEmail()==nuevoEmail)
            resultado=true

        then:"debe poder cambiarlo"
        resultado
    }

    void "Adquirir membresía premium"() {
        when: "se haya logueado y elija \"obtener membresía premium\""

        then: " debe poder elegir su nuevo plan"

    }

    void "Crear una encuesta"() {
        given: "un usuario entra para crear una encuesta"
        def usuario = new Usuario()
        usuario.encuestas=new ArrayList<Encuesta>()


        when: "él llena los datos de la misma"
        def encuesta = new Encuesta(titulo: "encuesta1")
        encuesta.usuario = usuario
        usuario.encuestas.add(encuesta)
        boolean resultado = false
        if(usuario.cantidadEncuestas()==1)
            resultado=true

        then: "debe tener una nueva encuesta vinculada con los datos correspondientes"
        resultado
    }

    void "Crear una pregunta en una encuesta"() {
        given: "un usuario está creando una encuesta"
        def encuesta = new Encuesta()
        encuesta.preguntas=new ArrayList<Pregunta>()

        when: "haya creado la encuesta e ingrese los datos para la pregunta"
        def pregunta = new Pregunta()
        encuesta.preguntas.add(pregunta)
        boolean resultado = false
        if(encuesta.cantidadPreguntas()==1)
            resultado=true
        then: "debe tener la pregunta asignada a la encuesta creada"
        resultado
    }

    void "Agregar una opción a una pregunta"() {
        given: "un usuario está creando una pregunta"
        def pregunta = new Pregunta()
        pregunta.opciones = new ArrayList<Opcion>()

        when: "haya creado la pregunta e ingrese los datos para la opción"
        def opcion = new Opcion()
        pregunta.opciones.add(opcion)
        boolean resultado=false
        if(pregunta.cantidadOpciones()==1)
            resultado = true

        then: "debe poder asignar la opción a la pregunta"
        resultado
    }

    void "Se quiere crear una encuesta por encima del límite y no es usuario premium"() {
        given:"usuario NO premium ya tiene 3 encuestas"
        def usuario = new Usuario(cuentaPremium: false)
        usuario.encuestas = new ArrayList<Encuesta>()
        Encuesta.limiteEncuestasSiNoPremium.times{ usuario.encuestas.add(new Encuesta())}

        when: "quiere crear una 4a"
        def encuesta = new Encuesta()
        boolean resultado = encuesta.puedeCrearEncuesta(usuario)

        then: "no se le permitirá crear la misma"
        !resultado
    }

    void "se quiere agregar una pregunta a una encuesta por encima del límite y no es usuario premium"() {
        given: "encuesta ya tiene 5 preguntas"
        def usuario = new Usuario(cuentaPremium: false)
        usuario.encuestas= new ArrayList<Encuesta>()
        def encuesta = new Encuesta()
        encuesta.preguntas= new ArrayList<Pregunta>()
        usuario.encuestas.add(encuesta)
        encuesta.limitePreguntasSiNoPremium.times {encuesta.preguntas.add(new Pregunta(orden: it+1))}

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

    void "Modificar una encuesta votada"() { //esta mal

        given: "la encuesta recibio al menos 1 votacion"
        def usuario = new Usuario(cuentaPremium: false)
        usuario.encuestas = new ArrayList<Encuesta>()
        def encuesta = new Encuesta(titulo: "nombre")
        usuario.encuestas.add(encuesta)
        def respuesta = new Respuesta(encuesta: encuesta)

        when: "seleccione para modificar los datos de la encuesta"
        boolean resultado = false
        encuesta.setTitulo("nuevoNombre")
        if(encuesta.titulo == "nuevoNombre")
            resultado = true
        then: "no podra modificarla"
        resultado

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
