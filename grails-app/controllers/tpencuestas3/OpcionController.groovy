package tpencuestas3

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class OpcionController {

    OpcionService opcionService
    SpringSecurityService springSecurityService
    EncuestaService encuestaService
    PreguntaService preguntaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond opcionService.list(params), model: [opcionCount: opcionService.count()]
    }

    def show(Long id) {
        respond opcionService.get(id)
    }

    def create() {
        Usuario usuario = springSecurityService.getCurrentUser() as Usuario
        Pregunta pregunta = Pregunta.get(params.get("pregunta.id"))
        try{
            if(pregunta.puedeAgregarOpciones(usuario))
                respond new Opcion(params)
            else
                throw new NoPremiumException()
        }catch (NoPremiumException e) {
            flash.message = e.getMessage()
            redirect (controller:"pregunta", action:"show", id: params.get("pregunta.id"))
        }
    }

    def save(Opcion opcion) {
        if (opcion == null) {
            notFound()
            return
        }

        Usuario usuario = springSecurityService.getCurrentUser() as Usuario
        Pregunta pregunta = Pregunta.get(params.get("pregunta.id"))
        Encuesta encuesta = Encuesta.get(pregunta.encuesta.getId())
        opcion.pregunta=pregunta

        if (encuestaService.tieneVotaciones(encuesta)) {
            flash.message = "Esta encuesta ya recibio votaciones, NO se puede modificar"
            respond opcion, view: 'edit'
            return
        }

        try {
            opcionService.guardar(opcion, usuario, pregunta)
        } catch (NoPremiumException e) {
            flash.message = e.getMessage()
            respond opcion, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'opcion.label', default: 'Opcion'), opcion.id])
                redirect controller: "pregunta", action:"show", id: params.get("pregunta.id")
            }
            '*' { respond opcion, [status: CREATED] }
        }
    }

    def edit(Long id) {
        Opcion opcion = opcionService.get(id)
        Pregunta pregunta = preguntaService.get(opcion.pregunta.getId())
        Encuesta encuesta = encuestaService.get(pregunta.encuesta.getId())

        if (encuestaService.tieneVotaciones(encuesta)) {
            flash.message = "Esta encuesta ya recibio votaciones, NO se puede modificar"
            respond opcion, view: 'show'
        }else
            respond opcion
    }

    def update(Opcion opcion) {
        if (opcion == null) {
            notFound()
            return
        }

        Pregunta pregunta = Pregunta.get(opcion.pregunta.getId())
        Encuesta encuesta = Encuesta.get(pregunta.encuesta.getId())

        if (encuestaService.tieneVotaciones(encuesta)) {
            flash.message = "Esta encuesta ya recibio votaciones, NO se puede modificar"
            respond opcion, view: 'edit'
            return
        }

        try {
            opcionService.save(opcion)
        } catch (ValidationException e) {
            respond opcion.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'opcion.label', default: 'Opcion'), opcion.id])
                redirect controller: "pregunta", action:"show", id: opcion.pregunta.getId()
            }
            '*' { respond opcion, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        Opcion opcion = opcionService.get(id)
        Pregunta pregunta = preguntaService.get(opcion.pregunta.getId())
        Encuesta encuesta = encuestaService.get(pregunta.encuesta.getId())

        if (encuestaService.tieneVotaciones(encuesta)) {
            flash.message = "Esta encuesta ya recibio votaciones, NO se puede modificar"
            respond opcion, view: 'show'
        }else {
            opcionService.delete(id)
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'opcion.label', default: 'Opcion'), id])
                    redirect controller: "pregunta", action: "show", id: preguntaId
                }
                '*' { render status: NO_CONTENT }
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'opcion.label', default: 'Opcion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
