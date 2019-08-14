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

    boolean esUsuarioActualPremium() {
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

    List getRespondidasPorEncuesta(Encuesta encuesta){
        Respuesta.findAllByEncuesta(encuesta)
    }


    Respuesta guardar(Encuesta encuesta, Map params, Respuesta respuesta) {
        respuestasValidas(encuesta, params, respuesta)
        respuestaService.save(respuesta)
    }

    void respuestasValidas(Encuesta encuesta, Map params, Respuesta respuesta) {
        int cantPreg = encuesta.cantidadPreguntas()
        respuesta.initCollection()
        params.eachWithIndex { preguntaId, opcionId, i ->
            if (i < cantPreg) {
                if (!preguntaId.isLong() || !opcionId.isLong()) {
                    throw new NoRespondioException()
                }
                else {
                    respuesta.agregarRespuesta(Opcion.get(opcionId))
                }
            }
        }
        respuesta.agregarFechaVotacion()
    }
}
