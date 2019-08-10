package tpencuestas3

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class PreguntaController {

    SpringSecurityService springSecurityService
    PreguntaService preguntaService
    EncuestaService encuestaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond preguntaService.list(params), model: [preguntaCount: preguntaService.count()]
    }

    def show(Long id) {
        respond preguntaService.get(id)
    }

    def create() {
        Encuesta encuesta = Encuesta.get(params.get("encuesta.id"))
        Usuario usuario = springSecurityService.getCurrentUser() as Usuario
        try{
            if(encuesta.puedeAgregarPreguntas(usuario))
                respond new Pregunta(params)
            else
                throw new NoPremiumException()
        }catch (NoPremiumException e) {
            flash.message = e.getMessage()
            redirect (controller:"encuesta", action:"show", id: params.get("encuesta.id"))
        }
    }

    def save(Pregunta pregunta) {
        if (pregunta == null) {
            notFound()
            return
        }

        Encuesta encuesta = Encuesta.get(params.get("encuesta.id"))
        pregunta.encuesta=encuesta
        Usuario usuario = springSecurityService.getCurrentUser() as Usuario

        if (encuestaService.tieneVotaciones(encuesta)) {
            flash.message = "Esta encuesta ya recibio votaciones, NO se puede modificar"
            respond pregunta, view: 'create'
            return
        }


        try {
            preguntaService.guardar(pregunta, usuario, encuesta)
        } catch (NoPremiumException e) {
            flash.message = e.getMessage()
            respond pregunta, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pregunta.label', default: 'Pregunta'), pregunta.id])
                redirect pregunta
            }
            '*' { respond pregunta, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond preguntaService.get(id)
    }

    def update(Pregunta pregunta) {
        if (pregunta == null) {
            notFound()
            return
        }

        Encuesta encuesta = Encuesta.get(pregunta.encuesta.getId())

        if (encuestaService.tieneVotaciones(encuesta)) {
            flash.message = "Esta encuesta ya recibio votaciones, NO se puede modificar"
            respond pregunta, view: 'edit'
            return
        }

        try {
            preguntaService.save(pregunta)
        } catch (ValidationException e) {
            respond pregunta.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pregunta.label', default: 'Pregunta'), pregunta.id])
                redirect controller: "encuesta", action:"show", id: pregunta.encuesta.getId()
            }
            '*' { respond pregunta, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        def pregunta = preguntaService.get(id)
        def encuestaId= pregunta.getProperty("encuesta").getId()

        preguntaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pregunta.label', default: 'Pregunta'), id])
                redirect controller: "encuesta", action:"show", id: encuestaId
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pregunta.label', default: 'Pregunta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
