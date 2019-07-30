package tpencuestas3

import grails.plugin.springsecurity.annotation.Secured

import java.util.Map

import static org.springframework.http.HttpStatus.NOT_FOUND

@Secured('permitAll')
class ParticipacionController {

    ParticipacionService participacionService
    UsuarioService usuarioService
    EncuestaService encuestaService

    def index(){
        redirect(uri:"/")
    }

    def show(){
        [encuestas: participacionService.encuestasValidas()]
    }

    def propias(){
        [encuestas: participacionService.getEncuestasUsuarioActual()]
    }

    def participar(Long id){
        [encuesta: Encuesta.get(id)]
    }

    def noRespondio(Encuesta encuesta){
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'Debe elegir al menos una opcion en cada pregunta', args: [message(code: 'encuesta.label', default: 'Encuesta'), params.id])
                redirect action: "create", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def validacion(Encuesta encuesta){
        int cantPreg = encuesta.cantidadPreguntas()
        int i = 0
        params.each{ preguntaId, opcionId ->
            if(i < cantPreg) {
                println(i)
                if ((!preguntaId.isLong())||(!opcionId.isLong())) {
                    noRespondio(encuesta)
                    return
                }
                i=i+1
            }
        }
    }

    def guardarRespuestas(){
        Usuario usuario = participacionService.getUsuarioActual()
        Encuesta encuesta = Encuesta.get(params.id)
        def respuestas = new Respuesta(votante: usuario, encuesta: encuesta)


        println("ids de respuestas!!")
        println(params)

        validacion(encuesta)


        respuestas = participacionService.guardarVotacion(respuestas,params)

        println("guardado")
        println(respuestas.respuestas)

        //falta implementar busquedas para quitar la encuesta respondida por el usuario


        [encuesta: encuesta]
    }

    def resultados(){
        [encuesta: participacionService.getRespondidas()]
    }

    private Map generarMapFromParams
    {
        Map paramsMap = new HashMap()

        params.each { key, value ->
            paramsMap.put(key, value)
        }
        paramsMap
    }

}
