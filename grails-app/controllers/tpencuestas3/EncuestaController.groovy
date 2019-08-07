package tpencuestas3

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class EncuestaController {

    SpringSecurityService springSecurityService
    EncuestaService encuestaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond encuestaService.list(params), model: [encuestaCount: encuestaService.count()]
    }

    def show(Long id) {
        respond encuestaService.get(id)
    }

    def create() {
        respond new Encuesta(params)
    }

    def save(Encuesta encuesta) {
        if (encuesta == null) {
            notFound()
            return
        }

        Usuario usuario = springSecurityService.getCurrentUser() as Usuario
        encuesta.usuario = usuario

        try {
            encuestaService.guardar(encuesta,usuario)
        } catch (NoPremiumException e) {
            flash.message = e.getMessage()
            respond encuesta, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'encuesta.label', default: 'Encuesta'), encuesta.id])
                redirect encuesta
            }
            '*' { respond encuesta, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond encuestaService.get(id)
    }

    def update(Encuesta encuesta) {
        if (encuesta == null) {
            notFound()
            return
        }

        try {
            if (encuestaService.tieneVotaciones(encuesta))
                throw new ModificarEncuestaVotadaException()
        } catch (ModificarEncuestaVotadaException e) {
            flash.message = e.getMessage()
            respond encuesta, view: 'edit'
            return
        }


        try {
            encuestaService.save(encuesta)
        } catch (ValidationException e) {
            respond encuesta.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'encuesta.label', default: 'Encuesta'), encuesta.id])
                redirect encuesta
            }
            '*' { respond encuesta, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        encuestaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'encuesta.label', default: 'Encuesta'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    def despliegaImagen = {
        def producto = Producto.get(params.id)
        response.contentType = "image/jpeg"
        response.contentLength = producto?.imagen.length
        response.outputStream.write(producto?.imagen)
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
