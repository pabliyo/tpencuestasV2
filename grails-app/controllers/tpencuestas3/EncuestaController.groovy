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
        Usuario usuario = springSecurityService.getCurrentUser() as Usuario
        try {
            if (usuario.puedeCrearEncuesta())
                respond new Encuesta(params)
            else
                throw new NoPremiumException()
        }catch (NoPremiumException e) {
            flash.message = e.getMessage()
            redirect(controller: "participacion", action: "propias")
        }
    }

    def save(Encuesta encuesta) {
        if (encuesta == null) {
            notFound()
            return
        }

        Usuario usuario = springSecurityService.getCurrentUser() as Usuario
        encuesta.usuario = usuario

        try {
            encuestaService.guardar(encuesta, usuario)
        } catch (NoPremiumException|VigenciaNopremiumException e) {
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

        if (encuestaService.tieneVotaciones(encuesta)) {
            flash.message = "Esta encuesta ya recibio votaciones, NO se puede modificar"
            respond encuesta, view: 'edit'
        }

        Vigencia vigenciaNueva= new Vigencia(fechaInicio: params.get("vigencia.fechaInicio"), fechaFin: params.get("vigencia.fechaFin"))
        encuesta.vigencia= vigenciaNueva
        Usuario usuario = springSecurityService.getCurrentUser() as Usuario
        encuesta.usuario = usuario

        try {
            encuestaService.guardar(encuesta, usuario)
        } catch (NoPremiumException|VigenciaNopremiumException e) {
            flash.message = e.getMessage()
            respond encuesta, view: 'create'
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
                redirect controller: "participacion", action:"propias"
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
