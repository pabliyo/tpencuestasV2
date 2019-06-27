package tpencuestas3

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class OpcionController {

    OpcionService opcionService

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

    def save(Opcion opcion) {
        if (opcion == null) {
            notFound()
            return
        }

        try {
            opcionService.save(opcion)
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
