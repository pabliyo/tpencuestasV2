package tpencuestas3

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class ParticipacionController {

    ParticipacionService participacionService
    RespuestaService respuestaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(){
        redirect(uri:"/")
    }

    def show(){
        [encuestas: participacionService.encuestasValidas()]
    }

    def propias(){
        [encuestas: participacionService.getEncuestasUsuarioActual()]
    }

    def resultados(){
        [respuestas: participacionService.getRespondidas()]
    }

    def participar(Long id){
        [encuesta: Encuesta.get(id)]
    }

    def noRespondio(Encuesta encuesta){
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'Debe elegir al menos una opcion en cada pregunta', args: [message(code: 'encuesta.label', default: 'Encuesta'), params.id])
                redirect action: "participar", id: encuesta.id, method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def guardarRespuestas(){
        Usuario usuario = participacionService.getUsuarioActual()
        Encuesta encuesta = Encuesta.get(params.id)
        def respuestas = new Respuesta(votante: usuario, encuesta: encuesta)

        if(participacionService.respuestasValidas(encuesta, params)) {
            participacionService.ingresarVotacion(respuestas, params)
            respuestaService.save(respuestas)
        }else {
            noRespondio(encuesta)
            return
        }

        [encuesta: encuesta]
    }

    private Map generarMapFromParams
    {
        Map paramsMap = new HashMap()

        params.each { key, value ->
            paramsMap.put(key, value)
        }
        paramsMap
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'encuesta.label', default: 'Encuesta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

}
