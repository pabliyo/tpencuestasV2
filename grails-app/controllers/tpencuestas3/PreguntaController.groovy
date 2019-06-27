package tpencuestas3

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PreguntaController {

    PreguntaService preguntaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond preguntaService.list(params), model:[preguntaCount: preguntaService.count()]
    }

    def show(Long id) {
        respond preguntaService.get(id)
    }

    def create() {
        respond new Pregunta(params)
    }

    def save(Pregunta pregunta) {
        if (pregunta == null) {
            notFound()
            return
        }

        try {
            preguntaService.save(pregunta)
        } catch (ValidationException e) {
            respond pregunta.errors, view:'create'
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

        try {
            preguntaService.save(pregunta)
        } catch (ValidationException e) {
            respond pregunta.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pregunta.label', default: 'Pregunta'), pregunta.id])
                redirect pregunta
            }
            '*'{ respond pregunta, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        preguntaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pregunta.label', default: 'Pregunta'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pregunta.label', default: 'Pregunta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
