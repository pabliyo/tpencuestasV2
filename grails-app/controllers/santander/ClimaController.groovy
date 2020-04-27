package santander

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ClimaController {

    ClimaService climaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond climaService.list(params), model:[climaCount: climaService.count()]
    }

    def show(Long id) {
        respond climaService.get(id)
    }

    def create() {
        respond new Clima(params)
    }

    def save(Clima clima) {
        if (clima == null) {
            notFound()
            return
        }

        try {
            climaService.save(clima)
        } catch (ValidationException e) {
            respond clima.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'clima.label', default: 'Clima'), clima.id])
                redirect clima
            }
            '*' { respond clima, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond climaService.get(id)
    }

    def update(Clima clima) {
        if (clima == null) {
            notFound()
            return
        }

        try {
            climaService.save(clima)
        } catch (ValidationException e) {
            respond clima.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'clima.label', default: 'Clima'), clima.id])
                redirect clima
            }
            '*'{ respond clima, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        climaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'clima.label', default: 'Clima'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'clima.label', default: 'Clima'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
