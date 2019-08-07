package tpencuestas3

import grails.gorm.services.Service
import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.beans.factory.annotation.Autowired

@Service
class ParticipacionService {

    @Autowired
    SpringSecurityService springSecurityService

    RespuestaService respuestaService

    Usuario getUsuarioActual() {
        springSecurityService.getCurrentUser() as Usuario
    }

    boolean esUsuarioActualPremium(){
        getUsuarioActual().esPremium()
    }

    List encuestasValidas() {
        Encuesta.findAllByIdNotInListAndFechaInicioLessThanAndFechaFinGreaterThan(getRespondidasUsuarioActual().encuesta.id, new Date(), new Date())
    }

    List getEncuestasUsuarioActual() {
        Encuesta.findAllByUsuario(getUsuarioActual())
    }

    List getRespondidasUsuarioActual() {
        Respuesta.findAllByVotante(getUsuarioActual())
    }

    List getRespondidasPorTodos() {
        Respuesta.findAll()
    }

    Respuesta guardar(Encuesta encuesta, Map params, Respuesta respuesta) {
        if (respuestasValidas(encuesta, params, respuesta)) {
            respuestaService.save(respuesta)
        } else {
            throw new NoRespondioException()
        }
    }

    boolean respuestasValidas(Encuesta encuesta, Map params, Respuesta respuesta) {
        boolean validas = true
        int cantPreg = encuesta.cantidadPreguntas()
        int i = 0
        respuesta.initCollection()
        params.each { preguntaId, opcionId ->
            if (i < cantPreg) {
                if ((!preguntaId.isLong()) || (!opcionId.isLong())) {
                    validas = false
                }
                if (preguntaId.isLong()) {
                    respuesta.agregarRespuesta(Opcion.get(opcionId))
                } else {
                    throw new NoRespondioException()
                }
                i = i + 1
            }
        }
        respuesta.agregarFechaVotacion()
        return validas
    }

}
