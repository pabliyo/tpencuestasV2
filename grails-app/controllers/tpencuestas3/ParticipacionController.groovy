package tpencuestas3

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class ParticipacionController {

    ParticipacionService participacionService

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

        if(participacionService.respuestasValidas(encuesta, params))
            respuestas = participacionService.guardarVotacion(respuestas,params)
        else {
            noRespondio(encuesta)
            return
        }

        println(participacionService.getRespondidas())

        [encuesta: encuesta]
    }

    def resultados(){
        println(participacionService.getRespondidas())
        [respuestas: participacionService.getRespondidas()]
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
