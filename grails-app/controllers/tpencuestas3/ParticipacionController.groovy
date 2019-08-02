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

    def guardarRespuestas(){
        Usuario usuario = participacionService.getUsuarioActual()
        Encuesta encuesta = Encuesta.get(params.id)
        def respuestas = new Respuesta(votante: usuario, encuesta: encuesta)

        try{
            participacionService.guardar(encuesta,params,respuestas)
        } catch (NoRespondioException e) {
            flash.message = e.getMessage()
            respond encuesta, view: 'participar', id: encuesta.id
            return
        }

        [encuesta: encuesta]
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
