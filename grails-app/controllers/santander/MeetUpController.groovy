package santander

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import org.openweathermap.OpenweathermapService

import java.time.LocalDateTime

import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class MeetUpController {

    SpringSecurityService springSecurityService
    MeetUpService meetUpService
    OpenweathermapService weatherService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if(Clima.all.isEmpty()){
            weatherService = new OpenweathermapService()
            weatherService.setupHttpClient('https://samples.openweathermap.org')
            weatherService.appid = '2160122c0251f1b3ebf3af8ceaef8cb0'
            weatherService.cityId = '3433955'
            weatherService.cargarDatosClima()
            MeetUp.all.each {it.calcularCantidadDeCajasDeBirra()}
        }
        respond meetUpService.list(params), model: [meetUpCount: meetUpService.count()]
    }

    def show(Long id) {
        respond meetUpService.get(id)
    }

    def create() {
        respond new MeetUp(params)
    }

    def save(MeetUp meetUp) {
        if (meetUp == null) {
            notFound()
            return
        }

        Usuario usuario = springSecurityService.getCurrentUser() as Usuario
        meetUp.save()

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'meetUp.label', default: 'MeetUp'), meetUp.id])
                redirect usuario
            }
            '*' { respond meetUp, [status: CREATED] }
        }
    }

    def edit(Long id) {
        MeetUp meetUp = meetUpService.get(id)
        respond meetUp
    }

    def update(MeetUp meetUp) {
        if (meetUp == null) {
            notFound()
            return
        }

        Usuario usuario = springSecurityService.getCurrentUser() as Usuario

        meetUp.fecha = params.get("fecha") as LocalDateTime
        meetUp.save()

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'meetUp.label', default: 'MeetUp'), meetUp.id])
                redirect meetUp
            }
            '*' { respond meetUp, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        //MeetUp meetUp = meetUpService.get(id)
        meetUpService.delete(id)
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'meetUp.label', default: 'MeetUp'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'meetUp.label', default: 'MeetUp'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
