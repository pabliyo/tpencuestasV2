package tpencuestas3

import grails.gorm.services.Service
import grails.plugin.springsecurity.SpringSecurityService
import grails.web.servlet.mvc.GrailsParameterMap
import org.hibernate.mapping.Map
import org.springframework.beans.factory.annotation.Autowired

@Service
class ParticipacionService {

    @Autowired
    SpringSecurityService springSecurityService
    PreguntaService preguntaService
    OpcionService opcionService

    Usuario getUsuarioActual() {
        springSecurityService.getCurrentUser() as Usuario
    }

    List encuestasValidas() {
        //def now = new Date()
        Encuesta.findAll()
        //Encuesta.findAllByStartDateLessThanAndExpirationDateGreaterThan(now, now)
    }

    List getEncuestasUsuarioActual(){
        Encuesta.findAllByUsuario(springSecurityService.getCurrentUser() as Usuario)
    }

    List getRespondidas(){
        Respuesta.findAllByVotante(springSecurityService.getCurrentUser())
    }

    Respuesta guardarVotacion(Respuesta respuestas, GrailsParameterMap params) {
        int cantidadPreguntas = respuestas.getEncuesta().cantidadPreguntas()
        params.each{ preguntaId, opcionId ->
            if(preguntaId.isLong())
                respuestas.respuestas.put(Pregunta.get(preguntaId), Opcion.get(opcionId))
        }
        respuestas.save()
        respuestas
    }
    //retocar detalles





}
