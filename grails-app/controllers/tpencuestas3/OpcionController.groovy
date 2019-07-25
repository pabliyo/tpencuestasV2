package tpencuestas3

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class OpcionController {

    OpcionService opcionService
    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond opcionService.list(params), model:[opcionCount: opcionService.count()]
    }

    def show(Long id) {
        respond opcionService.get(id)
    }

    def create() {
        respond new Opcion(params)
    }

    def validacion(){
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'Ha superado el limite de opciones para esta pregunta, Usuario no premium', args: [message(code: 'encuesta.label', default: 'Encuesta'), params.id])
                redirect action: "create", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def save(Opcion opcion) {
        if (opcion == null) {
            notFound()
            return
        }

        Usuario usuario = springSecurityService.getCurrentUser() as Usuario
        Pregunta pregunta = Pregunta.get(params.get("pregunta.id"))


        try {
            if(pregunta.puedeAgregarOpciones(usuario)) {
                opcionService.save(opcion)
            }else{
                validacion()
                return
            }
        } catch (ValidationException e) {
            respond opcion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'opcion.label', default: 'Opcion'), opcion.id])
                redirect opcion
            }
            '*' { respond opcion, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond opcionService.get(id)
    }

    def update(Opcion opcion) {
        if (opcion == null) {
            notFound()
            return
        }

        try {
            opcionService.save(opcion)
        } catch (ValidationException e) {
            respond opcion.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'opcion.label', default: 'Opcion'), opcion.id])
                redirect opcion
            }
            '*'{ respond opcion, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        opcionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'opcion.label', default: 'Opcion'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'opcion.label', default: 'Opcion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
