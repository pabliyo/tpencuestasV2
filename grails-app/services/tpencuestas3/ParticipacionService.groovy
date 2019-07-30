package tpencuestas3

import grails.gorm.services.Service
import grails.plugin.springsecurity.SpringSecurityService
import java.util.Map
import org.springframework.beans.factory.annotation.Autowired

@Service
class ParticipacionService {

    @Autowired
    SpringSecurityService springSecurityService

    Usuario getUsuarioActual() {
        springSecurityService.getCurrentUser() as Usuario
    }

    List encuestasValidas() {
        //def now = new Date()
        Encuesta.findAll()
        //Encuesta.findAllByStartDateLessThanAndExpirationDateGreaterThan(now, now)
    }

    List getEncuestasUsuarioActual() {
        Encuesta.findAllByUsuario(springSecurityService.getCurrentUser() as Usuario)
    }

    List getRespondidas() {
        Respuesta.findAllByVotante(springSecurityService.getCurrentUser() as Usuario)
    }

    Respuesta guardarVotacion(Respuesta respuesta, Map params) {
        params.each { preguntaId, opcionId ->
            if (preguntaId.isLong())
                respuesta.respuestas.put(Pregunta.get(preguntaId), Opcion.get(opcionId))
        }
        respuesta.save()
        respuesta
    }

}
